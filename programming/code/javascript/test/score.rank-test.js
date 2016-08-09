
var should = require('should');
var _ = require('underscore');

function rank(score) {
    var star
    if (score > 89) {
        star = 9
    } else if (score > 74 && score < 90) {
        star = 8
    } else if (score > 59 && score < 75) {
        star = 7
    } else if (score > 44 && score < 60) {
        star = 6
    } else if (score > 29 && score < 45) {
        star = 5
    } else if (score >10 && score < 30) {
        star = 4
    } else if (score > 8 && score < 11) {
        star = 3
    } else if (score > 6 && score < 9) {
        star = 2
    } else if (score < 7) {
        star = 1
    }
    return star
}

function rank2(score) {
    var ranges = {
        9: [90, Infinity],
        8: [75, 90],
        7: [60, 75],
        6: [45, 60],
        5: [30, 45],
        4: [11, 30],
        3: [9, 11],
        2: [7, 9],
        1: [-Infinity, 7]
    }
    var count = _.findKey(ranges, function(range) {
        return range[0] <= score && score < range[1]
    })
    return count >>> 0
}

function rank3(score) {
    return 1 + _.findLastIndex(
        [-Infinity, 7, 9, 11, 30, 45, 60, 75, 90],
        function(val){
            return score >= val;
    })
}

describe('rank test', function() {


    it('test the origin rank', function() {
        rank(0).should.eql(1)
        rank(6).should.eql(1)
        rank(7).should.eql(2)
        rank(8).should.eql(2)
        rank(9).should.eql(3)
        rank(10).should.eql(3)
        rank(11).should.eql(4)
        rank(29).should.eql(4)
        rank(30).should.eql(5)
        rank(44).should.eql(5)
        rank(45).should.eql(6)
        rank(59).should.eql(6)
        rank(60).should.eql(7)
        rank(74).should.eql(7)
        rank(75).should.eql(8)
        rank(89).should.eql(8)
        rank(90).should.eql(9)

        rank(-Infinity).should.eql(1)
        rank(Infinity).should.eql(9)

    })

    it('test the rank refactoring', function() {

        var rank = rank2

        rank(0).should.eql(1)
        rank(6).should.eql(1)
        rank(7).should.eql(2)
        rank(8).should.eql(2)
        rank(9).should.eql(3)
        rank(10).should.eql(3)
        rank(11).should.eql(4)
        rank(29).should.eql(4)
        rank(30).should.eql(5)
        rank(44).should.eql(5)
        rank(45).should.eql(6)
        rank(59).should.eql(6)
        rank(60).should.eql(7)
        rank(74).should.eql(7)
        rank(75).should.eql(8)
        rank(89).should.eql(8)
        rank(90).should.eql(9)

        rank(-Infinity).should.eql(1)
        // rank(Infinity).should.eql(9)

    })


        it('test the rank refactoring I love', function() {

            var rank = rank3

            rank(0).should.eql(1)
            rank(6).should.eql(1)
            rank(7).should.eql(2)
            rank(8).should.eql(2)
            rank(9).should.eql(3)
            rank(10).should.eql(3)
            rank(11).should.eql(4)
            rank(29).should.eql(4)
            rank(30).should.eql(5)
            rank(44).should.eql(5)
            rank(45).should.eql(6)
            rank(59).should.eql(6)
            rank(60).should.eql(7)
            rank(74).should.eql(7)
            rank(75).should.eql(8)
            rank(89).should.eql(8)
            rank(90).should.eql(9)

            rank(-Infinity).should.eql(1)
            rank(Infinity).should.eql(9)

        })

})
