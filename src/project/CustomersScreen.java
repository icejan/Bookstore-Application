
package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CustomersScreen extends OwnerScreen{
       public VBox changeScreen(CurrentScreen screen){
        VBox paneV = new VBox(10);
        HBox paneH1 = new HBox(10);
        HBox paneH2 = new HBox(10);
        HBox paneH3 = new HBox(10);

        //top part -- Table View. Username-Password-Pointers
        TableView table = new TableView();
        TableColumn<Customer, String> col1 = new TableColumn<>("Username");
        TableColumn<Customer, String> col2 = new TableColumn<>("Password");
        TableColumn<Customer, String> col3 = new TableColumn<>("Points");
        col1.setCellValueFactory(new PropertyValueFactory("username"));
        col2.setCellValueFactory(new PropertyValueFactory("password"));
        col3.setCellValueFactory(new PropertyValueFactory("points"));
        col1.setMinWidth(100);
        col2.setMinWidth(100);
        col3.setMinWidth(100);
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
        TableViewSelectionModel<Customer> sel = table.getSelectionModel();
        sel.setSelectionMode(SelectionMode.SINGLE);
        
        //updateTable --> updates the visual tableview on screen.
        updateTable(table);
        
        //middle part -- username and password textfile + Add button
        TextField text1 = new TextField();
        text1.setPromptText("Username");
        TextField text2 = new TextField();
        text2.setPromptText("Password");
        Label label1 = new Label("Enter Username: ");
        Label label2 = new Label("Enter Password: ");
        Button btAdd = new Button("Add");
        
        // bottom part -- delete and back button
        Button btDelete = new Button("Delete");
        Button btBack = new Button("Back");
        
        //spacing and alignments
        paneH1.setAlignment(Pos.CENTER);
        paneH2.setAlignment(Pos.CENTER);
        paneH3.setAlignment(Pos.CENTER);
        paneV.setAlignment(Pos.CENTER);
        paneV.setSpacing(5);
          
        //Goes back to OwnerScreen
        btBack.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                             screen.setScreen(new OwnerStartScreen());
                         }
              } 
        );
        
        //Adds customer in customerarraylist and updates Tableview. Clear text
         btAdd.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                            Customer temp;
                            temp = new Customer(text1.getText(),text2.getText(),0);
                            
                            for (Customer c: getCustomers().getAll()){
                                if (!(c.getUsername()).equals(text1.getText())) {
                                    if (!(c.getPassword()).equals(text2.getText())){
                                        temp = new Customer("","",1);
                                    }
                                }
                            }
                            if (!("".equals(text1.getText()))){
                                if(!"".equals(text2.getText())){
                                    if (temp.getPoints() == 0){
                                        getCustomers().getAll().add(temp);
                                        updateTable(table); 
                                    }
                                }
                            }
                            text1.clear();
                            text2.clear();
                        }
              } 
        );
        
        //removes customer in customers array and updates TableView
        btDelete.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                            getCustomers().getAll().remove(sel.getSelectedIndex());
                            updateTable(table);
                         }
              } 
        );

        //combining the horizontal panes into the vertical one
        paneH1.getChildren().addAll(table);
        paneH2.getChildren().addAll(label1,text1,label2,text2,btAdd);
        paneH3.getChildren().addAll(btDelete,btBack);
        paneV.getChildren().addAll(paneH1,paneH2,paneH3);
        return paneV;
    }
    
    public void display(CurrentScreen screen){
        Stage primaryStage = getStage();
        StackPane rootPane = new StackPane();
        
        rootPane.getChildren().add(changeScreen(screen));
        
        Scene scene = new Scene(rootPane, 600, 400);
        primaryStage.setScene(scene); 
    }
    public void updateTable(TableView table){
        table.getItems().clear();
        for (Customer c: getCustomers().getAll()){
            table.getItems().add(c);
            //table.getItems().add(new Customer(c.getUsername(),c.getPassword(),c.getPoints()));
        }
    }
}
