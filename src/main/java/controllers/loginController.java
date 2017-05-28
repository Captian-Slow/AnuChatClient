package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Client;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;


public class loginController implements Initializable
{


    @FXML private PasswordField passwordField;
    @FXML private TextField loginField;
    @FXML private Text serverStatusText;
    @FXML private TextField serverIPField;
    @FXML private TextField serverPortField;
    private Client client;

    @FXML protected void loginButtonAction()
    {
        disableGui(true);
        serverStatusText.setVisible(true);
        //Creates a new client object with the fields provided
        try
        {
            client = new Client(serverIPField.getText(), Integer.parseInt(serverPortField.getText()), loginField.getText(), passwordField.getText() );

        }catch (NumberFormatException e)
        {
            serverStatusText.setText("Enter valid PORT");
            disableGui(false);
            return;
        }
        //Calls the connect method to connect to the server
        if(!client.connect())
        {
            serverStatusText.setText("Could not connect to Server");
            disableGui(false);
            return;
        }
        try
        {
            serverStatusText.setText("Connected Successfully !!");
            System.out.println("Connected");
            disableGui(true);
            //Change the scene
            Main.changeScene("chatWindow.fxml");

        }catch (Exception e){e.printStackTrace();}
    }

    //Use to initialise GUI components
    public void initialize(URL location, ResourceBundle resources)
    {
        serverStatusText.setVisible(false);
    }

    //Used to control GUI components
    private void disableGui(boolean value)
    {
        loginField.setDisable(value);
        passwordField.setDisable(value);
        serverIPField.setDisable(value);
        serverPortField.setDisable(value);
    }
}
