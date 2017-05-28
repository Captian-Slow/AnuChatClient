package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application
{
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception
    {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Anu Chat - Login");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void changeScene(String sceneName)
    {
        try
        {
            Parent root = FXMLLoader.load(Main.class.getResource("/" +sceneName));
            primaryStage.setScene(new Scene(root, 600, 400));

        }catch (Exception   e){ e.printStackTrace();}
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
