package resources.testdata

const val TestData_ADD_UNSUPPORTED: String = """
{
  "errors": [
    {
      "op": [{ "op": "add", "path": "/a/b/c", "value": 1 }],
      "node": { "a": "b" },
      "message": "jsonPatch.noSuchParent"
    },
    {
      "op": [{ "op": "add", "path": "/~1", "value": 1 }],
      "node": [],
      "message": "jsonPatch.notAnIndex"
    },
    {
      "op": [{ "op": "add", "path": "/3", "value": 1 }],
      "node": [ 1, 2 ],
      "message": "jsonPatch.noSuchIndex"
    },
    {
      "op": [{ "op": "add", "path": "/-2", "value": 1 }],
      "node": [ 1, 2 ],
      "message": "jsonPatch.noSuchIndex"
    },
    {
      "op": [{ "op": "add", "path": "/foo/f", "value": "bar" }],
      "node": { "foo": "bar" },
      "message": "jsonPatch.parentNotContainer"
    }

  ],
  "ops": [
    {
      "op": [{ "op": "add", "path": "", "value": null }],
      "node": {},
      "expected": null
    }
  ]
}
"""