package main;

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Amit Kumar on 15-05-2017.
 */
public class Client
{
    private Socket socket;
    private int PORT = 8080;
    private String HOST_ADDRESS = "localhost";
    private String loginName;
    private String password;
    private ClientReadThread readThread;
    private ClientWriteThread writeThread;
    private static ArrayList<JSONObject> userList;
    private static ArrayList<String> userListNames;

    public Client(String HOST_ADDRESS, int PORT, String loginName, String password)
    {
        this.HOST_ADDRESS = HOST_ADDRESS;
        this.PORT = PORT;
        this.loginName = loginName;
        this.password = password;
        userList = new ArrayList<JSONObject>();
        userListNames = new ArrayList<String>();
    }

    //Connects to the server and calls for sending login data
    public boolean connect()
    {
        try
        {
            socket = new Socket(HOST_ADDRESS, PORT);
            readThread = new ClientReadThread(socket);
            writeThread = new ClientWriteThread(socket);
            sendLoginData();

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getUserListNames()
    {
        return userListNames;
    }

    //Sends the login data
    private void sendLoginData()
    {
        try
        {
            //Write the initial login data to the server.
            writeThread.writeLoginData(loginName, password);
            //Read the initial list of users from the server.
            JSONObject userListJson = readThread.readInitialData();
            //Storing all jsonObjects, where each object represents a user into an arrayList.
            for (Iterator keys = userListJson.keys(); keys.hasNext();)
            {
                userList.add((JSONObject) userListJson.get(keys.next().toString()));
            }
            for (int i = 0; i < userList.size(); i++)
            {
                String name = userList.get(i).getString("name");

                System.out.println(name);

                if (!name.equals(loginName))
                userListNames.add(i,name);     //Stores the name of the current iteration of the userList.
            }
            startThreads();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<JSONObject> getUserList()
    {
        return userList;
    }

    //Used to start I/O threads
    private void startThreads()
    {
        new Thread(readThread).start();
        new Thread(writeThread).start();
    }

}

