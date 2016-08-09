#coding:utf-8

# 1 无参数
def func():
    pass

# 2 带参数
def func2(arg1, arg2):
    pass

# 3 带有默认值的参数
def func3(arg1, arg2, arg3='3', arg4='4'):
    print arg1, arg2, arg3, arg4

func3(1, 2, 30, 40)  # 不使用默认值

func3(1, 2, 30)     # 使用默认值
func3(1, 2)


def func4(arg={'bar': 100}):
    print ('foo' in arg)
    arg['foo'] = 200

func4()
# 这个值只会被赋予一次！！
func4()
d = {}
def func5(arg=d):
    print ('foo' in arg)
d = {'foo': 100}

# 返回False，说明参数的默认值是在函数定义的时候确定的。因为函数是对象，这个时候创建了函数对象，并给他的参数赋予了默认值
func5()

# 4 使用keyword参数
def func6(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
    print voltage, state, action, type

func6(1000)
func6(voltage=10, type='NewType')
func6(500, 'new state')
func6(500, action='new action')

# 5
def func7(kind, *args, **darg):
    # 参数的顺序必须保持这样。先是*arg, 然后才能使**darg
    print kind # 调用的时候的第一个参数
    print args # 紧接着，没有指定参数名的参数组成的tuple, 可以用来接收可变参数列表
    print darg # 剩下的指定了参数名的参数组成的dict， 可以用来接收可变的keyword参数列表


func7('kind', 'arg1', 'arg2', 'arg3', 'arg4', fir_darg=10, sec_darg=20, thi_darg=30)
func7('kind', fir_darg=10, sec_darg=20, thi_darg=30)
func7('kind', 'arg1', 'arg2', 'arg3', 'arg4')



# ============================================

# 函数调用unpacking
def func8(arg1, arg2):
    print arg1, arg2

func8(1, 2)
# * unpacking List和tuple
# ** unpacking dict
func8(*[1, 2])
func8(*(1,2))
func8(**{'arg1': 10, 'arg2': 20})

sqrt = lambda x:x*2

print sqrt(4)



# 函数的文档
def func9():
    """
    This is
    The document of
    function 9
    """
    pass

print func9.__doc__
