var should = require('should')
var path = require('path')
var os = require('os')


var IS_WIN = os.platform().indexOf('win') == 0



describe('path', function(){

    it('path.normalize(p)', function(){

        var p = path.normalize('/foo/bar//baz/asdf/quux/../././xed/')
        if (IS_WIN)
        {
            p.should.eql('\\foo\\bar\\baz\\asdf\\xed\\')
        }
    })

    it('')
})
