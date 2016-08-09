import fs from 'fs'
import stream from 'stream'

const should = require('should');

describe('这是一个关于怎么使用stream的例子', function() {

  describe('IO异步', function(){
    it('这是一个IO异步调用', function(done) {

      fs.readFile(__dirname + '/data/text.txt', function(err, data) {
        should(data.toString()).eql('In unix, streams are implemented by the shell with | pipes.');
        done();
      })
    })

  })

  describe('readable stream', function() {
    it('读取中文文件时,看上去没什么问题', function(done) {

      let rs = fs.createReadStream(__dirname + '/data/chinese.txt');
      let data = ''
      rs.on('data', function(chunk){
        data += chunk;
      })
      rs.on('end', function(){
        should(data).eql('床前明月光，疑是地上霜；举头望明月，低头思故乡。');
        done()
      })
    })

    it('但是实际上是有问题的', function(done) {

      let rs = fs.createReadStream(__dirname + '/data/chinese.txt', {highWaterMark: 11});
      let data = ''
      rs.on('data', function(chunk){
        data += chunk;
      })
      rs.on('end', function(){
        should(data).not.eql('床前明月光，疑是地上霜；举头望明月，低头思故乡。');
        done();
      })
    })

    it('需要做一下特殊处理', function(done) {
      let rs = fs.createReadStream(__dirname + '/data/chinese.txt', {highWaterMark: 11});
      rs.setEncoding('utf-8')
      let data = ''
      rs.on('data', function(chunk){
        data += chunk;
      })
      rs.on('end', function(){
        should(data).eql('床前明月光，疑是地上霜；举头望明月，低头思故乡。');
        done();
      })
    })
  })

  describe('使用Readble stream', function() {
    it('创建一个readable的stream', function(done) {

      const Readable = stream.Readable

      let rs = new Readable();

      rs.push('beep ');
      rs.push('boop\n');
      rs.push(null); // 这个是用来告诉结束了！

      let data = ''
      rs.on('data', function(chunk){
        data += chunk; // 这里chunk是Buffer哦！
      })
      rs.on('end', function() {
        data.should.eql('beep boop\n');
        done();
      })

    })

    it('创建一个按需产生数据的readable stream', function(done) {
      let rs = new stream.Readable();

      let c = 97
      rs._read = () => { // 可以带一个参数来获得想要读取的size
        rs.push(String.fromCharCode(c++));
        if (c > 'z'.charCodeAt(0)) rs.push(null);
      }

      let data = ''
      rs.on('data', function(chunk){
        data += chunk; // 这里chunk是Buffer哦！
      })
      rs.on('end', function() {
        data.should.eql('abcdefghijklmnopqrstuvwxyz');
        done();
      })
    })

    /*
    我感觉应该是readable就是一次性读取多少数据，而data的次数对一个push的次数，这里再补充case
    来证实这一想法。
     */
    it('也可以这样来读取readable stream', function(done) {

      let rs = new stream.Readable();

      rs.push('a');
      rs.push('b');
      rs.push('c');
      rs.push(null);

      let data = '';

      rs.on('readable', () => {
        let buf = rs.read();
        data += buf;
      })
      rs.on('end', ()=> {
        should(data).eql('abc');
        done();
      })
    })

    describe('使用Writable stream', () => {
      it('创建writable stream， 定义_write和监听finsh事件', () => {
        let ws = new stream.Writable();
        let data = ''

        // 在 ws上调用write方法会触发此方法
        ws._write = (chunk, enc, next) => { // 数据，编码，next
          data += chunk.toString(enc)
          next();
        }

        // 在ws上调用end方法会触发此事件
        ws.on('finish', () => {
          should(data).eql('床前明月光，疑是地上霜；举头望明月，低头思故乡。')
        })

        let rs = fs.createReadStream(__dirname + '/data/chinese.txt')
        rs.pipe(ws)


      })

    })
  })



})
