import java.util.*;

public class BinaryTree {
    Node root;

    //Find the number of nodes between low and high. If input is -1, the return 0
    int giftAmount(Node root, int low, int high)
    {
        if(low < 0 || high < 0) {
            return 0;
        }
        int count = 0;
        if(root != null) {
            if(low <= root.value && high >= root.value) {
                count = 1 + giftAmount(root.left, low, high) + giftAmount(root.right, low, high);
            } else if(low > root.value) {
                count = giftAmount(root.right, low, high);
            } else if(high < root.value) {
                count = giftAmount(root.left, low, high);
            }
        } else {
            return 0;
        }
        return count;
    }

    //Find the Lowest Common Ancestor between n1 and n2
    Node lca(Node node, int n1, int n2)  {
        Node lcaNl, lcaNr = null;
        if(node == null) {
            return null;
        }
        //If n1 & n2 are less than node
        if(node.value > n1 && node.value > n2) {
            return lca(node.left, n1, n2);
        } //If n1 & n2 are greater than node
        else if(node.value < n1 && node.value < n2) {
            return lca(node.right, n1, n2);
        } //If n1 or n2 are equal to node
        else if(node.value == n1 || node.value == n2) {
            return node;
        }

        //Find Left node
        lcaNl = lca(node.left, n1, n2);
        //Find Right node
        lcaNr = lca(node.right, n1, n2);

        if(lcaNl != null && lcaNr != null) {
            return node;
        }
        if(lcaNl != null) {
            return lcaNl;
        } else {
            return lcaNr;
        }
    }

    //Print Ancestors of a given Target Node
    boolean printAncestors(Node node, int target) {
        boolean found = false;
        if(node == null) {
            return false;
        } else if(node.value == target) {
            found = true;
            System.out.print(node.value + " ");
            return found;
        } else if(node.value > target) {
            found = printAncestors(node.left, target);
        } else if(node.value < target) {
            found = printAncestors(node.right, target);
        }
        if(found){
            System.out.print(node.value + " ");
        }
        return found;
    }

    //Print PreOrder
    void printpreorder(Node node) {
        System.out.print(node.value + " ");
        if(node.left != null) {
            printpreorder(node.left);
        }
        if(node.right != null) {
            printpreorder(node.right);
        }
    }

    //Print InOrder
    void printinorder(Node node) {
        if(node.left != null) {
            printinorder(node.left);
        }
        System.out.print(node.value + " ");
        if(node.right != null) {
            printinorder(node.right);
        }
    }

    //Print PostOrder
    void printpostorder(Node node) {
        if(node.left != null) {
            printpostorder(node.left);
        }
        if(node.right != null) {
            printpostorder(node.right);
        }
        System.out.print(node.value + " ");
    }

    //Print Nodes without siblings
    void printSingles(Node node) 
    { 
        if(node == null) {
            return;
        }
        if(node.left != null && node.right == null) {
            System.out.print(node.left.value + " ");
            printSingles(node.left);
        } else if(node.left == null && node.right != null) {
            System.out.print(node.right.value + " ");
            printSingles(node.right);
        } else {
            printSingles(node.left);
            printSingles(node.right);
        }
    }

    //Parent Nodes = Sum of Child Nodes
    int sumchildren(Node node) {
        if(node == null) {
            return 0;
        }
        node.value = node.value + sumchildren(node.left) + sumchildren(node.right);
        return node.value;
    }

    //Adding Node values to a HashSet
    void inorder(Node node, HashSet<Integer> inSumTree) {
        if(node.left != null) {
            inorder(node.left, inSumTree);
        }
        inSumTree.add(node.value);
        if(node.right != null) {
            inorder(node.right, inSumTree);
        }
    }

    //Create a balanced BST from sorted array
    Node arrayBST(int array[], int s, int e) {
        if( s > e) {
            return null;
        }

        int m = (s + e) / 2;
        //System.out.println(m);
        Node node = new Node(array[m]);

        node.left = arrayBST(array, s, m - 1);
        node.right = arrayBST(array, m + 1, e);
        return node;
    }

    //Construct a BST
    Node insertRec(Node node, int levels[], int values[]) {
        sortlevel(levels, values);
        if(node == null) {
            node = new Node(values[0]);
            for(int i = 1; i < values.length; i++) {
                addNode(node, values[i]);
            }
        }
        return node;
    }

    //Adding nodes to the BST
    public void addNode(Node node, int value) {
        if(value < node.value) {
            if(node.left != null) {
                addNode(node.left, value);
            } else {
                node.left = new Node(value);
            }
        } else if(value > node.value) {
            if(node.right!= null) {
                addNode(node.right, value);
            } else {
                node.right = new Node(value);
            }
        }
    }

    //Sort arrays based on the levels
    void sortlevel(int levels[], int values[]) {
        for(int i = 0; i < levels.length; i++) {
            for(int j = 0; j < levels.length - 1 - i; j++) {
                if(levels[j] > levels[j + 1]) {
                    int lev = levels[j];
                    levels[j] = levels[j + 1];
                    levels[j + 1] = lev;
                    
                    int val = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = val;
                }
            }
        }
    }

    //Find depth of the tree
    public int func(Node node) {
        if (node == null)
            return 0;
        else {
            int lfunc = func(node.left);
            int rfunc = func(node.right);

            if (lfunc > rfunc)
                return (lfunc + 1);
            else
                return (rfunc + 1);
        }
    }
}
