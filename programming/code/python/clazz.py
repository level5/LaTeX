#coding:utf-8

# 涉及到的scope：
# 全局scope
# module的scope
# class的scope
# function的scope
# 没有 if with try 这种块级别的scope

# 可以直接进入的scope：
# 最里层的scope，最开始查找
# function的scope，从最近一层开始查找，非局部变量，也非全局变量
# 当前模块中的全局变量
# 最外面的scope，包含built-in的名字

# function-scope (层层嵌套)-> module global scope -> built-in global scope
# class的scope不在这个之列

# 如果一个名字定义为全局的，所有的引用和赋值都是直接操作这个变量，否则，除了最里面的scope，这个变量就是只读的。

b = 20
def func():
    a = 10
    if True:
        c = 30
        print a, b, c
func()

class Class1:
    """A simple example class"""
    i = 12345

    def __init__(self):
        self.data = []

    def greet(self):
        return 'hello world'

print Class1.__doc__
# 定义的方法和变量都可以通过Class Object来访问，应该是直接调用的话，需要自己制定self
print Class1.i
print Class1.greet

print Class1.__init__
