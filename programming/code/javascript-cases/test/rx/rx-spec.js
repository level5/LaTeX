'use strict';

import Rx from 'rx';
import should from 'should';
import sinon from 'sinon';

describe('RxJs', () => {

  it('is a sample example', (done) => {

    let message = 'Hello World';
    Rx.Observable.just(message).subscribe((value) => {
      value.should.eql(message);
      done();
    });

  });

  it('observer pattern', () => {

    function Producer() {
      this.listeners = [];
    }
    Producer.prototype.add = function (listener) {
      this.listeners.push(listener);
    }
    Producer.prototype.remove = function (listener) {
      this.listeners = this.listeners.filter(l => l !== listener);
    }
    Producer.prototype.notify = function (message) {
      this.listeners.forEach(listener => listener.update(message));
    }

    let spy1 = sinon.spy();
    let spy2 = sinon.spy();

    let listner1 = {
      update: spy1
    };
    let listner2 = {
      update: spy2
    };

    let notifier = new Producer();
    notifier.add(listner1);
    notifier.add(listner2);

    let message = 'Hello';
    notifier.notify(message);

    spy1.callCount.should.eql(1);
    spy1.args[0][0].should.eql(message);
    spy2.callCount.should.eql(1);
    spy2.args[0][0].should.eql(message);

  });

  it('iterator pattern', () => {
    // ...
  });

  it('Rx pattern', () => {});


  describe('create observable', () => {

    it('create with create method', (done) => {
      var observable = Rx.Observable.create((observer) => {
        observer.next(1);
        observer.next(2);
        observer.next(3);
        observer.completed();
      }).reduce((acc, x) => acc.concat([x]), []);

      observable.subscribe(e => {
        e.should.eql([1, 2, 3]);
        done();
      })


    });

  });

});
