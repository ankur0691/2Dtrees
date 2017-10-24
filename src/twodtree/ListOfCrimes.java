
package twodtree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * This class maintains a singly linked list of SingleNode objects which are found in the range of the rectangle. It has just one head.
 * @author ankur
 */
public class ListOfCrimes {
    //points to the first node of the list
    private SingleNode head;

    /**
     * msintains count of node visited
     */
    public int count1=0;

    /**
     * maintains count of node in the list
     */
    public int count2=0;
    /**
     * creates a linked  list with head =null
     */
    public ListOfCrimes(){
		this.head = null;
	}
    /**
     * Adds a node containing node object in the end. Head is updated.
     * @param node1 Node object
     */
    public void addCrime(Node node1){
            SingleNode temp = new SingleNode(node1,null);
                if(isEmpty()) {
                    this.head = temp;
                }
                else{
                    SingleNode temp2 = this.head;
                    while(temp2.getNext()!=null){
                        temp2 = temp2.getNext();
                    }
                    temp2.setNext(temp);
                }
    }
    
    /**
     * Checks for empty list
     * @return
     */
    public boolean isEmpty(){
        return this.head == null;
    }
    
    /**
     * Gives the first entry from the list
     * @return SingleNode object
     */
    public Node getCrime(){
                if(!isEmpty()) return null;
                SingleNode temp = this.head;
		this.head = this.head.getNext();
		return temp.getC();
    }
    @Override
    public String toString(){
        SingleNode temp = this.head;
        StringBuffer s = new StringBuffer();
        while(temp!=null){
            s.append(temp.getC().toString()).append('\n');
            temp=temp.getNext();
        } 
        return s.toString();
    }
    
    /**
     * This converts the list into a KML format in the file PGHCrimes.kml
     * pre-condition : list cannot be circular
     * post-condition: a kml file is created
     * @return  string of KML representation of the list
     */
    public String toKML(){
                //
		StringBuffer kml = new StringBuffer();
		kml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		kml.append("<kml xmlns=\"http://earth.google.com/kml/2.2\">\n");
		kml.append("<Document>\n");
		kml.append("<Style id=\"style1\">\n<IconStyle>\n<Icon>\n");
		kml.append("<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>\n");
		kml.append("</Icon>\n</IconStyle>\n");
		kml.append("</Style>\n");
		SingleNode temp = this.head; 
		//while list is not empty keep adding coordinates in different placemark
                while(temp !=null){
			kml.append("<Placemark>\n");
			kml.append("<name>" + temp.getC().data[4] + "</name>\n");
			kml.append("<description>" + temp.getC().data[3] + "</description>\n");
			kml.append("<styleUrl>#style1</styleUrl>\n");
			kml.append("<Point>\n");
			kml.append("<coordinates>\n");
			kml.append(temp.getC().data[8] + "," + temp.getC().data[7] + ",0.000000\n");
			kml.append("</coordinates>\n");
			kml.append("</Point>\n");
			kml.append("</Placemark>\n");
                        temp = temp .getNext();
		}
		kml.append("</Document>\n");
		kml.append("</kml>\n");
                //Copy this stringbuffer created into a kml file
                try{
                    BufferedWriter out = new BufferedWriter(new FileWriter(new File("PGHCrimes.kml")));
                    out.write(kml.toString());  //Replace with the string 
                    out.flush();
                    out.close();     
                } catch (IOException e){
                    System.out.println("Throw error");
                    }
		return kml.toString();
    }
}
