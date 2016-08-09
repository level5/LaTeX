import 'should';

describe('Location & URL', () => {


  describe('Location', () => {


    it('visits with window.location or document.location', () => {

      window.location.should.be.Object();
      window.location.should.eql(document.location);

    });


  });


  describe('URL', () => {});


});
