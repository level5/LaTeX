var should = require('should');

describe('object', function(){

	describe('#undefined', function(){
		it('void 0 is undefined', function(){
			should(void 0).be.exactly(undefined);
		});
	});

	describe('#prototype', function(){
		it('__proto__ of Object.prototype is null', function(){
			should(Object.prototype.__proto__).be.exactly(null);
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
				should(Object.create(null).__proto__ === Object.getPrototypeOf(Object.create(null))).not.be.ok;
			})
		});
	});

	describe('#instanceof', function(){
		it('test if Constructor.prototype on the prototype chain', function(){
			function Foo(){}

			var foo = Object.create(Foo.prototype);

			should(foo instanceof Foo).be.exactly(true);

			var foo2 = Object.create(Foo);

			should(foo2 instanceof Foo).be.exactly(false);
		});
	});
});
