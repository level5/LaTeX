var should = require('should');

describe('object', function(){

    describe('#undefined', function(){
        it('void 0 is undefined', function(){
            should(void 0).be.exactly(undefined);
        });
        it ('actually, void <any> is undefined', function(){
            should(void 1).be.exactly(undefined)
            should(void '').be.exactly(undefined)
            should(void null).be.exactly(undefined)
            should(void undefined).be.exactly(undefined)
            should(void {}).be.exactly(undefined)
        })
    });

    describe('#prototype', function(){
        it('__proto__ of Object.prototype is null, not undefined', function(){
            should(Object.prototype.__proto__).be.exactly(null);
            should(Object.getPrototypeOf(Object.prototype)).be.exactly(null)
        });
        describe('prototype of object created by Object.create(null)', function(){
            it('__proto__ is undefined', function() {
                should(Object.create(null).__proto__).be.exactly(undefined); // why?
            });
            it('but prototype is null', function(){
                should(Object.getPrototypeOf(Object.create(null))).be.exactly(null);
            });
            it('so __proto__ is not euqal to prototype', function() {
                //...
                should(Object.create(null).__proto__ === Object.getPrototypeOf(Object.create(null))).be.fasle;
            })
        });

        // Object.create(prototype, properties)
        describe('Object.create...', function(){
            it('Object.create的一个参数必须是Object或者Null', function(){
                (function(){
                    Object.create(undefined)
                }).should.throw();

                (function(){
                    Object.create(1)
                }).should.throw();

                (function(){
                    Object.create("haha")
                }).should.throw();

                // 这个和传入undefined有什么区别呢？
                (function(){
                    Object.create()
                }).should.throw();
                // 还有其他区别吗？？
                (function(){arguments.length.should.be.equal(0)})();
                (function(){arguments.length.should.be.equal(1)})(undefined);
            })
        })
    });

    describe('#instanceof', function(){
        it('test if Constructor.prototype on the prototype chain', function(){
            function Foo(){}

            var foo = Object.create(Foo.prototype);
            var foo2 = Object.create(Foo);

            should(foo instanceof Foo).be.true;
            should(foo2 instanceof Foo).be.false;

            Foo.prototype = Foo
            should(foo instanceof Foo).be.false;
            should(foo2 instanceof Foo).be.true;
        });
    });
});
