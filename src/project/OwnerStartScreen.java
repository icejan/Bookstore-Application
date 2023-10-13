
package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class OwnerStartScreen extends OwnerScreen{
    public VBox changeScreen(CurrentScreen screen){
        VBox paneV = new VBox(10);
        
        paneV.setAlignment(Pos.CENTER);
        
        Button btLogout = new Button("Logout");
        Button btBooks = new Button("Books");
        Button btCustomers = new Button("Customers");
        
        btBooks.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                             screen.setScreen(new BooksScreen());
                         }
              } 
        );
        
        btCustomers.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                             screen.setScreen(new CustomersScreen());
                         }
              } 
        );
        
        btLogout.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                             screen.setScreen(new LogInScreen());
                         }
              } 
        );


        paneV.getChildren().addAll(btBooks, btCustomers, btLogout);
        
        return paneV;
    }
    
    public void display(CurrentScreen screen){
        Stage primaryStage = getStage();
        StackPane rootPane = new StackPane();
        
        rootPane.getChildren().add(changeScreen(screen));
        
        Scene scene = new Scene(rootPane, 400, 400);
        primaryStage.setScene(scene); 
    }
    
}
