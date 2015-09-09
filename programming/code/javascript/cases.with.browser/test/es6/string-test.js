var should = window.Should;


/*
使用firefox nightly来运行测试
*/
describe('String', function() {
    describe('Unicode', function() {

        it('test', function() {
            should(1).eql(1)
        })
/*
code unit, js使用16bit的code unit
code point, code和字符一一对应关系

在UTF-16中，code unit是16bit， code point可能是16bit，也可能是32bit。这样就导致了很多

Basic Multilingual Plan(BMP)， UTF-16中可以使用16bit表示的字符
Supplementary plane， UTF-16中使用32bit表示的字符

*/
        it('code unit 和 code point', function() {
            // 对于non-BMP,hex大于4位，采用{}来通知当做一个处理
            var text0 = '\u{20BB7}'
            var text1 = '𠮷';
            '\u00613'.should.eql('a3') // 会被截断，取四位hex

            // 这种情况是两个unicode组成一个字符， 一个基本字符和一个辅助字符
            var text2 = '\u0061\u0300';
            var text3 = 'à'
            // String.prototype.length是根据code uint来计算的。
            text0.length.should.eql(2)
            text3.length.should.eql(2)

            // text0只有一个unicode字符，charCodeAt取到的字符是没有意义的。

            // text2只有一个字符，但是由一个基本字符，一个辅助字符组成的，
            // 所以charCodeAt(0)取得的第一个字符是有意义的a

            text0.codePointAt(0).should.eql(134071).and.not.eql(text0.charCodeAt(0))
            // 这里说明了这个情况，codePointAt(0)是字符a
            text2.codePointAt(0).should.eql(97).and.eql(text2.charCodeAt(0))


        })

        // 可惜u flag目前浏览器还不支持，没办法使用正则来演示这个问题.
        it('regexp对于unicode的字符匹配可能和想象的就不一样了', function() {
            var text1 = '𠮷';
            // . 只能匹配一个code unit
            /^.$/.test(text1).should.be.false();
            /^..$/.test(text1).should.be.true();
        })

    })
})
