# Binary Search

Binary search is a way of finding something in a **sorted** collection without checking every item.

The main idea is:

> **Look at the middle, decide which half is impossible, throw that half away, and repeat.**

---

## Example

Suppose we have:

```text
[2, 5, 8, 12, 16, 23, 38, 45, 56, 61, 72, 84, 91, 99]
```

And we're looking for:

```text
x = 72
```

Because the array is sorted, we can use the position of the middle value to eliminate half of the array.

---

# 1. Give every value an index

```text
index:   0   1   2   3   4   5   6   7   8   9   10  11  12  13
         ↓   ↓   ↓   ↓   ↓   ↓   ↓   ↓   ↓   ↓   ↓   ↓   ↓   ↓
values: [2,  5,  8, 12, 16, 23, 38, 45, 56, 61, 72, 84, 91, 99]
```

Initially:

```text
low  = 0
high = 13
```

This means:

> "The answer could be anywhere from index 0 to index 13."

---

# 2. Find the middle

The simple way to think about finding the middle is:

```python
mid = (low + high) // 2
```

So:

```text
low  = 0
high = 13

mid = (0 + 13) // 2
mid = 6
```

Our middle is index `6`.

```text
index:   0   1   2   3   4   5   6   7   8   9   10  11  12  13
         ●   ●   ●   ●   ●   ●   ●   ●   ●   ●   ●   ●   ●   ●
                                 ↑
                                mid
values: [2,  5,  8, 12, 16, 23, 38, 45, 56, 61, 72, 84, 91, 99]
```

`lst[mid]` is:

```text
38
```

But we're looking for:

```text
72
```

So:

```text
38 < 72
```

---

# 3. Target is bigger than the middle

This is the important part.

We know:

```text
38 < 72
```

And the array is sorted.

Therefore everything **before and including 38** is too small.

```text
index:   0   1   2   3   4   5   6   7   8   9   10  11  12  13
         X   X   X   X   X   X   X   ●   ●   ●   ●   ●   ●   ●
                                 ↑   ↑
                                mid  new low
                                     = 7
```

We've eliminated indexes:

```text
0 → 6
```

The first index that could still contain `72` is:

```text
7
```

And that's why we write:

```python
low = mid + 1
```

It means:

> **"I already checked `mid`, and everything before it is impossible. Start immediately after `mid`."**

---

# 4. Search the remaining half

Now:

```text
low  = 7
high = 13
```

Our remaining search area is:

```text
index:   7   8   9   10  11  12  13
         ●   ●   ●   ●   ●   ●   ●
values: 45  56  61  72  84  91  99
```

Find the middle:

```text
mid = (7 + 13) // 2
mid = 10
```

So:

```text
index:   7   8   9   10  11  12  13
         ●   ●   ●   ●   ●   ●   ●
                     ↑
                    mid
values: 45  56  61  72  84  91  99
                     ↑
                    72
```

We found it!

```text
lst[mid] == x
```

```text
72 == 72
```

So we return:

```python
return mid
```

Result:

```text
10
```

`72` is at index `10`.

---

# What if the target is smaller?

This is the opposite situation.

Suppose:

```text
target = 2
```

Our middle is still:

```text
38
```

So:

```text
2 < 38
```

The number we're looking for must be on the **left**.

```text
index:   0   1   2   3   4   5   6   7   8   9   10  11  12  13
         ●   ●   ●   ●   ●   ●   X   X   X   X   X   X   X   X
                                 ↑
                                mid
```

Everything from `mid` onwards is impossible.

So we move `high` to the position immediately **before** `mid`:

```python
high = mid - 1
```

If:

```text
mid = 6
```

then:

```text
high = 5
```

The important mental model is:

> **"I already checked `mid`, so I don't need it anymore. Move `high` to the position before it."**

---

# Why does this halve the search?

The code isn't magically halving the array.

We're doing this:

```text
               MID
                ↓
[ ?  ?  ?  ?  ? | ?  ?  ?  ?  ? ]
```

We inspect `MID`.

If the target is bigger:

```text
[ X  X  X  X  X | ?  ?  ?  ?  ? ]
                 ↑
                mid
                  ↑
                 low
```

So:

```python
low = mid + 1
```

If the target is smaller:

```text
[ ?  ?  ?  ?  ? | X  X  X  X  X ]
                 ↑
                mid
```

So:

```python
high = mid - 1
```

We're simply **moving a boundary past the middle** and throwing away the impossible side.

---

# The three rules

Memorize the idea, not the syntax.

### Target equals middle

```python
lst[mid] == x
```

> **Found it.**

---

### Target is bigger than middle

```python
lst[mid] < x
```

```python
low = mid + 1
```

> **Target must be to the right.**

---

### Target is smaller than middle

```python
lst[mid] > x
```

```python
high = mid - 1
```

> **Target must be to the left.**

---

# Why `mid + 1` and `mid - 1`?

This is one of the most important details.

If:

```text
mid = 6
```

then the indexes around it are:

```text
        5    6    7
        ↑    ↑    ↑
      before mid after
```

If `mid` is wrong:

```text
mid + 1
```

means:

> **the first position after the middle**

And:

```text
mid - 1
```

means:

> **the first position before the middle**

So:

```python
low = mid + 1
```

means:

> "Start searching immediately after the position I just checked."

And:

```python
high = mid - 1
```

means:

> "Stop searching immediately before the position I just checked."

---

# Finding the middle safely

You will often see:

```python
mid = low + (high - low) // 2
```

Instead of:

```python
mid = (low + high) // 2
```

For example:

```text
low  = 7
high = 13
```

Then:

```text
high - low
= 13 - 7
= 6
```

Half of that:

```text
6 // 2 = 3
```

Then:

```text
low + 3
= 7 + 3
= 10
```

So:

```text
mid = 10
```

The two formulas produce the same middle:

```python
(low + high) // 2

low + (high - low) // 2
```

The second form is commonly preferred in languages such as Java because it avoids a potential integer-overflow problem when `low` and `high` are extremely large.

For understanding the algorithm, it's perfectly fine to think:

```python
mid = (low + high) // 2
```

---

# The mental model

Don't think:

> "Binary search has these weird formulas."

Think:

```text
                 MIDDLE
                    ↓
[──────────────────●──────────────────]
```

Ask:

> **Is my target left or right of here?**

Then move the boundary:

```text
target > middle
        ↓
low = mid + 1
```

or:

```text
target < middle
        ↓
high = mid - 1
```

or:

```text
target == middle
        ↓
FOUND
```

That's binary search.

The code is just a translation of that thought process.

---

# Why is it O(log n)?

Every time we inspect the middle, we eliminate roughly half of the remaining possibilities.

For example:

```text
14 items
   ↓
  7 items
   ↓
  3 items
   ↓
  1 item
```

So instead of potentially checking every item:

```text
14 comparisons
```

we need only around:

```text
log₂(14) ≈ 4
```

comparisons.

That's why binary search is:

```text
O(log n)
```

The crucial requirement is that the data must be **sorted** (or otherwise structured so that one comparison lets you safely eliminate a portion of the search space).
