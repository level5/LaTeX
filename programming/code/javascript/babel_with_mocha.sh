#!/bin/bash
# run test in ./ecma6/**/*-test.es6 with cygwin64 
find ./ecma6 -name '*-test.es6' | xargs mocha --compilers js:babel-core/register -R spec
