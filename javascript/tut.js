"use strict";

function resolveAfter2Seconds(x) {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(x);
    }, 2000);
  });
}
var aa = { duration: 50, title: "" };

aa.duration ||= 10;
console.log(aa.duration);
// expected output: 50

aa.title ||= "title is empty.";
console.log(aa.title);

const add = async function (x) {
  // async function expression assigned to a variable
  let a = await resolveAfter2Seconds(20);
  let b = await resolveAfter2Seconds(30);
  return x + a + b;
};

add(10).then((v) => {
  console.log(v); // prints 60 after 4 seconds.
});

(async function (x) {
  // async function expression used as an IIFE
  let p_a = resolveAfter2Seconds(20);
  let p_b = resolveAfter2Seconds(30);
  return x + (await p_a) + (await p_b);
})(10).then((v) => {
  console.log(v); // prints 60 after 2 seconds.
});

function myFunction(x, y, z) {}
const args = [0, 1, 2];
myFunction.apply(null, args);

function multiply(multiplier, ...theArgs) {
  return theArgs.map((element) => {
    return multiplier * element;
  });
}

function* func1() {
  yield 42;
}

function* func2() {
  yield* func1();
}

iterator = func2();

console.log(iterator.next().value);

function* foo(index) {
  while (index < 2) {
    yield index;
    index++;
  }
}

var bb = { duration: 50 };

bb.duration ??= 10;
console.log(bb.duration);
// expected output: 50

bb.speed ??= 25;
console.log(bb.speed);

var iterator = foo(0);

console.log(iterator.next().value);
// expected output: 0

console.log(iterator.next().value);

let a, b, rest;
[a, b] = [10, 20];

console.log(a);
// expected output: 10

console.log(b);
// expected output: 20

[a, b, ...rest] = [10, 20, 30, 40, 50];

console.log(rest);

let arr = multiply(2, 15, 25, 42);
console.log(arr); // [30, 50, 84]

const xx = async function* (y) {
  yield Promise.resolve(y * y);
};
xx(6)
  .next()
  .then((res) => console.log(res.value)); //

const myOtherPromise = async function (myResolve, myReject) {
  return new Promise((resolve) => setTimeout(resolve, 2000, "done"));
};
const myPromise = async function (myResolve, myReject) {
  return await myOtherPromise();
};

myPromise().then(
  (value) => console.log(`Then: ${value}`),
  (error) => console.log(`Error: ${error}`)
);
myPromise().finally(() => console.log(`Finally`));

let x = 2 ** 3;
console.log(`This var: ${x}`);

const fruits = ["Banana", "Orange", "Apple", "Mango"];

console.log(`Before unshift: ${fruits}`);
fruits.unshift("Lemon");
console.log(`After unshift: ${fruits}`);
fruits.shift();
console.log(`After shift: ${fruits}`);
fruits.push("Lemon");
console.log(`After push: ${fruits}`);
fruits.pop();
console.log(`After pop: ${fruits}`);
fruits.splice(2, 2, "Lemon", "Kiwi");
console.log(`After splice: ${fruits}`);

const points = [40, 100, 1, 5, 25, 10];
console.log(`Pre sort: ${points}`);
points.sort((a, b) => b - a);
console.log(`Post sort: ${points}`);
console.log(`Max: ${Math.max.apply(null, points)}`);
console.log(`Min: ${Math.min.apply(null, points)}`);
points.forEach((x) => console.log(x));

const lsum = points.reduce((total, value, index, array) => total + value);
console.log(`Reduce it: ${lsum}`);

const allOver18 = points.every((x) => x > 18);
console.log(`allOver18: ${allOver18}`);
const someOver18 = points.some((x) => x > 18);
console.log(`someOver18: ${someOver18}`);

const rand = Math.floor(Math.random() * 100) + 1;
console.log(`Random: ${rand}`);

const person = { fname: "John", lname: "Doe", age: 25 };
for (let x in person) {
  console.log(`${x}: ${person[x]}`);
}
for (let x of points) {
  console.log(`${x}`);
}
let text = "";
list: {
  text += points[0] + "<br>";
  text += points[1] + "<br>";
  break list;
  text += points[2] + "<br>";
  text += points[3] + "<br>";
}
console.log(`Broken out ${text}`);

text = "Visit W3Schools";
let n = text.search(/w3schools/i);
console.log(`Regex search: ${n}`);

y = 5; // Assign 5 to x
console.log(`Hoisted ${y}`);
var y; // Declare x
