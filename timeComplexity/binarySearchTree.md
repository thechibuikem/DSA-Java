# O(log n) in a Balanced Binary Search Tree

## The core idea

Don't memorize:

> "BST search is O(log n)."

Picture this instead:

> **Every comparison lets me throw away a large part of the tree.**

In a balanced BST, each decision takes us roughly halfway closer to the answer.

---

# 1. Start with the actual tree

Suppose we have this balanced BST:

```text
                    8
                 /     \
                4       12
              /  \     /  \
             2    6   10   14
            / \  / \  / \  / \
           1  3 5  7 9 11 13 15
```

We want:

```text
x = 7
```

---

# 2. Start at the root

We begin at `8`.

```text
                    8   ← looking here
                 /     \
                4       12
              /  \     /  \
             2    6   10   14
            / \  / \  / \  / \
           1  3 5  7 9 11 13 15
```

Ask:

```text
Is 7 smaller or larger than 8?

7 < 8
```

Therefore, go left.

But don't think:

> "We just move left."

Think:

> **"Everything on the right is now impossible."**

```text
                    8
                 /     \
                ↓       ✗
               4       12
              /  \     /  \
             2    6   10   14
            / \  / \  / \  / \
           1  3 5  7 9 11 13 15
```

We just eliminated an entire subtree.

---

# 3. Now we're at 4

Our remaining search space is:

```text
                    8
                   /
                  4   ← looking here
                /  \
               2    6
                   / \
                  5   7
```

Compare:

```text
7 > 4
```

Therefore, `7` must be somewhere on the right.

Everything on the left is impossible:

```text
                    8
                   /
                  4
                /  \
               ✗    6
                   / \
                  5   7
```

Again, we've eliminated an entire subtree.

---

# 4. Now we're at 6

```text
                6   ← looking here
               / \
              5   7
```

Compare:

```text
7 > 6
```

So go right:

```text
                6
               / \
              ✗   7   ← found!
```

We're done.

---

# 5. What just happened?

We started with:

```text
15 possible nodes
```

Then each comparison reduced the search space:

```text
15
 ↓
 ~7
 ↓
 ~3
 ↓
 ~1
```

So the important pattern is:

> **Every comparison eliminates roughly half of the remaining possibilities.**

That is the reason logarithms appear.

---

# 6. Why does "half" become `log n`?

Forget trees for a moment.

Imagine I'm thinking of one number between `1` and `16`.

If every question allows you to eliminate half the possibilities:

```text
16 possibilities
        ↓
        8
        ↓
        4
        ↓
        2
        ↓
        1
```

How many times did we divide by 2?

```text
4 times
```

Because:

```text
2 × 2 × 2 × 2 = 16
```

Or:

```text
2⁴ = 16
```

Therefore:

```text
log₂(16) = 4
```

A useful way to think about a logarithm is:

> **"How many times do I have to divide by 2 before I'm down to 1?"**

---

# 7. Now put the tree back

A balanced binary tree grows by doubling the number of nodes at each level:

```text
Level 0:       1

Level 1:       2

Level 2:       4

Level 3:       8

Level 4:      16
```

So:

```text
1 → 2 → 4 → 8 → 16 → 32 → 64 → ...
```

Every additional level lets the tree hold roughly twice as many nodes.

Therefore:

```text
number of levels ≈ log₂(n)
```

And when searching a BST, we only follow **one path downward**.

So:

```text
work ≈ number of levels
     ≈ log₂(n)

Therefore:

O(log n)
```

---

# 8. The mental picture

Don't picture:

```text
BST search = O(log n)
```

Picture:

```text
                 8
              /     \
             4       ✗
           /   \
          2     6
               / \
              ✗   7
```

At every node:

```text
             CURRENT NODE
                  │
           ┌──────┴──────┐
           │             │
       impossible      possible
```

You choose one side.

The other side is eliminated.

So:

```text
15 nodes
   ↓
  ~7
   ↓
  ~3
   ↓
  ~1
```

That is the intuition behind:

```text
O(log n)
```

---

# 9. Connect the idea directly to the code

The search code:

```python
def search(node, x):
    while node:
        if x == node.val:
            return node

        elif x < node.val:
            node = node.left

        else:
            node = node.right

    return None
```

## `if x == node.val`

```python
if x == node.val:
    return node
```

This means:

> **"We found the answer. Stop."**

For example:

```text
        8
       / \
      4   12

x = 8
```

We check once:

```text
8 == 8
```

Done.

---

## `elif x < node.val`

```python
elif x < node.val:
    node = node.left
```

Suppose:

```text
x = 7

        8
       / \
      ↓   ✗
```

Because:

```text
7 < 8
```

Everything to the right of `8` is impossible.

Therefore:

```python
node = node.left
```

means:

> **"Continue searching only in the part of the tree where the answer could still exist."**

---

## `else`

```python
else:
    node = node.right
```

Suppose we're at `4`:

```text
        4
       / \
      ✗   ↓
```

Because:

```text
7 > 4
```

Everything on the left is impossible.

So we continue into the right subtree.

---

# 10. Why balance matters

Now compare the balanced tree:

```text
        8
       / \
      4   12
     / \  / \
    2  6 10 14
   / \
  1   3
```

with a skewed BST:

```text
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
           \
            7
             \
              8
```

Both are valid BSTs.

But the second one doesn't really give us a "half" to eliminate.

Searching for `8` becomes:

```text
1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
```

We may have to visit every node.

---

# 11. Balanced vs skewed

### Balanced

```text
15 → ~7 → ~3 → ~1
      ↑
   roughly half
```

The search space keeps shrinking quickly.

Therefore:

```text
height ≈ log n

search ≈ O(log n)
```

### Skewed

```text
15 → 14 → 13 → 12 → ... → 1
```

The tree behaves almost like a linked list.

Therefore:

```text
height ≈ n

search ≈ O(n)
```

---

# 12. Best, average, and worst case

There is an important correction to remember.

For **searching** a BST:

```text
Best case:     O(1)
Average case:  O(log n)   ← assuming a reasonably balanced tree
Worst case:    O(n)       ← if the tree is skewed
```

## Why is the best case O(1)?

Because the target could be the root.

```text
        8   ← x = 8
       / \
      4   12
```

We check one node:

```text
8 == 8
```

Done.

It doesn't matter whether the tree contains:

```text
3 nodes
1,000 nodes
1,000,000 nodes
```

We still only checked one node.

Therefore:

```text
Best case = O(1)
```

---

# 13. Important interview distinction

You may hear:

> "Searching a balanced BST is O(log n)."

That's usually describing the **height-bound search time** of the balanced tree.

But if the interviewer specifically asks:

> "What is the best-case time complexity of searching a BST?"

The precise answer is:

```text
O(1)
```

Because the best possible situation is finding the target at the root.

A useful full answer is:

```text
BST search:

Best:     O(1)
Average:  O(log n)   if reasonably balanced
Worst:    O(n)       if skewed
```

If the BST is **guaranteed balanced**, then the worst-case search is:

```text
O(log n)
```

---

# 14. One subtle Big-O detail

Technically, you might encounter someone saying:

> "O(1) is also O(log n)."

This is mathematically true because Big-O means an **upper bound**.

But when an interview asks for the **best-case complexity**, they normally want the tightest useful description.

So say:

```text
Best case = O(1)
```

Not:

```text
Best case = O(log n)
```

---

# The mental model

Remember these four things:

### 1. BST search follows ONE path

```text
root
  ↓
child
  ↓
child
  ↓
child
```

### 2. Each comparison eliminates a subtree

```text
        8
       / \
    search ✗
```

### 3. In a balanced tree, the remaining search space roughly halves

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

That's why:

```text
O(log n)
```

### 4. But the best case can finish immediately

```text
        8  ← target
```

One comparison:

```text
O(1)
```

---

# Final cheat sheet

```text
                    BST SEARCH
                         │
          ┌──────────────┼──────────────┐
          │              │              │
       BEST           AVERAGE         WORST
          │              │              │
        O(1)          O(log n)          O(n)
          │              │              │
     target is       reasonably       skewed
      at root         balanced          tree
```

For a **guaranteed balanced BST**:

```text
Best case:   O(1)
Worst case:  O(log n)
```

The key idea behind `O(log n)`:

> **You're not searching every node. You're repeatedly throwing away about half of the remaining search space.**

And the key idea behind `O(1)`:

> **Sometimes you don't need to search at all — you check the root and immediately find the answer.**
 ,,