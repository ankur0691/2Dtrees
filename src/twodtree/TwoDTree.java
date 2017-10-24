/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twodtree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class implements the creation, different traversals, range search and nearest neighbor of a 2-D tree.
 * It uses objects from ListOfCrimes.java and Neighbor.java
 * @author ankur
 */
public class TwoDTree {

    /**
     * root node of the tree
     */
    public Node root = null;
    
    /**
     * Constructs a 2d tree from the file input
     * @param crimeDataLocation the file name with location
     * pre-condition: The String crimeDataLocation contains the path to a file formatted in the exact same way as CrimeLatLonXY.csv
     * post-condition: The 2d tree is constructed and may be printed or queried.

     */
    public TwoDTree(String crimeDataLocation){
        BufferedReader input = null;
        String cvsSplitBy = ",";
        String currentLine = "";
        this.root = null;
        long count=0;
        //keep reading one entry and call the heloer function to insert that entry
        try {
            input = new BufferedReader(new FileReader(crimeDataLocation));
            //ignore the column names
            input.readLine();
            while ((currentLine = input.readLine())!= null){
                // use comma as separator
                String[] coordinate = currentLine.split(cvsSplitBy);
                //insert this single entry in 2d tree
                this.root = insert(this.root, coordinate,0);
                //maintain the count
                count++;
                //System.out.println("[ x=" + coordinate[0] + " , y=" + coordinate[1] + "]");
                //if(count ==6) break;
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Crime file loaded into 2d tree with " + count + " records");
    }
    
    //helper function for inserting nodes in 2d tree
    private Node insert(Node current, String[] data,int level)
    {
        if(current == null) {
            current = new Node(data);
        }else if(data[level].compareTo(current.data[level])<0){
            current.left = insert(current.left,data,1-level);
        }else current.right = insert(current.right,data,1-level);
        return current;
    }
    
    /**
     * This prints the 2d tree in pre-order
     * Run time analysis of this method is O(n) as it will go to every node in the tree to print it
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a pre-order traversal
     */
    public void preOrderPrint(){
        preOrder(this.root);
    }

    //helper function for pre-orser traversal
    private void preOrder(Node current){
        if(current == null) return;
        //print the current node then go to it's left child then right
        System.out.println(current);
        if(current.left != null)
            preOrder(current.left);
        if(current.right != null)
            preOrder(current.right);
    }
    
    /**
     * This prints the 2d tree in in=order
     * Run time analysis of this is O(n) as it will go and print every node in the tree
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with an in-order traversal. 
     */
    public void inOrderPrint(){
        inOrder(this.root);
    }
    
    // the helper function for inorder traversal
    private void inOrder(Node current){
        if(current == null) return;
        //first go to left susbtree
        if(current.left != null)
            inOrder(current.left);
        //print the current node
        System.out.println(current);
        //go to right subtree
        if(current.right != null)
            inOrder(current.right);
    }
    
    /**
     *This method prints the post order traversal of the tree
     * The run time analysis of this algorithm is O(n) as it has to visit and print every node
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a post-order traversal. 
     */
    public void postOrderPrint(){
        postOrder(this.root);
    }
    
    //post order helper function
    private void postOrder(Node current){
        if(current == null) return;
        if(current.left != null)
            postOrder(current.left);
        if(current.right != null)
            postOrder(current.right);
        System.out.println(current);
    }
    
    /**
     * This method prints the level order traversal of the tree
     * The run time analysis of this is O(n)
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a level-order traversal. 
     */
    public void levelOrderPrint(){
        Queue q = new Queue();
        Node temp = this.root;
        //put the root in queue
        q.enqueue(temp);
        //now while queue is not empty print the fron tof he queue and keep enqueuing it's child
        while(!q.isEmpty()){
            temp = q.dequeue();
            System.out.println(temp);
            if(temp.left != null) q.enqueue(temp.left);
            if(temp.right!= null) q.enqueue(temp.right);
        }
    }
    
    /**
     * This method prints the reverse level order traversal of the tree
     * the run time analysis of this is O(n)
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a reverse level-order traversal. 
     * 
     */
    public void reverseLevelOrderPrint(){
        Queue q = new Queue();
        Stack s = new Stack();
        Node temp = this.root;
        //put the rrot node in the queue
        q.enqueue(temp);
        //while queue is not empty
        while(!q.isEmpty()){
            //dequeue one node
            temp = q.dequeue();
            //push it in stack
            s.push(temp);
            //enqueue it's child node
            if(temp.left!= null) q.enqueue(temp.left);
            if(temp.right != null) q.enqueue(temp.right);
        }
        //while stack is not empty, pop one elemnt and print it
        while(!s.isEmpty()){
            temp = s.pop();
            System.out.println(temp);
        }
    }
    
    /**
     * This method finds the points within the rectangle specified
     * Pre-condition: A 2d tree is constructed
     * Post-condition: All the points within the rectangle are returned
     * @param x1 double bottom left x-axis of the rectangle
     * @param y1 double bottom left x-axis of the rectangle
     * @param x2 double upper right x-axis of the rectangle
     * @param y2 double upper right x-axis of the rectangle
     * @return listOfCrimes object with a list of all the nodes within the rectangle
     */
    public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2){
        ListOfCrimes lsc = new ListOfCrimes();
        findPointsInRangeHelper(x1,y1,x2,y2,this.root,lsc,0);
        return lsc;
    }
    
    //helper function to find points in the range
    private void findPointsInRangeHelper(double x1, double y1, double x2, double y2, Node node1,ListOfCrimes lsc,int level){
        //if there is no node present in the  tree then return
        if(node1 == null) {
            return;
        }
        //maintain the  count of the visited nodes
        lsc.count2++;
        //if the point lies within x1,y1 and x2,y2 then add it to the list and traverse it's child nodes as both the nodes can be within the range
        if(((node1.data[0].compareTo(String.valueOf(x1))) >= 0 && (node1.data[0].compareTo(String.valueOf(x2))) <= 0) && ((node1.data[1].compareTo(String.valueOf(y1))) >= 0 && (node1.data[1].compareTo(String.valueOf(y2))) <= 0)){
            //add this node to the list
            lsc.addCrime(node1);
            //increase count of the nodes found within the range
            lsc.count1++;
            findPointsInRangeHelper(x1,y1,x2,y2,node1.left,lsc,1-level);
            findPointsInRangeHelper(x1,y1,x2,y2,node1.right,lsc,1-level);
            }
        //if the  node does not fall within the range itself, then traverse to the left or roght node base don it's location
        else{
            //if level is 0 means we are comaprng x axis, 
            //then check the current node lies on the left or right side of the rectangular region and traverse accrodingly to it's child nodes
            if(level == 0){
                if((node1.data[level].compareTo(String.valueOf(x1)) <=0) && (node1.data[level].compareTo(String.valueOf(x2)) <=0) ) 
                    findPointsInRangeHelper(x1,y1,x2,y2,node1.right,lsc,1-level);
                else
                    findPointsInRangeHelper(x1,y1,x2,y2,node1.left,lsc,1-level);
                }
            //if level is 1 means we are comparing y-axis, 
            //then check the current node lies on the top or bottom of the rectangular region and traverse accordingly to it's child nodes
            else if(level == 1){
                if((node1.data[level].compareTo(String.valueOf(y1)) <=0) && (node1.data[level].compareTo(String.valueOf(y2)) <=0) ) 
                    findPointsInRangeHelper(x1,y1,x2,y2,node1.right,lsc,1-level);
                else
                    findPointsInRangeHelper(x1,y1,x2,y2,node1.left,lsc,1-level);
                }
            }
       
    }

    /**
     * This function finds the nearest neighbor of the passed point from the 2d tree. 
     * It prunes the left-right subtree search based on the x or y distance of the node from the minimum distance 
     * @param x1 double x-axis of the investigating point
     * @param y1 double y-axis of the investigating point
     * @return Neighbor object the nearest neighbor of the passed point from the 2 d tree
     */
    public Neighbor nearestNeighbor(double x1, double y1){
        Neighbor nb= new Neighbor();
        double a[] = new double[2];
        //store the coordinates in adoubloe array so that we can directly compare the x or y axis alternatively, just like level variable
        a[0]=x1;
        a[1]=y1;
        nearestNeighborHelper(a,this.root,nb,0);
        return nb;
    }

    // this method is the helper method in finding the nearest neighbor
    private void nearestNeighborHelper(double a[], Node node1, Neighbor nb, int level){
       if(node1 == null) return;
       //maintain the count of visited nodes
       nb.count3++;
       //System.out.println(node1);
       //calculate the distance of the point from the current node
       double localDistance = distance(a[0],a[1],Double.parseDouble(node1.data[0]),Double.parseDouble(node1.data[1]));
       //if this calculated distance is less than the minimum till now, then we have found a better neighbor  
       if(localDistance <= nb.distance ){
           nb.distance = localDistance;
           nb.nearestneighbor = node1;
       }
       //Now we wil prune left-or right subtree search based on the below criteris:
       //If the separation criteria x or y value at this node is so far away from this node's range that we cannot beat the minimum distance calulcated then we can prune one of the subtrees
       if(nb.distance <= Math.abs(Double.parseDouble(node1.data[level]) - a[level])) {
           //if the point of investigation's level(if level=0 then x else y) coordinate has lesser value then the current node's coordinate then we only need to g to left subtree 
           if(a[level]<Double.parseDouble(node1.data[level]))
               nearestNeighborHelper(a,node1.left,nb,1-level);
           //if it's greater or equal to then we only need ot go to right subtree, wehre our point of investigation lies
           else 
               nearestNeighborHelper(a,node1.right,nb,1-level);
       }else{
           //else we can't prune any sub-tree and let's traverse both of them
            nearestNeighborHelper(a,node1.left,nb,1-level);    
            nearestNeighborHelper(a,node1.right,nb,1-level);
       }
    }

    //distance calculator between two points
    private double distance(double x1,double y1,double x2,double y2){
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
    
    
}
