# SNAP Tools

SNAP Tools is a collection of utilities and tools related to **ESA SNAP** (Sentinel Application Platform).

## Modules
- **snap-performance-test**: Tools for benchmarking SNAP readers and writers

## Requirements
- **Java 21**

## Installation & Usage
Clone the repository and build with Maven:

```
git clone https://github.com/bcdev/snap-tools.git
cd snap-tools
mvn clean install
```
- **Execute snap-performance-test**:
  - find snap-performance-test/src/test/resources/config.properties
  - define testDataDir with base directory for your test data
  - define outputDir to save your results
  - define testNames as comma-separated list of your test names
  - define your test cases as shown in the examplary config.properties file
  - run Main method (if you choose to define your own properties file, add it to the CLI arguments)
