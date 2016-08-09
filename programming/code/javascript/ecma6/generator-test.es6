// 这里要保留，用来支持generator，想办法看看是不是可以解决掉
import Polyfill from 'babel-polyfill'
import should from 'should'

describe('Generator', function(){

  it('创建一个iterator', () => {
    // 跟在function之后的 * 表示这是一个generator
    function * createIterator() {
      yield 1;
      yield 2;
      yield 3;
    }

    let it = createIterator();
    should(it.next().value).eql(1)
    should(it.next().value).eql(2)
    should(it.next().value).eql(3)
    // 最后的结果就是undefined了
    should(it.next().value).eql(undefined)
  })

  it('再来一个iterator', () => {
    function *createIterator(items) {
      for(let i = 0; i < items.length; i++ ) {
        yield items[i]
      }
    }

    let it = createIterator([1, 2, 3])
    should(it.next()).eql({value: 1, done: false})
    should(it.next()).eql({value: 2, done: false})
    should(it.next()).eql({value: 3, done: false})
    should(it.next()).eql({value: undefined, done: true})
  })

  it('for-of 语法', () => {

    let values = [1, 2, 3]
    let i = 0
    for( let num of values) {
      should(num).eql(values[i])
      i++
    }

    // 这是因为 数组会生成一个generator
    let it = values[Symbol.iterator]()
    console.log(it)
    should(it.next()).eql({value: 1, done: false})
    should(it.next()).eql({value: 2, done: false})
    should(it.next()).eql({value: 3, done: false})
    should(it.next()).eql({value: undefined, done: true})
  })

  it('定义自己的对象来支持 for-of', () => {
    let collection = {
      items: [],
      *[Symbol.iterator]() {
        // 这里的this是个隐患
        for( let item of this.items) {
          yield item;
        }
      }
    }

    collection.items.push(1);
    collection.items.push(2);
    collection.items.push(3);

    let i = 0
    for( let num of collection) {
      should(num).eql(collection.items[i])
      i++
    }
  })

  it('spread 操作', () => {
    let arr1 = [1, 2, 3]
    let arr2 = [4, 5, 6]

    should([...arr1, ...arr2]).eql([1, 2, 3, 4, 5, 6])
  })

  it('给iterator传入参数', () => {
    function *createIterator() {
      let first = yield 1;
      let second = yield first + 2;
      yield second + 3;
    }

    let it = createIterator()

    // 第一次调用，执行到函数的第一行 yiled，所以这个时候不接受参数，
    should(it.next().value).eql(1)
    // 第二次调用的时候，继续从yield 1开始执行，这个时候传入的值付给了first
    // 然后一直执行到第二行的yield first + 2,所以等于 4 + 2 = 6
    should(it.next(4).value).eql(6)
    // 第三次的传入的值被赋给了second,然后yiled second + 3 = 5 + 3 = 8
    should(it.next(5).value).eql(8)
  })

  it('iterator中抛异常', () => {
    function * createIterator() {
      let first = yield 1
      let second
      try {
        second = yield first + 2
      } catch( ex) {
        second = 6
      }
      yield second + 3
    }

    let it = createIterator()

    should(it.next().value).eql(1)
    should(it.next(4).value).eql(6)
    should(it.throw(new Error('Boom')).value).eql(9);
    should(it.next().value).be.undefined
  })
})
