var foo = {};
var obj = {a : 1, b : 2};
var names = Object.keys(obj);

function f(name) {
    return obj[name];
}

for (var i = 0; i < names.length; i++) {
    var name_obj = {name: names[i]};

    var name2 = name_obj.name; // Split on name_obj.name
    foo[name_obj.name] = f(name2);
}

if (foo.a !== undefined) {
    TAJS_assertEquals(1, foo.a);
}

if (foo.b !== undefined) {
    TAJS_assertEquals(2, foo.b);
}