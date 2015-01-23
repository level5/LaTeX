var foo  = Object.create({}, {
	bar: {}
});

var descriptor = Object.getOwnPropertyDescriptor(foo, 'bar');
console.log(descriptor);

var foo  = Object.create({}, {
	bar: {value: 100, writable:false, configurable: true, enumerable: true}
});

foo.bar = 200;
console.log("cannot change foo.bar, it still is", foo.bar);

for(var key in foo)
{
	console.log("key in foo:", key);
}
//因为是configurable的，所以可以修改
var descriptor = Object.getOwnPropertyDescriptor(foo, "bar");
descriptor.enumerable = false;
Object.defineProperty(foo, "bar", descriptor);
descriptor = Object.getOwnPropertyDescriptor(foo, "bar");
console.log(descriptor);
for(var key in foo)
{
	console.log("key in foo:", key);
}

descriptor.writable = true;
descriptor.configurable =false;
Object.defineProperty(foo, "bar", descriptor);
descriptor = Object.getOwnPropertyDescriptor(foo, "bar");
console.log(descriptor);

descriptor.writable = false;
descriptor.enumerable = true;
Object.defineProperty(foo, "bar", descriptor);
descriptor = Object.getOwnPropertyDescriptor(foo, "bar");


