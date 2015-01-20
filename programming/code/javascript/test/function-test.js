var should = require('should');

describe('function', function(){

	describe('#function declaration', function(){

		it('[[Scope]] should bind with variableEnvironment', function(){
			// cannot prove
			var foo = {bar: 100};

			with(foo) {
				function func() {
					(function(){
						bar;
					}).should.throw();
				}
			}
			func();
		});

		it('does not work as expect', function(){
			var foo = {bar: 200};
			with(foo) {
				eval('function func(){return bar;}');
			}
			func().should.be.exactly(200);
		});

	});

	describe('#function expression', function(){

		it('[[Scope]] should bind with lexicalEnvironment', function(){

			try {
				throw {bar: 100};
			} catch(e) {
				var foo = function(){
					return e;
				}
			}

			should(foo()).be.eql({bar: 100});
		});

		it('[[Scope]] should bind with a new DeclarativeEnvironment which outer is lexicalEnvironment of running execution context', function(){

			try {
				throw {bar: 100};
			} catch(e) {
				var foo = function e() {
					return e;
				}
				e.should.eql({bar: 100});
			}
			should(foo()).be.exactly(foo);
		});		
	});

	describe('#construct', function(){

		it('[[Class]] is set to function', function(){
			(typeof function(){}).should.be.exactly('function');
		});

		it('__proto__ is set to Function.prototype', function(){
			(function(){}).__proto__.should.be.exactly(Function.prototype);
		});

		it('length is the number of parameters', function(){
			(function(a, b, c, d, e, f){}).length.should.be.exactly(6);
		});

		it('default prototype should be an object with constructor property', function(){
			function foo(){}
			foo.prototype.should.eql({constructor: foo});
		});
	});

	describe('#constructor', function(){
		it('steps for new operator', function(){
			function Foo(){
				this.bar = 200;
			}

			var newObj;
			var foo = {};
			foo.__proto__ = Foo.prototype;
			var result = Foo.call(foo);
			if(result === undefined || result === null || typeof result == 'number' || typeof result =='string' || typeof result == 'boolean') {
				newObj = foo;
			} else {
				newObj = result;
			}
		});

		it('__proto__ is set to Object.prototyp if constructor.prototype is primitive or null', function(){
			function Foo(){}
			Foo.prototype = 1;

			var foo = new Foo;
			foo.__proto__.should.be.exactly(Object.prototype);
		});
	});

	describe("#this", function(){
		it('dot expression or square bracket', function(){
			var foo = {
				bar: function(){ return this;}
			}
			foo.bar().should.be.exactly(foo);
			foo['bar']().should.be.exactly(foo);
		});

		it('variable', function(){
			var foo = function(){return this;};
			foo().should.be.exactly(global);
		});

		it('variable in with', function(){
			var foo = {
				bar: function(){return this;}
			}
			with(foo){
				bar().should.be.exactly(foo);
			}
		});

		it('call, apply', function(){
			function foo(){return this;}
			var bar = {};
			
			foo.call(bar).should.be.exactly(bar);
			foo.apply(bar).should.be.exactly(bar);

			var res = foo.call(1);
			should(res).be.Object;
			(res == 1).should.be.true;

			foo.call(undefined).should.be.exactly(global);
		});
	});

	describe('#arguments', function(){
		it('last should override first if the has same name', function(){
			function foo(a, b, c, a){return a;}
			foo(1,2,3,4).should.be.exactly(4);
		});

		it('arguments should be replace by function', function(){
			function foo(){
				arguments.should.be.type('function');
				function arguments(){}
				var arguments = {};
				arguments.should.eql({});
			}
			foo();
		});

		it('arguments should exist', function(){
			function foo(){
				arguments.should.be.arguments;
				var arguments= {};
				arguments.should.eql({});
			}
			foo();
		});

		it('should be', function(){
			function foo(){

				bar.should.be.type('function');

				var bar = 10;

				bar.should.be.exactly(10);

				function bar(){}

				bar.should.be.exactly(10);

			}

			foo();
		});

		it('should be', function(){
			function foo() {
				should(bar).be.exactly(undefined);
				var bar = 10;
				bar.should.be.exactly(10);
			}

			foo();
		});
	});

});