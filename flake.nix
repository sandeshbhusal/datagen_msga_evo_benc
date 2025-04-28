{
  description = "A flake for building a Singularity image";
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};

      container_pkg_list = with pkgs; [
        bash
        coreutils
        jdk11
        maven
        python312
        z3
        python312Packages.beartype
        python312Packages.z3-solver
        python312Packages.sympy
      ];

      appSrc = pkgs.runCommand "app-src" {} ''
        mkdir -p $out/app
        cp -r ${self}/* $out/app/
	  '';

    in
    {
      devShells."${system}".default = pkgs.mkShell {
        name = "devenv";
        buildInputs = container_pkg_list ++ [ pkgs.jdk21 ];
      };

      packages."${system}".image = pkgs.singularity-tools.buildImage {
        name = "benchmark-image";
        contents = [ appSrc ] ++ container_pkg_list;

        runScript = ''
          #!${pkgs.bash}/bin/bash
          set -e
          exec python3 main.py "$@"
        '';

		diskSize = 1024*20;
		memSize  = 1024*8;
      };
    };
}
