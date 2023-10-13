
package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class CustomerStartScreen extends CustomerScreen{
    
    public CustomerStartScreen(Customer c) {
        super(c);
    }
    
    public VBox changeScreen(CurrentScreen screen){
        VBox paneV = new VBox(10);
        HBox paneH1 = new HBox(10);
        HBox paneH2 = new HBox(10);
     
        Customer c2 = super.getCustomer();
        
        //top -- label
        Label label1;
        if (c2.getPoints()>= 1000){
            label1 = new Label("Welcome " + c2.getUsername() + 
                    ". You have " + c2.getPoints() + " points. Your "
                            + "status is Gold.");
        } else {
            label1 = new Label("Welcome " + c2.getUsername() + 
                    ". You have " + c2.getPoints() + " points. Your "
                            + "status is silver.");
        }
        
        //middle -- table
        TableView table = new TableView();
                
        TableColumn<Book, String> col1 = new TableColumn("Book Name");
        TableColumn<Book, String> col2 = new TableColumn("Price ($)");
        TableColumn<Book, Boolean> col3 = new TableColumn("Select");     
        col1.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setCellValueFactory(new PropertyValueFactory("price"));
        col3.setCellValueFactory(new PropertyValueFactory("box"));
        col1.setMinWidth(200);
        col2.setMinWidth(200);
        col3.setMinWidth(200); 
        col1.setSortable(false);
        col2.setSortable(false);
        col3.setSortable(false);
        col1.setResizable(false);
        col2.setResizable(false);
        col3.setResizable(false);
        col1.setStyle("-fx-alignment: CENTER;");
        col2.setStyle("-fx-alignment: CENTER;");
        col3.setStyle("-fx-alignment: CENTER;");
        table.getColumns().addAll(col1,col2,col3);
        
        for (Book b: getBooks().getAll()){
            table.getItems().add(b);
            //table.getItems().add(new Book(b.getName(),b.getPrice()));
        }
                
        //bottom -- buy, redeem and buy, logout
        Button btBuy = new Button("Buy");
        Button btBuyRedeem = new Button("Redeem points and Buy");
        Button btLogout = new Button("Logout");
        
        paneH1.setAlignment(Pos.CENTER);
        paneH2.setAlignment(Pos.CENTER);
        paneV.setAlignment(Pos.CENTER);
        paneV.setSpacing(5);
        
        btLogout.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                            screen.setScreen(new LogInScreen());
                         }
              } 
        );
        
        btBuyRedeem.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                            int tc = 0;
                            ArrayList<Book> toDelete = new ArrayList<>();
                            for (Book b : getBooks().getAll()){
                                if (b.getBox().isSelected()){
                                    toDelete.add(b);
                                    tc += b.getPrice();
                                }                         
                                b.getBox().setSelected(false);
                            }
                            
                            for(Book b:toDelete){
                                getBooks().delete(b);
                            }
                            
                            //lose points
                            if (c2.getPoints() < (tc*100)){
                                tc = tc - (c2.getPoints()/100);
                                c2.setPoints(0);
                            } else {
                                c2.setPoints(c2.getPoints()-(tc*100));
                                tc = 0;
                                screen.setScreen(new CostScreen(getCustomer(),tc));
                            }

                            //cant redeem points if tc = 0;
                            
                            //update status
                            c2.setStatus(c2.getPoints());
                            
                            if (tc != 0){ // if no books are selected, nothing happens
                                screen.setScreen(new CostScreen(getCustomer(),tc));
                            } 
                         }
              } 
        );
        
        btBuy.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                            int tc = 0;
                            ArrayList<Book> toDelete = new ArrayList<>();
                            for (Book b : getBooks().getAll()){
                                if (b.getBox().isSelected()){
                                    toDelete.add(b);
                                    tc += b.getPrice();
                                }                  
                                b.getBox().setSelected(false);

                            }
                            
                            for(Book b:toDelete){
                                getBooks().delete(b);
                            }
                            
                            //update points --> points + tc*10
                            c2.setPoints(c2.getPoints()+tc*10);
                            
                            //update status
                            c2.setStatus(c2.getPoints());
                            
                            if (tc != 0){ // if no books are selected, nothing happens
                                screen.setScreen(new CostScreen(getCustomer(),tc));
                            } 
                         }
              } 
        );

        paneH1.getChildren().add(label1);
        paneH2.getChildren().addAll(btBuy, btBuyRedeem, btLogout);
        paneV.getChildren().addAll(paneH1,table,paneH2);
        return paneV;
    }
    
    public void display(CurrentScreen screen){
        Stage primaryStage = getStage();
        StackPane rootPane = new StackPane();
        
        rootPane.getChildren().add(changeScreen(screen));
        
        Scene scene = new Scene(rootPane, 605, 400);
        primaryStage.setScene(scene); 
    }
}
