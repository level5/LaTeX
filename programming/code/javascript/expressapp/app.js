var express = require('express')
var app = express()

/*
路由： 结构： app.METHOD(path,[callback...], callback)

HTTP路由方法：get, post, put, head, delete, options, trace, copy, lock, mkcol,
move, purge, propfind, proppatch, unlock, report, mkactivity, checkout, merge,
m-search, notify, subscribe, unsubscribe, path, search, connect

m-search不符合js命名规范，需要这样使用：app['m-search']('/', function(req, res){...})

app.all('/secret', function(req, res, next){
    ...
    next() // 传递给下一个handler
})
*/

/*
路由是从上往下执行，当匹配到一个合适的中间件，如果他没有使用next()跳到下一个路由的话。处理就
结束了。
所以如果有多个路由匹配一个页面时，第一个匹配的生效，然后后续处理的话就看他有没有next向下继续转发了

*/

/*

req.body,如果使用body-parser和multer,就会将body中的form等解析到req.body上供使用

*/
var bodyParser = require('body-parser');
var multer = require('multer');
var upload = multer({ dest: 'uploads/' }) // 上传文件的目录

app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded
app.use(upload.single('avatar')); // for parsing multipart/form-data 上传文件名

/*
使用cookie-parser,将cookies解析到req.cookies上
*/


var req_api = require('./request-api')
app.use('/req', req_api)


app.get('/', function(req, res){
    res.send('Hello World!')
})

/*
路径可以使用字符串，字符串模式或者正则表达式
*/

// 匹配字符串
app.get('/about', function(req, res){
    res.send('description about the app')
})

// 匹配字符串模式
app.get('/ab?cd', function(req, res){
    res.send('your are requesting /acd or /abcd')
})

app.get('/abcdef+', function(req, res){
    res.send('you are requesting /abcdef, /abcdeff...')
})

app.get('/aaa*', function(req, res){
    res.send('you are requesting /aaa<Any-Chars>')
})

app.get('/a(kk)?e', function(req, res) {
    res.send('you are requesting /akke or /ae')
});

// 匹配正则表达式
app.get(/a/, function(req, res){
    res.send('request page contain a.')
})

/*
路由句柄
可以通过next('route')而略过其他路由会调函数
*/
app.get('/example/b', function(req, res, next){
    console.log('response will be sent by the next function...')
    next()
}, function(req, res){
    res.send('Hello from B!')
})

// 会调数组
var cb0 = function(req, res, next) {
    console.log('CB0, response will be sent by the next function...')
    next()
}

var cb1 = function(req, res, next) {
    console.log('CB1, response will be sent by the next function...')
    next()
}

var cb2 = function(req, res, next) {
    res.send('Hello from C!')
}

app.get('/example/c', [cb0, cb1, cb2])

var cbd0 = function(req, res, next) {
    console.log('CB0 for D, response will be sent by the next function...')
    next()
}

var cbd1 = function(req, res, next) {
    console.log('CB1 for D, response will be sent by the next function')
    next()
}

app.get('/example/d', [cbd0, cbd1], function(req, res, next){
    console.log('CB for D, response will be sent by the next function')
    next()
}, function(req, res){
    res.send('Hello from D!')
})


/*
app.route() 可以用来对某个URL路径的各种METHOD使用链式语句来处理，放置写错...
*/
app.route('/book')
    .get(function(req, res){
        res.send('Get a Random book')
    })
    .post(function(req, res){
        res.send('Add a book')
    })
    .put(function(req, res){
        res.send('Update the book')
    })

/*
express.Router，创建模块化可挂载路由
*/
var birds = require('./birds')
app.use('/birds', birds)

/*
中间件：
*/

// 应用级中间件： 使用 app.use()和app.<METHOD>()

/*
实际上放在这里不会生效，因为看上去是从上往下的顺序处理的，如果路径已经被前面的路由处理掉了，
是不会走到这个地方来的。
*/
app.use(function(req, res, next){
    console.log('req %s time:', req.uri, Date.now())
    next()
})

/*

API

*/

/*
express.static(root, [options])
唯一内置的中间件， 基于serve-static开发的。

root， 静态资源文件所在的根目录

options
dotfiles: 默认值为ignore，表示是否隐藏.开头的文件；
etag：是否开启etag，默认为true
...
*/
app.use(express.static('public'))


/*
错误处理
*/

var server = app.listen(3000, function() {
    var host = server.address().address
    var port = server.address().port
    console.log('Example app listening at http://%s:%s', host, port)
})
