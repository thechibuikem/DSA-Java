# Let's make this click

You have this Python code:

def reverse_list(arr):
    reversed_arr = []

    for i in range(len(arr) - 1, -1, -1):
        reversed_arr.append(arr[i])

    return reversed_arr

Input:

arr = [7, 12, 3, 9, 5]

n = 5


## 1. First, forget Big-O for a moment

Picture `reversed_arr` as a row of memory slots.

When we write:

    reversed_arr = []

the important idea is:

> We are preparing a block of memory where elements can be stored.

The simulator says the initial capacity is 4.

So mentally picture:

    reversed_arr

    ┌─────┬─────┬─────┬─────┐
    │     │     │     │     │
    └─────┴─────┴─────┴─────┘
       0     1     2     3

    capacity = 4
    elements = 0

There are 4 available slots.

The slots exist even though they are currently empty.

That's what the statement means by:

> `reversed_arr = []` is not free.

It's not saying the list contains 4 elements.

It means:

    MEMORY HAS BEEN RESERVED
    ┌─────┬─────┬─────┬─────┐
    │  ?  │  ?  │  ?  │  ?  │
    └─────┴─────┴─────┴─────┘
       0     1     2     3

    4 cells allocated
    0 cells actually containing our elements


# 2. Now watch what append actually does

Our input is:

    arr = [7, 12, 3, 9, 5]

We reverse it.

So the elements arrive in this order:

    5 → 9 → 3 → 12 → 7


## First append

    append(5)

We have an empty slot.

    ┌─────┬─────┬─────┬─────┐
    │  5  │     │     │     │
    └─────┴─────┴─────┴─────┘
       ↑
       5 goes here

Nothing needs to be allocated.

Why?

Because the memory was already reserved.

    capacity = 4
    used     = 1

No resize.


## Second append

    append(9)

    ┌─────┬─────┬─────┬─────┐
    │  5  │  9  │     │     │
    └─────┴─────┴─────┴─────┘
       0     1     2     3

    capacity = 4
    used     = 2

Still no new allocation.


## Third append

    append(3)

    ┌─────┬─────┬─────┬─────┐
    │  5  │  9  │  3  │     │
    └─────┴─────┴─────┴─────┘

    capacity = 4
    used     = 3

Still no new allocation.


## Fourth append

    append(12)

    ┌─────┬─────┬─────┬─────┐
    │  5  │  9  │  3  │ 12  │
    └─────┴─────┴─────┴─────┘

    capacity = 4
    used     = 4

Now the array is FULL.


# 3. The important moment: append(7)

We now need to do:

    append(7)

But look:

    ┌─────┬─────┬─────┬─────┐
    │  5  │  9  │  3  │ 12  │
    └─────┴─────┴─────┴─────┘
                            ↑
                          NO SPACE

We cannot simply put `7` into the next slot.

There is no next slot.

So the computer needs a bigger block.


# 4. Why does it become 8?

The implementation typically grows the capacity by doubling it.

We had:

    old capacity = 4

Double it:

    4 × 2 = 8

So a NEW block is allocated:

    OLD BLOCK

    ┌─────┬─────┬─────┬─────┐
    │  5  │  9  │  3  │ 12  │
    └─────┴─────┴─────┴─────┘
             capacity = 4


              ↓ allocate new block


    NEW BLOCK

    ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐
    │     │     │     │     │     │     │     │     │
    └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘
       0     1     2     3     4     5     6     7

             capacity = 8


Then the existing elements are copied:

    OLD                         NEW

    5  ──────────────────────→  5
    9  ──────────────────────→  9
    3  ──────────────────────→  3
    12 ──────────────────────→  12


So:

    ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐
    │  5  │  9  │  3  │ 12  │     │     │     │     │
    └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘
       0     1     2     3     4     5     6     7

    capacity = 8
    used     = 4


Now `7` can finally be inserted:

    ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐
    │  5  │  9  │  3  │ 12  │  7  │     │     │     │
    └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘
       0     1     2     3     4     5     6     7

    capacity = 8
    used     = 5


# 5. So where does O(n) come from?

Here's the key.

Our input has:

    n = 5

We eventually need space for:

    5 elements

But because of resizing, we don't necessarily allocate EXACTLY 5 cells.

We allocated:

    4 → 8

The final block has:

    capacity = 8


Notice:

    n = 5
    capacity = 8

8 is bigger than 5.

But it's still proportional to 5.

In fact, with doubling, the final capacity is bounded by roughly:

    n ≤ capacity < 2n

For n = 5:

    5 ≤ 8 < 10


So even though we have some unused memory, the amount of memory still grows linearly with `n`.


# 6. What happens when n gets bigger?

Imagine n = 16.

The capacity grows like this:

    START

    capacity = 4

          ↓ full

    capacity = 8

          ↓ full

    capacity = 16

          ↓ full

    capacity = 32


So visually:

    4 ─────→ 8 ─────→ 16 ─────→ 32
       ×2        ×2         ×2


The important thing:

> The array does NOT allocate a new block every time you append.


Instead:

    append 1
        ↓
    use existing slot

    append 2
        ↓
    use existing slot

    append 3
        ↓
    use existing slot

    append 4
        ↓
    use existing slot

    append 5
        ↓
    FULL!
        ↓
    allocate bigger block
        ↓
    copy elements
        ↓
    continue


# 7. This is why "memory grows in lockstep" needs a little clarification

The final amount of memory is proportional to `n`.

But the allocation does NOT happen smoothly like this:

    element 1 → +1 memory
    element 2 → +1 memory
    element 3 → +1 memory
    element 4 → +1 memory
    element 5 → +1 memory


Instead, it happens in jumps:

    MEMORY

    4 ────────────────┐
                      │
                      ↓
    8 ───────────────────────────────┐
                                     │
                                     ↓
    16 ───────────────────────────────────────────

       ↑                    ↑
       resize               resize


So:

> The NUMBER OF ELEMENTS grows one at a time, but the ALLOCATED MEMORY grows in chunks.


That's the important distinction.


# 8. What should you picture in your head?

Don't picture:

    "Every append allocates memory."

Picture:

    ┌───────────────────────────────────┐
    │        PRE-ALLOCATED SPACE       │
    │                                   │
    │  [5][9][3][12][ ][ ][ ][ ]       │
    │   ↑  ↑  ↑  ↑                     │
    │   elements already stored         │
    └───────────────────────────────────┘

Appending means:

    "Put the next element into an available slot."

Only when:

    USED == CAPACITY

do we say:

    "I need a bigger box."


# 9. Why does this still count as O(n)?

Suppose:

    n = 10

Maybe capacity becomes:

    16

Suppose:

    n = 100

Maybe capacity becomes:

    128

Suppose:

    n = 1,000

Maybe capacity becomes:

    1,024


The exact number isn't important.

What's important is:

    n grows
      ↓
    required storage grows proportionally
      ↓
    O(n)


The unused space doesn't suddenly make it O(2n) in a meaningful complexity sense.

Because:

    O(2n) = O(n)

Why?

Big-O ignores constant multipliers.

For example:

    2 × n

is still linear growth.

If:

    n = 100

then:

    2n = 200

If:

    n = 1,000

then:

    2n = 2,000

The amount still grows at the same fundamental rate:

    n → n × constant


# 10. One more important distinction: allocation vs copying

During a resize, TWO things happen:

    1. Allocate a bigger block
    2. Copy the old elements


For example:

    capacity 4 → capacity 8

We allocate 8 cells:

    [ ][ ][ ][ ][ ][ ][ ][ ]

Then copy:

    [5][9][3][12]
     ↓  ↓  ↓  ↓
    [5][9][3][12][ ][ ][ ][ ]

Then the old block is freed.

That copying can make ONE particular `append()` expensive.

But resizing doesn't happen on every append.

That's why dynamic arrays can still have efficient append behavior overall.

You don't need to memorize that yet.

Just remember:

    NORMAL APPEND

    [5][9][3][ ][ ]
             ↑
          put here

    ↓

    [5][9][3][12][ ]


    RESIZE APPEND

    [5][9][3][12]
                 ↑
               FULL

                 ↓

          allocate bigger block

                 ↓

    [5][9][3][12][ ][ ][ ][ ]


# Compact mental model

    INPUT
    [7][12][3][9][5]
           n = 5
             │
             ↓
    Need storage for n elements
             │
             ↓
    ┌───────────────────────┐
    │ capacity = 4          │
    │ [ ][ ][ ][ ]          │
    └───────────────────────┘
             │
       append elements
             ↓
    ┌───────────────────────┐
    │ [5][9][3][12]         │
    └───────────────────────┘
             │
           FULL
             ↓
       allocate 8
             ↓
    ┌───────────────────────────────┐
    │ [5][9][3][12][7][ ][ ][ ]     │
    └───────────────────────────────┘
             │
             ↓
       capacity = 8
             │
             ↓
       proportional to n
             │
             ↓
          O(n) SPACE


### Remember these 4 rules:

1. **Capacity ≠ number of elements.**
   Capacity is how many slots the memory block can hold.

2. **Appending normally uses an existing empty slot.**
   No new allocation is needed.

3. **When capacity is full, the array gets a bigger block.**
   Here: `4 → 8 → 16 → 32`.

4. **The final capacity is proportional to n.**
   Therefore the auxiliary space is **O(n)**.

The key picture is:

    elements arrive ONE BY ONE

             ↓
    [5][9][3][12][ ][ ][ ][ ]
     └────── used ──────┘
     └──────── capacity ────────┘

             ↓ FULL

    allocate a bigger box

             ↓

    [5][9][3][12][7][ ][ ][ ]
    └────────── capacity ──────────┘

So the memory doesn't grow by one cell per element.

**The elements grow one-by-one; the allocated box grows in jumps.**