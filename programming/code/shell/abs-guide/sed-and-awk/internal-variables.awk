# BEGIN在读入行之前执行；
BEGIN {
    FS=" +"; 
#   ^ FS 指定输入的列分割符号 
    printf "There are %d arguments. they are ", ARGC;
    for(i=0;i<ARGC;i++)
        printf "%s ", ARGV[i]
    print ""
}

{
    printf "%2d, %2d: %s\n", NR, FNR, $0
#                             ^             NR  是当前的行数，如果读入多个文件，NR是总得行数
#                                 ^         FNR 是当前文件的行数。
#                                     ^     $0  代表这一行
    printf "Columns: "
    for(i=1; i<=NF; i++) {
#               ^ NF 表示读入的列数
        printf "(%2d) %-10s ", i, $i
#                                 ^ $n表示第n列数据           
    }
    print ""
    printf "输入的文件名是 %s\n", FILENAME
#                                   ^ 当前的文件名 

}

