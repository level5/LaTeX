var should = require('should');

describe('Constructor Pattern', function(){
  it('three ways to create empty object', function(){
    should({}).be.eql({});
    should(new Object()).be.eql({});
    should(Object.create(Object.prototype)).be.eql({});
  });
  it('four ways assign key value to object', function(){
    //dot syntax
    var obj = {};
    obj.someKey = 'helloWorld';

    // square bracket syntax
    var obj2 = {};
    obj2['someKey'] = 'helloWorld';

    // Object.defineProperty
    function defineProp(obj, key, value)
    {
      Object.defineProperty(obj, key, {
        value: value,
        writable: true,
        enumerable: true,
        configurable: true
      });
    }
    var obj3 = {};
    defineProp(obj3, 'someKey', 'helloWorld');
    // Object.defineProperties
    var obj4 = {};
    Object.defineProperties(obj4, {
      "someKey": {
        value: 'helloWorld',
        writable: true,
        enumerable: true,
        configurable: true
      }
    });
    should(obj).be.eql({someKey: 'helloWorld'});
    should(obj2).be.eql({someKey: 'helloWorld'});
    should(obj3).be.eql({someKey: 'helloWorld'});
    should(obj4).be.eql({someKey: 'helloWorld'});
  });
});
