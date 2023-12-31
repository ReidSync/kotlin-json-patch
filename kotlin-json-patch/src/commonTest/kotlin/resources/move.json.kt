package resources.testdata

const val TestData_MOVE: String = """
{
    "errors": [
        {
            "op": [{ "op": "move", "from": "/a", "path": "/a/b" }],
            "node": {},
            "message": "jsonPatch.noSuchPath"
        },
        {
            "op": [{ "op": "move", "from": "/a", "path": "/b/c" }],
            "node": { "a": "b" },
            "message": "jsonPatch.noSuchParent"
        },
        {
            "op": [{ "op": "move", "path": "/b/c" }],
            "node": { "a": "b" },
            "message": "Missing from field"
        }
    ],
    "ops": [
        {
            "op": [{ "op": "move", "from": "/x/a", "path": "/x/b" }],
            "node": { "x": { "a": "helo" } },
            "expected": { "x": { "b": "helo" } }
        },
        {
            "op": [{ "op": "move", "from": "/x/a", "path": "/x/a" }],
            "node": { "x": { "a": "helo" } },
            "expected": { "x": { "a": "helo" } }
        },
        {
            "op": [{ "op": "move", "from": "/0", "path": "/0/x" }],
            "node": [ "victim", {}, {} ],
            "expected": [ { "x": "victim" }, {} ]
        },
        {
            "op": [{ "op": "move", "from": "/0", "path": "/-" }],
            "node": [ 0, 1, 2 ],
            "expected": [ 1, 2, 0 ]
        },
        {
            "op": [{ "op": "move", "from": "/a", "path": "/b/2" }],
            "node": { "a": "helo", "b": [ 1, 2, 3, 4 ] },
            "expected": { "b": [ 1, 2, "helo", 3, 4 ] }
        }
    ]
}
"""