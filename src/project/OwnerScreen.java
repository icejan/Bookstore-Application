
package project;

public abstract class OwnerScreen extends ScreenType{
    public OwnerScreen(){
        super(new Books(), new Customers());
    }
}
