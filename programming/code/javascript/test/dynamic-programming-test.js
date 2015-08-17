// programming 是规划，计划的意思
// 动态规划


// 递归实现的斐波拉契

function fibonacci(n)
{
  if (n == 0) return 0;
  if (n == 1) return 1;
  return fibonacci(n-1) + fibonacci(n-2);
}

console.log('fib(9) =', fibonacci(9))

// 因为子问题会叠加，会对叠加子问题多次重复求解。
//那么不如对子问题求一次结果，然后记录在表中.
function cacheFib(n) {
  var cache = [];
  cache[0] = 0;
  cache[1] = 1;
  for (var i = 2; i <= n; i++)
  {
    cache[i] = cache[i - 2] + cache[i - 1];
  }
  return cache[n];
}

console.log('fib(9) with cache =', cacheFib(9))

// 更容易一点的写法是
function fibC(n) {
  if (typeof fibC.cache[n] == 'undefined')
  {
    fibC.cache[n] = fibC(n-2) + fibC(n-1);
  }
  return fibC.cache[n];
}
fibC.cache = [];
fibC.cache[0] = 0;
fibC.cache[1] = 1;

console.log('fib(9) with cache =', fibC(9))

//但是，实际上，对于斐波拉契来说，并不需要保存所有子集。只需要保存最后两个子问题的解就好了

function fib(n) {
  if (n < 2) return n;
  var p1 = 0; p2 = 1;
  var i = 2, cur;
  while (i <= n)
  {
    cur = p1 + p2;
    p1 = p2;
    p2 = cur;
    i++;
  }
  return p2;
}

console.log('fib(9) = ', fib(9))



// 例子1： 给定一排硬币， 面值均为正整数，c1, c2, c3, ... cn。选取两两不相邻的硬币，使得
// 值最大

var coins = [0, 5, 1, 2, 10, 6, 2]; // 为了计算方便，coins[0]排除

function maxValue(cs, n) {
  if (n == 0) return 0;
  if (n == 1) return cs[1];
  var fn_1 = maxValue(cs, n - 1)
  return Math.max(maxValue(cs, n - 1), cs[n] + maxValue(cs, n - 2));
}
console.log('max value of', coins, "is", maxValue(coins, 6))

// 结构就类似于斐波拉契数列， 只是相加变成了求最大值

// 省略

// 例子2：需要找零n，一堆 d1 < d2 < ... < dn的硬币，怎么使用最少的硬币

var charges = [0, 1, 3, 4];

// 解法就是： F(0) = 0, F(n) = min{F(n-dj)} + 1
function charge(cs, n) {
  if (n == 0) return 0;
  var count = Infinity;
  var i = 1;
  while (i <= cs.length && n >= cs[i])
  {
    count = Math.min(count, charge(cs, n-cs[i]));
    i++;
  }
  return count + 1;
}

console.log("min charge from", charges, "for 6 is ", charge(charges, 6))

// 同样，从小到大求F
function cacheCharge(cs, n) {
  var cache = [];
  cache[0] = 0;
  var i = 1;
  while (i <= n)
  {
    var tmp = Infinity, j = 1;
    while (j <= cs.length && i >= cs[j])
    {
      tmp = Math.min(tmp, cache[i - cs[j]]);
      j++;
    }
    cache[i] = tmp + 1;
    i++;
  }
  return cache[n];
}

console.log("min charge from", charges, "for 6 is ", cacheCharge(charges, 6))
