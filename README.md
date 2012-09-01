[![Build Status](https://secure.travis-ci.org/avh4/junit-nested.png?branch=master)](http://travis-ci.org/avh4/junit-nested)

## Maven profiles

To use a profile, use the `-P` argument to maven.  Profiles can also be combined:

    mvn -P skip-android <target>


Profiles for `uilayer`:

 - *skip-android*: skip projects that require Android

