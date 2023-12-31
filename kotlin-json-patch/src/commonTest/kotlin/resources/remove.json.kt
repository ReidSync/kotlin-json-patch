package resources.testdata

const val TestData_REMOVE: String = """
{
    "errors": [
        {
            "op": [{ "op": "remove", "path": "/x/y" }],
            "node": { "x": "just a string" }
        },
        {
            "op": [{ "op": "remove", "path": "/x/1" }],
            "node": { "x": [ "single" ] }
        }
    ],
    "ops": [
        {
            "op": [{ "op": "remove", "path": "/x/y" }],
            "node": { "x": { "a": "b", "y": {} } },
            "expected": { "x": { "a": "b" } }
        },
        {
            "op": [{ "op": "remove", "path": "/0/2" }],
            "node": [ [ "a", "b", "c"], "d", "e" ],
            "expected": [ [ "a", "b" ], "d", "e" ]
        },
        {
            "op": [{ "op": "remove", "path": "/x/0" }],
            "node": { "x": [ "y", "z" ], "foo": "bar" },
            "expected": { "x": [ "z" ], "foo": "bar" }
        }
    ]
}
"""