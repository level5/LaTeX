var should = require('should');

describe('equality', function(){

	describe('toNumber', function(){
		it('String to Number', function(){
			// 前后可以有空格或者换行
			should(' \
			1  \
			' - 0).be.eql(1)

			// 数字，小数，正负数
			should('1' - 0).be.eql(1)
			should('1.2' - 0).be.eql(1.2)
			should('+1' - 0).be.eql(1)
			should('-1' - 0).be.eql(-1)

			//16进制
			should('0xF' - 0).be.eql(15)
			should('0Xf' - 0).be.eql(15)

			//无限大
			should('Infinity' - 0).be.eql(Infinity)

			// 科学计数法
			should('1E2' - 0).be.eql(100)
			should('1e+2' - 0).be.eql(100)
			should('1e-2' - 0).be.eql(0.01)

			// NaN
			should(isNaN('1R' - 0)).be.true

		});

		it('boolean to Number', function(){
			should(true - 0).be.eql(1)
			should(false - 0).be.eql(0)
		});

		it('Object to Primitive', function() {
			var obj = {
				valueOf: function() { return {}},
				toString: function() {return 'obj'}
			}

			var obj2 = {
				valueOf: function() {return 2},
				toString: function() {return {}}
			}

			var obj3 = {
				valueOf: function() {return {}},
				toString: function() {return {}}
			}



		});
	});

	describe('# ==',function(){
		describe('比较相同的类型', function(){

			it('基本类型的比较，按值比较', function(){
				should(undefined == undefined).be.true;
				should(null == null).be.true;
				should(1 == 1).be.true;
				should('abc' == 'abc').be.true;
				should(true == true).be.true;
			});

			it('对于Number型来说，特殊值NaN和任何值都不想等', function(){
				should(NaN == 1).be.false;
				should(NaN == NaN).be.false;
			});

			it('对于Object类型来说，相同的引用才相等',function(){
				should({} == {}).be.false;

				var obj = {};
				should(obj == obj).be.true;
			});
		});

		describe('比较不同的类型', function(){
			it('undefined等于null; undefined和null不等于其他', function(){
				should(undefined == null).be.true;

				should(0 == undefined).be.false;
				should(0 == null).be.false;

				should('undefined' == undefined).be.false;
				should('null' == null).be.false;

				should(true == undefined).be.false
				should(false == undefined).be.false
				should(true == null).be.false
				should(false == null).be.false
			});

			it('String和Number比较时，String转换为Number',function(){
				should('Infinity' == Infinity).be.true
				should('1' == 1).be.true
				should('1R' == 1).be.false
			});

			it('有一方是boolean型的时候，将boolean转换为Number，再进行比较', function(){
				should(true == 1).be.true
				should(false == 0).be.true
			});

			it('有一方是String或者Number，另外一方是Object，将Object转换为Primitive，再进行比较', function(){

			});


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
