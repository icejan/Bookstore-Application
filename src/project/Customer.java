
package project;

public class Customer {
    private String username, password;
    private String status;
    private int points;
    
    
    public Customer(String username, String password, int points){
        this.username = username;
        this.password = password;
        this.points = points;
        setStatus(points);
    }
    
    public int getPoints(){
        return points;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    
    public void setStatus(int points){
        if (points >= 1000){
            this.status = "Gold";
        } else {
            this.status = "Silver";
        }
    }
    
    public boolean equals(String username, String password){
        return username.equals(this.username) && password.equals(this.password);
    }

}
