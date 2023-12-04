package resources.testdata

const val TestData_REPLACE_UNSUPPORTED: String = """
{
    "errors": [
        {
            "op": [{ "op": "replace", "path": "/x/y", "value": 42 }],
            "node": { "x": {} },
            "message": "jsonPatch.noSuchPath"
        }
    ],
    "ops": [
    ]
}
"""