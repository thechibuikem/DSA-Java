## JAVA INNER CLASS + STATIC

If Node is inside another class:

```
class BinarySearchTree {
    class Node { }
}
```

Node is a NON-STATIC inner class.
It belongs to a specific BinarySearchTree object.

So you need:

- BinarySearchTree tree = new BinarySearchTree();
- BinarySearchTree.Node node = tree.new Node();


### EASIER OPTION:

```
class BinarySearchTree {
    static class Node { }
}
```

Now Node does NOT need a BinarySearchTree object.

You can simply do:

``` Node node = new Node(); ```


### MENTAL RULE:

non-static inner class → needs an outer-class object
static inner class → does not need an outer-class object