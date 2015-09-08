var should = require('should')

describe('Object', function() {

    describe('typeof Object', function(){
        it('typeof null也是object', function(){
            (typeof null).should.eql('object')
        })

        it('typeof <funciton>是function', function(){
            (typeof function(){}).should.eql('function');

            // 这些都是function object
            (typeof Object).should.eql('function');
            (typeof Function).should.eql('function');
            (typeof Array).should.eql('function');
            (typeof Date).should.eql('function');
        })

        it('其他情况下,typeof <object>都是object', function(){
            (typeof {}).should.eql('object')
        })
    })

    describe('Property:Object就是一个Property的集合', function(){

        describe('要使用的3个方法', function(){

            it('Object.getOwnPropertyDescriptor， 取得对应的property的descriptor', function(){})

            it('Object.defineProperty, 根据传入的property descriptor来在对象上定义property', function(){})

            it('Object.create(prototype, propertyDescriptor)', function() {})
        })

/*
Named Data Property的descriptor可以写做这样由下面的attributes来表示
{
    value: undefined,       对应property绑定的值；
    writable: false,        表示这个property的值是否可以被修改；
    enumerable: false,      表示这个值是否会出现在for-in语句中
    configurable: false     如果为false，不能修改除value之外的其他attribute，不能从
                            Data Property修改为Access Property，不能delete这个property
}
*/
        describe('Named Data Property', function(){
            it('平时代码写的方式就是创建Data Property', function() {
                var foo = { bar: 'foo'}
                var descriptor = Object.getOwnPropertyDescriptor(foo, 'bar')
                descriptor.should.eql({
                    value: 'foo',
                    writable: true,
                    enumerable: true,
                    configurable: true
                })
            })
            it('attribute的默认值是undefined和false', function(){
                var foo = Object.create(null, {
                    bar: {} // 默认创建的是 data property
                })

                var descriptor = Object.getOwnPropertyDescriptor(foo, 'bar');
    			descriptor.should.eql({
					value: undefined,
					writable: false,
					enumerable: false,
					configurable: false
				});

            })

            it('attribute value就是这个property的值', function(){
                var foo = Object.create(null, {
                    bar: { value: 'test string'} // 默认创建的是 data property
                })
                foo.bar.should.eql('test string')
            })

            it('attribute writable为false的时候，value的值不能修改', function(){
                var foo = Object.create(null, {
                    bar: {
                        value: 'test string',
                        writable: false, // 刚刚写case的时候犯了个错误，false写成了'false'
                                         // 导致实际'false' to boolean 变为了true
                        enumerable: true,
                        configurable: true
                    }
                })
                foo.bar.should.eql('test string')
                foo.bar = 'test test string'
                foo.bar.should.eql('test string')
                // 但是可以删除
                delete foo.bar
                should(foo.bar).be.undefined()
            })

            it('enumerable为false时，for-in中不会出现对应property', function(){
                var foo = Object.create(null, {
                    bar: {},
                    bar2: {enumerable: true}
                })

                // ES5 中js没有块级别的作用域，ES6好像有了, 所以改写下
                var i;
                for(i in foo) {
                    i.should.not.eql('bar')
                }
                i.should.eql('bar2')
            })

            it('configurable为false的时候，不能修改value之外的attribute配置，不能delete property，不能置换到Access Property', function(){
                var foo = Object.create(null, {
                    bar: {value: 100, writable: true, configurable: false, enumerable: true}
                })

                foo.bar = 200
                // 因为writable为true，所以仍然可以修改foo.bar
                foo.bar.should.eql(200)

                // 不能delete
                delete foo.bar
                foo.bar.should.eql(200)

                // 不能置换成Access Property
                should(function(){
                    Object.defineProperty(foo, 'bar', {
                        get: undefined
                    })
                }).throw(TypeError)

            })

        })

/*
Access Property的话，相关Attribute是：
{
    get: undefined,         get方法
    set: undefined,         set方法
    enumerable: false,      同上
    configurable: false     同上
}
*/
        describe('Named Access Property', function(){
            it('同样的，默认值是undeinfed和false', function(){
                var foo = Object.create(null, {
                    bar: {
                        set: undefined
                    },
                    baz: {
                        get: undefined
                    }
                })

                var descriptor = Object.getOwnPropertyDescriptor(foo, 'bar')
                var descriptor2 = Object.getOwnPropertyDescriptor(foo, 'baz')

                descriptor.should.eql({
                    get: undefined,
                    set: undefined,
                    enumerable: false,
                    configurable: false
                })

                descriptor2.should.eql({
                    get: undefined,
                    set: undefined,
                    enumerable: false,
                    configurable: false
                })
            })
        })

        describe('internal Property', function(){
            // 这个没办法来写测试了， 比如说[[prototype]]就是内部属性
        })

        describe('property其他的部分', function() {

            it('""空字符串也可以作为property的key', function() {
                var a = { "": 100 }
                a[""].should.eql(100)
            })
        })
    })

    describe('Prototype', function(){})

    describe('new操作', function() {})

    describe('Object上的方法', function(){})

    describe('Object.prototype上的方法', function () {})

})
