/*
1. 创建.babelrc, 指定presets
2. 安装babel-core 和 presets
    npm install -g babel-core
    npm install --save-dev babel-preset-es2015
*/
require("babel-core/register")

// 然后这之后就可以require es6语法的文件了。
require('./function.es6')
