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
    private void fixTree(Node node) {
        // Этот цикл обеспечивает корректировку после вставки нового узла.
        // Если родительский узел красный, это нарушает правила красно-черного дерева.
        while (node != root && node.parent.color == RED) {

            // Рассмотрим случай, когда родитель текущего узла является левым потомком дедушки.
            if (node.parent == node.parent.parent.left) {

                Node uncle = node.parent.parent.right;  // Определим дядю текущего узла.

                // Если дядя существует и он красный, это означает, что у нас два красных узла подряд (родитель и дядя).
                if (uncle != null && uncle.color == RED) {
                    // Делаем родитель и дядю черными.
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    // Дедушка становится красным, чтобы сохранить свойства красно-черного дерева.
                    node.parent.parent.color = RED;
                    // Теперь дедушка может стать причиной нарушения, поэтому переходим к нему для дальнейшей корректировки.
                    node = node.parent.parent;
                } else {
                    // В этом случае дядя черный или отсутствует.
                    // Если текущий узел является правым потомком родителя, требуется левое вращение.
                    if (node == node.parent.right) {
                        node = node.parent;    // Перемещаем указатель на родителя.
                        rotateLeft(node);      // Производим левое вращение.
                    }
                    // Затем меняем цвета и выполняем правое вращение.
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent);
                }

            } else {  // Аналогично для случая, когда родитель текущего узла является правым потомком дедушки.
                Node uncle = node.parent.parent.left;

                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        // Гарантируем, что корень дерева всегда черный.
        root.color = BLACK;
    }


    // X
    //  \                  Y
    //    Y --------->    / \
    //   /               X   B
    // B
    private void rotateLeft(Node X) {
        // TODO: Реализация левого вращения
        Node Y = X.right; // Устанавливаем Y
        X.right = Y.left; // Перемещаем левого потомка Y на место правого потомка X

        if (Y.left != null) {
            Y.left.parent = X;// Если у Y есть левый потомок, устанавливаем для него родителя X
        }
        Y.parent = X.parent;
        if(X.parent == null) {
            root =Y;
        }else if (X == X.parent.left){
            X.parent.left = Y;
        } else{
            X.parent.right = Y;
        }
        Y.left = X;
        X.parent = Y;

    }

    //   Y
    //  /                X
    // X   --------->   / \
    //  \              B   Y
    //   B

    // Правое вращение
    private void rotateRight(Node Y) {
        // TODO: Реализация правого вращения
        Node X = Y.left;           // Устанавливаем X
        Y.left = X.right;          // Перемещаем правого потомка X на место левого потомка Y

        if (X.right != null) {
            X.right.parent = Y;    // Если у X есть правый потомок, устанавливаем для него родителя Y
        }
        X.parent = Y.parent;       // Устанавливаем родителя X вместо родителя Y

        if (Y.parent == null) {
            root = X;              // Если Y был корнем, делаем X новым корнем
        } else if (Y == Y.parent.left) {
            Y.parent.left = X;     // Если Y был левым потомком, делаем X левым потомком
        } else {
            Y.parent.right = X;    // Иначе делаем X правым потомком
        }

        X.right = Y;               // Перемещаем Y на место правого потомка X
        Y.parent = X;              // Устанавливаем X в качестве родителя Y
    }
    private void swapColors(Node node1, Node node2) {
        int temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }
}
