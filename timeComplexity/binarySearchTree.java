package timeComplexity;

public class binarySearchTree {
    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val){
            this.val = val;
        }
    }

    public Node searchTree(Node node, int x){
    while (node != null){
        if (node.val == x){
            return node;
        } 
        else if (x < node.val){
            node = node.left;
        }
        else {
            node = node.right;
        }
    }
    return null;
}

    public static void main(String[] args){
        binarySearchTree obj = new binarySearchTree();

        Node root = new Node(8);
        root.left = new Node(4);
        root.right = new Node(12);

        Node result = obj.searchTree(root, 41);
        if (result == null){
        System.out.println("cannot print answer as result is not found");
        } else{
            System.out.println(result.val);
        }

    }

}
