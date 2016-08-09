#coding:utf-8

def run():
    a = A()
    print a

# 这里A必须继承于object，不带这个object，Python的对象是继承于什么呢？
class A(object):
    def __new__(cls, *args, **kwargs):
        print 'new A'
        obj = object.__new__(cls)
        print 'A instance created' # 这里说明，__init__是在这之后执行的，上面的调用中执行的
        return obj

    def __init__(self):
        print 'init A'

class B(object):

    def __init__(self):
        print 'init class B'

if __name__ == '__main__':
    run()