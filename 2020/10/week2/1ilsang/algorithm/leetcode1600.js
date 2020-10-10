/**
 * 1600. Throne Inheritance
 * https://leetcode.com/contest/weekly-contest-208/problems/throne-inheritance/
 * @param {string} kingName
 */
const ThroneInheritance = function(kingName) {
  this.tree = {
    root: kingName,
    [kingName]: []
  };
  this.deadMap = {};
};

/** 
 * @param {string} parentName 
 * @param {string} childName
 * @return {void}
 */
ThroneInheritance.prototype.birth = function(parentName, childName) {
  this.tree[parentName].push(childName);
  this.tree[childName] = [];
};

/** 
 * @param {string} name
 * @return {void}
 */
ThroneInheritance.prototype.death = function(name) {
  this.deadMap[name] = true;
};

/**
 * @return {string[]}
 */
ThroneInheritance.prototype.getInheritanceOrder = function() {
  const answer = [];
  const queue = [this.tree.root];
  while(queue.length > 0) {
    const cur = queue.shift();
    if(!this.deadMap[cur]) answer.push(cur);
    queue.unshift(...this.tree[cur]);
  }
  return answer;
};

/** 
 * Your ThroneInheritance object will be instantiated and called as such:
 * var obj = new ThroneInheritance(kingName)
 * obj.birth(parentName,childName)
 * obj.death(name)
 * var param_3 = obj.getInheritanceOrder()
 */