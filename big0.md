````markdown
# The Big-O Complexity Chart

The main idea is:

> **Big O tells us how fast the amount of work grows as `n` gets bigger.**

Here is the ladder you should picture:

```text
FAST / GOOD
    ↓
O(1)        ────────────────  Constant
O(log n)    ────────────────  Logarithmic
O(n)        ────────────────  Linear
O(n log n)  ────────────────  Linearithmic
O(n²)       ────────────────  Quadratic
O(2ⁿ)       ────────────────  Exponential
O(n!)       ────────────────  Factorial
    ↓
SLOW / EXPENSIVE
````

## 1. O(1) — Constant

The input can grow, but the work stays roughly the same.

```java
int first = arr[0];
```

```text
n = 10       →  1 operation
n = 1,000    →  1 operation
n = 1,000,000 → 1 operation
```

Think:

**"I can get it immediately."**

---

## 2. O(log n) — Logarithmic

Each step throws away a large portion of the problem.

Binary search:

```text
n
↓
n/2
↓
n/4
↓
n/8
↓
...
```

Think:

**"Cut the problem down repeatedly."**

---

## 3. O(n) — Linear

Work grows directly with the input.

```java
for (int i = 0; i < n; i++) {
    System.out.println(arr[i]);
}
```

```text
n = 10    → 10 operations
n = 100   → 100 operations
n = 1000  → 1000 operations
```

Think:

**"One pass through the data."**

---

## 4. O(n log n) — Linearithmic

You usually see this in efficient sorting algorithms.

Think:

```text
O(n) work
   ×
O(log n) levels
   ↓
O(n log n)
```

Examples include Merge Sort and Heap Sort.

---

## 5. O(n²) — Quadratic

Usually comes from comparing many items with many other items.

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        // work
    }
}
```

For every `i`, we do `n` operations:

```text
n × n = n²
```

Think:

**"Everyone checks everyone."**

---

## 6. O(2ⁿ) — Exponential

The amount of work roughly doubles every time `n` increases by 1.

```text
n = 1 → 2
n = 2 → 4
n = 3 → 8
n = 4 → 16
n = 5 → 32
```

This often appears when every item creates **two choices**:

```text
             item
            /    \
         take    don't take
         / \       / \
        ...       ...
```

Think:

**"Every step creates two possibilities."**

---

## 7. O(n!) — Factorial

This grows even faster.

```text
1! = 1
2! = 2
3! = 6
4! = 24
5! = 120
6! = 720
```

It often appears when generating **every possible ordering/permutation**.

Think:

**"Try every possible arrangement."**

---

# 🔥 The most important Big-O rules

### Rule 1 — Ignore constants

```text
O(2n)
```

becomes:

```text
O(n)
```

Why?

Because both grow linearly.

---

### Rule 2 — Keep the fastest-growing term

```text
O(n² + n)
```

becomes:

```text
O(n²)
```

Because as `n` becomes huge:

```text
n² >>> n
```

---

### Rule 3 — Count total work, not just nesting

Don't simply say:

> "There are two loops, so it's O(n²)."

Look at **how many times the innermost work actually runs**.

---

#  The ladder to memorize

```text
O(1)
 ↓
O(log n)
 ↓
O(n)
 ↓
O(n log n)
 ↓
O(n²)
 ↓
O(2ⁿ)
 ↓
O(n!)
```

As you move **down**, growth gets dramatically faster.

### Mental model:

```text
BIG O = "What happens to the amount of work
         when the input gets REALLY BIG?"
```

And for space:

```text
Space complexity
=
input space
+
extra/auxiliary space
```

Sometimes we deliberately use:

```text
MORE TIME  ↔  LESS SPACE
LESS TIME  ↔  MORE SPACE
```

This is called a **time-space tradeoff**.

```


::contentReference[oaicite:0]{index=0}
```
