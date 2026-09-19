````markdown
## Common Data Structures — Big-O Cheat Sheet

| Structure | Access | Search | Insert | Delete | Space |
|---|---:|---:|---:|---:|---:|
| **Array** | O(1) | O(n) | O(n) | O(n) | O(n) |
| **Linked List** | O(n) | O(n) | O(1) | O(1) | O(n) |
| **Stack** | O(n) | O(n) | O(1) | O(1) | O(n) |
| **Queue** | O(n) | O(n) | O(1) | O(1) | O(n) |
| **Hash Table** | O(1) | O(1) | O(1) | O(1) | O(n) |
| **BST — Average** | O(log n) | O(log n) | O(log n) | O(log n) | O(n) |
| **BST — Worst / Unbalanced** | O(n) | O(n) | O(n) | O(n) | O(n) |
| **Graph — Adjacency List** | — | O(V + E) | O(1)* | O(1)* | O(V + E) |

> **\* Graph insert/delete depends on exactly what operation you're performing and the implementation.**

###  How to remember it

**Array**
```text
[index] → directly jump there
          ↓
        O(1) access

Search → may check everything
         ↓
        O(n)
````

**Linked List**

```text
A → B → C → D

To reach D:
A → B → C → D
└───────→ O(n)

But inserting/removing a node when you already
have the correct position/node can be O(1).
```

**Stack**

```text
TOP
 ↓
[3] ← insert/delete here
[2]
[1]
```

Think:

**LIFO = Last In, First Out**

Push → O(1)
Pop → O(1)

**Queue**

```text
FRONT                  BACK
  ↓                      ↓
[1] → [2] → [3] → [4]
 ↑                      ↑
remove                 insert
```

Think:

**FIFO = First In, First Out**

Enqueue → O(1)
Dequeue → O(1)

**Hash Table**

```text
key
 ↓
hash function
 ↓
index
 ↓
[value]
```

The hash function lets us jump close to the location directly:

**Average access/search/insert/delete → O(1)**

**BST**

Balanced:

```text
        8
       / \
      4   12
     / \  / \
    2  6 10 14
```

Each comparison roughly cuts the remaining search area in half:

```text
n → n/2 → n/4 → n/8 → ...
```

So:

**O(log n)**

Unbalanced:

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
```

It becomes basically a linked list:

**O(n)**

### Core mental model

```text
ARRAY       → jump by index       → O(1) access
LINKED LIST → follow pointers     → O(n) access
STACK       → work at the top     → O(1) push/pop
QUEUE       → work at the ends    → O(1) enqueue/dequeue
HASH TABLE  → jump using hash     → O(1) average
BST         → eliminate half      → O(log n) if balanced
GRAPH       → visit vertices/edges→ O(V + E)
```

```
```
