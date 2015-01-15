function foo()
{
	if (!(this instanceof foo)) {
		return new foo();
	}
	// 此处初始化对象
	this.bar = 100;
	// ...
}

console.log(foo());
console.log(new foo);
console.log(new foo());


//new foo 相当于
function createFoo()
{
	var obj = {};
	obj.__proto__ = foo.prototype;
	var result = foo.apply(obj, arguments);
	// 此处判断不一定
	if (result == undefined || typeof result == 'string' || typeof result == 'number' || typeof result == 'boolean') 
	{
		return obj;
	} else 
	{
		return result;
	}
}