#coding:utf-8

# 这个文件就是一个module， 文件名就是module的名字（module.py的名字就是module）






# import <module>时，module中的语句会被执行

import Sample
from Sample import method1, method2
from Sample import *                    # 这样不会import Sample到当前module来
                                        # _开头的名字不会被import进来



# module 查找位置
# 1. 先查找built-in的module
# 2. 然后查找sys.path变量指定的目录
#   . 包含input script的目录（或者当前目录）
#   . PYTHONPATH环境变量指定的一系列目录，格式和SHELL的PATH相同
#   . installation-dependent default


# dir 列出模块中定义的名字
print dir(Sample) # 不会列出built-in function and variable

# ==========================================
# __name__是当前模块的名字，而如果是将当前模块当做script来执行，__name__的值就是__main__
if __name__ == '__main__':
    # 可以在这里来执行代码
    pass
#
