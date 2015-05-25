# use GNU grep's feature

grep -r --include "*.java" teststring /home/user1


# --include
# --exclude
# -r


find /home/user1 -name '*.java' -exec grep teststring {} +


find /home/user01 -name "*.java" -print0 | xargs -0 grep teststring
