package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import main.Client;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Amit Kumar on 25-05-2017.
 */
public class chatWindowController implements Initializable
{
    @FXML private ListView userChat;
    @FXML private ListView<String> userList;
    private ObservableList<String> observableList = FXCollections.observableArrayList(Client.getUserListNames());

    public void initialize(URL location, ResourceBundle resources)
    {
        userList.setItems(observableList);
    }
}
