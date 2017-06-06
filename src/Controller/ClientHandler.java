/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.Socket;

/**
 *
 * @author adilson_f
 */
public class ClientHandler {

    public ClientHandler(Socket accept) 
    {
        
    }
    
    public void run()
    {
        // Cria um buffer que armazenará as informações de entrada do teclado  
                BufferedReader inFromUSer = new BufferedReader(new InputStreamReader(System.in));  
                // Cria um stream de saída   
                DataOutputStream outToServer = new DataOutputStream(Client.getOutputStream());  
                // Cria um buffer que armazenará as informações retornadas pelo servidor  
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(Client.getInputStream()));  
                // Atribui as informações armazenadas no buffer do teclado à variável "sentence"  
                sentence = inFromUSer.readLine();  
                // Disponibiliza as informações contidas em "sentence" para a stream de saída do cliente  
                outToServer.writeBytes(sentence + "\n");  
                // Atribui as informações modificadas pelo servidor na variável "modifiedSentence"  
                modifiedSentence = inFromServer.readLine();  
                // Imprime no console do cliente a informação retornada pelo servidor  
                System.out.println("do servidor: " + modifiedSentence);  
                // Fecha o Socket  
              Client.close(); 
    }
    
}
