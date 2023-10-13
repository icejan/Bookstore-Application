
package project;

import java.util.ArrayList;

public class Customers extends Data{
    private static ArrayList<Customer> customers = new ArrayList<>();
    
    public void add(Customer c){
        customers.add(c);
    }
    
    public void delete(Customer c){
        customers.remove(c);
    }
        
    public ArrayList<Customer> getAll(){
        return (ArrayList<Customer>)customers;
    }
 
    public Customer get(String username, String password){
        for(Customer i:customers){
            if(i.equals(username, password)){
                return i;
            }
        }
        return null;
    }
}
