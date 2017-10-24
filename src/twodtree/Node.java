
package twodtree;

/**
 * This class contains a Node which represents a node in  2d tree with a String[] data and right and left subtree
 * @author ankur
 */
public class Node {
    /**
     * String[] data
     */
        public String[] data = new String[9];

    /**
     * pointer to left child node
     */
        public Node left;

    /**
     * pointer to right child node
     */
    public Node right;
        
    /**
     * Constructor without parameter
     * @param data String[] of data
     */
    public Node(String[] data){
            System.arraycopy(data, 0, this.data, 0, data.length);
            this.left = null;
            this.right = null;
        }
    
        @Override
        public String toString(){
            return this.data[0] + " , " + this.data[1] + " , " + this.data[2] + " , " + this.data[3] + " , " + this.data[4] + " , " + this.data[5] + " , " + this.data[6] + " , " + this.data[7] + " , " + this.data[8];
        }
}
