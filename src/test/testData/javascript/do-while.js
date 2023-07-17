let result = "";
let i = 0;
do {
    i += 1;
    result += `${i} `;
} while (i > 0 && i < 5);
// Despite i === 0 this will still loop as it starts off without the test

console.log(result);
