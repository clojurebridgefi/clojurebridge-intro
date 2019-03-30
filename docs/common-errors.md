# Common syntax errors in Clojure (v1.10)

## Stack trace format

Errors manifest as Java stack traces. The format usually is following:

```
Exception in thread "main" Syntax error compiling at (foo.clj:7:1).
        at clojure.lang.Compiler.analyze(Compiler.java:6808)
        at clojure.lang.Compiler.analyze(Compiler.java:6745)
        ...
Caused by: java.lang.RuntimeException: No such var: s/foobar
        at clojure.lang.Util.runtimeException(Util.java:221)
        at clojure.lang.Compiler.resolveIn(Compiler.java:7387)
        ...
```

In the stack traces the most useful information usually is the error message of RuntimeException, here `No such var: s/foobar`.

On the first row, the row and column of the syntax error is mentioned. Here it is `(foo.clj:7:1)` which means first character on 7th row in `foo.clj` file.

# RuntimeExceptions

- `Unmatched delimiter: )`
  - One closing parenthesis too much

- `EOF while reading, starting at line 1`
  - Missing closing parenthesis

- `java.lang.Long cannot be cast to clojure.lang.IFn`
  - First symbol after parenthesis is not a function but a long.
  - If you want to create a list, use quote: `'(1 2 3)`

- `java.lang.String cannot be cast to clojure.lang.IFn`
  - First symbol after parenthesis is not a function but a string.

- `Caused by: clojure.lang.ArityException: Wrong number of args (1) passed to: user/test`
  - Wrong number of arguments given to a function.

- `Unable to resolve symbol: foo in this context`
  - fsunction name not found

- `No such var: s/foo`
  - The name `s/foo` is not bound to a variable or function.

- `Don't know how to create ISeq from: clojure.core$inc`
  - Wrong order of arguments for e.g. `map`, `filter` or `reduce`

- `Unable to resolve symbol: bar' in this context`
  - Single quotes used for a string instead of double quotes
