class Node{
    int value, level;
    Node left, right;

    Node(int item) 
    {
        value = item;
        left = right = null;
    }

    Node(int item, int levelno) {
            value = item;
            level = levelno;
            left = right = null;
        }
}
