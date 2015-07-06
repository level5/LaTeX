#!/bin/bash

find . -name '*-test.js' | xargs mocha -R spec
