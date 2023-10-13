
package project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class LogInScreen extends ScreenType{
    private String username, password;
    
    public LogInScreen(){
        super(new Customers(), Owner.get());
    }
    
    public VBox changeScreen(CurrentScreen screen){
        HBox paneH1 = new HBox(10);
        HBox paneH2 = new HBox(10);
        VBox paneV = new VBox(10);
        
        paneH1.setAlignment(Pos.CENTER);
        paneH2.setAlignment(Pos.CENTER);
        paneV.setAlignment(Pos.CENTER);
        
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        
        Label label1 = new Label("Username: ");
        Label label2 = new Label("Password: ");
        Label label3 = new Label("Welcome to the BooksStoreApp!");
        
        Button btLogin = new Button("Login");
        
        btLogin.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                            username = text1.getText();
                            password = text2.getText();
                            if(username != null && password != null){
                                Customer account = getCustomers().get(username, password);
                                if(account == null){
                                    if(!Owner.equals(username, password)){
                                        label3.setText("Incorrect username or password.");
                                    }
                                    else{
                                        screen.setScreen(new OwnerStartScreen());
                                    }
                                }
                                else{
                                    screen.setScreen(new CustomerStartScreen(account));
                                }
                            }
                         }
              } 
        );


        paneH1.getChildren().addAll(label1, text1);
        paneH2.getChildren().addAll(label2, text2);
        paneV.getChildren().addAll(label3, paneH1, paneH2, btLogin);
        
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
