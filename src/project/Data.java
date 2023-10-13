package project;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public abstract class Data {
    private static String book_file;
    private static String customers_file;
    static Customers c = new Customers();
    static Books x = new Books();
    
    public static void open(String file1, String file2){
        customers_file = file1;
        book_file = file2;
        //creating arraylists for both books and customers
        Customers d = new Customers();
        Books b = new Books();
        
        /* reading customer file */
        Scanner sc1;
        try{
        File customerf = new File(customers_file);
        sc1 = new Scanner(customerf);
        while (sc1.hasNextLine()){
            String password = "123";
            int points = 1;
            //reading from customer file and assigning values in file to customer variables
            String username = sc1.nextLine();
            if (sc1.hasNextLine()){
            password = sc1.nextLine();
            }
            if (sc1.hasNextLine()){
            points = Integer.parseInt(sc1.nextLine());
            }
            //adding new customer to customers Arraylist
            d.add(new Customer(username, password, points));
        }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
       
        
        
        
       
        /* scanning book files */
        Scanner sc2 = null;
        try{
            File book = new File(book_file);
            // new scanner
            sc2 = new Scanner(book);
            while (sc2.hasNextLine()){ // while the book file still has another line
                int price=1;
                String name = sc2.nextLine();
                if (sc2.hasNextLine()){
                price = Integer.parseInt(sc2.nextLine());
                }
                // creating a new book inside book arraylist
                b.add(new Book(name, price));
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        // creating the owner
        Owner.add("admin", "admin");        
        
        c = d;
        x=b;
        
    }
    
    
    public static void close(){
        
        
        //Writing into Customer File
        File file = new File(customers_file); 
        FileWriter fr = null;
        BufferedWriter br = null;
        try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            for(Customer i: c.getAll()){
                br.write(i.getUsername());
                br.write("\n");
                br.write(i.getPassword());
                br.write('\n');
                br.write(Integer.toString(i.getPoints()));
                br.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Writing into books File
        File file2 = new File(book_file); 
        FileWriter fr2 = null;
        BufferedWriter br2 = null;
        try{
            fr2 = new FileWriter(file2);
            br2 = new BufferedWriter(fr2);
            for(Book i: x.getAll()){
                br2.write(i.getName());
                br2.write("\n");
                br2.write(Integer.toString(i.getPrice()));
                br2.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br2.close();
                fr2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
