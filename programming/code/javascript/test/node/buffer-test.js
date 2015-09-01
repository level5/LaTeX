var should = require('should')

/*
Buffer是在V8堆外分配的内存，类似于Array
*/

var TEST_FILE_NAME = __dirname + '/test.md'

describe('Buffer', function(){

    it('Buffer进程启动时已加载，无需require', function(){
        should(function(){
            var buf = new Buffer('nodejs buffer', 'utf-8')
        }).not.throw()
    })

    it('Buffer可以通过下标访问，值为0~255之间', function(){
        var buf = new Buffer(100)
        buf.length.should.eql(100)
        //
        buf[10].should.above(-1).and.below(256)
    })

    it('Buffer默认使用UTF-8编码', function(){

    })

    it('Buffer截断字符导致乱码的问题', function(done){
        var fs = require('fs')

        // 强制每次最多读取11个字符
        var rs = fs.createReadStream(TEST_FILE_NAME, {highWaterMark: 11})
        var data = ''
        rs.on('data', function(chunk) {
            // chunk是Buffer类型
            chunk.should.be.instanceof(Buffer)
            data += chunk // 这里隐式的调用了chunk.toString
            data.should.be.type('string')
        });
        rs.on('end', function() {
            console.log(data)
            // 这里UTF-8导致从字符中间截断导致了乱码
            data.should.not.eql('床前明月光，疑是地上霜，举头望明月，低头思故乡。\n')
            done()
        })
    })

    // 限制性，能处理的字符类型有限
    it('上一个Buffer截断字符乱码问题的修正', function(done){
        var fs = require('fs')

        // 强制每次最多读取11个字符
        var rs = fs.createReadStream(TEST_FILE_NAME, {highWaterMark: 11})
        rs.setEncoding('UTF-8')
        var data = ''
        rs.on('data', function(chunk) {
            // chunk是string
            chunk.should.be.type('string')
            data += chunk
        });
        rs.on('end', function() {
            data.should.eql('床前明月光，疑是地上霜，举头望明月，低头思故乡。\n')
            done()
        })
    })

    //
    it('上一个Buffer截断字符乱码问题的修正2', function(done){
        var fs = require('fs')

        // 强制每次最多读取11个字符
        var rs = fs.createReadStream(TEST_FILE_NAME, {highWaterMark: 11})
        var chunks = []
        var size = 0
        rs.on('data', function(chunk) {
            // chunk是Buffer类型
            chunk.should.be.instanceof(Buffer)
            chunks.push(chunk)
            size += chunk.length
        });
        rs.on('end', function() {
            var buf = Buffer.concat(chunks, size)
            buf.toString().should.eql('床前明月光，疑是地上霜，举头望明月，低头思故乡。\n')
            done()
        })
    })
})
