var should = require('should');

describe('typeof & instanceof', function(){
	describe('# typeof', function(){
		it('typeof primitive value', function(){
			(typeof undefined).should.be.exactly('undefined');
			(typeof null).should.be.exactly('object');
			(typeof 'this is a string').should.be.exactly('string');
			(typeof false).should.be.exactly('boolean');
			// Not-a-Number is a number
			(typeof 1).should.be.exactly('number');
			(typeof NaN).should.be.exactly('number');


		});

		it('typeof object', function(){
			(typeof function(){}).should.be.exactly('function');
			(typeof {}).should.be.exactly('object');
			(typeof []).should.be.exactly('object');
		});

		it('typeof Symbol', function(){})

		it('Primitive Wrapper is object', function(){
			(typeof new Boolean(true)).should.be.exactly('object');
			(typeof new String('this is a string')).should.be.exactly('object');
			(typeof new Number(0)).should.be.exactly('object');
		});
	});

	describe('# instanceof', function(){});
});