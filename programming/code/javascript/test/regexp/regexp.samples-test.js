var should = require('should')

var fs = require('fs')

describe('例子', function() {

    it('变量名', function() {
        // 只包含字母数字下划线，而且第一个字符不能是数字
        var regexp = /[a-zA-Z_][a-zA-Z0-9_]*/;
    })

    it('引号内的字符串', function() {
        var regexp = /"[^"]*"/
        // 更复杂的定义是允许
    })

    it('空白行', function() {

        var regexp = /^\s*$/
    })

    it('替换^，可以在行首插入想要的字符串', function () {
        'Li kicked your ass.'.replace(/^/, 'Bruce ').should.eql('Bruce Li kicked your ass.');

        // 同样也可以通过$来插入到尾部
        'Bruce Li kicked you'.replace(/$/, 'r ass.').should.eql('Bruce Li kicked your ass.')
    })

    it("Jeffs替换为Jeff's", function(){
        // 因为(?=)不会占用字符
        'Take Jeffs car'.replace(/\bJeffs\b/, "Jeff's").should.eql("Take Jeff's car");
        'Take Jeffs car'.replace(/\b(Jeff)(s)\b/, "$1'$2").should.eql("Take Jeff's car");
        //这里只匹配了Jeff,因为(?=)不会占用字符
        'Take Jeffs car'.replace(/\bJeff(?=s\b)/, "Jeff'").should.eql("Take Jeff's car");
        // 如果支持逆环视的话，可以同时使用环视和逆环视

    })

    it('匹配日期 例如: Jan 31', function() {

        var shouldMatch = ["Jan 01", "Jan 1", "Jan 21", "Jan 29", "Jan 11", "Jan 19", "Jan 31"]
        var shouldNotMatch = ["Jan 00", "Jan 33"]


    })

    it('123456789替换成123,456,789， 使用环视', function(){
        // (?=...) {?!...} lookahead
        // (?:...) match but does not capture
        var regexp = /(\d)(?=(?:\d\d\d)+(?!\d))/g;
        '123456789'.replace(regexp, '$1,').should.eql('123,456,789');

        'I have 123456789 yuan.'.replace(regexp, '$1,').should.eql('I have 123,456,789 yuan.');

        'tone of 12345HZ'.replace(regexp, '$1,').should.eql('tone of 12,345HZ');
    })


    it('123456789替换成123,456,789，使用环视:方案2', function(){

        // 这里不是嵌套lookahead，而是匹配后面有一个非数字或者单词边界，感觉还是上面的方案更好
        var regexp = /(\d)(?=(?:\d\d\d)+(\D|\b))/g;

        '123456789'.replace(regexp, '$1,').should.eql('123,456,789');

        'I have 123456789 yuan.'.replace(regexp, '$1,').should.eql('I have 123,456,789 yuan.');

        'tone of 12345HZ'.replace(regexp, '$1,').should.eql('tone of 12,345HZ');
    })

    it('/^\s*$/mg效果是怎么样的？', function() {

        var str = 'line1\nline2\n\n\n\t \nline3';
        /**/
        str.replace(/^[ \t]*$/mg, '<p>').should.eql('line1\nline2\n<p>\n<p>\n<p>\nline3');
        str.replace(/^\s*$/mg, '<p>').should.eql('line1\nline2\n<p>\nline3');
        // [^\s]

        var str = 'line1\nline2\n\n\n\nline3';

/*
这里因为$匹配的是\n之前和最后的位置,^匹配的是最开始和\n之后的位置

line1\nline2\n\n\n\nline3
              ^   ^
              第一次匹配从这开始,匹配了\n\n,到最后一个\n结束；
                  第二次匹配是从这里开始，匹配了0个字符，只是匹配了这个位置。
*/
        str.replace(/^\s*$/mg, '<p>').should.eql('line1\nline2\n<p><p>\nline3');
    })

    it('删除重复单词问题', function() {

    })
})
