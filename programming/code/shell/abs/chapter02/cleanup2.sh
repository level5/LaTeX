#! /bin/bash
# proper header for a Bash script.

# version 2.0

# run as root, of course
# insert code here to print error message and exit if not root.
# how to check if run as root?

LOG_DIR=/var/log

cd $LOG_DIR

cat /dev/null > messages
cat /dev/null > wtmp

echo "Logs cleaned up."

exit # the right and proper method of "exiting" from a script
     # A bare "exit"(no parameter) return the exit status of
     # the preceding command.
