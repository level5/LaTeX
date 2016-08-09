'use strict'

var api = require('./customAPI');

exports.max = (a, b) =>　a > b ? a : b;
exports.min = exports.max;

exports.asyncCall = (fn) => {
  setTimeout(function(){
    fn('message');
  }, 1000);
}

exports.promise = () => {
  return Promise.resolve("message");
}

exports.hello = () => "hello " + api.get('name') + "!";

var favoriteClub = "inter"
function club() {

  return "inter"
}
