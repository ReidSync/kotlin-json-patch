package resources.testdata

const val TestData_DIFF_UNSUPPORTED: String = """
[
  {
    "message": "similar element is copied instead of added",
    "first": {
      "a": "c"
    },
    "second": {
      "a": "c",
      "d": "c"
    },
    "patch": [
      { "op": "copy", "path": "/d", "from": "/a" }
    ]
  },
  {
    "message": "similar element removed then added is moved instead",
    "first": { "a": "b" },
    "second": { "c": "b" },
    "patch": [
      { "op": "move", "path": "/c", "from": "/a" }
    ]
  }
]
"""