public class Node {
        private int data;
        private Node left;
        private Node right;
        private Node parent;
        
        public Node() {
            this.left = null;
            this.right = null;
            this.parent = null;
            data = 0;
        }
        
        public Node(int data, Node left, Node right, Node parent) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.data = data;
            
        }
        
        public Node(int data, Node parent) {
            this.data = data;
            this.parent = parent;
        }
        
        public Node(int data) { this(data, null, null, null); }
        public void setLeft(Node n) {   left = n;   }
        public void setRight(Node n) {  right = n;  }
        public void setParent(Node n) { parent = n; }
        
        public Node getLeft() { return left;    }
        public Node getRight() {    return right;   }
        public Node getParent() {   return parent;  }
        public void setData(int d) {    data = d;   }
        public int getData() {  return data;    }
    }
