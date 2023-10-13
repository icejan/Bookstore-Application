
package project;


public class Owner extends Data{
    private static Owner owner = null;
    private String username, password;
    
    private Owner(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public static void add(String username, String password){
        if(owner == null){
            owner = new Owner(username, password);
        }
    }
    
    public static Owner get(){
        return owner;
    }
    
    public static boolean equals(String username, String password){
        return username.equals(owner.getUsername()) && password.equals(owner.getPassword());
    }
}
