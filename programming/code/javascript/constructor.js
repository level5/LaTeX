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
	if (typeof foo.prototype != 'object') {
		obj.__proto__ = Object.prototype;
	} else {
		obj.__proto__ = foo.prototype;
	}
	
	var result = foo.apply(obj, arguments);
	// 此处判断不一定
	if (typeof result != 'object') {
		return obj;
	} else {
		return result;
	}
}