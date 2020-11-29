/**
 * @param {number} n
 */
const OrderedStream = function (n) {
  this.parts = new Array(n);
  this.head = 0;
};

/**
 * @param {number} id
 * @param {string} value
 * @return {string[]}
 */
OrderedStream.prototype.insert = function (id, value) {
  this.parts[id - 1] = value;

  const res = [];
  if (id - 1 === this.head) {
    while (this.parts[this.head]) {
      res.push(this.parts[this.head++]);
    }
  }

  return res;
};

/**
 * Your OrderedStream object will be instantiated and called as such:
 * var obj = new OrderedStream(n)
 * var param_1 = obj.insert(id,value)
 */
