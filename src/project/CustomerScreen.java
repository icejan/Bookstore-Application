
package project;

public abstract class CustomerScreen extends ScreenType{
    private Customer customer;
    
    public CustomerScreen(Customer c){
        super(new Books());
        customer = c;
    }
    
    public Customer getCustomer(){
        return customer;
    }
}
