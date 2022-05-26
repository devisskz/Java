/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Batyr Issabekov
 *     username: batyriss@bu.edu
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }

    //non-static Iter() method that takes takes an 
    // integer key as its only parameter and that uses uses
    // iteration to determine and return the depth of the node with that key. 

    public int depthIter(int key) { //similar to problem 4:

        int count = 0;  //set count to 0
        Node temp = root; //point a temp node to root

        if (root == null) { //if root is null, returns -1
            return -1;
        }
        if (key == root.key) { //if the first node contains the key
            return 0; //returns 0
        }
        while (temp.key != key) { //enters while loop while we have not found the key
            if (key < temp.key) { //if key is less than the temp key
                temp = temp.left; //we go left, as they contain smaller keys
                count++;            // adds to count

            }
            if (key>temp.key ) { //if the key is larger than temp key
                temp = temp.right;  // go to the right, as they contain larger keys
                count++; //adds to count

            }
            if(temp==null){ //otherwise, if the key is not found ( we reach the end)
                return -1; //returns -1
            }
        }
        return count; //returns count if the while loop has found the key
    }
    
    //a private static method called numEvenKeysRec() that takes a reference to a Node object as its 
    // only parameter; it should use recursion to find and return the number 
    // of even keys in the binary search tree or subtree whose root node is specified 
    // by the parameter.

    private static int numEvenKeysRec(Node node) {
        
        if (node == null) { //base case, if the node is null, we return 0
            return 0;
        }
        //a recursive call to the next nodes on the right and left
        int count = numEvenKeysRec(node.right) + numEvenKeysRec(node.left);
        //check if the key of the node is even 
        if (node.key %2 ==0) {
            return count += 1; // adds 1 to count
        }
        else {
            return count; //otherwise, does not change count and returns it
        }
    }


    //public non-static method called numEvenKeys() 
    // that takes no parameters and that returns the number of even keys in the entire tree. 
    private int numEvenKeys() {
        return numEvenKeysRec(root); //call to numevenkeysrec from the first node (root)
    }

    private int deleteMax() {
        //considering that we are looking at a binary search tree:

        Node largest = null; //declare largest to null as a placeholder
        Node temp = root; //make temp point to root

        if (root == null) { //if the root is empty, returns -1
            return -1;
        }

        while (temp!= null) { //enter a while loop
            if (temp.right.right == null && temp.right.left != null) { 
                //if the next, next right node is the leaf AND the left of the next right exists
                temp.right.left = temp.right; //bump the left leaf into the place of the current right
            }
            if (temp.right.right == null){ //if the the next, next right node is the leaf
                largest = temp.right; //the largest is set to the next
                temp.right = null; //and the current leaf is now null (get rid of it)
            }
            
            temp = temp.right; //iterates through the right nodes while in the loop
        }

        return largest.key; //returns the key of the largest
        
    }
        
    

    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
    
    /*
     * "wrapper method" for the recursive Tree() method
     * from PS 7, Problem 6
     */
    public int depth(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }
        
        return depthInTree(key, root);
    }
    
    /*
     * original version of the recursive depthInTree() method  
     * from PS 7, Problem 6. You will write a more efficient version
     * of this method.
     */
    private static int depthInTree(int key, Node root) {
        if (key == root.key) {
            return 0;
        }
        
        if (root.left != null) {
            int depthInLeft = depthInTree(key, root.left);
            if (depthInLeft != -1) {
                return depthInLeft + 1;
            }
        }
        
        if (root.right != null) {
            int depthInRight = depthInTree(key, root.right);
            if (depthInRight != -1) {
                return depthInRight + 1;
            }
        }
        
        return -1;
    }
    
    
    public static void main(String[] args) {
        System.out.println("--- Testing depth()/depthInTree() from Problem 4 ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);
            
            int results = tree.depth(28);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 3);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
                           
        /*
         * Add at least two unit tests for each method from Problem 6.
         * Test a variety of different cases. 
         * Follow the same format that we have used above.
         */
        System.out.println("--------");
        LinkedTree tree1 = new LinkedTree();
        System.out.println("testing depthIter,  supposed to output -1");
        System.out.println( tree1.depthIter(48) );

        System.out.println();
        System.out.println("--------");
        System.out.println("outputting a given list of keys");

        int[] keys = { 44, 35, 53, 23, 48, 62, 28, 57, 80 };
        tree1.insertKeys(keys);
        tree1.levelOrderPrint();

        System.out.println();
        System.out.println("--------");

        System.out.println("testing various keys for depthiter");
        System.out.println(tree1.depthIter(48));
        System.out.println(tree1.depthIter(28));
        System.out.println(tree1.depthIter(44));
        System.out.println(tree1.depthIter(30));
        System.out.println(tree1.depthIter(62));
        System.out.println(tree1.depthIter(28));
        System.out.println();
        System.out.println("--------");

        System.out.println("testing numEvenKeys");
        System.out.println("testing a null pointer, supposed to return 0");
        LinkedTree tree2 = new LinkedTree();
        System.out.println( tree2.numEvenKeys() );
        System.out.println();
        System.out.println("--------");

        System.out.println("testing a tree, exptected 5");
        LinkedTree tree = new LinkedTree();
        int[] keys3 = { 44, 35, 53, 23, 48, 62, 28, 57, 80 };
        tree.insertKeys(keys3);
        System.out.println(tree.numEvenKeys());
        System.out.println();
        
        System.out.println("testing another tree, expected 4");
        LinkedTree tree6 = new LinkedTree();
        int[] keys5 = { 45, 36, 53, 23, 47, 62, 28, 58, 79 };
        tree6.insertKeys(keys5);
        System.out.println(tree6.numEvenKeys());


        System.out.println();
        System.out.println("--------");
        System.out.println("testing delete max");
        LinkedTree tree4 = new LinkedTree();
        int[] keys2 = {44, 35, 53, 23, 48, 62, 28, 57, 80};
        tree4.insertKeys(keys2);
        System.out.println(tree4.deleteMax());
        tree4.levelOrderPrint();
        System.out.println();
        System.out.println("testing another delete max");
        System.out.println( tree4.deleteMax() );
        tree4.levelOrderPrint(); 
    }
}
