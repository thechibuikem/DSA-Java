# 🌳 BFS QUEUE SPACE — O(n)

Let's use the exact tree you have:

                  1
                /   \
               2     3
              / \   / \
             4   5 6   7
            / \ / \ / \ / \
           8  9 10 11 12 13 14 15

There are:

    n = 15 nodes

 <br>


# 1. What should you picture?

BFS means:

> "Visit everything on this level before moving to the next level."

The QUEUE is simply the line of nodes waiting to be visited.

Think:

    TREE                         QUEUE

      1                           [1]
     / \
    2   3


Visit 1.

Its children, 2 and 3, are added to the queue:

    [2, 3]

So the queue represents:

    "These are the nodes I still need to process."

 <br>

# 2. Watch the queue grow

### Start

    queue = [1]

    size = 1

```text
             1
           /   \
          2     3
```
```
QUEUE → [1]
```
We remove 1:

node = queue.remove(0);

Why?

Because 1 is the FIRST node waiting in line.

Then:
```
queue.add(node.left);
queue.add(node.right);
```
So:
```
[2, 3]
```
#  3. Next level

Now:
```
QUEUE → [2, 3]
          ↑  ↑
          │  │
          └──┴── nodes waiting to be visited
```
Process 2.

Its children are 4 and 5.

Queue becomes:
```
[3, 4, 5]
```
Process 3.

Add 6 and 7:
```
[4, 5, 6, 7]
```
Now look at the queue:
```
QUEUE → [4][5][6][7]
```
          4 nodes

That's the entire next level.

 <br>

# 4. Now the important part

Process 4, 5, 6 and 7.

Each one adds TWO children.

So eventually:
```
QUEUE → [8][9][10][11][12][13][14][15]
```
          8 nodes

Notice what happened:

```
Level 0 → 1 node
Level 1 → 2 nodes
Level 2 → 4 nodes
Level 3 → 8 nodes
```
The queue got biggest when it reached the bottom level.

5. Why does that become O(n)?

Our tree has:

```
n = 15
```

Bottom level:

8 nodes

And:
```
8 ≈ 15 / 2
```
So roughly:
```
bottom level ≈ n/2
```
The queue may therefore hold about:
```
n/2 nodes
```
But Big-O ignores constant factors:
```
O(n/2)
```
becomes:
```
O(n)
```

6. Here's the mental picture

For a balanced binary tree:
```
Level 0       ●                 1
             / \
Level 1     ●   ●               2
           / \ / \
Level 2   ●  ● ●  ●             4
         /\/\/\/\/\/\
Level 3 ● ● ● ● ● ● ● ●         8
```
The number of nodes approximately doubles each level:
```
1 → 2 → 4 → 8
```
But the TOTAL number of nodes is:
```
1 + 2 + 4 + 8 = 15
```
The final level contains:
```
8 / 15 ≈ half the tree
```

7. Why isn't BFS O(n²)?

Because the queue does NOT contain the entire tree at once.

At its largest point, it contains roughly ONE LEVEL:
```
TREE

              1
           /     \
          2       3
        /  \     /  \
       4    5   6    7
      / \  / \ / \  / \
     8  9 10 11 12 13 14 15
     └───────────────────────┘
             ↑
          QUEUE

     about n/2 nodes
```
So:
```
maximum queue size ≈ n/2

O(n/2) = O(n)
```
## Connect it to the Java code
```
queue.add(node.left);
queue.add(node.right);
```
Each node can put its children into the waiting line.

So the queue grows as BFS moves toward the wider levels.

Then:
```
node = queue.remove(0);
```
removes the node at the front.

So the queue is constantly:

```
ADD children
     ↓
[ waiting nodes ]
     ↓
REMOVE front node
     ↓
ADD its children
     ↓
[ waiting nodes ]
```

It is basically a moving "frontier" across the tree.

## Mental model

Don't think:

"BFS visits n nodes, so space is O(n)."

Picture the QUEUE.
```
             TREE
              │
              ↓
      ┌─────────────────┐
      │ nodes waiting   │
      │ to be visited   │
      └─────────────────┘
              │
              ↓
      widest level ≈ n/2
              │
              ↓
         O(n) space
```

Remember:
```
BFS → QUEUE
      ↓
stores a level/frontier
      ↓
widest level of balanced tree ≈ n/2
      ↓
O(n/2)
      ↓
O(n)
```

The key picture: BFS's queue gets widest when it reaches the widest level of the tree.