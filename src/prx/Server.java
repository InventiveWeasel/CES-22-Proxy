/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx;

import java.io.*;
import java.net.*;

/**
 *
 * @author Ana Cuder
 */
public class Server {
    OutputStream os;
    InputStream is;
    
    
    public static void main(String[] args){
        try{
            ServerSocket server = new ServerSocket(3122);
            System.out.println("Servidor iniciado na porta 3122");
            System.out.println("Porta = "+server.getLocalPort());
           
            
            //Se o cliente conseguir se conectar
            System.out.println("Cliente conectado do IP "+client.getInetAddress().getHostAddress());
            
            server.close();
        }catch(IOException e){
            System.err.println("IOException in Server");
        }
    }
    
    private void getRequest(){
        
    }
}
