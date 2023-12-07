package resources.testdata

const val TestData_MOVE_UNSUPPORTED: String = """
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
        }
    ],
    "ops": [
    ]
}
"""