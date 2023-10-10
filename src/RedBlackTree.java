public class RedBlackTree {
    private static final int RED = 0;
    private static final int BLACK = 1;

    private class Node {
        int value;
        int color;
        Node left, right, parent;

        Node(int value){
            this.value = value;
            color = RED;
            left = right = parent = null;
        }

    }


    private Node root = null;

    public void insert(int data){
        Node node = new Node(data);

        if (root == null) {
            root = node;
            root.color = BLACK;
        }
        else { Node temp = root;
            while (true){
                if (data < temp.value){
                    if (temp.left == null) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    }
                    temp = temp.left;
                }
                else if (data >= temp.value){
                    if(temp.right == null){
                        temp.right = node;
                        node.parent = temp;
                        break;
                    }
                    temp = temp.right;
                }
            }
            fixTree(node);
        }
    }
    // Исправление нарушений после вставки
    private void fixTree(Node node){
        // TODO: Реализация исправления нарушений
    }

    private void rotateLeft(Node node) {
        // TODO: Реализация левого вращения
        
    }

    // Правое вращение
    private void rotateRight(Node node) {
        // TODO: Реализация правого вращения
    }
    private void swapColors(Node node1, Node node2) {
        int temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }
}
