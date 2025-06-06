from typing import Set, List
import os
import tempfile
import glob
import subprocess

DATAGEN_NUM_ITERATIONS = 20

AUGMENTATION = True

# Hold a list of classpaths to be used to run datagen, evosuite, etc.
classpaths: Set[str] = set()
classpaths.add(os.path.join(os.path.dirname(__file__), "dependencies", "*.jar"))
classpaths.add(
    os.path.join(os.path.dirname(__file__), "datagen", "target", "datagen-1.0.jar")
)
classpaths.update(
    glob.glob(os.path.join(os.path.dirname(__file__), "datagen", "libs", "*.jar"))
)


def run_datagen_on(filepath: str, augment_code: bool = True):
    # Store the current working directory.
    cwd = os.getcwd()

    # Make a temporary working directory with "_datagen" suffix.
    # The directory should be persisted.
    working_dir = tempfile.mkdtemp(suffix="_datagen")

    # Store the absolute path of the file.
    filepath = os.path.abspath(filepath)

    # We need to move into the working directory, so as not not pollute other processes
    # that might be running at the same time. Otherwise, evosuite-tests and evosuite-report
    # might be moved in the middle of the run by other processes (or we might move it).
    os.chdir(working_dir)

    print("Running datagen on", filepath)
    # We need to add this filepath's dir to the classpath.

    local_classpath = os.path.dirname(filepath)
    classpath_mod = classpaths.copy()
    classpath_mod.add(local_classpath)

    augment_flag = "true" if augment_code else "false"
    invariant_flag = "false"  # We do not want to generate invariants yet. That's used when datagen is run standalone.

    cmd = [
        "java",
        "-cp",
        ":".join(classpath_mod),
        "edu.boisestate.datagen.App",
        "-s",
        filepath,
        "-w",
        working_dir,
        "-k",
        augment_flag,
        "-x",
        invariant_flag,  # We do not want to generate invariants yet. That's used when datagen is run standalone.
        "-i",
        f"{DATAGEN_NUM_ITERATIONS}",  # Number of iterations to run datagen for.
    ]

    try:
        logfile_out = open(os.path.join(working_dir, "datagen.out"), "w")
        logfile_err = open(os.path.join(working_dir, "datagen.err"), "w")

        output = subprocess.run(
            cmd, 
            check=True, 
            stdout=logfile_out, 
            stderr=logfile_err, 
            text=True
        )

        if output.returncode != 0:
            print("Datagen failed with return code", output.returncode)
            print("Output:", output.stdout)
            print("Error:", output.stderr)
            exit(-1)
            return None
        print("Datagen completed successfully.")

        # Capture the stdout, stderr and log to a file in the workdir.
        with open(os.path.join(working_dir, "datagen.log"), "w") as log_file:
            log_file.write(output.stdout if output.stdout else "NO STDOUT PRODUCED\n")
            log_file.write(output.stderr if output.stderr else "NO STDERR PRODUCED\n")

    except subprocess.CalledProcessError as e:
        print("Error running datagen:", e)
        print("Output:", e.output)
        print("Error:", e.stderr)
        exit(-1)
        return None

    except Exception as e:
        # Can happen if we do not have the right permissions to create the dir/ run command
        # Borah can be a PITA sometimes.
        print("Error running datagen:", e)
        exit(-1)
        return None

    # Go back to the original directory.
    os.chdir(cwd)

    # Return the working directory.
    return working_dir

if __name__ == "__main__":
    run_datagen_on("inputs/A_LT_B/")