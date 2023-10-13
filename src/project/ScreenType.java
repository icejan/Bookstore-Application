
package project;

import javafx.stage.Stage;

public abstract class ScreenType {
    private Data owner = null, costumers = null, books = null;
    private static Stage stage;
    
    public ScreenType(Data d){
        set(d);
    }
    
    public ScreenType(Data d1, Data d2){
        set(d1);
        set(d2);
    }
    
    public static void setStage(Stage s){
        stage = s;
    }
    
    public Stage getStage(){
        return stage;
    }
    
    private void set(Data d){
        if(d instanceof Owner){
            owner = d;
        }
        else if(d instanceof Customers){
            costumers = d;
        }
        else if(d instanceof Books){
            books = d;
        }
    }
    
    public Owner getOwner(){
        return (Owner)owner;
    }
    
    public Customers getCustomers(){
        return (Customers)costumers;
    }
    
    public Books getBooks(){
        return (Books)books;
    }
    
    abstract void display(CurrentScreen c);
}
