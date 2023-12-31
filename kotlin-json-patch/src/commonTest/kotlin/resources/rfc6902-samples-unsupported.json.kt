package resources.testdata

const val TestData_RFC6902_SAMPLES_UNSUPPORTED: String = """
{
    "errors": [
        {
            "message": "A.9.  Testing a Value: Error",
            "op": [{ "op": "test", "path": "/baz", "value": "bar" }],
            "node": { "baz": "qux" }
        },
        {
            "message": "A.15.  Comparing Strings and Numbers",
            "op": [{"op": "test", "path": "/~01", "value": "10"}],
            "node": {
                "/": 9,
                "~1": 10
            }
        }
    ],
    "ops": [
        {
            "message": "A.8.  Testing a Value: Success",
            "op": [{ "op": "test", "path": "/baz", "value": "qux" },
                { "op": "test", "path": "/foo/1", "value": 2 }],
            "node": {
                "baz": "qux",
                "foo": [ "a", 2, "c" ]
            }
        },
        {
            "message": "A.14.  ~ Escape Ordering",
            "op": [{"op": "test", "path": "/~01", "value": 10}],
            "node": {
                "/": 9,
                "~1": 10
            },
            "expected": {
                "/": 9,
                "~1": 10
            }
        }
    ]
}
"""