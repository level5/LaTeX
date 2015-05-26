#! /bin/bash

# gcd.sh: Greatest Common Divisor



# Argument check
ARGS=2
E_BADARGS=85

if [ $# -ne "$ARGS" ]; then
  echo "Usage: `basename $0` first-number second-number"
  exit $E_BADARGS
fi

# check if arguments are integers
# solution 1, regex [[ $1 =~ ^-?[0-9]+$ ]]
# solution 2, test with if [ "$1" -eq "$1" ] 2>/dev/null; then ...; fi

gcd ()
{
  dividend=$1
  divisor=$2

  reminder=1

  until [[ "$reminder" -eq 0 ]]; do
    let "reminder=$dividend %"
  done
}
