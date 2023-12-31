package resources.testdata

const val TestData_DIFF: String = """
[
    {
        "message": "empty patch if no changes",
        "first": "hello",
        "second": "hello",
        "patch": []
    },
    {
        "message": "array members appended use special end-of-array pointer",
        "first": [ 1, 2, 3 ],
        "second": [ 1, 2, 3, 4, 5 ],
        "patch": [
            { "op": "add", "path": "/-", "value": 4 },
            { "op": "add", "path": "/-", "value": 5 }
        ]
    },
    {
        "message": "array members are correctly deleted",
        "first": [ 1, 2, 3 ],
        "second": [ 1 ],
        "patch": [
            { "op": "remove", "path": "/1" },
            { "op": "remove", "path": "/1" }
        ]
    },
    {
        "message": "single object member is deleted",
        "first": { "a": "b", "c": "d" },
        "second": { "a": "b" },
        "patch": [
            { "op": "remove", "path": "/c" }
        ]
    },
    {
        "message": "added object members are in natural order",
        "first": { "a": 1 },
        "second": { "a": 1, "c": 2, "b": 3, "d": 4 },
        "patch": [
            { "op": "add", "path": "/b", "value": 3 },
            { "op": "add", "path": "/c", "value": 2 },
            { "op": "add", "path": "/d", "value": 4 }
        ]
    },
    {
        "message": "single object value change is replaced",
        "first": { "a": null },
        "second": { "a": 6 },
        "patch": [
            { "op": "replace", "path": "/a", "value": 6 }
        ]
    },
    {
        "message": "full value replacement is accounted for",
        "first": [ 1, 2, 3 ],
        "second": { "hello": "world" },
        "patch": [
            { "op": "replace", "path": "", "value": { "hello": "world" } }
        ]
    },
    {
        "message": "embedded object addition/replacement works",
        "first": {
            "a": "b",
            "c": {
                "d": "e"
            }
        },
        "second": {
            "a": "b",
            "c": {
                "d": 1,
                "e": "f"
            }
        },
        "patch": [
            { "op": "add", "path": "/c/e", "value": "f" },
            { "op": "replace", "path": "/c/d", "value": 1 }
        ]
    },
    {
        "message": "embedded array addition/replacement works",
        "first": {
            "a": [ 1, 2, 3 ]
        },
        "second": {
            "a": [ "b", 2, 3, 4 ]
        },
        "patch": [
            { "op": "replace", "path": "/a/0", "value": "b" },
            { "op": "add", "path": "/a/-", "value": 4 }
        ]
    },
    {
        "message": "embedded object addition/replacement works (#2)",
        "first": [ { "a": "b" }, "foo", { "bar": null } ],
        "second": [ { "a": "b", "c": "d" }, "foo", { "bar": "baz" } ],
        "patch": [
            { "op": "add", "path": "/0/c", "value": "d" },
            { "op": "replace", "path": "/2/bar", "value": "baz" }
        ]
    },
    {
        "message": "embedded array addition/replacement works (#2)",
        "first": [ 1, [ 2, 3 ], 4 ],
        "second": [ "x", [ 2, 3, "y" ], 4 ],
        "patch": [
            { "op": "replace", "path": "/0", "value": "x" },
            { "op": "add", "path": "/1/-", "value": "y" }
        ]
    }
]
"""