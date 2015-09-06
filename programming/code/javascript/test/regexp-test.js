var should = require('should')

describe('RegExp', function() {

    describe('RegExp 对象', function(){

        describe('构造函数', function(){

/*
    /pattern/flag
    new RegExp(pattern, [flag])

    flag可以是g, i, m, y的任意组合：

g
global match
i
ignore case
m
multiline; (^和$)匹配每一个行的开始和结束，而不是匹配整个字符串的开始和结束
y(还不能用啊，实验性质的API)
sticky; 在匹配时，从正则表达式的lastIndex property指定的位置开始匹配目标字符串
*/

            it('两种方式来构造正则表达式', function() {

                // /pattern/flag
                (/ab+c/i).test('AbcDe').should.be.true();

                // new RegExp(pattern, [flag])
                new RegExp('ab+c', 'i').test('AbcDe').should.be.true();
            })


            it('g 表示全局匹配', function() {

            })

            it('i 表示忽略大小写', function() {
                /ab+c/.test('ABC').should.be.false();
                /ab+c/i.test('ABC').should.be.true();
            })

            it('m 表示多行匹配，^ $匹配逻辑行的首尾，而不是整个字符串的首尾', function() {
                (/^.*$/m).exec('Bruce Li kicked your\n ass.')[0].should.eql('Bruce Li kicked your');

                // 非多行模式下，^和$匹配的是整个字符串的首尾
                (/^[^]*$/).exec('Bruce Li kicked your\n ass.')[0].should.eql('Bruce Li kicked your\n ass.');
            })

            it('RegExp对象所含有的properties', function() {
                var hostnameRegexp =  /[-a-z0-9]+(\.[-a-z0-9]+)*\.(com|edu|info)/g;

                // flag g是不会包含进去的。
                hostnameRegexp.source.should.eql('[-a-z0-9]+(\\.[-a-z0-9]+)*\\.(com|edu|info)');

                // lastIndex为下次开始匹配的位置，初始值应该是为0吧;如果不带flag g，那么一直为0
                hostnameRegexp.lastIndex.should.eql(0);
            })

            it('使用变量来复用正则表达式', function() {
                var usenameRegexp = /\w[-.\w]*/g;
                var hostnameRegexp =  /[-a-z0-9]+(\.[-a-z0-9]+)*\.(com|edu|info)/g;

                //匹配Email的正则表达式
                var emailRegexp = new RegExp('^' + usenameRegexp.source + '@' + hostnameRegexp.source + '$', 'g');
                emailRegexp.test('hsfhsf_2000@sina.com').should.be.true();
            })

        })

        describe('RegExp API', function() {

            it('String.prototype.replace(regexp|substr, newSubStr|function[, flags])', function() {

                var str = 'Apples are round, and apples are juicy.';
                // flags不是标准化接口，在V8不工作，也就是Node下不工作

                //
                var regexp1 = /Apples/;
                str.replace(regexp1, 'Oranges').should.eql('Oranges are round, and apples are juicy.')

                var regexp2 = /Apples/i;
                // 只替换了第一个匹配的字符串，因为不是g
                str.replace(regexp2, 'Oranges').should.eql('Oranges are round, and apples are juicy.')

                var regexp3 = /Apples/ig;
                // 只替换了第一个匹配的字符串，因为不是g
                str.replace(regexp3, 'Oranges').should.eql('Oranges are round, and Oranges are juicy.')

                // 参数$&代表匹配的字符串
                var regexp4 = /Apples/ig;
                str.replace(regexp4, '$& and oranges').should.eql('Apples and oranges are round, and apples and oranges are juicy.')

                // 参数$n表示匹配的
                var regexp5 = /(\w)pples/g;
                str.replace(regexp5, '$1s').should.eql('As are round, and as are juicy.')

                // $` $' 分别表示匹配字符串前和匹配字符串后的部分
                var regexp6 = /\s+.*\s+/
                'before m after'.replace(regexp6, " $` XXX $' ").should.eql('before before XXX after after')

                // 传入function作为第二个参数的时候，function的返回值会被当做替换的字符串
                var regexp7 = /(a)pples/
                str.replace(regexp7, function(match, p1 /*, ... pn*/, offset, source){
                    match.should.eql('apples');
                    p1.should.eql('a');
                    offset.should.eql(22);
                    source.should.eql(str);
                    return 'oranges'; // 这个值会用来替换匹配到的字符串
                }).should.eql('Apples are round, and oranges are juicy.')
            })

            it('String.prototype.match(regexp)', function() {

                var str = 'Brouce Li kicked your ass.'

                // 没有匹配的时候返回null
                var result = str.match(/^a.*/);
                should(result).be.null();

                // 返回值是一个数组, 不带g的话，返回结果和RegExp.prototype.exec一样
                var regexp2 = /^(b|B)(?:.*?)\b/

                // 原来这些property是一值有的。
                regexp2.source.should.eql('^(b|B)(?:.*?)\\b');
                regexp2.lastIndex.should.eql(0);

                result = str.match(regexp2);

                //
                result[0].should.eql('Brouce'); // 因为这里用了.*?,表示非贪婪匹配，所有只匹配了一个单词

                result[1].should.eql('B');
                should(result[2]).be.undefined(); // (?:x)表示匹配但是不捕获

                result.input.should.eql(str);
                result.index.should.eql(0);

                // 带g的话，则是返回所有匹配的子字符串的数组，capture group不会返回。
                result = str.match(/\b(\w+)\b/g);
                result.should.eql(['Brouce', 'Li', 'kicked', 'your', 'ass'])

            })

            it('String.prototype.search(),类似RegExp.prototype.test', function() {

                // 返回匹配字符串的index

                // 没有匹配就返回 -1
            })

            it('RegExp.prototype.test(str), 测试一个pattern是否能在str中找到', function() {

                // 不是全匹配，而是看看是否可以找到一个子字符串是匹配的
                (/a/).test('abc').should.be.true();
                (/a/).test('bcd').should.be.false();

                // 想匹配整个字符串，可以这样, 使用^和$
                /^[0-9]*$/.test('123a').should.be.false();
                /^[0-9]*$/.test('123').should.be.true();


            })


            it('RegExp.prototype.exec(str)', function() {
                var regexp1 = /brouce\s+(l|g)i.*ass/igm;
                var result = (regexp1).exec('Brouce Li kicked\n your ass.');
                // 因为.不匹配newline，所以不匹配，没有匹配的返回是null
                should(result).be.null();
                // 下一次匹配开始的index
                regexp1.lastIndex.should.eql(0);
                regexp1.ignoreCase.should.be.true();
                regexp1.global.should.be.true();
                regexp1.multiline.should.be.true();
                regexp1.source.should.eql('brouce\\s+(l|g)i.*ass');

                regexp2 = /^brouce\s+(l|g)i.*kicked$/im;
                result = (regexp2).exec('Brouce Li kicked\n your ass.');


                // 不带g flag的时候，lastIndex为0
                regexp2.lastIndex.should.eql(0)

                result.should.be.instanceof(Array);
                // 匹配的结果
                result[0].should.eql('Brouce Li kicked');
                // 第一个括号匹配的内容
                result[1].should.eql('L');

                var regexp3 = /\b\w+\b/ig;
                result = (regexp3).exec('Brouce Li kicked\n your ass.');

                result.should.be.instanceof(Array)
                result.input.should.eql('Brouce Li kicked\n your ass.')
                result.index.should.eql(0) // 匹配字符串的起始位置，0-base
                result[0].should.eql('Brouce');

                // regexp3.$1.should.eql('Brouce') // $1..$9 非标准接口
                regexp3.lastIndex.should.eql(6) // 下一次匹配开始的位置

                result = (regexp3).exec('Brouce Li kicked\n your ass.');
                result.should.be.instanceof(Array)
                result.index.should.eql(7); // 匹配字符串的起始位置，0-base
                result[0].should.eql('Li')  // 0...n
                // regexp3.$1.should.eql('Li') // $1..$9 非标准接口
                regexp3.lastIndex.should.eql(9)
                // ...

            })
        })

        describe('特殊的字符', function() {

            it('. 匹配除了 \\n, \\r, \\u2028, \\u2029之外的所有字符. 多行模式下行为也不会变', function() {
                (/./).test('\r').should.be.false();
                (/./).test('\n').should.be.false();

                // 不能匹配换行
                (/.*/m).exec('Bruce Li kicked your\n ass.')[0].should.eql('Bruce Li kicked your')
            })

            it('\\d, \\D', function(){

                // \d就是等于[0-9]

                // \D就是等于[^0-9]
            })

            it('\\w, \\W', function() {

                // \w就是等于[0-9a-zA-Z_]

                // \W就是等于[^0-9a-zA-Z_]
            })

            it('\\s, \\S', function() {
                // [ \f\n\r\t\v​\u00a0\u1680​\u180e\u2000​-\u200a​\u2028\u2029\u202f\u205f​\u3000\ufeff]
                // 知道空格，\n, \r, \t就差不多了吧。其他了解下



            })

            it('[xyz], [^xyz]', function(){

                // xyz任意值

                // 非xyz的值
            })

            it('[.]...', function() {
                // 匹配字面值
                /[.]/.test('.').should.be.true();
                /[.]/.test('a').should.be.false();
            })

            it('^ $', function() {
                // ^ 行收

                // [^] 所有字符， ^在这里取反

                // $ 行尾
            })

            it('\\b, \\B', function() {

                // \b 单词边界

                /*
                That dang-toolin' #@!%* varmint's cost me $199.95!
                   ^ ^  ^ ^    ^        ^     ^ ^ ^  ^ ^^  ^ ^ ^^
                看上去是\w和其他字符之间就是单词间隔符
                */
                var str = "That dang-toolin' #@!%* varmint's cost me $199.95!";
                var result = str.match(/\b\w+\b/g);
                result.should.eql(['That', 'dang', 'toolin', 'varmint', 's', 'cost', 'me', '199', '95']);
                // \B 非单词边界

            })

            it('x|y,', function() {

            })

        })

        describe('分组', function() {

            it('(x)', function() {
                var result = /(a(B|([a-z])))/.exec('ac')
                console.log(result)

            })

            it('\\n', function(){

            })

            it('(?:x), 非捕获型的括号', function(){
                t1 = /^([-+]?[0-9]+)([CF])$/;
                t2 = /^([-+]?[0-9]+(?:\.[0-9])*)([CF])$/;

                var result1 = t1.exec('5F')
                var result2 = t2.exec('-5.7F')
                // t2的第二个括号没有捕获
                result1[2].should.eql(result2[2]).and.eql('F');
            })
        })

        describe('量词', function() {

            it('x*', function() {

            })

            it('x+', function() {

            })

            it('x*?, x+?', function() {

            })

            it('x?', function() {

            })

            it('x{n}', function() {

            })

            it('x{n,}', function() {

            })

            it('x{n, m}', function() {

            })

            it('x(?=y), x(?!y)', function() {

                // 不支持逆环视
            })
        })
    })

    describe('正则表达式处理汉字', function() {
        describe('code的一些基本概念', function() {


            it('code point', function() {

            })

            it('code unit', function() {

            })

        })
    })

})
