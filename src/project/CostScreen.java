
package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CostScreen extends CustomerScreen{
    
    private int totalCost;
    
    public CostScreen(Customer c, int totalCost){
        super(c);
        this.totalCost = totalCost;
    }
    
    public VBox changeScreen(CurrentScreen screen){
        VBox paneH = new VBox(10);
        HBox paneTop = new HBox(10);
        HBox paneMid = new HBox(10);
        HBox paneBot = new HBox(10);
        
        paneH.setAlignment(Pos.CENTER);
        paneTop.setAlignment(Pos.CENTER);
        paneMid.setAlignment(Pos.CENTER);
        paneBot.setAlignment(Pos.CENTER);
        
        //top item: total cost
        Label costLabel = new Label ("Total Cost: " + totalCost);
        
        //middle item: point and status
        Label pointLabel = new Label ("Points: " + super.getCustomer().getPoints());
        Label statusLabel = new Label ("Status: " + super.getCustomer().getStatus());
        
        //bottom item: logout button
        Button btLogout = new Button("Logout");
        btLogout.setOnAction(
	    new EventHandler<ActionEvent>() {
                @Override
                    public void handle(ActionEvent e) {
                    screen.setScreen(new LogInScreen());
                    }
            } 
        );
        
        paneTop.getChildren().addAll(costLabel);
        paneMid.getChildren().addAll(pointLabel, statusLabel);
        paneBot.getChildren().addAll(btLogout);
        paneH.getChildren().addAll(paneTop, paneMid, paneBot);
        return paneH;
    }
    
    public void display(CurrentScreen screen){
        Stage primaryStage = getStage();
        StackPane rootPane = new StackPane();
        
        rootPane.getChildren().add(changeScreen(screen));
        
        Scene scene = new Scene(rootPane, 400, 400);
        primaryStage.setScene(scene); 
    }
}