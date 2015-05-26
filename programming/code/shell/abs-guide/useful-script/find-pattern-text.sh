
# find pattern text

# use GNU grep's feature
grep -r --include "*.java" teststring /home/user1


# --include
# --exclude
# -r


find /home/user1 -name '*.java' -exec grep teststring {} +

find /home/user01 -name "*.java" -print0 | xargs -0 grep teststring




# check if run by root user.
ROOT_UID=0
if ["$UID" -ne "$ROOT_UID"]; then
  echo "not root user, please run with root."
else
  echo "running with root user."
fi



# add directory to PATH
HOME=/java
export PATH="$HOME/bin:$PATH"


# define a function
prepend() { [ -d "$2"] && eval $1=\"$2':'\$$1\" && export $1; }




# how to change directory from script

# The reason is that each process has its own current directory, and when you execute a program from the shell it is run in a new process.
# The standard "cd", "pushd" and "popd" are builtin to the shell interpreter so that they affect the shell process.
# By making your program a shell function, you are adding your own in-process command and then any directory change gets reflected in the shell process.

# When you start your script, a new process is created that only inherits your environment. When it ends, it ends. Your current environment stays as it is.
# Instead, you can start your script like this:
. myscript.sh       # The . will evaluate the script in the current environment, so it might be altered
