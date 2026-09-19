# O(2ⁿ) SPACE — Let's make it click



Example:

arr = [a, b, c, d]

We want EVERY possible subset.

Start:

```
result = [[]]
```

So:

```
┌─────┐
│  [] │
└─────┘
```
1 subset.


## 1. Add `a`

For the existing subset `[]`, we have TWO choices:

    Don't take a → []
    Take a      → [a]

Now:

    []
    [a]

1 → 2


## 2. Add `b`

Every existing subset gets the same TWO choices:

    []       → []       OR [b]
    [a]      → [a]      OR [a,b]

Now:

    []
    [a]
    [b]
    [a,b]

2 → 4


## 3. Add `c`

Again, every subset splits into TWO:

    []       → []       OR [c]
    [a]      → [a]      OR [a,c]
    [b]      → [b]      OR [b,c]
    [a,b]    → [a,b]    OR [a,b,c]

4 → 8



## 4. Add `d`

8 existing subsets each produce 2 possibilities:

    8 × 2 = 16

So:

    1 → 2 → 4 → 8 → 16

<br>


# 🌳 Picture the tree

Each element creates one YES/NO decision:

                    start
                      │
                     a?
                   /   \
                 NO     YES
                 │       │
                []      [a]
               /  \     /  \
              b?  b?   b?  b?
             ... ...   ... ...

Each level doubles because EVERY node gets two children.

    Level 0:   1
                ↓ ×2
    Level 1:   2
                ↓ ×2
    Level 2:   4
                ↓ ×2
    Level 3:   8
                ↓ ×2
    Level 4:  16

<br>


# 🔢 Why 2ⁿ?

Each element has exactly 2 choices:

    ┌─────────────────┐
    │ DON'T TAKE ITEM │
    └─────────────────┘
             OR
    ┌─────────────────┐
    │    TAKE ITEM    │
    └─────────────────┘

For n elements:

    2 × 2 × 2 × ... × 2
    └────── n times ──────┘

    = 2ⁿ

For 4 elements:

    2⁴
    = 2 × 2 × 2 × 2
    = 16

<br>


# 💾 Why is this SPACE?

The important part is:

    result = ...

We KEEP every subset.

After processing `a`, we keep:

    []
    [a]

After `b`, we keep:

    []
    [a]
    [b]
    [a,b]

After `c`, we keep all 8.

After `d`, we keep all 16.

They are all sitting in memory at the same time:

    result
    ↓
    ┌───────┐
    │  []   │
    │  [a]  │
    │  [b]  │
    │ [a,b] │
    │  [c]  │
    │ [a,c] │
    │  ...  │
    │[a,b,c,d]
    └───────┘
         ↑
      16 subsets

<br>

# 🎯 Mental model

Don't think:

    "There is a complicated loop."

Think:

    EACH ITEM
        ↓
    YES or NO
        ↓
    TWO POSSIBILITIES
        ↓
    EVERY POSSIBILITY branches again
        ↓
    1 → 2 → 4 → 8 → 16 → ...
        ↓
       2ⁿ


The crucial difference for SPACE:

    We don't throw the possibilities away.

    We KEEP them.

Therefore:

    number of stored subsets = 2ⁿ

    → SPACE = O(2ⁿ)

### Remember:

    1 item  → 2 possibilities
    2 items → 4
    3 items → 8
    4 items → 16
    n items → 2ⁿ

**Picture a tree where every input element creates a YES/NO split, and all the resulting branches are kept in memory.**