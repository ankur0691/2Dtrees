
package twodtree;

/** This class is a linked list implementation of the queue with a front and rear pointer. It has a  node of SingleNode type. 
 * It is used in the level order and reverse ordere traversal
 *
 * @author ankur
 */
public class Queue {
        //points to the first node of the queue
        private SingleNode front;
        //points to the last node of the queue
        private SingleNode rear;

    /**
     * creates a linked  list with head and tail pointing to null
     */
    public Queue(){
		this.front = null;
                this.rear = null;
	}

    /**
     * Checks for an empty queue
     * @return true if list is empty, else false
     * Complexity : BigTheta(n)
     */
    public boolean isEmpty(){
		return this.front == null;
	}
    
    /**
     * Adds a SingleNode type node to the end of the list
     * @param node1 SingleNode node
     */
    public void enqueue(Node node1){
		SingleNode temp = new SingleNode(node1,null);
                if(this.front == null) {
                    this.front = temp;
                }
		if(this.rear != null) this.rear.setNext(temp);
		this.rear = temp;
	}
    
    /**
     * Removes node from the front of the queue.Sets the head or tail pointer accordingly.
     * @return the Node value of the first node of the list
     * Pre-condition : the list is not empty
     * Post-condition : a node is removed from front and head/tail is updated
     * Complexity - BigTheta(1)
     */
	public Node dequeue(){
                if(isEmpty()) return null;
		SingleNode temp = this.front;
                this.front = this.front.getNext();
		if(this.front == null){
		this.rear = null;
		};
		return temp.getC();
        }
        
    /**
     * Counts the number of nodes in the list
     * @return the int size of the queue
     */
    public int countNodes(){
		SingleNode temp = this.front;
		int size = 0;
		while(temp!=null){
			size++;
			temp = temp.getNext();
		}
		return size;
	}
        
    @Override
    public String toString(){
		SingleNode node = this.front;
		String str = "";
		while(node != null)
		{
			str= str + node.toString();
			node = node.getNext();
		}
		return str;
    }
        
    /**
     * Get method for front of the queue
     * @return a singleNode pointing to head
     */
    public SingleNode getFront(){
            return this.front;
        }
        
    /**
     * Set Method for front of the queue
     * @return a single node pointing to tail
     */
    public SingleNode getRear(){
            return this.rear;
          }
}