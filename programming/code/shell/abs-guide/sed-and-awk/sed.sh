#!/bin/bash

# sed 面向字节流的编辑器；
# sed 如果不带文件名的话，就是读取标准输入的内容，处理之后写出到标准输出；
#     如果带文件名的话，就是读入文件中的内容，处理之后，写出到标准输出；
#     默认情况下，sed不会修改输入的内容。而是拷贝一份，在拷贝的内容上面做修改

# sed的处理步骤是，从输入读如一行内容，将其复制，然后对他匹配第一个模式，如果匹配上，就执行相关指令；
# 然后应用第二个模式，直到所有的模式都处理完。就读入下一行内容来处理。

# sed 的格式
sed 's/pattern/replace/' filename
# 多条指令的时候
sed -e 's/pattern1/replace1' -e 's/pattern2/replace2' filename
sed 's/pattern1/replace1/; s/pattern2/replace2' filename

# 指令在脚本文件中
sed -f scriptname filename
