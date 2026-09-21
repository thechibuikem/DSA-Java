# Recursion: Summing a List

Think of the array as a pile of receipts:

**[12, 7, 25, 9]**

We want:

> 12 + 7 + 25 + 9 = 53

## Mental Model

Don't think "recursion" first.

Think:

```text
sum(whole pile)
    ↓
first receipt + sum(rest of pile)
[12, 7, 25, 9]
 ↓
12 + sum([7, 25, 9])
          ↓
        7 + sum([25, 9])
              ↓
            25 + sum([9])
                    ↓
                  9 + sum([])
                         ↓
                         0
Why the Base Case?
Eventually we reach:
[]
There's nothing left to add.
So:
sum([]) = 0
That's why:
if (arr.length == 0) {
    return 0;
}
The empty array is where recursion stops.
Why arr[0]?
For:
[12, 7, 25, 9]
 ↑
arr[0] is 12, the first receipt.
We take that receipt and then ask:
"What's the total of everything underneath it?"
Why Arrays.copyOfRange()?
We already took arr[0].
[12, 7, 25, 9]
 ↑
take this
The remaining array is:
[7, 25, 9]
 ↑
start at index 1
So:
Arrays.copyOfRange(arr, 1, arr.length)
means:
"Give me everything from index 1 to the end."
Therefore:
return arr[0] +
       sumList(Arrays.copyOfRange(arr, 1, arr.length));
literally means:
first receipt + total of remaining receipts
The Important Trap
The slide says:
"One recursive call per invocation, so O(n) time."
That's wrong for this implementation.
Why?
Because Arrays.copyOfRange() is not free.
Look at what happens:
sumList([12, 7, 25, 9])
        ↓ copies 3 elements

sumList([7, 25, 9])
        ↓ copies 2 elements

sumList([25, 9])
        ↓ copies 1 element

sumList([9])
        ↓ copies 0 elements

sumList([])
Total copying:
3 + 2 + 1 + 0
For n elements:
(n-1) + (n-2) + ... + 1
which is:
n(n-1) / 2
Therefore:
Time = O(n²)
The recursion itself has only n calls, but each call is doing additional work to copy the array.
The Key Distinction
Recursive calls
      │
      └── n calls → O(n)

Array copying
      │
      └── n + (n-1) + (n-2) + ... → O(n²)
So:
Operation
Complexity
Recursive calls
O(n)
Array copying
O(n²)
Total time
O(n²)
Better Implementation
Avoid creating new arrays.
static int sumList(int[] arr, int i) {
    if (i == arr.length) {
        return 0;
    }

    return arr[i] + sumList(arr, i + 1);
}
Now we keep the same array and simply move the index:
[12, 7, 25, 9]
 ↑
 i = 0

     ↓ i + 1

[12, 7, 25, 9]
     ↑
     i = 1

         ↓ i + 1

[12, 7, 25, 9]
         ↑
         i = 2
No copying.
Each call does constant work.
Therefore:
Time:  O(n)
Space: O(n)
O(n) space comes from the recursion stack:
sumList(i=0)
    ↓
sumList(i=1)
    ↓
sumList(i=2)
    ↓
sumList(i=3)
    ↓
sumList(i=4)
Compact Mental Model
When analyzing recursive code, don't just count recursive calls.
Ask:
What happens inside each call?
Is that work constant or proportional to n?
Does the code create/copy anything?
How deep does the recursion go?
One recursive call per level does NOT automatically mean O(n) time.
You must analyze the work inside each call too.