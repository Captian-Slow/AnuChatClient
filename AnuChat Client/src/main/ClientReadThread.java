package main;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Amit Kumar on 15-05-2017.
 */
public class ClientReadThread implements Runnable
{
    private Socket serverSocket;
    private InputStream inputStream;
    private DataInputStream dataInputStream;

    public ClientReadThread(Socket serverSocket)
    {
        try
        {
            this.serverSocket = serverSocket;

        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void run()
    {

    }

    public JSONObject readInitialData()
    {
        try
        {
            inputStream = serverSocket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            return new JSONObject(dataInputStream.readUTF());

        }catch (Exception e){e.printStackTrace();}

        //Returns a null JSONObject to satisfy the return condition of this method.
        return new JSONObject();
    }
}
