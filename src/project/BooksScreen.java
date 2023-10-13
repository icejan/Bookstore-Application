
package project;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BooksScreen extends OwnerScreen{
    
    public VBox changeScreen(CurrentScreen screen){
        VBox paneV = new VBox(10);
        HBox paneHtop = new HBox (10);
        HBox paneHmid = new HBox (10);
        HBox paneHbot = new HBox (10);
        
        paneV.setAlignment(Pos.BOTTOM_CENTER);

        //Top part: TableView of two columns: Book Name, Book Price
        Label label = new Label("Books");
        label.setFont(new Font("Arial", 20));
        
        TableView table = new TableView();
        
        TableColumn<Book, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(300);
        nameCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Book, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));
        priceCol.setMinWidth(100);
        priceCol.setStyle("-fx-alignment: CENTER;");
        
        table.getColumns().addAll(nameCol,priceCol);
        
        TableViewSelectionModel<Book> s = table.getSelectionModel();
        s.setSelectionMode(SelectionMode.SINGLE);
        
        //initialize table
        for (Book b1 : getBooks().getAll()){
            table.getItems().add(b1);
        }
        
        //Middle part: book name and book price text fields and an add button
        TextField addBookName = new TextField();
        addBookName.setPromptText("Book Name");
        addBookName.setMaxWidth(nameCol.getPrefWidth());
        
        TextField addBookPrice = new TextField();
        addBookPrice.setMaxWidth(priceCol.getPrefWidth());
        addBookPrice.setPromptText("Book Price");
        
        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                Book b;
                b = new Book(addBookName.getText(), Integer.parseInt(addBookPrice.getText()));
                getBooks().getAll().add(b);
                table.getItems().add(b);
            }
        });
        
        //Bottom part: delete and back button
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                getBooks().getAll().remove(s.getSelectedIndex());
                table.getItems().remove(s.getSelectedIndex()); 
            }
        });
        
        //Back to ownerscreen
        Button btBack = new Button("Back");
        btBack.setOnAction(
	       new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent e) {
                             screen.setScreen(new OwnerStartScreen());
                         }
              } 
        );
        
        paneHtop.setAlignment(Pos.CENTER);
        paneHmid.setAlignment(Pos.CENTER);
        paneHbot.setAlignment(Pos.CENTER);
        paneV.setSpacing(5);

        paneHtop.getChildren().addAll(table);
        paneHmid.getChildren().addAll(addBookName, addBookPrice, addButton);
        paneHbot.getChildren().addAll(deleteButton, btBack);
        paneV.getChildren().addAll(label,paneHtop,paneHmid,paneHbot);
        
        return paneV;
    }
    
    public void display(CurrentScreen screen){
        Stage primaryStage = getStage();
        StackPane rootPane = new StackPane();

        
        rootPane.getChildren().add(changeScreen(screen));
        
        Scene scene = new Scene(rootPane, 600, 400);
        primaryStage.setScene(scene); 
    }
    
}
