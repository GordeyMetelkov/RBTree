class RBTree {
    private class Node{
        Node left;
        Node right;
        Color color;
        int value;
    }
    private enum Color {
        RED,
        BLACK
    }
    
    private Node root;
    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        }
        else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
        }
        return true;
    }
    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        }
        else {
            if (node.value > value) {
                if (node.left != null){
                    boolean result = addNode(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                }
                else {
                    node.left = new Node();
                    node.left.color = Color.RED;
                    node.left.value = value;
                    return true;
                }
            }
            else {
                if (node.right != null){
                    boolean result = addNode(node.right, value);
                    node.right = rebalance(node.right);
                    return result;
                }
                else {
                    node.right = new Node();
                    node.right.color = Color.RED;
                    node.right.value = value;
                    return true;
                }
            }
        }
    }
    private Node rebalance(Node node) {
        boolean result;
        do {
            result = true;
            if (node.right != null && node.right.color == Color.RED && 
                (node.left == null || node.left.color == Color.BLACK)){
                    result = false;
                    node = rightSwap(node);
                }
            if (node.left != null && node.left.color == Color.RED && 
                node.left.left != null && node.left.left.color == Color.RED){
                result = false;
                node = leftSwap(node);
            }
            if (node.left != null && node.left.color == Color.RED && 
                node.right != null && node.right.color == Color.RED){
                result = false;
                colorSwap(node);
            }
        }
        while(result == false);
        return node;
    }
    private Node rightSwap(Node node) {
        Node rChild = node.right;
        Node temp = rChild.left;
        rChild.left = node;
        node.right = temp;
        rChild.color = node.color;
        node.color = Color.RED;
        return rChild;
    }
    private Node leftSwap(Node node) {
        Node lChild = node.left;
        Node temp = lChild.right;
        lChild.right = node;
        node.left = temp;
        lChild.color = node.color;
        node.color = Color.RED;
        return lChild;
    }
    private void colorSwap(Node node) {
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }
}
