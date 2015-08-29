var should = require('should');

describe('execution context', function() {

	describe('#function', function(){

		it('this should be object', function(){
			function foo(){
				should(typeof this).be.exactly('object');
			}
			foo.call(1);
		});

		it('this should be global object if null or undefined been treat as this', function(){
			function foo(){
				this.should.be.exactly(global);
			}

			foo.call(null);
			foo.call(undefined);
		});

		it('this should be global object if null or undefined been treat as this', function(){
			function foo(){
				this.should.be.exactly(global);
			}

			foo();
		});

		it('different between function declaration & function expression',function(){
			var foo = {bar: 200};
			with(foo){
				var func = function() {
					bar.should.be.exactly(200);
				}

				function func2(){
					bar;
				}

				eval('function func3(){return bar;}');
			}
			func();
			func2.should.throw();
			func3().should.be.exactly(200); // I don't know why

		});
	});

	describe('#eval', function(){

		it('should been add to environment of running execution context', function(){
			(function(){
				eval('var bar = 100');
				bar.should.be.exactly(100);
			})();
		});


		it('should use new declarative environment in strict mode', function(){
			(function(){
				'use strict';
				var bar = 200;
				eval('var bar = 100');
				bar.should.be.exactly(200);
			})();
		});

		it('should use new declarative environment in strict mode 2', function(){
			(function(){
				var bar = 200;
				eval('"use strict"; var bar = 100');
				bar.should.be.exactly(200);
			})();
		});
	});

	describe('#with statement', function(){
		it('should bind this to with object', function(){
			var foo = {
				bar: function (){return this;}
			};
			with(foo) {
				(bar()).should.be.exactly(foo);
			}
		});

		it('should been add to with variable environment of running execution context', function(){
			var foo = {};

			(function(){
				bar;
			}).should.throw();

			with(foo){
				eval('var bar = 200');
			}
			bar.should.be.exactly(200);
			foo.should.eql({});
		});

		it('should been add to with variable environment of running execution context', function(){
			var foo = {};
			should(bar).be.exactly(undefined);
			with(foo){
				var bar = 200;
			}
			bar.should.be.exactly(200);
			foo.should.eql({});
		});

		it('bar should be defined and did not init', function(){
			var foo = {bar: 200};
			(function(){
				bar;
			}).should.throw();
			with(foo){
				eval('var bar = 300');
			}
			foo.bar.should.be.exactly(300);
			should(bar).be.exactly(undefined);
		});

		it('function declaration should be bind to variableEnvironment', function(){
			var foo = {bar: 200};
			(function(){
				bar;
			}).should.throw();

			with(foo){
				eval('function bar(){}');
			}
			foo.bar.should.be.exactly(200);
			should(bar).be.instanceOf(Function);
		});

	});
});
