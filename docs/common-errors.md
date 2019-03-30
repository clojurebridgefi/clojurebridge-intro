# Common errors in Clojure (v1.10)

```
Unmatched delimiter: )
```

One closing parenthesis too much

```
Exception in thread "main" Syntax error reading source at (foo.clj:2:1).
...
Caused by: java.lang.RuntimeException: EOF while reading, starting at line 1
```

Missing closing parenthesis

```
Exception in thread "main" Syntax error reading source at (foo.clj:1:19).
...
Caused by: java.lang.RuntimeException: Unmatched delimiter: )
```

One closing parenthesis too much

```
Caused by: java.lang.ClassCastException: java.lang.Long cannot be cast to clojure.lang.IFn
```

First symbol after parenthesis is not a function but a long.

```
Caused by: java.lang.ClassCastException: java.lang.String cannot be cast to clojure.lang.IFn
```

First symbol after parenthesis is not a function but a string.


```
Caused by: clojure.lang.ArityException: Wrong number of args (1) passed to: user/test
```

Wrong number of arguments given to a function.
