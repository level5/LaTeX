describe('Event', function(){

  describe('create event', function(){

    it('document.createEvent old-fashioned way', function(done){
      var event = document.createEvent('Event');
      event.initEvent('build', true, true);
      document.addEventListener('build', function(e){
        event.should.equal(e);
        done();
      });
      document.dispatchEvent(event);
    });

    it('click on position', function(done){

      // create an element, and set positioin to x, y
      
      // add to document

      // add a listener to this element

      // elementFromPoint

      // set click event to element

    });
  });
});
