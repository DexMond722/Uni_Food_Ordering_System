/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;


/**
 *
 * @author User
 */
public class MenuItem extends UserVendor{
    private static int foodIDCounter = 1;
    private int foodID;
    private String foodName;
    private double price;

    public MenuItem(int foodID, String foodName, double price) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.price = price;
    }

    @Override
    public String toString() {
    String formattedFoodID = "F" + String.format("%02d", foodID);
    return formattedFoodID + "," + foodName + "," + price;
}

}
