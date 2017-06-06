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
public class Server implements Runnable
{
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    Thread listener = null;
    private String serial;
   // private DataInputStream bEntrada;
    private BufferedReader bEntrada;
    private DataOutputStream bSaida;
    private static final Logger LOGGER = Logger.getLogger("LOG");
    private DataInputStream bIn;
    /***
     * test of client-driven subnegotiation.
     * 


     * @param port - server port on which to listen.
     ***/
    public Server(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        listener = new Thread(this);
        listener.start();
        LOGGER.info("Log Habilitado");
        
    }

    /***
     * Run for the thread. Waits for new connections
     ***/
    public void run()
    {
        System.out.println("Servidor de comunicação iniciado na porta: "+serverSocket.getLocalPort());
        int i = 0;
        boolean bError = false;
        while(!bError)
        {
            try
            {
                clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket);
                Thread t = new Thread((Runnable) handler);
                t.start();
                
            }
            catch (IOException e)
            {
                bError = true;
            }
        }

        try
        {
            serverSocket.close();
        }
        catch (Exception e)
        {
            System.err.println("Exception in close, "+ e.getMessage());
        }
    }
    
    /*
    
    System.out.println("Cliente conectado...: "+clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                bIn = new DataInputStream(clientSocket.getInputStream());	
                bSaida = new DataOutputStream(clientSocket.getOutputStream());
                
                Usuario u = new Usuario();
                u.setUsUsuario(bIn.readUTF());
                u.setUsSenha(bIn.readUTF());
    */
    
    
    
    
    /***
     * Disconnects the client socket
     ***/
    public void disconnect()
    {
        synchronized (clientSocket)
        {
            try
            {
                clientSocket.notify();
            }
            catch (Exception e)
            {
                System.err.println("Exception in notify, "+ e.getMessage());
            }
        }
    }

    /***
     * Stop the listener thread
     ***/
    public void stop()
    {
        listener.interrupt();
        try
        {
            serverSocket.close();
        }
        catch (Exception e)
        {
            System.err.println("Exception in close, "+ e.getMessage());
        }
    }

    /***
     * Gets the input stream for the client socket
     ***/
    public InputStream getInputStream() throws IOException
    {
        if(clientSocket != null)
        {
            return(clientSocket.getInputStream());
        }
        else
        {
            return(null);
        }
    }

    /***
     * Gets the output stream for the client socket
     ***/
    public OutputStream getOutputStream() throws IOException
    {
        if(clientSocket != null)
        {
            return(clientSocket.getOutputStream());
        }
        else
        {
            return(null);
        }
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
 