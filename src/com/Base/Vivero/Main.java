package com.Base.Vivero;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Scene scene;
	public static Stage primaryStage;
	public static FXMLLoader fxmlLoader;
	
	@Override
	public void start(Stage stage) {
		Main.primaryStage = stage;
		try {
			scene = new Scene(loadFXML("Login"));
			primaryStage.centerOnScreen();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setFXML(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

	private static Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(Main.class.getResource("View/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
