# Let's make it click: Why a 2D matrix is **O(n²) space**

Let's use a tiny example first.

Suppose:

```java
int n = 4;
```

We're creating a **4 × 4 matrix**.

That means:

```text
        columns
       0   1   2   3
     ┌───┬───┬───┬───┐
  0  │   │   │   │   │
     ├───┼───┼───┼───┤
  1  │   │   │   │   │
     ├───┼───┼───┼───┤
  2  │   │   │   │   │
     ├───┼───┼───┼───┤
  3  │   │   │   │   │
     └───┴───┴───┴───┘
        ↑
       rows
```

There are:

```text
4 rows
×
4 columns
────────
16 cells
```

So:

**4 × 4 = 16**

---

## 1. Here's the important part

Imagine we write:

```java
int[][] matrix = new int[n][n];
```

If:

```text
n = 4
```

Java needs space for:

```text
4 × 4 = 16 integers
```

So immediately after initialization, we have:

```text
┌───┬───┬───┬───┐
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
└───┴───┴───┴───┘

       16 cells
```

The memory is already claimed.

That's the key idea.

---

# 2. Now suppose we fill the diagonal

We might have:

```java
for (int i = 0; i < n; i++) {
    matrix[i][i] = 1;
}
```

At first:

```text
┌───┬───┬───┬───┐
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
└───┴───┴───┴───┘
```

Then:

```java
matrix[0][0] = 1;
```

```text
┌───┬───┬───┬───┐
│ 1 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
└───┴───┴───┴───┘
```

Then:

```java
matrix[1][1] = 1;
```

```text
┌───┬───┬───┬───┐
│ 1 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 1 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 0 │
└───┴───┴───┴───┘
```

Eventually:

```text
┌───┬───┬───┬───┐
│ 1 │ 0 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 1 │ 0 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 1 │ 0 │
├───┼───┼───┼───┤
│ 0 │ 0 │ 0 │ 1 │
└───┴───┴───┴───┘
```

### Here's the important distinction:

The loop **didn't create those cells**.

They were already there.

The loop simply changed:

```text
0 → 1
```

That's why the loop doesn't add another `O(n²)` amount of memory.

---

# 3. Think of it like a building

Imagine you rent an apartment building with:

```text
4 floors
×
4 rooms per floor
```

That's:

```text
4 × 4 = 16 rooms
```

You then walk through the building and put a chair in one room on each floor.

You haven't rented another building.

You haven't created new rooms.

You're just modifying rooms you already have.

Same idea:

```text
ALLOCATE
    ↓
┌───┬───┬───┬───┐
│   │   │   │   │
├───┼───┼───┼───┤
│   │   │   │   │
├───┼───┼───┼───┤
│   │   │   │   │
├───┼───┼───┼───┤
│   │   │   │   │
└───┴───┴───┴───┘
       16 rooms
           │
           ↓
        MODIFY
           │
           ↓
      some rooms become 1
```

**The building is the memory.**

**The `1`s are just values inside that memory.**

---

# 4. Now let's see why it's n²

Here's the part that makes the Big-O click.

For:

```text
n = 1
```

we have:

```text
1 × 1 = 1
```

```text
[ ]
```

For:

```text
n = 2
```

we have:

```text
2 × 2 = 4
```

```text
[ ][ ]
[ ][ ]
```

For:

```text
n = 3
```

we have:

```text
3 × 3 = 9
```

```text
[ ][ ][ ]
[ ][ ][ ]
[ ][ ][ ]
```

For:

```text
n = 4
```

we have:

```text
4 × 4 = 16
```

```text
[ ][ ][ ][ ]
[ ][ ][ ][ ]
[ ][ ][ ][ ]
[ ][ ][ ][ ]
```

So:

| Input `n` | Rows | Columns | Total cells |
| --------: | ---: | ------: | ----------: |
|         1 |    1 |       1 |       **1** |
|         2 |    2 |       2 |       **4** |
|         3 |    3 |       3 |       **9** |
|         4 |    4 |       4 |      **16** |
|         5 |    5 |       5 |      **25** |
|        10 |   10 |      10 |     **100** |

The pattern is:

```text
n rows
  ×
n columns
  ↓
n × n
  ↓
n²
```

Therefore:

> **Space = O(n²)**

---

# 5. Why does doubling n quadruple the memory?

This is the part that often feels weird.

Suppose:

```text
n = 4
```

Our matrix is:

```text
4 × 4 = 16 cells
```

Now double `n`:

```text
n = 8
```

The matrix becomes:

```text
8 × 8
```

That's:

```text
8 × 8 = 64 cells
```

Compare:

```text
16 → 64
```

That's **4× more memory**.

Why?

Because you doubled **both dimensions**:

```text
      BEFORE             AFTER

      4 columns          8 columns
      ↓                  ↓

    ┌───────┐          ┌───────────────┐
  4 │       │        8 │               │
rows│       │          │               │
    │       │          │               │
    └───────┘          │               │
                       │               │
                       │               │
                       └───────────────┘
```

Mathematically:

```text
Original:

n × n


Double n:

(2n) × (2n)


Multiply:

2n × 2n

= 4n²
```

So doubling `n` makes the memory **4 times larger**.

---

# 6. Compare this with O(n) space

This distinction is really useful.

### Linear:

```text
n = 4

[ ][ ][ ][ ]

4 cells
```

Double n:

```text
n = 8

[ ][ ][ ][ ][ ][ ][ ][ ]

8 cells
```

Memory:

```text
4 → 8

2×
```

That's:

**O(n)**

### Quadratic:

```text
n = 4

[ ][ ][ ][ ]
[ ][ ][ ][ ]
[ ][ ][ ][ ]
[ ][ ][ ][ ]

16 cells
```

Double n:

```text
n = 8

[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]

64 cells
```

Memory:

```text
16 → 64

4×
```

That's:

**O(n²)**

---

# 7. One subtle thing: "allocation is the cost; filling is free"

"Free" here doesn't mean the operation literally takes zero time.

The loop still takes **time** to execute.

For example:

```java
for (int i = 0; i < n; i++) {
    matrix[i][i] = 1;
}
```

This runs `n` times.

So its **time complexity** is:

```text
O(n)
```

But in terms of **additional space**, it doesn't create another data structure.

The matrix already exists.

So:

```text
MEMORY

new int[n][n]
      │
      ↓
┌─────────────────┐
│    n × n cells  │
└─────────────────┘
        │
        │
        ↓
loop changes values
        │
        ↓
NO NEW MATRIX
NO NEW n² BLOCK
```

Therefore the matrix is what determines the space:

```text
n × n = n²

       ↓

Space = O(n²)
```

---

# The mental model

Don't think:

> "There are two loops, therefore O(n²) space."

That's the wrong mental model.

Think:

> **"What data structure are we actually storing?"**

Here we're storing:

```text
        n columns
    ┌───────────────┐
 n  │               │
rows│    MATRIX     │
    │               │
    │               │
    └───────────────┘
```

So:

```text
ROWS × COLUMNS

n × n

= n²
```

And the timeline is:

```text
             INITIALIZATION
                    │
                    ↓
          allocate n × n cells
                    │
                    ↓
             ┌─────────────┐
             │             │
             │   n × n     │
             │   matrix    │
             │             │
             └─────────────┘
                    │
                    ↓
               LOOP RUNS
                    │
                    ↓
             change some
                values
                    │
                    ↓
              same memory
```

### Remember these 4 rules:

1. **`n × n` storage → `O(n²)` space.**
2. **The memory is allocated when the matrix is created.**
3. **Writing values into existing cells doesn't create additional space.**
4. **Double `n` → double the rows AND columns → 4× the cells.**

> **Picture a square.**
>
> If the side becomes 2× longer, the area becomes 4× larger.
>
> That's exactly why an `n × n` matrix uses **O(n²) space**.
# 🧠 O(2ⁿ) SPACE — Let's make it click

Let's use your example:

arr = [a, b, c, d]

We want to generate EVERY possible subset.

Start with:

result = [[]]

So initially:

┌─────────┐
│    []   │
└─────────┘

There is only 1 subset.


## 1. Add `a`

```java
result += [subset + [a] for subset in result]