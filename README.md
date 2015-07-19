#File Renamer

## Project status
[![Build Status](https://travis-ci.org/barriosnahuel/fileRenamer.png)](https://travis-ci.org/barriosnahuel/file-renamer)
[![unstable](https://img.shields.io/badge/stability-unstable-yellow.svg)](https://nodejs.org/api/documentation.html#documentation_stability_index)

*FileRenamer* is a Java application that lets users rename a set of files from a specific pattern to a another one using the
command line.

##Usage
* Generate a runnable .jar file with Eclipse.
* From command line run:

> java -jar FileRenamer.jar aDirectory/otherDirectory "%a - %t" "%t - %a"

##Currently used by
- [File Renamer SWT](https://github.com/barriosnahuel/file-renamer_SWT)
