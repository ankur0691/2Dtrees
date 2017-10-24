
package twodtree;

/**
 * The class implements an object with data as Node. It  has a pointer to next node.
 * @author ankur
 */
public class SingleNode {
        //stores String[] at node
        private Node node1;
        
        //points to next node
	private SingleNode next;

    /**
     * Constructor with no parameter.
     * Initializes node with a null string and next pointing to null
     */
    public SingleNode(){
		this.node1 = null;
		this.next = null;			
	}

    /**
     * Constructor with parameter
     * @param node1 the data of node type to be inserted
     * @param next the next SingleNode pointer
     */
    public SingleNode(Node node1, SingleNode next){
		this.node1 = node1;
		this.next = next;
	}

    /**
     * Get data value of the node
     * @return String[] of data value
     */
    public Node getC(){
		return this.node1;
	}

    /**
     * set Node value of current SingleNode
     * @param temp
     */
    public void setC(Node temp){
		this.node1 = temp;
	}

    /**
     * get next pointer of the SingleNode
     * @return next SingleNode
     */
    public SingleNode getNext(){
		return this.next;
	}

    /**
     * Sets the pointer to next SingleNode
     * @param next pointer to next SingleNode
     */
    public void setNext(SingleNode next){
		this.next = next;
	}
    @Override
    public String toString(){
		return this.getC()+"";
    }
}
