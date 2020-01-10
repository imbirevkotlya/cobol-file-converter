# COBOL file converter

The project is about COBOL files conversion (EBCDIC encoding) to human readable/PC encodings via the
specified copybook format.

## Getting started

Prerequisites:

1. You need to have Java 11 installed on your machine
2. You need to have Gradle 5 installed on your machine
3. You need to prepare your COBOL copybooks according to rules, described below
4. You need to have COBOL QSAM dataset according to rules, described below
5. You need to have built and version correlates Copybook parser library (https://github.com/imbirevkotlya/CopybookParser)
 in the dependencies

### Installing

- Clone the project from the version control system:

`git clone ...`

- Build the dependent jar according to it's README (https://github.com/imbirevkotlya/CopybookParser)

- Go to the project root:

`cd cobol-file-converter`

- Put the dependent jar file into the folder 'lib' inside the project:

`cp <jar_path>/test-1.0-SNAPSHOT.jar <project_root>/lib/`

- execute the gradle task to build the application jar file:

`gradle clean jar --info`

- now, you can find the jar file in this path: <project_root>/build/libs/---.jar

### Running tests

- execute the gradle task to run tests:

`gradle clean test` 

### Build with

- Gradle (dependency management and build system)

### Authors

- Imbirev Nikolai (creator and supporter)

### Versioning

- Now, the 1.0-SNAPSHOT version is released with simple conversion with EBCDIC/ASCII
- In development stage: 1.1-SNAPSHOT version with another encodings and conversion stability improvements
- In plans: 1.2-SNAPSHOT version with supporting new copybook fields (according to the
updates in Copybook parser library)
- ...

### Acknowledges

Supported copybook formats:
Are described in the dependent library (https://github.com/imbirevkotlya/CopybookParser)

Dataset limitations:

- Only QSAM datasets are supported with sequential records disposition.

- If some fields are incomplete with real data (according to the copybook) you need
to fill them with zeroes or spaces to fill the field lengths (more about it in the javadocs)

- Only EBCDIC/ASCII conversion is supported

- Dependent library correct version (https://github.com/imbirevkotlya/CopybookParser) to use - 1.0-SNAPSHOT

### Licensing

The License file can be found in project root (LICENSE.txt)