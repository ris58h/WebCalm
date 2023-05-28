// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Functions#rest_parameters
function multiply(multiplier, ...theArgs) {
  return theArgs.map((x) => multiplier * x);
}
