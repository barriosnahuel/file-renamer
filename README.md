# File Renamer

*FileRenamer* is a Java application that lets users rename a set of files from a specific pattern to a another one using the
command line.

## Project status
[![GitHub version](https://badge.fury.io/gh/barriosnahuel%2Ffile-renamer.svg)](http://github.com/barriosnahuel/file-renamer/releases)
[![](https://jitpack.io/v/barriosnahuel/file-renamer.svg)](https://jitpack.io/#barriosnahuel/file-renamer)
[![Semver](http://img.shields.io/SemVer/2.0.0.png)](http://semver.org/spec/v2.0.0.html)
[![unstable](https://img.shields.io/badge/stability-unstable-yellow.svg)](https://nodejs.org/api/documentation.html#documentation_stability_index)
[![Build Status](https://travis-ci.org/barriosnahuel/file-renamer.svg?branch=master)](https://travis-ci.org/barriosnahuel/file-renamer)

## Usage
* Download latest [release](https://github.com/barriosnahuel/file-renamer/releases/latest).
* From your command line just run:

> java -jar FileRenamer.jar aDirectory/otherDirectory "%a - %t" "%t - %a"

**Notes**:
- You must use just 1 letter for each variable.
- You cannot use a simple white space as a separator because it can be confused.
- Each of the 3 arguments are required.

## Contributing

### Repo usage
*Do not clone or push to* **master** *branch.*

Create branch or fork from **develop**, then push or create pull requests (if you don't have access) to that branch.

The repo uses [this branching model](http://nvie.com/posts/a-successful-git-branching-model/).
