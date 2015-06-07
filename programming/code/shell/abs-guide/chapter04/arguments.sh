#!/bin/bash

if [ "$#" -ne 5 ]; then
  echo "Usage: arguments.sh arg1 arg2 arg3 arg4 arg5"
  exit 1
fi

echo "\$* is: $*"

echo "\$@ is: $@"

echoArgsNum(){ echo "arguments number is $#";}

echo 'check the difference between $* and $@'

echoArgsNum "$*"    # 1

echoArgsNum "$@"    # 5


# actually, I don't understand it completely.

# test 1.
echoArgsNum "\"a\" \"b\""

# test 2.
echoArgsNum "a b"

# test 3
echoArgsNum $*    # 5
echoArgsNum $@    # 5

# how to create a string just like $@ ??
