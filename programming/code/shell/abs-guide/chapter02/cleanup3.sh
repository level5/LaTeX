#! /bin/bash
# proper header for a Bash script.

# version 3.0


LOG_DIR=/var/log
ROOT_UID=0        # Only users with $UID 0 have root privilegs
LINES=50          # Default number of lines saved.
E_XCD=86          # Cannot change directory?
E_NOTROOT=87      # Non-root exit error.

# run as root, of course
if [ "$UID" -ne "$ROOT_UID" ]
  then
    echo "Must be root to run this script."
    exit $E_NOTROOT
fi

if [ -n "$1"]
# Test wether command-line argument is present (non-empty)
  then
    lines=$1
  else
    lines=$LINES
fi

cd $LOG_DIR

if [ `pwd` != "$LOG_DIR" ] # or if [ "$PWD" != "$LOG_DIR" ]
  then
    echo "Cannot change to $LOG_DIR"
    exit $E_XCD
fi


# cd /var/log || {
#   echo "Cannot change to necessary directory." >&2  # why need redirect content to &2
#   exit $E_XCD;
# }

tail -n $lines messages > messg.temp
mv mesg.temp messages

cat /dev/null > wtmp # ': > wtmp' ??

echo "Log files cleaned up."

exit 0
