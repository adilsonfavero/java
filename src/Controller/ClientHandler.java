/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Mensagem;
import com.sun.security.ntlm.Client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adilson_f
 */
public class ClientHandler implements Runnable
{

    private DataInputStream bIn;
    private DataOutputStream bSaida;
    private Socket socket;

    ClientHandler(Socket socket) 
    {
        socket = new Socket();
        this.socket = socket;
        System.out.println("Cliente conectado: "+socket.getInetAddress()+ ":" + socket.getPort());
    }

    @Override
    public void run() 
    {
        try 
        {
            bIn = new DataInputStream(socket.getInputStream());
            boolean retorno = true;	
            bSaida = new DataOutputStream(socket.getOutputStream());
            while(retorno)
            {
                
            }
        } 
        catch (IOException ex) 
        {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public String sendMessage(Mensagem message)
    {
        return "";
    }
    
    
    
    
    
    
    
}
