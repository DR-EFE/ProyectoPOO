package Model;

import java.io.IOException;

import Controller.Main;
import Controller.SampleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ventanas {

    /*
     * public void mostrarVentana(String fxmlPath, String title, double width,
     * double height, String cssPath) {
     * try {
     * FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
     * AnchorPane root = loader.load();
     * 
     * Stage primaryStage = new Stage();
     * primaryStage.setTitle(title);
     * primaryStage.setFullScreen(true);
     * 
     * Scene scene = new Scene(root, width, height);
     * scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
     * 
     * primaryStage.setScene(scene);
     * primaryStage.show();
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     */

    public void MostrarPane(String fxmlPath) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage primaryStage = new Stage();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
          primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void MostrarPane2(String fxmlPath) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage primaryStage = new Stage();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
          //primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
            
         
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

   
      public void mostrarVentana(String fxmlPath, String title) {
      try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
      Parent root = loader.load();
      
      Stage primaryStage = new Stage();
      primaryStage.setTitle(title);
      
      Scene scene = new Scene(root);
     primaryStage.setScene(scene);
      primaryStage.show();
      
      SampleController controlador = loader.getController();
   
     
      } catch (Exception e) {
      e.printStackTrace();
      }
      
      
      }
     

}
