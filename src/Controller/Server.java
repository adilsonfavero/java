/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
                System.out.println("Cliente conectado...: "+clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                //bEntrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                bIn = new DataInputStream(clientSocket.getInputStream());
                System.out.println("Status de recebimento: "+clientSocket.getRemoteSocketAddress());
                System.out.println("Status de recebimento:"+clientSocket.getInputStream());
                boolean retorno = true;	
                bSaida = new DataOutputStream(clientSocket.getOutputStream());
                String txt = null;
                String r = "False";
                    try
                    {
                        bSaida.write(("OK").getBytes());
                        bSaida.flush();
                        //r = bEntrada.readLine();
                        System.out.println("Recebe aceito1 - Inicio de processo");
                        r = bIn.readUTF();
                        System.out.println("Recebe aceito1 "+r);
                        while(retorno)
                        {
                            String posicao = "";
                            String sequencia = "";
                            String formacao = "";
                            //bSaida.write(("Verifica_Paletes").getBytes());
                            //bSaida.flush();
                            /*Inicio do processo de leitura da banda - Robo sempre envia PALNOK inicializand o processo*/
                            bSaida.write(("PALNOK").getBytes());
                            bSaida.flush();
                            //r = bEntrada.readLine();
                            
                            bSaida.write(("Verifica_Paletes").getBytes());
                            bSaida.flush();
                            
                            
                            
                                /*Verifica Fecha Pallets*/
                                 /*   
                                bSaida.write(("Verifica_Paletes").getBytes());
                                bSaida.flush();
                                r = bEntrada.readLine(); //Recebe False
                                bSaida.write(("Ver1").getBytes());
                                bSaida.flush();
                                r = bEntrada.readLine();
                                bSaida.write(("Ver2").getBytes());
                                bSaida.flush();
                                r = bEntrada.readLine();
                                bSaida.write(("Ver3").getBytes());
                                bSaida.flush();
                                r = bEntrada.readLine();
                                bSaida.write(("Ver4").getBytes());
                                bSaida.flush();*/
                                
                                System.out.println("True: "+r);
                                 
                                if(r == "True")
                                {
                                    bSaida.write(("Zerar").getBytes());
                                    bSaida.flush();
                                    if(bEntrada.readLine() == "Aceito2")
                                    {
                                        System.out.println("Erro ao palletizar");
                                    }
                                    else
                                    {
                                        bSaida.write(("FP:True;False;False;False;False").getBytes());
                                        bSaida.flush();
                                    }
                                   
                                }
                                else
                                {
                                     bSaida.write(("Ver1").getBytes());
                                     bSaida.flush();
                                }
                               
                                //*Verifica pallet 2
                                r = bEntrada.readLine();
                                if(r == "True")
                                {
                                    bSaida.write(("Zerar").getBytes());
                                    bSaida.flush();
                                    if(bEntrada.readLine() == "Aceito2")
                                    {
                                        System.out.println("Erro ao palletizar");
                                    }
                                    else
                                    {
                                        bSaida.write(("FP:False;True;False;False;False").getBytes());
                                        bSaida.flush();
                                    }
                                }
                                else
                                {
                                    //*Verifica pallet 3
                                bSaida.write(("Ver2").getBytes());
                                bSaida.flush();
                                }
                                r = bEntrada.readLine();
                                if(r == "True")
                                {
                                    bSaida.write(("Zerar").getBytes());
                                    bSaida.flush();
                                    if(bEntrada.readLine() == "Aceito2")
                                    {
                                        System.out.println("Erro ao palletizar");
                                    }
                                    else
                                    {
                                        bSaida.write(("FP:False;False;True;False;False").getBytes());
                                        bSaida.flush();
                                    }
                                }
                                else
                                {
                                    //*Verifica pallet 4
                                bSaida.write(("Ver3").getBytes());
                                bSaida.flush();
                                }
                                r = bEntrada.readLine();
                                if(r == "True")
                                {
                                    bSaida.write(("Zerar").getBytes());
                                    bSaida.flush();
                                    if(bEntrada.readLine() == "Aceito2")
                                    {
                                        System.out.println("Erro ao palletizar");
                                    }
                                    else
                                    {
                                        bSaida.write(("FP:False;False;False;True;False").getBytes());
                                        bSaida.flush();
                                    }
                                }
                                else
                                {
                                    //Verifica pallet 5
                                bSaida.write(("Ver4").getBytes());
                                bSaida.flush();
                                }
                                r = bEntrada.readLine();
                                if(r == "True")
                                {
                                    bSaida.write(("Zerar").getBytes());
                                    bSaida.flush();
                                    if(bEntrada.readLine() == "Aceito2")
                                    {
                                        System.out.println("Erro ao palletizar");
                                    }
                                    else
                                    {
                                        bSaida.write(("FP:False;False;False;False;True").getBytes());
                                        bSaida.flush();
                                    }
                                }
                                
                                    /*Consulta Dados*/
                                    bSaida.write(("Consulta_Dados").getBytes());
                                    bSaida.flush();
                                    posicao = bEntrada.readLine();
                                    bSaida.write(("V1").getBytes());
                                    bSaida.flush();
                                    sequencia = bEntrada.readLine();
                                    bSaida.write(("V2").getBytes());
                                    bSaida.flush();
                                    formacao = bEntrada.readLine();
                                    bSaida.write(("TRUE").getBytes());
                                    bSaida.flush();

                                    /*Paletiza*/
                                    bSaida.write(("PALOK").getBytes());
                                    bSaida.flush();

                                    System.out.println("Iteração de armazenamento terminada\n");
                                    retorno = true; 
                                
                            
                        }
                        
                        /**
                         * Sequencia do robo - programa Totvs
                         * PI-testa-Robo: OK
                         * PI-TRATA-RETORNO_ROBO: 
                         *  - CONEXÃO: "OK"
                         *  - VERIFICA_PALLETS: "Verifica_Pallets" Posicao 1
                            *  - Verificacao de pallets: 
                            *                   * "ver1"  (Verifica pallets fechados(true/false) Posicao 2
                            *                   * "ver2"  (Verifica pallets fechados(true/false) 3
                            *                   * "ver3"  (Verifica pallets fechados(true/false) 4
                            *                   * "ver4"  (Verifica pallets fechados(true/false) 5
                            *                   * "ver5"  (Verifica pallets fechados(true/false)
                         *Consulta dados: "consulta_dados" (envia a posicao)
                         *Sequencia: "v1"
                         * Formacao: "v2"
                         * Paletizacao OK: "PALOK" Robo vai receber ("Aceito1").
                         * Paletizacao NOK: PALNOK Robo vai receber ("Aceito1").
                         * Zerar: "Zerar" , Robo vai receber ("Aceito2").
                         * If retorno do robo inicia por "FP="  Robo recebe ("Aceito3")
                         * 
                         */
                        
                    }
                    catch (Exception e)
                    {
                        System.err.println("Exception in wait, "+ e.getMessage());
                    }
                    try
                    {
                        clientSocket.close();
                    }
                    catch (Exception e)
                    {
                        System.err.println("Exception in close, "+ e.getMessage());
                    }
                    System.out.println("Saiu");
                //}
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
 /*private void execucao(BufferedReader bEntrada, DataOutputStream bSaida) throws IOException, InterruptedException
    {
        String txt = bEntrada.readLine();
        System.out.println("Totvs Solicita: "+txt+"\n");
        //Timer
        int i = 0;
        
        if(i / 1000000000 == 0)
        {
            //JOptionPane.showMessageDialog(null, "Totvs enviou: "+txt);
            System.out.println("Totvs Enviou: "+txt+"\n" );
            switch(txt)
            {
                case "+":
                {
                    System.out.println("Leitora Habilitada");
                    execucao(bEntrada, bSaida);
                }
                case "-":
                {
                    System.out.println("Leitora Desabilitada");
                    execucao(bEntrada, bSaida);
                }
                case "Num_Pallet_s":
                {
                    System.out.println("Totvs solicitou Num_Pallet_s");
                    ///*Retorna V1
                    this.enviaParaTotvs(bSaida, "V1");
                    execucao(bEntrada, bSaida);
                    //this.execucao(bEntrada, bSaida);
                    //bSaida.write("V1".getBytes());
                }
                break;
                
                case "Seq_Banda_s":
                {
                    System.out.println("Totvs solicitou Seq_Banda_s");
                    ///*Retorna V2
                    bSaida.write("V2".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                case "Formacao_s":
                {
                    System.out.println("Totvs solicitou Formacao_s");
                    ///*Retorna Palletização OK
                    bSaida.write("TRUE".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                case "Aceito1":
                {
                    System.out.println("Totvs solicitou Aceito 1");
                    ///*Retorna Palletização OK
                    this.enviaParaTotvs(bSaida, "PALNOK");
                    execucao(bEntrada, bSaida);
                    //bSaida.write("PALNOK".getBytes());
                }
                break;
                
                case "Fecha Pallet1":
                {
                    System.out.println("Totvs solicitou Fecha Pallet1");
                    ///*Retorna Palletização OK
                    
                    bSaida.write("Ver1".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                case "Fecha Pallet2":
                {
                    System.out.println("Totvs solicitou Fecha Pallet2");
                    ///*Retorna Palletização OK
                    
                    bSaida.write("Ver2".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                case "Fecha Pallet3":
                {
                    System.out.println("Totvs solicitou Fecha Pallet3");
                    ///*Retorna Palletização OK
                    
                    bSaida.write("Ver3".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                case "Fecha Pallet4":
                {
                    System.out.println("Totvs solicitou Fecha Pallet4");
                    ///*Retorna Palletização OK
                    
                    bSaida.write("Ver4".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                case "Fecha Pallet5":
                {
                    System.out.println("Totvs solicitou Fecha Pallet5");
                    ///*Retorna Palletização OK
                    
                    bSaida.write("FP=".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                case "Aceito2":
                {
                    System.out.println("Totvs solicitou Aceito2");
                   ///*Retorna Palletização OK
                    
                    bSaida.write("FP=".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                case "Aceito3":
                {
                    System.out.println("Totvs solicitou Aceito2");
                    ///*Retorna Palletização OK
                    bSaida.write("FP=".getBytes());
                    execucao(bEntrada, bSaida);
                }
                break;
                
                
                
                
                
                
                case "status_proc":
                {
                    System.out.println("Status_proc");
                    //bSaida.write("PALOK".getBytes());
                    this.enviaParaTotvs(bSaida, "OK");
                    execucao(bEntrada, bSaida);
                    /*retorno = bEntrada.readLine();
                    if(retorno.equals("Aceito1"))
                    {

                    }
                    Thread.sleep(5000);
                };
                break;
                case "Consulta Dados":
                {
                    System.out.println("Robo enviou Consulta Dados");
                    this.enviaParaTotvs(bSaida, "OK");
                    execucao(bEntrada, bSaida);
                    //bSaida.write("OK".getBytes());
                }
                break;

                
            }
        }
    }*/