var should = require('should')


/*
一段比较长的描述：

Lexical Environment, 两部分组成，一个指向Outer Lexcal Environment的引用；一个Environment Record；

Environment Record又分为两种:

Declarative Environment Record和Object Environment Record

Declarative Environment Record：可以理解为标识符绑定函数，变量声明到这个上面（艹，感觉不知道怎么说清啊！！）
                                ImplicitThisValue 总是返回 undefined;

Object Environment Record: 参考with，将标识符和某个js object绑定
                           她附带一个provideThis的标识，默认为false
                           如果provideThis为false，ImplicitThisValue 返回 undefined;否则返回绑定的object

然后进入Execution Context了。
三部分组成：
        LexicalEnvironment, 用来解析标识符绑定的值；
        VariableEnvironment, 用来保存函数，变量声明中，标识符对应的值
        ThisBinding

最初创建Execution Context的时候，LexicalEnvironment和VariableEnvironment指向的是同一个Lexical Environment.
这样，在一般情况下，函数中定义，到后面函数中使用某个标识符取到的值才是同一个。

VariableEnvironment是不会变的，而LexicalEnvironment是可以变的，比如说with语句，这个时候，其实就是LexicalEnvironment
变了，这样解析标识符得到的引用就不一样了。

=================================================

引用类型 {Base: xxx, Name: xxx, Strict: xxx}
Base: undefined, Object, Number, String, Boolean, Environment Record（没有Null）
Name: String
Strict: Boolean

标识符 identifier-name 到引用类型的解析, 假设在非严格模式下面：
取得Execution Context的LexicalEnvironment(是LexicalEnvironment,而不是VariableEnvironment,对应上面的解释).
如果LexicalEnvironment不存在，返回 {Base: undefined, Name: identifier-name, Strict: false}
如果存在，就取得LexicalEnvironment的Environment Record。看看上面是否存在identifier-name这个名字的绑定，如果存在
返回 {Base: envRecord(是LexicalEnvironment包含的Environment Record), Name: identifier-name, Strict: false}

=================================================

引用类型 {Base: base, Name: name, Strict: false} 获取值 ：
（忽略strict为ture的流程，要不扩充的内容不得完了）
如果base是undefined, 抛出 ReferenceError；
如果base是Object，取Object对应name的property；
如果base是Primitive的话，转换为装箱类型，然后去对应property的值
如果base是Environment Record的话，就在Environment Record上取得绑定的值

=================================================



*/
describe('Execution Context', function(){

    // 三种类型的Execution Context

/*

Global Execution Context:


GlobalEnvironment = {
    Outer: null,
    EnvironmentRecord: ObjectEnvironmentRecord{ Binding: GlobalObject}
}

GlobalExecutionContext = {
    LexicalEnvironment: GlobalEnvironment,
    VariableEnvironment: GlobalEnvironment,
    ThisBinding: GlobalObject
}

所以全局环境下的this就是GlobalObject.因为ThisBindg了GlobalObject；

在全局范围上声明的函数，变量也都绑定在GlobalObject上，因为此时的VariableEnvironment的EnvironmentRecord
是一个ObjectEnvironmentRecord,绑定到了GlobalObject上。

*/
    describe('Global Execution Context', function(){
        // 看上去没什么好写的Case...
    })

/*
    没有calling Execution Context的时候，创建的是Global Execution Context。这个在代码中一般应该不会出现

    设定ThisBinding为当前执行上下文的ThisBinding;
    设定LexicalEnvironment为当前执行上下文的LexicalEnvironment;
    设定VariableEnvironment为当前执行上下文的VariableEnvironment

    CurrentExecutionContext = {
        LexicalEnvironment: currentLexicalEnvironment,
        VariableEnvironment: currentVariableEnvironment,
        ThisBinding:currentThis
    }

    EvalExecutionContext = {
        LexicalEnvironment: currentLexicalEnvironment,
        VariableEnvironment: currentVariableEnvironment,
        ThisBinding: currentThis
    }

    如果是strict mode,就会创建一个新的DeclarativeEnvironemt,Outer指向currentLexicalEnvironment，然后
    将这个新的DeclarativeEnvironemt当做LexicalEnvironment和VariableEnvironment

    StrictDeclarativeEnvironment = DeclarativeEnvironemtRecord{
        Outer: currentLexicalEnvironment
    }

    EvalExecutionContext(strict mode) = {
        LexicalEnvironment: StrictDeclarativeEnvironment,
        VariableEnvironment: StrictDeclarativeEnvironment,
        ThisBinding: currentThis
    }

*/

    describe('Eval Execution Context', function(){
        it('都创建在当前的Execution Context上', function(){
            eval('var a = 10; \
                  var b = 20; \
                  ');
            a.should.eql(10)
            b.should.eql(20)
        })

        it('而且都是创建在当前Execution Context的VariableEnvironmentRecord上', function(){
            var obj = {};

            with(obj) {
                eval('var a = 10;')
            }

            should(obj.a).be.undefined() // obj在with中是处于LexicalEnvironment链顶端
            a.should.eql(10)

        })

        it('strict mode下，创建在自己的LexicalEnvironmentRecord上', function(){
            'use strict'
            eval('var a = 10; \
                  ');
            should(function(){
                a;
            }).throw(ReferenceError)
        })

        it('this的值就是当前的this', function(){
            var foo = {
                bar: function(){
                    eval('this.a=10')
                }
            }
            foo.bar()
            foo.a.should.eql(10)
        })
    })


/*

localEnv = {
    Outer: function.[[Scope]],
    EnvironmentRecord: NewDeclarativeEnvironementRecord{}
}

FunctionExecutionContext = {
    LexicalEnvironment: localEnv,
    VariableEnvironment: localEnv
    ThisBinding: ThisArg/toObject(ThisArg)/null,undfined -> GlobalObject
}

ThisBinding是一个Object;
function.[[Scope]]是在在Function Object创建时的根据当时的ExecutionContext的LexicalEnvironment来创建的


ThisArg的取值：

对于Function.prototype.apply, Function.protoype.call, Function.prototype.bind相关的this值，见case

对于一个函数调用，解析取得引用类型，{Base: base, Name:name, Strict: true/false}
当Base是对象时，Base当做this值；
当Base是EnvironmentRecord时，this就取EnvironmentRecord.ImplicitThisValue:
        DeclarativeEnvironemtRecord.ImplicitThisValue都是返回undefined
        ObjectEnvironmentRecord.ImplicitThisValue默认是undfined,with语句时，返回的是with的那个Object

function.[[Scope]]的取值：

*/
    describe('Function Execution Context', function(){

        describe('Function Execution Context的this值', function(){
            it('Function.protototype.call和Function.protototype.apply类似，这里只使用call', function(){

                function foo() { return this }
                // 没有什么花样，注意PrimitiveValue各对应的Object
                should(foo.call(1)).be.instanceof(Number)
                should.equal(foo.call(1), 1)

                should(foo.call('')).be.instanceof(String)
                should.equal(foo.call(''), '')

                should(foo.call(true)).be.instanceof(Boolean)
                should.equal(foo.call(true), true)

                // null和undefined会导致ThisBinding的值为GlobalObject
                // Node中的全局对象是global
                should(foo.call(null)).eql(global)
                should(foo.call(undefined)).eql(global)

            })

            it('Function.prototype.bind', function(){
                // 待补充
            })

            it('identifier.indentifer()或者identifier[expression]()形式的调用', function(){
                // 这个也没有什么太多问题
                function foo() {return this}
                var a = {}
                a.foo = foo
                a.foo().should.eql(a)

                var count = 0; var arg
                -function bar() {
                    count++
                    if (count == 1) {
                        this.should.eql(global)
                    } else if (count == 2) {
                        this.should.eql(arg)
                    } else {
                        return
                    }
                    arg = arguments;
                    arguments.callee() // 这个时候this就是arguments了。
                }()

            })

            it('identifier()形式的调用', function(){
                // 这里的引用时{Base: localEnv.DeclarativeEnvironemtRecord, ...}
                // 因此this为undefined，因为DeclarativeEnvironemtRecord.ImplicitThisValue都是返回undefined
                // 然后ThisBinding 就绑定到了GlobalObject
                function foo (){ return this }
                foo().should.eql(global)
            })

/*
            with(obj)---->
            进入
            创建一个新的ObjectEnvironment, 他的ObjectEnvironmentRecord绑定当前的obj
            (如果obj是Primitive,需要转换成Object),Outer引用原来的LexicalEnvironment.
            provideThis设定为true。然后替换原来的LexicalEnvironment.

            ExecutionContext = {
                LexicalEnvironment: {
                    Outer: localEnv
                    EnvironmentRecord: NewObjectEnvironementRecord{
                        binding: toObject(obj)
                        provideThis: true
                    }
                }
                VariableEnvironment: localEnv
            }

            退出
            恢复成原来的样子

*/
            it('with block中identifier()形式的调用', function(){
                //
                var obj = {
                    foo: function() {return this}
                }

                // 这里this是obj
                with(obj){
                    foo().should.eql(obj)
                }

                // Primitive会被装箱
                with(1){
                    valueOf().should.eql(1)

                    // 这里不能使用should(valueof()).eql(1),因为这样访问的是Number上的should
                    require('should')(valueOf()).eql(1)
                }
            })

/*
            catch(e) ------>
            进入
            创建新的DeclarativeEnvironemt, Outer设定为原来的LexicalEnvironment.在这个
            新的DeclarativeEnvironemt上创建绑定一个可变的e。这里是DeclarativeEnvironemt,
            这一点和with不一样，所以ImplicitThisValue为undefined。

            退出
*/
            it('catch block 中identifier()形式的调用', function(){

                function foo() {return this}
                function bar() {return this}

                should(foo()).eql(global)

                try {
                    throw ""
                } catch(foo) {
                    should(function(){
                        foo()
                    }).throw(TypeError);

                    // global
                    bar().should.eql(global)

                }

                should(foo()).eql(global)

            })
        })


    })

})
