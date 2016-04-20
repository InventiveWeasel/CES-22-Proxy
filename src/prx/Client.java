/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 *
 * @author Gabriel Brito
 */
public class Client {
    Socket soc;
    OutputStream os;
    InputStream is;
    
    public static void main(String[] args){
        Client client;
        try{
            client = new Client("127.0.0.1", 3122);
            client.request(12335);
            client.getResponse();
            client.close();
            
        }catch(IOException e){
            System.err.println("Falha na conexão do client");
        }
    }
    
    Client(String server, int port) throws IOException, UnknownHostException{
        soc = new Socket(server, port);
        os = soc.getOutputStream();
        is = soc.getInputStream();
    }
    
    void request(int n){
        try{
            String message = Integer.toString(n) + "\n\n";
            os.write(message.getBytes());
            os.flush();
        }catch(IOException e){
            System.err.println("Error in HTTP request");
        }
    }
    
    void getResponse(){
        int c;
        try{
            while((c = is.read()) != -1){
                System.out.print((char) c);
            }
        }catch(IOException e){
            System.err.println("IOException na leitura do cliente");
        }
    }
    
    public void close(){
        try{
            is.close();
            os.close();
            soc.close();
        }catch(IOException e){
            System.err.println("IOException ao fechar conexoes");
        }
    }
}
