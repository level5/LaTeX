/*

一个文件就是一个Module

导出module：
挂载到export的属性上；
或者替换掉module上的export;

*/

export.add = fucntion() {
    var sum = 0
    Array.prototype.forEach.call(arguments, function(v){
        sum += v
    })
    return sum
}

module.export = {
    max: funtion() {
        var max = -Infinity
        Array.prototype.forEach.call(arguments, function(v){
            max = max > v ? max : v
        })
        return max
    }
}

// 这样做是不行的!!
export = {
    min = Infinity
    Array.prototype.forEach.call(arguments, function(v){
        min = min > v ? v : min
    })
    return min
}

/*
导入module:
require('math')
*/

var math = require('math')


/*
require module分三步：
1. 路径分析
2. 文件定位
3. 编译执行

module分为核心module，和文件module


#) 优先缓存中加载

#) 路径分析
1. 核心模块
2. .或者..开始的相对路径文件模块
3. /开始的绝对路径模块
4. 非路径模式的文件模块


模块查找路径是当前目录下的node_modules,然后上一级目录的node_modules...一直查找到根目录为止;
如果不带扩展名，按.js,.json,.node的次序补全尝试
当不存在对应文件，而存在对应的目录时，会根据包中的package.json中指定的main属性指定的文件进行定位，
如果没有main属性火鹤main属性错误，则会将index当做默认文件名，按index.js,index.json,index.node的
顺序查找。


每个模块文件中存在的三个变量名是：require, module, export
还有两个变量__filename, __dirname

*/

// 实际上模块文件会被包装成这个样子：
(function (exports, require, module, __filename, __dirname) {
    var math = require('math');
    exports.area = function (radius) {
    return Math.PI * radius * radius;
    };
});
