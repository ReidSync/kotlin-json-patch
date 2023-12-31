package resources.testdata

const val TestData_RFC6902_SAMPLES: String = """
{
    "errors": [
        {
            "message": "A.13.  Invalid JSON Patch Document",
            "op": [{ "op": "add", "path": "/baz", "value": "qux", "op": "remove" }],
            "node": { "foo": "bar" },
            "disabled": true
        },
        {
            "message": "A.12.  Adding to a Nonexistent Target",
            "op": [{ "op": "add", "path": "/baz/bat", "value": "qux" }],
            "node": { "foo": "bar" }
        }
    ],
    "ops": [
        {
            "message": "A.1.  Adding an Object Member",
            "op": [{ "op": "add", "path": "/baz", "value": "qux" }],
            "node": { "foo": "bar" },
            "expected": {
                "baz": "qux",
                "foo": "bar"
            }
        },
        {
            "message": "A.2.  Adding an Array Element",
            "op": [{ "op": "add", "path": "/foo/1", "value": "qux" }],
            "node": { "foo": [ "bar", "baz" ] },
            "expected": { "foo": [ "bar", "qux", "baz" ] }
        },
        {
            "message": "A.3.  Removing an Object Member",
            "op": [ { "op": "remove", "path": "/baz" }],
            "node": {
                "baz": "qux",
                "foo": "bar"
            },
            "expected": { "foo": "bar" }
        },
        {
            "message": "A.4.  Removing an Array Element",
            "op": [{ "op": "remove", "path": "/foo/1" }],
            "node": { "foo": [ "bar", "qux", "baz" ] },
            "expected": { "foo": [ "bar", "baz" ] }
        },
        {
            "message": "A.5.  Replacing a Value",
            "op": [{ "op": "replace", "path": "/baz", "value": "boo" }],
            "node": {
                "baz": "qux",
                "foo": "bar"
            },
            "expected": {
                "baz": "boo",
                "foo": "bar"
            }
        },
        {
            "message": "A.6.  Moving a Value",
            "op": [{ "op": "move", "from": "/foo/waldo", "path": "/qux/thud" }],
            "node": {
                "foo": {
                    "bar": "baz",
                    "waldo": "fred"
                },
                "qux": {
                    "corge": "grault"
                }
            },
            "expected": {
                "foo": {
                    "bar": "baz"
                },
                "qux": {
                    "corge": "grault",
                    "thud": "fred"
                }
            }
        },
        {
            "message": "A.7.  Moving an Array Element",
            "op": [{ "op": "move", "from": "/foo/1", "path": "/foo/3" }],
            "node": { "foo": [ "all", "grass", "cows", "eat" ] },
            "expected": { "foo": [ "all", "cows", "eat", "grass" ] }
        },
        {
            "message": "A.10.  Adding a Nested Member Object",
            "op": [{ "op": "add", "path": "/child", "value": { "grandchild": { } } }],
            "node": { "foo": "bar" },
            "expected": {
                "foo": "bar",
                "child": {
                    "grandchild": {
                    }
                }
            }
        },
        {
            "message": "A.11.  Ignoring Unrecognized Elements",
            "op": [{ "op": "add", "path": "/baz", "value": "qux", "xyz": 123 }],
            "node": { "foo": "bar" },
            "expected": {
                "foo": "bar",
                "baz": "qux"
            }
        },
        {
            "message": "A.16.  Adding an Array Value",
            "op": [{ "op": "add", "path": "/foo/-", "value": ["abc", "def"] }],
            "node": { "foo": ["bar"] },
            "expected": { "foo": ["bar", ["abc", "def"]] }
        }
    ]
}
"""