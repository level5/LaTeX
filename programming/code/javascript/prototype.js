var a = {};	
a.b = 10;
console.log(a.b); // 10
console.log(a["b"]); // 10
a.foo = function() {
	console.log("invoke foo");
};
a.foo();


var proto = {bar : 10};
var foo = Object.create(proto);

console.log(foo.bar); // 10
console.log(foo.bar2); // undefined

proto.bar2 = 20; 
console.log(foo.bar2); // 20



var proto = {x: 10};

var foo = Object.create(proto);
var bar = Object.create(proto);

console.log(foo.x); // 10
console.log(bar.x); // 10

foo.x = 20;

console.log(foo.x); // 20
console.log(bar.x); // 10
console.log(proto.x); // 10


var Ibar = undefined;
if (Ibar === void 0) 
{
	console.log("Ibar is undefined");
}