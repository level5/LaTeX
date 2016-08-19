'use strict';

import Rx from 'rx';
import should from 'should';
import sinon from 'sinon';
import _ from 'lodash';

describe('Operators', () => {


  describe('', () => {

    it('',  () => {

      let stub = sinon.stub();
      stub.returns(1);

      let observable = Rx.Observable.from([1, 2, 3]).map(stub);

      observable.subscribe(_.noop);
      stub.callCount.should.eql(3);
      observable.subscribe(_.noop);
      stub.callCount.should.eql(6);

    });

    it('', () => {

      let stub = sinon.stub();
      stub.returns(1);

      let subject = new Rx.Subject();
      Rx.Observable.from([1, 2, 3]).map(stub).subscribe(subject);

      subject.subscribe(_.noop);
      stub.callCount.should.eql(3);
      subject.subscribe(_.noop);
      stub.callCount.should.eql(3);
    });


    it('',  () => {

      let stub = sinon.stub();
      stub.returns(1);

      // 再读读源码，看看怎么实现只读取全面两个， 苦逼啊
      let observable = Rx.Observable.from([1, 2, 3]).map(stub).take(2);
      observable.subscribe(_.noop);
      stub.callCount.should.eql(2);
      observable.subscribe(_.noop);
      stub.callCount.should.eql(4);

    });

  });


});
