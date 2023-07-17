// Parenthesize the whole initializer
for (let i = ("start" in window ? window.start : 0); i < 9; i++) {
    console.log(i);
}

// Parenthesize the `in` expression
for (let i = ("start" in window) ? window.start : 0; i < 9; i++) {
    console.log(i);
}


for (let i = 0, getI = () => i; i < 3; i++) {
    console.log(getI());
}
// Logs 0, 0, 0

for (let i = 0, getI = () => i; i < 3; i++, getI = () => i) {
    console.log(getI());
}
// Logs 0, 1, 2

for (
    let i = 0, getI = () => i, incrementI = () => i++;
    getI() < 3;
    incrementI()
) {
    console.log(i);
}
// Logs 0, 0, 0


const arr = [1, 2, 3, 4, 5, 6];
for (let l = 0, r = arr.length - 1; l < r; l++, r--) {
    console.log(arr[l], arr[r]);
}
// 1 6
// 2 5
// 3 4
