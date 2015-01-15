var should = require("should");

describe('Property', function(){
	describe('#Data Proerty', function(){
		it('should get correct default descriptor', function(){
			var foo  = Object.create({}, {
				bar: {}
			});
			var descriptor = Object.getOwnPropertyDescriptor(foo, 'bar');
			descriptor.should.eql(
				{
					value: undefined, 
					writable: false, 
					enumerable: false, 
					configurable: false
				});
		});

		it('should not be able to change property value when writable is false', function(){
			var foo = Object.create({}, {
				bar: {value: 100, writable: false}
			})

			foo.bar = 200;
			foo.bar.should.be.exactly(100).and.be.Number;
		});

		it('should not be able to enumerate in for in statement when enumerable is false', function(){
			var foo = Object.create({}, {
				bar: {value: 100, enumerable: false}
			});

			for (var key in foo)
			{
				key.should.not.be.exactly('bar');
			}
		});

		it('should not be able to modify attributes of property excluded [[Value]] when configurable is false', function(){
			var foo = Object.create({}, {
				bar: {value: 100, writable: true, enumerable: false, configurable: false}
			});

			var descriptor = {value: 200, writable: false, enumerable: true, configurable: true};
			(function(){
				Object.defineProperty(foo, 'bar', descriptor);
			}).should.throw();

			foo.bar = 200;
			foo.bar.should.be.exactly(200).and.be.Number;			
		});
	});
	
	describe("#Accessor Property", function(){
		it('should get correct default descriptor', function(){
			var foo = Object.create({}, {
				bar : {get: undefined}
			});
			var descriptor = Object.getOwnPropertyDescriptor(foo, 'bar');
			descriptor.should.eql({
				get: undefined,
				set: undefined,
				enumerable: false,
				configurable: false
			});
		});

		it('base should be treat as this when assign or get property value', function(){
			var foo = Object.create({}, {
				bar: {get: function(){
					return this._bar;
				}, 
				set: function(value){
					this._bar = value;
				}}
			});
			foo.bar = 100;
			foo.should.have.ownProperty('_bar').be.exactly(100);
			foo.should.have.ownProperty('bar').be.exactly(100);
		});
	});

	describe('#Get & Set', function(){
		it('should create a own data property when assignning a not exist property', function() {
			foo = Object.create({}, {});
			foo.bar = 100;
			foo.should.have.ownProperty('bar').be.exactly(100);
			var descriptor = Object.getOwnPropertyDescriptor(foo, 'bar');
			descriptor.should.eql({
				value: 100,
				writable: true,
				enumerable: true,
				configurable: true
			})
		});

		it('should create a own data property when assignning a inherited data property', function(){
			foo = Object.create({bar: 200}, {});

			foo.should.not.have.ownProperty('bar');
			foo.bar = 100;
			foo.should.have.ownProperty('bar').be.exactly(100);
		});

		it('should not create own propert when assignning a inherited accessor property', function(){
			var x = 100
			var parent= {};
			Object.defineProperty(parent, 'bar', {set: function(value){x = value}});

			foo = Object.create(parent, {});

			foo.should.not.have.ownProperty('bar');
			foo.bar = 200;
			foo.should.not.have.ownProperty('bar');
			x.should.be.exactly(200);
		});
	});
});
