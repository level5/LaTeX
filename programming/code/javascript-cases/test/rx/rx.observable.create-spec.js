'use strict';


import 'babel-polyfill';

import Rx from 'rx';
import should from 'should';
import sinon from 'sinon';
import _ from 'lodash';

describe('Observable create', () => {

  it('创建了一个observable', () => {

    let observable = Rx.Observable.create((observer) => {
      observer.onNext(1);
      observer.onNext(2);
      observer.onNext(3);
      observer.onCompleted();
    });

    let onNext = sinon.spy();
    let onCompleted = sinon.spy();
    observable.subscribe(
      onNext,
      undefined,
      onCompleted);

    onNext.callCount.should.eql(3);
    onCompleted.callCount.should.eql(1);
  });

  it('每次subscribe，都会导致传入的函数重新被调用一次', () =>  {

    let _subscribe = sinon.spy();

    let observable = Rx.Observable.create(_subscribe);
    observable.subscribe(_.noop);
    _subscribe.callCount.should.eql(1);
    observable.subscribe(_.noop);
    _subscribe.callCount.should.eql(2);
  });

  it('最后会调用函数返回的action', () => {
    let dispose = sinon.spy();
    let observable = Rx.Observable.create((observer) => {
      observer.onCompleted();
      return dispose;
    });

    observable.subscribe(_.noop);

    dispose.callCount.should.eql(1);
  });


  it('generator', () => {


    let it = (function *() {

      console.log('xxxxxx', '1');
      yield 1;

      console.log('xxxxxx', '2');
      yield 2;

      console.log('xxxxxx', '3');
      yield 3;

    })();

    Rx.Observable.from(it).take(1).subscribe(_.noop)

  });

});
