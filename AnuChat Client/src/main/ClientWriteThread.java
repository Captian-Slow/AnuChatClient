package main;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Amit Kumar on 15-05-2017.
 */


public class ClientWriteThread implements Runnable
{
    private Socket socket;
    private OutputStream outputStream;
    private String message;
    private DataOutputStream dataOutputStream;

    public ClientWriteThread(Socket serverSocket)
    {
        try
        {
            this.socket = serverSocket;

        }catch (Exception e){e.printStackTrace();}

    }

    @Override
    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        try
        {
            while (true)
            {
                message = scanner.next();
                dataOutputStream.writeUTF(message);
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void writeLoginData(String loginName, String password)
    {
        try
        {
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            JSONObject loginJsonObject = new JSONObject();
            loginJsonObject.put("type", "LOGIN").put("loginName", loginName).put("loginPassword", password);
            //First sending login info to server
            dataOutputStream.writeUTF(loginJsonObject.toString());

        }catch (Exception e){e.printStackTrace();}

    }
}