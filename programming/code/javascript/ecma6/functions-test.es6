var should = require('should');

describe('ES6 Function', function(){

    describe('Function的默认参数', function() {
        it('默认参数', function() {

            function test(a, b = 2000, c=function() {}) {
                return {
                    a: a,
                    b: b,
                    c: c
                }
            }
            // 没有赋值或者显示的使用undefined会使用default值
            test(1000).b.should.eql(2000);
            test(1000).c.should.be.a.Function();
            test(1000, undefined, null).b.should.eql(2000);

            // 赋值null的话，将不使用default值
            should(test(1000, null).b).be.null();

        })

        it('默认参数每次都会初始化一个新的default值, 而不是在函数定义的时候调用', function() {

            function test(a = {count: 0}) {
                a.count++;
                return a;
            }

            var a = test();
            var b = test();
            should(a===b).be.false();
            b.count.should.eql(1);


            // 因此，就可能游侠面这种情况
            var getValue = function () {return 5}

            function get(a = getValue()) { return a}

            get().should.eql(5)

            getValue = function() {return 10}

            get().should.eql(10)


        })

        it('带default的arguments行为和es5中的strict mode一直', function() {
            function test(a, b = 'max') {
                arguments.length.should.eql(1)
                should(a === arguments[0]).be.true();
                should(b === arguments[1]).be.false();

                a = 'key', b = 'value';
                should(a === arguments).be.false();
                should(b === arguments).be.false();
            }
            test('min')
        })
    })

    describe('可变参数列表', function() {
        it('...<name> 作为参数名', function() {
            function pick(object, ...keys) {

            }
        })

        it('解构数组作为参数', function() {

        })
    })

    describe('构造函数', function() {

    })

    describe('Function的name属性', function() {

    })

    describe('Arrow Function', function() {
        /*
        it ('没有this, super, arguments, new.target这些绑定，使用的是外层的非箭头函数的这些值', function() {
            let that = this;
            let args = arguments;
            let sup = super
            let check = () => this === that && super === sup && args === arguments
            check().should.be.true();
        })
        */
        it('this的值不能改变', function() {})

        it ('不能new操作', function() {})

        it('没有prototype', function() {}, function() {

        })

        it('不能有重复命名的参数', function() {})

        it('Arrow Function的语法', function() {})

        it('多条语句的Arrow Function需要显示的return语句', function() {})
    })
})
