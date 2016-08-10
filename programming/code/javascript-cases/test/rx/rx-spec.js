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


  describe('observable', () => {

    it('create with create method', (done) => {
      var observable = Rx.Observable.create((observer) => {

        observer.onNext(1);
        observer.onNext(2);
        observer.onNext(3);
        observer.onCompleted(); // 表示结束了
      }).reduce((acc, x) => acc.concat([x]), []); // 不知道还有没有更好的方法来合成一个数组

      observable.subscribe(e => {
        e.should.eql([1, 2, 3]);
        done();
      })

    });

    it('create from array-like or iterator with from', () => {

      // ...

    });

    it('create observable from javascript event', () => {

      // ...

    });

    it('create observable from node callback', () => {

      // ...

    });

    it('create observable from callback functions', () => {

      // ...

    });



    it('Observables don’t do anything until at least one Observer subscribes to them', () => {



    });


    describe('onError', () => {

    });

  });

  describe('observer', () => {

    it('create observer with create method', () => {

      let onNext = sinon.spy();
      let onError = sinon.spy();
      let onCompleted = sinon.spy();

      let observer = Rx.Observer.create(
        onNext,
        onError,
        onCompleted
      );

      Rx.Observable.just(32).subscribe(observer);

      onNext.callCount.should.eql(1);
      onNext.args[0][0].should.eql(32);

    });

  });

  describe('Operator', () => {

    it('range', (done) => {

      Rx.Observable.range(1, 3)
        .reduce((acc, x) => acc.concat([x]), [])
        .subscribe(res => {
          res.should.eql([1, 2, 3]);
          done();
        });

    });

    it('interval', (done) => {

      Rx.Observable.interval(1).take(10)
        .reduce((acc, x) => acc.concat([x]), [])
        .subscribe(res => {
          res.should.eql([0, 1, 2, 3, 4, 5, 6, 7, 8, 9]);
          done();
        })

    });

    it('merge', () => {
      // ...
    });


    describe('reduce or scan', () => {});


  });

});
