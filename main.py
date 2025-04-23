"""
Run benchmarks for the datagen-exclusion approach.
"""

import os
import sys
import glob
import shutil
from run_datagen import run_datagen_on

# Ensure that datagen, evosuite and evosuite-mod exist.
expected_datagen_path = os.path.join(
    os.path.dirname(__file__), "datagen", "target", "datagen-1.0.jar"
)
expected_evosuite_clean = os.path.join(
    os.path.dirname(__file__), "dependencies", "evosuite-clean.jar"
)
expected_evosuite_mod = os.path.join(
    os.path.dirname(__file__), "dependencies", "evosuite-mod.jar"
)

assert os.path.exists(expected_datagen_path), (
    f"Expected datagen jar not found at {expected_datagen_path}"
)
assert os.path.exists(expected_evosuite_clean), (
    f"Expected evosuite-clean jar not found at {expected_evosuite_clean}"
)
assert os.path.exists(expected_evosuite_mod), (
    f"Expected evosuite-mod jar not found at {expected_evosuite_mod}"
)

if __name__ == "__main__":
    # Get the argument, if any. If so, find the folder in "/inputs/", and run the benchmark on it.

    if len(sys.argv) == 2:
        # passed a test name.
        path = f"inputs/{sys.argv[1]}"
        if not os.path.exists(path):
            print(f"Path {path} does not exist.")
            exit(-1)

        else:
            # Run the benchmark on this path.
            print(f"Running benchmark for {path}")
            output_dir = run_datagen_on(path)
            shutil.move(output_dir, os.path.join("datagen-outputs", os.path.basename(path)))
            exit(0)

    else:
        input_folders = glob.glob("inputs/*")

        # For every benchmark in the input folder, run the benchmark on datagen first.
        # We will collect the results in a "datagen-outputs/" folder.
        for benchmark_name in input_folders:
            if "Knuth" in benchmark_name:
                print(f"Running benchmark for {benchmark_name}")
                output_dir = run_datagen_on(benchmark_name)
                shutil.move(
                    output_dir,
                    os.path.join("datagen-outputs", os.path.basename(benchmark_name)),
                )
