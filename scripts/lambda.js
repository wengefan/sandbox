const ZERO  = f => s => s;
const ONE   = f => s => f(s);
const TWO   = f => s => f(f(s));
const THREE = f => s => f(f(f(s)));

const toInt = n => n(x => x + 1)(0);

console.log(toInt(ZERO));
console.log(toInt(ONE));
console.log(toInt(TWO));
console.log(toInt(THREE));

