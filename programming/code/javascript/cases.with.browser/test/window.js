var age = 29;

function sayAge() {
    return this.age;
}

describe('Window', function(){

    describe('Global Context', function(){
        it('window should be the global object.', function(){
            window.age.should.equal(29);
            window.sayAge().should.equal(29);
        });
    });

    describe('frameset', function(){
        it('not easy to implement', function());
    });
});