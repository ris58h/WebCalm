function factorial(n) {
  if (n === 0) {
    return 1;
  } else {
    return n * factorial(n - 1);
  }
};

function fibonacci(n) {(n <= 2 ? 1 : fibonacci(n - 1) + fibonacci(n - 2));}

function foo() {
  foo();
}
