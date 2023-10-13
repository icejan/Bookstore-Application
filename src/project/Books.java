
package project;

import java.util.ArrayList;


public class Books extends Data{
    private static ArrayList<Book> books = new ArrayList<>();
    
    public void add(Book b){
        books.add(b);
    }
    
    public void delete(Book b){
        books.remove(b);
    }
    
    public ArrayList<Book> getAll(){
        return (ArrayList<Book>)books;
    }
}
