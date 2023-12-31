package resources.testdata

const val TestData_JS_LIB_SAMPLES_UNSUPPORTED: String = """
{
  "errors": [
    { "node": ["foo", "bar"],
      "op": [{"op": "test", "path": "/1e0", "value": "bar"}],
      "message": "test op shouldn't get array element 1" },

    { "node": {"foo": {"bar": [1, 2, 5, 4]}},
      "op": [{"op": "test", "path": "/foo", "value": [1, 2]}],
      "message": "test op should fail" }

  ],
  "ops": [
    { "message": "test against implementation-specific numeric parsing",
      "node": {"1e0": "foo"},
      "op": [{"op": "test", "path": "/1e0", "value": "foo"}],
      "expected": {"1e0": "foo"} },

    { "message": "spurious patch properties",
      "node": {"foo": 1},
      "op": [{"op": "test", "path": "/foo", "value": 1, "spurious": 1}],
      "expected": {"foo": 1} },

    { "node": {"foo": null},
      "op": [{"op": "test", "path": "/foo", "value": null}],
      "message": "null value should be valid obj property" },

    { "node": {"foo": null},
      "op": [{"op": "move", "from": "/foo", "path": "/bar"}],
      "expected": {"bar": null},
      "message": "null value should be valid obj property to be moved" },

    { "node": {"foo": null},
      "op": [{"op": "copy", "from": "/foo", "path": "/bar"}],
      "expected": {"foo": null, "bar": null},
      "message": "null value should be valid obj property to be copied" },

    { "node": {"foo": {"foo": 1, "bar": 2}},
      "op": [{"op": "test", "path": "/foo", "value": {"bar": 2, "foo": 1}}],
      "message": "test should pass despite rearrangement" },

    { "node": {"foo": [{"foo": 1, "bar": 2}]},
      "op": [{"op": "test", "path": "/foo", "value": [{"bar": 2, "foo": 1}]}],
      "message": "test should pass despite (nested) rearrangement" },

    { "node": {"foo": {"bar": [1, 2, 5, 4]}},
      "op": [{"op": "test", "path": "/foo", "value": {"bar": [1, 2, 5, 4]}}],
      "message": "test should pass - no error" },

    { "message": "Whole document",
      "node": { "foo": 1 },
      "op": [{"op": "test", "path": "", "value": {"foo": 1}}],
      "disabled": true },

    { "message": "Empty-string element",
      "node": { "": 1 },
      "op": [{"op": "test", "path": "/", "value": 1}] },

    { "node": {
      "foo": ["bar", "baz"],
      "": 0,
      "a/b": 1,
      "c%d": 2,
      "e^f": 3,
      "g|h": 4,
      "i\\j": 5,
      "k\"l": 6,
      " ": 7,
      "m~n": 8
    },
      "op": [{"op": "test", "path": "/foo", "value": ["bar", "baz"]},
        {"op": "test", "path": "/foo/0", "value": "bar"},
        {"op": "test", "path": "/", "value": 0},
        {"op": "test", "path": "/a~1b", "value": 1},
        {"op": "test", "path": "/c%d", "value": 2},
        {"op": "test", "path": "/e^f", "value": 3},
        {"op": "test", "path": "/g|h", "value": 4},
        {"op": "test", "path":  "/i\\j", "value": 5},
        {"op": "test", "path": "/k\"l", "value": 6},
        {"op": "test", "path": "/ ", "value": 7},
        {"op": "test", "path": "/m~0n", "value": 8}] },

    { "node": {"baz": [{"qux": "hello"}], "bar": 1},
      "op": [{"op": "copy", "from": "/baz/0", "path": "/boo"}],
      "expected": {"baz":[{"qux":"hello"}],"bar":1,"boo":{"qux":"hello"}} },

    { "message": "replacing the root of the document is possible with add",
      "node": {"foo": "bar"},
      "op": [{"op": "add", "path": "", "value": {"baz": "qux"}}],
      "expected": {"baz":"qux"}},
  ]
}
"""