/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twodtree;

/**
 * This class contains the detail of the neighbor Node with distance and point details.
 * @author ankur
 */
public class Neighbor {
    public double distance;
    public Node nearestneighbor;
    public int count3 = 0;
    public Neighbor(){
        distance = 999999999;
        nearestneighbor = null;
    }
    
}
