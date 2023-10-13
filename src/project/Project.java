package project;

import javafx.application.Application;
import javafx.stage.Stage;

public class Project extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ScreenType.setStage(primaryStage);
    CurrentScreen myScreen = new CurrentScreen();
    myScreen.setScene();
    primaryStage.setTitle("BooksStoreApp"); 
    primaryStage.show(); // Display the stage
  }
  

  public static void main(String[] args) {
    Data.open("C:\\Users\\dumla\\OneDrive\\Documents\\NetBeansProjects\\coe528\\project\\customers_file.txt", 
            "C:\\Users\\dumla\\OneDrive\\Documents\\NetBeansProjects\\coe528\\project\\books_file.txt"); //**CHANGE THIS WHEN USING**
    launch(args);
    Data.close();
  }
}
