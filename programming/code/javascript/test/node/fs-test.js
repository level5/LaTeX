var should = require('should')
var fs = require('fs')

/*
 wrappers around standard POSIX functions.

 异步形式一般最后都带一个completion callback.这个callback的第一个参数一般是exception.
 如果调用正常则这个参数一般是null或者undefined。

*/

var TMP_DIR = __dirname + '/tmp'

var FILE_TO_CREATE = TMP_DIR + '/file_to_create'

before(function(){
    fs.mkdirSync(TMP_DIR)
})

after(function(){
    // 清空目录

    // 删除目录
    fs.rmdirSync(TMP_DIR)
})




describe('fs', function(){

    describe('基础知识', function(){

        it('相对路径对应的是process.cwd(), 也就是执行命令行的当前目录', function(){
            // process是一个全局对象
            fs.realpathSync('.').should.eql(process.cwd())
        });


    })

    describe('API', function(){
        describe('# fs.createWriteStream(path[, options])', function(){
            // it('1', function(done) {
            //     var ws = fs.createWriteStream(FILE_TO_CREATE);
            //     ws.on('open', function(fd){
            //         ws.write('xxx', 'utf-8', function(){
            //             done()
            //         })
            //     })
            // })
        })
    })
})
