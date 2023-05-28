// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Working_with_objects#using_object_initializers
const obj = {
  property1: value1, // property name may be an identifier
  2: value2, // or a number
  "property n": value3, // or a string
};

// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/get#defining_a_getter_on_new_objects_in_object_initializers
const obj = {
  log: ["example", "test"],
  get latest() {
    if (this.log.length === 0) return undefined;
    return this.log[this.log.length - 1];
  },
};
console.log(obj.latest); // "test"

// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/get#using_a_computed_property_name
const expr = "foo";

const obj = {
  get [expr]() {
    return "bar";
  },
};

console.log(obj.foo); // "bar"
