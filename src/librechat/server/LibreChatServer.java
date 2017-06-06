/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librechat.server;

import Controller.Server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adilson_f
 */
public class LibreChatServer {

    public static void criaInstanciaSocket(final Integer porta)
    {
        new Thread()
        {
            @Override
            public void run() {
                try {
                    Server server = new Server(porta);
                    //leitora.runServer(porta);
                } catch (IOException ex) {
                    //Logger.getLogger(IServer.class.getName()).log(Level.ALL, null, ex);
                }
            }
            
        }.start();
                
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        criaInstanciaSocket(9999);
    }
    
}
