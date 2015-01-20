var should = require('should');

describe('equality', function(){
	describe('#==',function(){
		it('test between same type', function(){
			should(undefined == undefined).be.ok;
			should(null == null).be.ok;
			should(NaN == 1).not.be.ok;
			should(NaN == NaN).not.be.ok;
			should({} == {}).not.be.ok;
		});

		it('test between different type', function(){
			should(undefined == null).be.ok;
			should(0 == undefined).not.be.ok;
			should(0 == null).not.be.ok;
			should(2 == true).not.be.ok;
			should(1 == true).be.ok;
			should(0 == false).be.ok;

			should(1 == '1').be.ok;

			var a = {
				toString: function() {return "a"},
				valueOf: function(){return 1;}
			}
			should(a == 1).be.ok;
			should(a == "a").not.be.ok;

			should(true == a).be.ok;
		});

		it('should throw exception', function(){
			(function(){
				var a = {toString: undefined, valueOf: undefined};
				a == 1;
			}).should.throw();

			(function(){
				var a = {toString: function(){ return {}}, valueOf: function(){return {}}};
				a == 1;
			}).should.throw();
		});

	});

	describe('#===', function(){
		it('test for same type', function(){
			should(undefined === undefined).be.ok;
			should(null === null).be.ok;
			should(NaN === 1).not.be.ok;
			should(NaN === NaN).not.be.ok;
			should({} === {}).not.be.ok;
		});
	});
});