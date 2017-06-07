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

    public static void criaInstanciaSocket(final Integer porta) throws IOException
    { 
       Server server = new Server(porta);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException  {
        criaInstanciaSocket(9999);
    }
    
}
