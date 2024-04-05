import java.util.*;

// Node sınıfı, ağaç düğümlerini temsil eder.
class Node {
    int value; // Düğümün değeri
    Node left, right; // Sol ve sağ alt düğümler

    // Node sınıfının yapıcısı
    public Node(int item) {
        value = item;
        left = right = null;
    }
}

// BinarySearchTree sınıfı, ikili arama ağacını uygular.
class BinarySearchTree {
    Node root; // Ağacın kök düğümü

    // BinarySearchTree yapıcısı, ağacın kök düğümünü başlatır.
    BinarySearchTree() {
        root = null;
    }

    // Ağaca yeni bir düğüm ekler.
    void insert(int value) {
        root = insertRec(root, value);
    }

    // Yinelemeli olarak düğüm eklemeyi gerçekleştiren yardımcı bir yöntem.
    Node insertRec(Node root, int value) {
        // Kök düğüm boşsa, yeni bir düğüm oluşturup kök olarak ayarlar.
        if (root == null) {
            root = new Node(value);
            return root;
        }

        // Eğer eklenecek değer, kök düğümün değerinden küçükse, sol alt ağaca ekler.
        if (value < root.value)
            root.left = insertRec(root.left, value);
            // Eğer eklenecek değer, kök düğümün değerinden büyükse, sağ alt ağaca ekler.
        else if (value > root.value)
            root.right = insertRec(root.right, value);

        return root;
    }

    // Belirli bir değeri içeren düğüm var mı diye kontrol eder.
    boolean contains(int value) {
        return containsRec(root, value);
    }

    // Yinelemeli olarak içeriyorsa true, içermiyorsa false döndüren yardımcı bir yöntem.
    boolean containsRec(Node root, int value) {
        // Kök düğüm null ise, değer ağaçta bulunmaz.
        if (root == null)
            return false;

        // Eğer değer kök düğümün değerine eşitse, değer bulunmuştur.
        if (value == root.value)
            return true;

        // Eğer değer kök düğümün değerinden küçükse, sol alt ağaçta arar.
        return value < root.value
                ? containsRec(root.left, value)
                // Değilse, sağ alt ağaçta arar.
                : containsRec(root.right, value);
    }

    // Ağaçtaki en küçük değeri bulur.
    int minValue() {
        Node current = root;
        while (current.left != null)
            current = current.left;
        return current.value;
    }

    // Ağaçtaki en büyük değeri bulur.
    int maxValue() {
        Node current = root;
        while (current.right != null)
            current = current.right;
        return current.value;
    }

    // BFS (Breadth First Search) gezinme yöntemini gerçekleştirir.
    void BFS() {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node tempNode = queue.poll(); // Kuyruğun başındaki düğümü alır ve kuyruktan çıkarır.
            System.out.print(tempNode.value + " ");

            // Düğümün sol alt düğümü varsa, kuyruğa ekler.
            if (tempNode.left != null)
                queue.add(tempNode.left);

            // Düğümün sağ alt düğümü varsa, kuyruğa ekler.
            if (tempNode.right != null)
                queue.add(tempNode.right);
        }
    }

    // DFS PreOrder (Depth First Search) gezinme yöntemini gerçekleştirir.
    void DFSPreOrder() {
        DFSPreOrderUtil(root);
    }

    // Yinelemeli olarak DFS PreOrder gezinme yöntemini gerçekleştiren yardımcı bir yöntem.
    void DFSPreOrderUtil(Node node) {
        if (node != null) {
            // Önce mevcut düğümün değerini yazdırır,
            System.out.print(node.value + " ");
            // Sonra sol alt ağaca derinliği öncelikle gezinir,
            DFSPreOrderUtil(node.left);
            // En son sağ alt ağaca derinliği öncelikle gezinir.
            DFSPreOrderUtil(node.right);
        }
    }

    // DFS PostOrder gezinme yöntemini gerçekleştirir.
    void DFSPostOrder() {
        DFSPostOrderUtil(root);
    }

    // Yinelemeli olarak DFS PostOrder gezinme yöntemini gerçekleştiren yardımcı bir yöntem.
    void DFSPostOrderUtil(Node node) {
        if (node != null) {
            // Önce sol alt ağaca derinliği öncelikle gezinir,
            DFSPostOrderUtil(node.left);
            // Sonra sağ alt ağaca derinliği öncelikle gezinir,
            DFSPostOrderUtil(node.right);
            // En son mevcut düğümün değerini yazdırır.
            System.out.print(node.value + " ");
        }
    }

    // DFS InOrder gezinme yöntemini gerçekleştirir.
    void DFSInOrder() {
        DFSInOrderUtil(root);
    }

    // Yinelemeli olarak DFS InOrder gezinme yöntemini gerçekleştiren yardımcı bir yöntem.
    void DFSInOrderUtil(Node node) {
        if (node != null) {
            // Önce sol alt ağaca derinliği öncelikle gezinir,
            DFSInOrderUtil(node.left);
            // Sonra mevcut düğümün değerini yazdırır,
            System.out.print(node.value + " ");
            // En son sağ alt ağaca derinliği öncelikle gezinir.
            DFSInOrderUtil(node.right);
        }
    }

    // Ana yöntem, ağaç üzerinde işlemleri gerçekleştirir.
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Örnek ağaç oluşturulur.
        tree.insert(38);
        tree.insert(19);
        tree.insert(69);
        tree.insert(12);
        tree.insert(24);
        tree.insert(59);
        tree.insert(95);

        // Ağaç gezinme yöntemleri çağrılır ve sonuçlar yazdırılır.
        System.out.println("BFS (Breadth First Search):");
        tree.BFS();
        System.out.println("\n\nDFS PreOrder (Depth First Search):");
        tree.DFSPreOrder();
        System.out.println("\n\nDFS PostOrder:");
        tree.DFSPostOrder();
        System.out.println("\n\nDFS InOrder:");
        tree.DFSInOrder();
    }
}
