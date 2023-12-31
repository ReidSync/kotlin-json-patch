package resources.testdata

const val TestData_SAMPLE: String = """
[
    {
        "first": { "a": 1 },
        "second": { "b": 2 }
    },
    {
        "first": { "a": null },
        "second": { "b": 1 }
    },
    {
        "first": {},
        "second": {}
    },
    {
        "first": { "a": 0.1 },
        "second": { "b": 0.1 }
    },
    {
        "first": {},
        "second": {
            "a": "b"
        }
    },
    {
        "first": {
            "a": "b"
        },
        "second": {}
    },
    {
        "first": {
            "a": "b"
        },
        "second": {
            "a": "c"
        }
    },
    {
        "first": [],
        "second": [
            "a"
        ]
    },
    {
        "first": [
            "hello",
            "world"
        ],
        "second": [
            "hello",
            "world!"
        ]
    },
    {
        "first": {
            "a": "b",
            "c": [
                "d"
            ]
        },
        "second": {
            "a": "b",
            "c": [
                "d",
                "e"
            ]
        }
    },
    {
        "first": [
            1,
            2,
            3,
            4
        ],
        "second": [
            0,
            2,
            3
        ]
    },
    {
        "first": [
            "a",
            {
                "b": "c"
            },
            {
                "d": [
                    1,
                    2
                ]
            }
        ],
        "second": [
            "x",
            {
                "b": 1
            },
            {
                "d": [
                    1,
                    3,
                    ""
                ]
            },
            null
        ]
    },
    {
        "first": {
            "b": "a"
        },
        "second": {
            "c": "a"
        }
    },
    {
        "first": {
            "b": [
                1,
                2
            ]
        },
        "second": {
            "c": [
                1,
                2
            ]
        }
    },
    {
        "first": [
            0,
            1,
            2
        ],
        "second": [
            1,
            2,
            0
        ]
    },
    {
        "first": [
            0,
            1,
            2,
            3,
            4,
            5
        ],
        "second": [
            1,
            3,
            4,
            0,
            5
        ]
    },
    {
        "first": [
            0,
            1,
            2,
            3,
            4,
            5,
            6,
            7
        ],
        "second": [
            3,
            6,
            4,
            5,
            7
        ]
    },
    {
        "first": {
            "b": [
                0,
                1,
                2,
                3
            ],
            "c": [
                1
            ]
        },
        "second": {
            "b": [
                1,
                3
            ],
            "c": [
                0,
                1
            ]
        }
    },
    {
        "first": {
            "b": [
                0,
                1,
                2,
                3
            ],
            "c": [
                1
            ],
            "d": []
        },
        "second": {
            "b": [
                1,
                3
            ],
            "c": [
                2,
                1
            ],
            "d": [
                0
            ]
        }
    },
    {
        "first": {
            "a": 0,
            "b": [
                1,
                2
            ]
        },
        "second": {
            "b": [
                1,
                2,
                0
            ]
        }
    },
    {
        "first": {
            "b": [
                0,
                1,
                2
            ]
        },
        "second": {
            "b": [
                1,
                2
            ],
            "c": 0
        }
    },
    {
        "first": {
            "b": [
                0,
                1,
                3,
                4,
                5
            ]
        },
        "second": {
            "b": [
                1,
                2,
                3,
                5
            ],
            "c": 0
        }
    },
    {
        "first": {
            "b": [
                0,
                1,
                3,
                4,
                5
            ]
        },
        "second": {
            "b": [
                1,
                2,
                3,
                5
            ],
            "c": 0,
            "d": 4
        }
    },
    {
        "first": {
            "b": [
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8
            ]
        },
        "second": {
            "b": [
                1,
                6,
                2,
                3,
                5,
                7,
                0,
                8
            ],
            "c": 4
        }
    },
    {
        "first": {
            "b": [
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8
            ]
        },
        "second": {
            "b": [
                1,
                3,
                6,
                4,
                5,
                7,
                8
            ],
            "c": 2
        }
    },
    {
        "first": {
            "b": [
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8
            ]
        },
        "second": {
            "b": [
                1,
                3,
                6,
                4,
                5,
                7,
                0,
                8
            ],
            "c": 2
        }
    },
    {
        "first": {},
        "second": {
            "a": 1,
            "b": 1
        },
        "patch": [
            {
                "op": "ADD",
                "path": "[b]",
                "value": 1
            },
            {
                "op": "ADD",
                "path": "[a]",
                "value": 1
            }
        ]
    },
    {
        "first": {},
        "second": {
            "a": {
                "a": 1
            },
            "b": {
                "a": 1
            }
        }
    },
    {
        "first": [],
        "second": [
            [
                0
            ],
            [
                0
            ]
        ]
    },
    {
        "first": [
            "eol"
        ],
        "second": [
            {
                "a": 1
            },
            {
                "a": 1
            },
            [],
            [],
            [
                0
            ],
            [
                0
            ],
            "eol"
        ]
    },
    {
        "first": [
            1,
            2
        ],
        "second": [
            2,
            1
        ]
    },
    {
        "first": [
            {
                "name": "a"
            },
            {
                "name": "b"
            },
            {
                "name": "c"
            }
        ],
        "second": [
            {
                "name": "b"
            }
        ]
    },
    {
        "first": [
            1,
            2,
            3,
            4,
            5,
            {
                "0": "0"
            }
        ],
        "second": [
            1,
            2,
            4,
            5,
            {
                "a": "0"
            }
        ]
    },
    {
        "first": [
            "a",
            "b",
            "c",
            "d",
            "e"
        ],
        "second": [
            "e",
            "a",
            "f",
            "c",
            "d",
            "b"
        ]
    },
    {
        "first": [
            0,
            1,
            2,
            3,
            4,
            5
        ],
        "second": [
            1,
            3,
            4,
            0,
            5
        ]
    },

    {
        "first": {},
        "second": {}
    },
    {
        "first": {
            "foo": 1
        },
        "second": {
            "foo": 1
        }
    },
    {
        "first": {
            "foo": 1,
            "bar": 2
        },
        "second": {
            "bar": 2,
            "foo": 1
        }
    },
    {
        "first": [
            {
                "foo": 1,
                "bar": 2
            }
        ],
        "second": [
            {
                "bar": 2,
                "foo": 1
            }
        ]
    },
    {
        "first": {
            "foo": {
                "foo": 1,
                "bar": 2
            }
        },
        "second": {
            "foo": {
                "bar": 2,
                "foo": 1
            }
        }
    },
    {
        "first": {
            "foo": null
        },
        "second": {
            "foo": 1
        }
    },
    {
        "first": [],
        "second": [
            "foo"
        ]
    },
    {
        "first": [
            "foo"
        ],
        "second": [
            "foo"
        ]
    },
    {
        "first": {},
        "second": {
            "foo": "1"
        }
    },
    {
        "first": {},
        "second": {
            "foo": 1
        }
    },
    {
        "first": {},
        "second": {
            "": 1
        }
    },
    {
        "first": {
            "foo": 1
        },
        "second": {
            "foo": 1,
            "bar": [
                1,
                2
            ]
        }
    },
    {
        "first": {
            "foo": 1,
            "baz": [
                {
                    "qux": "hello"
                }
            ]
        },
        "second": {
            "foo": 1,
            "baz": [
                {
                    "qux": "hello",
                    "foo": "world"
                }
            ]
        }
    },
    {
        "first": {
            "foo": 1
        },
        "second": {
            "foo": 1,
            "bar": true
        }
    },
    {
        "first": {
            "foo": 1
        },
        "second": {
            "foo": 1,
            "bar": false
        }
    },
    {
        "first": {
            "foo": 1
        },
        
        "second": {
            "foo": 1,
            "bar": null
        }
    },
    {
        "first": {
            "foo": 1
        },
       
        "second": {
            "0": "bar",
            "foo": 1
        }
    },
    {
        "first": [
            "foo"
        ],
        
        "second": [
            "foo",
            "bar"
        ]
    },
    {
        "first": [
            "foo",
            "sil"
        ],
       
        "second": [
            "foo",
            "bar",
            "sil"
        ]
    },
    {
        "first": [
            "foo",
            "sil"
        ],
        
        "second": [
            "bar",
            "foo",
            "sil"
        ]
    },
    {
        "first": [
            "foo",
            "sil"
        ],
       
        "second": [
            "foo",
            "sil",
            "bar"
        ]
    },
    {
        "first": {
            "1e0": "foo"
        },
        
        "second": {
            "1e0": "foo"
        }
    },
    {
        "first": [
            "foo",
            "sil"
        ],
        "second": [
            "foo",
            [
                "bar",
                "baz"
            ],
            "sil"
        ]
    },
    {
        "first": {
            "foo": 1,
            "bar": [
                1,
                2,
                3,
                4
            ]
        },
       
        "second": {
            "foo": 1
        }
    },
    {
        "first": {
            "foo": 1,
            "baz": [
                {
                    "qux": "hello"
                }
            ]
        },
       
        "second": {
            "foo": 1,
            "baz": [
                {}
            ]
        }
    },
    {
        "first": {
            "foo": 1,
            "baz": [
                {
                    "qux": "hello"
                }
            ]
        },
       
        "second": {
            "foo": [
                1,
                2,
                3,
                4
            ],
            "baz": [
                {
                    "qux": "hello"
                }
            ]
        }
    },
    {
        "first": {
            "foo": [
                1,
                2,
                3,
                4
            ],
            "baz": [
                {
                    "qux": "hello"
                }
            ]
        },
       
        "second": {
            "foo": [
                1,
                2,
                3,
                4
            ],
            "baz": [
                {
                    "qux": "world"
                }
            ]
        }
    },
    {
        "first": [
            "foo"
        ],
       
        "second": [
            "bar"
        ]
    },
    {
        "first": [
            ""
        ],
       
        "second": [
            0
        ]
    },
    {
        "first": [
            ""
        ],
       
        "second": [
            true
        ]
    },
    {
        "first": [
            ""
        ],
       
        "second": [
            false
        ]
    },
    {
        "first": [
            ""
        ],
       
        "second": [
            null
        ]
    },
    {
        "first": [
            "foo",
            "sil"
        ],
       
        "second": [
            "foo",
            [
                "bar",
                "baz"
            ]
        ]
    },
    {
        "first": {
            "foo": "bar"
        },
        
        "second": {
            "baz": "qux"
        }
    },
    {
        "first": {
            "foo": 1
        },
       
        "second": {
            "foo": 1
        }
    },
    {
        "first": {
            "foo": 1
        },
        
        "second": {
            "foo": 1
        }
    },
    {
        "first": {
            "foo": 1,
            "baz": [
                {
                    "qux": "hello"
                }
            ]
        },
        
        "second": {
            "baz": [
                {
                    "qux": "hello"
                }
            ],
            "bar": 1
        }
    },
    {
        "first": {
            "baz": [
                {
                    "qux": "hello"
                }
            ],
            "bar": 1
        },
        
        "second": {
            "baz": [
                {},
                "hello"
            ],
            "bar": 1
        }
    },
    {
        "first": {
            "baz": [
                {
                    "qux": "hello"
                }
            ],
            "bar": 1
        },
       
        "second": {
            "baz": [
                {
                    "qux": "hello"
                }
            ],
            "bar": 1,
            "boo": {
                "qux": "hello"
            }
        }
    },
    {
        "first": {
            "foo": "bar"
        },
       
        "second": {
            "baz": "qux"
        }
    },
    {
        "first": [
            1,
            2
        ],
       
        "second": [
            1,
            2,
            {
                "foo": [
                    "bar",
                    "baz"
                ]
            }
        ]
    },
    {
        "first": [
            1,
            2,
            [
                3,
                [
                    4,
                    5
                ]
            ]
        ],
        
        "second": [
            1,
            2,
            [
                3,
                [
                    4,
                    5,
                    {
                        "foo": [
                            "bar",
                            "baz"
                        ]
                    }
                ]
            ]
        ]
    },
    {
        "first": [
            1,
            2,
            3,
            4
        ],
       
        "second": [
            2,
            3,
            4
        ]
    },
    {
        "first": [
            1,
            2,
            3,
            4
        ],
        
        "second": [
            1,
            3
        ]
    },
     {
        "first":{"a":{"b/c/d":"i m here"}},
        "second" :{"a":{"b/c/d":"i m not here"}}
     },
     {
                "first": {"b":[0,1,2,3]},
                "second": {"b":[1,3],"c":0}
     },
     {
                 "first": {
                     "c_i": [
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 419,
                                     "nm": "Children"
                                 },
                                 {
                                     "id": 420,
                                     "nm": "General"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 4390,
                                     "nm": "Teens"
                                 },
                                 {
                                     "id": 5796,
                                     "nm": "Fantasy"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 4390,
                                     "nm": "Teens"
                                 },
                                 {
                                     "id": 5800,
                                     "nm": "Horror And Ghost Stories"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 419,
                                     "nm": "Children"
                                 },
                                 {
                                     "id": 420,
                                     "nm": "General"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 4390,
                                     "nm": "Teens"
                                 },
                                 {
                                     "id": 5796,
                                     "nm": "Fantasy"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 4390,
                                     "nm": "Teens"
                                 },
                                 {
                                     "id": 5800,
                                     "nm": "Horror And Ghost Stories"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 419,
                                     "nm": "Children"
                                 },
                                 {
                                     "id": 7991,
                                     "nm": "Children Literature"
                                 },
                                 {
                                     "id": 7993,
                                     "nm": "Family"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 4390,
                                     "nm": "Teens"
                                 },
                                 {
                                     "id": 5796,
                                     "nm": "Fantasy"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 4390,
                                     "nm": "Teens"
                                 },
                                 {
                                     "id": 5800,
                                     "nm": "Horror And Ghost Stories"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 419,
                                     "nm": "Children"
                                 },
                                 {
                                     "id": 7991,
                                     "nm": "Children Literature"
                                 },
                                 {
                                     "id": 7993,
                                     "nm": "Family"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 4390,
                                     "nm": "Teens"
                                 },
                                 {
                                     "id": 5796,
                                     "nm": "Fantasy"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 4390,
                                     "nm": "Teens"
                                 },
                                 {
                                     "id": 5800,
                                     "nm": "Horror And Ghost Stories"
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "id": 1,
                                     "nm": "Books_Tree"
                                 },
                                 {
                                     "id": 419,
                                     "nm": "Children"
                                 },
                                 {
                                     "id": 7991,
                                     "nm": "Children Literature"
                                 },
                                 {
                                     "id": 7993,
                                     "nm": "Family"
                                 }
                             ]
                         }
                     ]
                 },
                 "second": {
                     "c_i": [
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Teens",
                                     "id": 4390
                                 },
                                 {
                                     "nm": "Fantasy",
                                     "id": 5796
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Teens",
                                     "id": 4390
                                 },
                                 {
                                     "nm": "Horror And Ghost Stories",
                                     "id": 5800
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Children",
                                     "id": 419
                                 },
                                 {
                                     "nm": "Children Literature",
                                     "id": 7991
                                 },
                                 {
                                     "nm": "Family",
                                     "id": 7993
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Teens",
                                     "id": 4390
                                 },
                                 {
                                     "nm": "Fantasy",
                                     "id": 5796
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Teens",
                                     "id": 4390
                                 },
                                 {
                                     "nm": "Horror And Ghost Stories",
                                     "id": 5800
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Children",
                                     "id": 419
                                 },
                                 {
                                     "nm": "Children Literature",
                                     "id": 7991
                                 },
                                 {
                                     "nm": "Family",
                                     "id": 7993
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Children",
                                     "id": 419
                                 },
                                 {
                                     "nm": "General",
                                     "id": 420
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Teens",
                                     "id": 4390
                                 },
                                 {
                                     "nm": "Fantasy",
                                     "id": 5796
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Teens",
                                     "id": 4390
                                 },
                                 {
                                     "nm": "Horror And Ghost Stories",
                                     "id": 5800
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Children",
                                     "id": 419
                                 },
                                 {
                                     "nm": "General",
                                     "id": 420
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Teens",
                                     "id": 4390
                                 },
                                 {
                                     "nm": "Fantasy",
                                     "id": 5796
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Teens",
                                     "id": 4390
                                 },
                                 {
                                     "nm": "Horror And Ghost Stories",
                                     "id": 5800
                                 }
                             ]
                         },
                         {
                             "c_n_i": [
                                 {
                                     "nm": "Books_Tree",
                                     "id": 1
                                 },
                                 {
                                     "nm": "Children",
                                     "id": 419
                                 },
                                 {
                                     "nm": "Children Literature",
                                     "id": 7991
                                 },
                                 {
                                     "nm": "Family",
                                     "id": 7993
                                 }
                             ]
                         }
                     ]
                 }
     },
     {
             "first": {"b":[{"b":[2,3,4,5]},{"c":1},{"d":1},{"e":1},{"f":1}]},
             "second": {"b":[{"b":1},{"c":1},{"d":1},{"e":1},{"f":[1,2,3,4,5]}]}
    },
    {
            "first": {"a":[{"b":[{"c":[{"k1":"v1"},{"k2":"v2"},{"k3":"v3"},{"k4":"v4"},{"k5":"v5"}]}]}]},
            "second": {"a":[{"b":[{"c":[{"k1":"v1"},{"k3":"v3"},{"k5":"v5"},{"k2":"v2"}]}]}]}
    },
    {
            "first":[{"name":"winters","country":["aus","nz","sl","rsa","wi","eng"]},{"name":"winters","country":["india","aus","nz","sl"]},{"name":"autumn","country":["aus","nz","sl","rsa","wi"]},{"name":"winters","country":["aus","nz","sl","rsa","wi","eng"]},{"name":"summers","country":["nz","sl","rsa","wi","eng"]},{"name":"autumn","country":["aus","nz","sl","rsa","wi","eng"]},{"name":"rainy","country":["nz","sl"]}],
            "second":[{"name":"winters","country":["india","aus","nz","sl","rsa"]},{"name":"summers","country":["nz","sl","rsa","wi","eng"]},{"name":"autumn","country":["nz","sl","rsa","wi"]},{"name":"summers","country":["india","aus","nz","sl","rsa","wi","eng"]},{"name":"autumn","country":["aus","nz","sl","rsa","wi","eng"]},{"name":"winters","country":["india","aus","nz","sl","rsa","wi","eng"]},{"name":"spring","country":["india","aus","nz","sl"]},{"name":"summers","country":["sl"]},{"name":"autumn","country":["sl","rsa","wi","eng"]}]
    },
    {
          "first":[],
          "second":[]
    },
    {
          "first":[{"age":8,"country":["sl","rsa","wi","eng"]},{"age":9,"country":["aus","nz","sl"]},{"age":9,"country":["india","aus","nz","sl"]},{"age":8,"country":["sl"]},{"age":2,"country":["nz","sl","rsa","wi"]},{"age":7,"country":["nz","sl","rsa"]}],
          "second":[{"age":10,"country":["sl"]},{"age":6,"country":["india","aus","nz","sl","rsa"]},{"age":1,"country":["sl","rsa","wi","eng"]},{"age":8,"country":["sl","rsa","wi"]},{"age":9,"country":["sl","rsa","wi"]},{"age":9,"country":["india","aus","nz","sl"]},{"age":5,"country":["aus","nz","sl","rsa","wi","eng"]},{"age":4,"country":["sl","rsa","wi","eng"]},{"age":9,"country":["india","aus","nz","sl"]}]

    },
     {
    "first":[{"friends":"male","age":2,"name":"summers","gender":"male","country":["nz","sl","rsa","wi","eng"]},{"friends":"male","age":5,"name":"spring","gender":"male","country":["india","aus","nz","sl","rsa","wi","eng"]},{"friends":"male","age":9,"name":"spring","gender":"male","country":["india","aus","nz","sl","rsa"]},{"friends":"male","age":10,"name":"summers","gender":"female","country":["nz","sl","rsa","wi","eng"]}],

    "second":[{"friends":"male","age":8,"name":"spring","gender":"female","country":["aus","nz","sl"]},{"friends":"female","age":6,"name":"summers","gender":"male","country":["india","aus","nz","sl","rsa"]},{"friends":"male","age":10,"name":"summers","gender":"female","country":["nz","sl","rsa","wi","eng"]},{"friends":"female","age":5,"name":"spring","gender":"female","country":["nz","sl"]},{"friends":"female","age":1,"name":"summers","gender":"male","country":["aus","nz","sl"]},{"friends":"male","age":2,"name":"summers","gender":"male","country":["nz","sl","rsa","wi","eng"]},{"friends":"female","age":3,"name":"spring","gender":"female","country":["india","aus","nz","sl","rsa"]},{"friends":"female","age":1,"name":"summers","gender":"female","country":["nz","sl","rsa","wi"]},{"friends":"male","age":6,"name":"rainy","gender":"male","country":["aus","nz","sl","rsa","wi","eng"]}]
    },
    {
       "first":{"compare":{"":"a"},"tags":{}},
       "second":{"compare":{"":"b"},"tags":{"a":"b"}}
    }
]
"""