# Space Complexity

The easiest way to picture **space complexity** is:

> **"While my program is running, how much memory does it need?"**

Not how long it takes.

Not how many operations it performs.

**How much memory it needs.**

---

## 1. Start with a normal array

Imagine:

```python
arr = [10, 20, 30, 40, 50]
```

Picture the memory like this:

```text
┌─────┬─────┬─────┬─────┬─────┐
│ 10  │ 20  │ 30  │ 40  │ 50  │
└─────┴─────┴─────┴─────┴─────┘
   0     1     2     3     4
```

There are **5 cells**.

So:

```text
5 elements  →  5 cells
100 elements → 100 cells
1,000 elements → 1,000 cells
```

The memory grows with the size of the input.

Therefore:

```text
Space = O(n)
```

where `n` is the number of elements.

---

# 2. The important part: allocation happens first

This is where people often get confused.

Suppose you create an array with capacity 5:

```python
arr = [None] * 5
```

Immediately, the memory has been allocated:

```text
┌──────┬──────┬──────┬──────┬──────┐
│ None │ None │ None │ None │ None │
└──────┴──────┴──────┴──────┴──────┘
   ↑       ↑       ↑       ↑       ↑
   └────────── 5 cells allocated ─────┘
```

The memory for all 5 cells already exists.

Now:

```python
arr[0] = 10
```

gives:

```text
┌──────┬──────┬──────┬──────┬──────┐
│  10  │ None │ None │ None │ None │
└──────┴──────┴──────┴──────┴──────┘
```

Did we allocate another cell?

**No.**

We simply put `10` into a cell that already existed.

### Key idea

> **Writing data into already-allocated memory does not create additional space.**

---

# 3. Think of it like parking spaces

Imagine you rent a parking lot with 1,000 spaces.

```text
[ ][ ][ ][ ][ ][ ][ ][ ] ... [ ]
<------------- 1,000 ------------>
```

You currently have only 3 cars:

```text
[🚗][🚗][🚗][ ][ ][ ][ ][ ] ... [ ]
```

You still have 1,000 parking spaces.

Putting another car into an empty space does not mean you rented another space.

Arrays work similarly.

```text
Allocate memory
       ↓
┌───┬───┬───┬───┬───┐
│   │   │   │   │   │
└───┴───┴───┴───┴───┘
       ↓
Fill memory
       ↓
┌───┬───┬───┬───┬───┐
│ A │ B │ C │   │   │
└───┴───┴───┴───┴───┘
```

The second step does not require new array space.

---

# 4. What about dynamic arrays?

Now things get interesting.

A dynamic array, such as a Python `list`, can grow.

Imagine the array currently has capacity 4:

```text
Capacity = 4

┌───┬───┬───┬───┐
│ A │ B │ C │ D │
└───┴───┴───┴───┘
```

Now we want to add `E`.

There is no empty cell:

```text
[A][B][C][D]
            ↑
          FULL
```

So the array needs a larger block of memory.

---

# 5. The resize

Imagine the growth strategy doubles the capacity.

We start with:

```text
OLD MEMORY

┌───┬───┬───┬───┐
│ A │ B │ C │ D │
└───┴───┴───┴───┘

Capacity = 4
```

A new block is allocated:

```text
NEW MEMORY

┌───┬───┬───┬───┬───┬───┬───┬───┐
│   │   │   │   │   │   │   │   │
└───┴───┴───┴───┴───┴───┴───┴───┘

Capacity = 8
```

The old elements are copied:

```text
OLD                         NEW

┌───┬───┬───┬───┐          ┌───┬───┬───┬───┬───┬───┬───┬───┐
│ A │ B │ C │ D │  ─────→  │ A │ B │ C │ D │   │   │   │   │
└───┴───┴───┴───┘          └───┴───┴───┴───┴───┴───┴───┴───┘
```

Then `E` can be added:

```text
┌───┬───┬───┬───┬───┬───┬───┬───┐
│ A │ B │ C │ D │ E │   │   │   │
└───┴───┴───┴───┴───┴───┴───┴───┘
```

The capacity went:

```text
4 → 8
```

Eventually:

```text
4 → 8 → 16 → 32 → 64 → ...
```

---

# 6. When does memory actually increase?

Suppose:

```text
Capacity = 8
```

and we currently have:

```text
[A][B][C][D][E]
```

Adding `F`:

```text
BEFORE

[A][B][C][D][E][ ][ ][ ]

              ↓ add F

AFTER

[A][B][C][D][E][F][ ][ ]
```

No new memory was allocated.

We simply used an empty cell.

But eventually:

```text
[A][B][C][D][E][F][G][H]
```

The array is now full:

```text
Capacity = 8
Used     = 8

[A][B][C][D][E][F][G][H]
```

If we add `I`, we need more memory.

```text
OLD: 8 cells

[A][B][C][D][E][F][G][H]

              ↓ RESIZE

NEW: 16 cells

[A][B][C][D][E][F][G][H][ ][ ][ ][ ][ ][ ][ ][ ]
```

So:

> **The memory jump happens when the array is resized, not every time an element is inserted.**

---

# 7. Why does this matter for space complexity?

Because space complexity asks:

> **"As `n` gets bigger, how does the amount of memory needed grow?"**

Imagine:

```text
Elements     Capacity

1            4
2            4
3            4
4            4
5            8
6            8
7            8
8            8
9            16
...
```

The memory grows in jumps:

```text
Capacity

16 |                         ┌────────
   |                         │
 8 |             ┌───────────┘
   |             │
 4 |─────────────┘
   |
   +--------------------------------→
       1  2  3  4  5  6  7  8  9
                   elements
```

Even though it grows in jumps, the overall amount of memory still grows with `n`.

Therefore:

```text
Space = O(n)
```

---

# 8. Input space vs auxiliary space

Now we need to separate two ideas.

Suppose:

```python
def find_max(arr):
    max_value = arr[0]

    for x in arr:
        if x > max_value:
            max_value = x

    return max_value
```

The input is:

```text
arr

┌───┬───┬───┬───┬───┐
│ 4 │ 8 │ 2 │ 9 │ 1 │
└───┴───┴───┴───┴───┘
```

That's the **input space**.

The algorithm also uses:

```text
max_value
x
```

Those are only a fixed number of variables.

```text
arr        → n elements
max_value  → 1 variable
x          → 1 variable
```

So:

```text
Input space      = O(n)
Auxiliary space  = O(1)
```

Total:

```text
O(n) + O(1)
```

We simplify this to:

```text
O(n)
```

---

# 9. What does O(1) space look like?

Consider:

```python
x = 10
y = 20
z = x + y
```

We're only creating a fixed number of variables:

```text
x → [10]

y → [20]

z → [30]
```

Even if the input gets bigger, we're still only using these few variables.

So:

```text
Space = O(1)
```

Think:

```text
O(1) = fixed amount of extra memory
```

---

# 10. What does O(n) space look like?

Now:

```python
result = [0] * n
```

If:

```text
n = 5
```

we need:

```text
┌───┬───┬───┬───┬───┐
│ 0 │ 0 │ 0 │ 0 │ 0 │
└───┴───┴───┴───┴───┘
```

If:

```text
n = 10
```

we need:

```text
┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
│ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │
└───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
```

As `n` doubles, the memory required also grows.

Therefore:

```text
Space = O(n)
```

---

# 11. The mental model you should picture

Whenever you are asked about space complexity, imagine:

```text
             PROGRAM
                │
                ↓
        ┌───────────────┐
        │ What memory   │
        │ does it need? │
        └───────┬───────┘
                ↓
       Does memory stay fixed?
          /              \
        YES              NO
         ↓                ↓
      O(1)           Does it grow
                     with input n?
                         ↓
                        O(n)
```

The main question is:

> **"As the input gets bigger, does my algorithm need more memory?"**

---

# Compact mental model

Remember these:

```text
1. Space complexity = how much memory the algorithm needs.

2. Allocating an array claims its memory.

3. Filling an already-allocated array does NOT create
   additional array space.

4. A dynamic array grows by allocating a larger block
   when its current capacity is full.

5. O(1) space:
   Memory stays roughly fixed as n grows.

6. O(n) space:
   Memory grows proportionally with n.

7. Always ask:
   "What new memory does my algorithm need as n gets bigger?"
```

## The most important picture

```text
ALLOCATE
   ↓
┌───┬───┬───┬───┬───┐
│   │   │   │   │   │
└───┴───┴───┴───┴───┘
   ↓
FILL
   ↓
┌───┬───┬───┬───┬───┐
│ A │ B │ C │   │   │
└───┴───┴───┴───┴───┘

Allocation = claiming memory
Filling     = using memory you already claimed
```

**That distinction is the key to understanding array space complexity.**
