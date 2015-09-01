/*
    request API
*/
var express = require('express')
var router = express.Router()

router.get('/', function(req, res){

    var content = 'request对象的属性: <p/>'

    // req.app: 使用此中间件的app的引用
    content += 'views的设定:' + req.app.get('views') + '<p/>'

    // 显示的是这个router的挂载路径, 而不是这个中间件的Url
    // 这里显示/req，因为挂载点和这个url刚好相同而已
    content += '挂载路径:' + req.baseUrl + '<p/>'

    // req.body

    // req.cookies

    //
    content += '主机名:' + req.hostname + '<p/>'

    content += 'IP:' + req.ip + '<p/>'

    content += 'originalUrl:' + req.originalUrl + '<p/>'

    //
    req.path;

    req.query;

    req.protocol;



    res.send(content)
})

router.get('/baseurl', function(req, res){

    // 如果是一个express.Router的话，显示的是这个router的挂载路径, 而不是这个中间件的
    // 所以这里还是显示的/req
    var content = '挂载路径:' + req.baseUrl + '<p/>'

    // 当前路径
    content += 'originalUrl:' + req.originalUrl + '<p/>'
    res.send(content)
})

router.get('/params/:name', function(req, res){

    console.log(req.route)

    res.send('params.name = ' + req.params.name)
})


module.exports = router
