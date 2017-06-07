/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author adilson_f
 */
public class Server
{
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private BufferedReader bEntrada;
    private DataOutputStream bSaida;
    private static final Logger LOGGER = Logger.getLogger("LOG");
    private DataInputStream bIn;
    
    public Server(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        LOGGER.info("Log Habilitado");
        System.out.println("Servidor de comunicação iniciado na porta: "+serverSocket.getLocalPort());
    
        try 
        {
            while(true)
            {
                clientSocket = new Socket();
                clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket);
                System.out.println("Accept");
                Thread t = new Thread(handler);
                t.start();   
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Erro: "+ e.getMessage());
        }
 
    }
}
 