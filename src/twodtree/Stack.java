/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twodtree;

/**
 * This class implements a stack using a linked list of SingleNode object with only a front pointer
 * @author ankur
 */
public class Stack {
    //points to the first node of the queue
    private SingleNode front;
    
    /**
     *Constructor without parameter
     */
    public Stack(){
	this.front = null;
    }

    /**
     * Checks if the stack is empty
     * @return true if empty, else false
     */
    public boolean isEmpty(){
        return this.front == null;
    }
    
    /**
     * This pushes a Node object into a SingleNode element to the front of the stack
     * @param node1 Node to be pushed
     */
    public void push(Node node1){
        SingleNode temp = new SingleNode(node1, null);
        if(this.front == null) {
            this.front = temp;
        } else{
            temp.setNext(this.front);
            this.front = temp;
        }
    }
    
    /**
     * Pops an element from the front of the stack
     * @return a Node object
     */
    public Node pop(){
        if(isEmpty()) return null;
        SingleNode temp = this.front;
        this.front = this.front.getNext();
        return temp.getC();
    }
    
    /**
     * get the top of the stack
     * @return
     */
    public SingleNode getFront(){
            return this.front;
        }
    
}
