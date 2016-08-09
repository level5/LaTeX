'use strict'

var chai = require('chai');
var chaiAsPromised = require("chai-as-promised");
chai.use(chaiAsPromised);
chai.should();

var rewire = require('rewire');

const sinon = require('sinon');

var demo = require('./demo');
var process = require('process');

describe('This is a demo', () => {

  it('test inner method', sinon.test(function() {

    let demo = rewire('./demo');

    let club = demo.__get__('club');
    club().should.eql("inter");

  }));
});
