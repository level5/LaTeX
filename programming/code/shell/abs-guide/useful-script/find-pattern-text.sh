
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


#
