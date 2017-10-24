
package twodtree;

import java.util.Scanner;

/**
 * This class contains the main method.
 * It drives the different functionalities of 2d tree including insertion, pre-order, post-order,level-order,reverse-level order traversal,
 * finding nearest neighbor and range search
 * @author ankur
 */
public class TwoDTreeDriver {

    /**
     *
     * @param args
     */
    public static void main(String args[]){
        TwoDTree tree = new TwoDTree("CrimeLatLonXY.csv");
        Scanner reader = new Scanner(System.in);
        int input;
        while(true){
        System.out.println("What would you like to do?");
        System.out.println("1: inorder");
        System.out.println("2: preorder");
        System.out.println("3: levelorder");
        System.out.println("4: postorder");
        System.out.println("5: reverseLevelOrder");
        System.out.println("6: search for points within rectangle");
        System.out.println("7: search for nearest neighbor");
        System.out.println("8: quit");
        input = reader.nextInt();
        if(input >= 8) {
            System.out.println("Thank you for exploring Pittsburgh crimes in the 1990's ");
            break;
        }
        switch(input){
            case 1: 
                tree.inOrderPrint();
                break;
            case 2:
                tree.preOrderPrint();
                break;
            case 3:
                tree.levelOrderPrint();
                break;
            case 4:
                tree.postOrderPrint();
                break;
            case 5:
                tree.reverseLevelOrderPrint();
                break;
            case 6:
                System.out.println("Enter a rectangle bottom left (X1,Y1) and top right (X2, Y2) as four doubles each separated by a space.");
                double x1 = reader.nextDouble();
                double y1 = reader.nextDouble();
                double x2 = reader.nextDouble();
                double y2 = reader.nextDouble();
                System.out.println("Searching for points within (" + x1 + "," + y1 + ") and (" + x2 + "," + y2 + ")" );
                ListOfCrimes lsc = new ListOfCrimes();
                lsc = tree.findPointsInRange(x1, y1, x2, y2);
                System.out.println("Examined " + lsc.count2 + " nodes durirng the search");
                System.out.println("Found " + lsc.count1 + " crimes");
                System.out.println(lsc);
                lsc.toKML();
                System.out.println("The crime data has been written to PGHCrimes.kml.It is viewable in Google Earth ");
                break;
            case 7:
                System.out.println("Enter a point to find nearest crime");
                double x11 = reader.nextDouble();
                double y11 = reader.nextDouble();
                Neighbor nb = new Neighbor();
                nb = tree.nearestNeighbor(x11,y11);
                System.out.println("Looked at " + nb.count3 + " nodes durirng the search. Found the nearest crime at:");
                System.out.println(nb.nearestneighbor);
                System.out.println("Distance: " + nb.distance);
                break;
                }
        }
        
    }
    
}
