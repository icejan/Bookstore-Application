
package project;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;

public class Book {
    private String name;
    private int price;
    private CheckBox box = new CheckBox();
    
    public Book(String name, int price){
        this.name = name;
        this.price = price;
        this.box.setAlignment(Pos.CENTER);
    }
    
    public String getName(){
        return name;
    }
    
    public int getPrice(){
        return price;
    }
    
    public CheckBox getBox(){
        return box;
    }

    
}
