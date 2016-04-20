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
    BufferedReader is;
    Socket client;
    int resource;
    
    public static void main(String[] args){
        try{
            ServerSocket s = new ServerSocket(3122);
            System.out.println("Servidor iniciado na porta 3122");
            System.out.println("Porta = "+s.getLocalPort());
            Server server = new Server(s.accept());
            
            //Se o cliente conseguir se conectar
            System.out.println("Cliente conectado do IP "+s.getInetAddress().getHostAddress());
            
            server.getRequest();
            server.returnResponse();
        }catch(IOException e){
            System.err.println("IOException in Server");
        }
    }
    
    Server(Socket s) throws IOException{
        client = s;
        os = s.getOutputStream();
        is = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    
    void getRequest(){
        try{
            String message;
            while((message=is.readLine()) != null){
                if(message.equals(""))
                    break;
                System.err.println(message);
                resource = Integer.valueOf(message);
            }
        }catch(IOException e){
            System.err.println("Erro ao receber a requisição");
        }
    }
    
    void returnResponse(){
        PrintStream printer = new PrintStream(os);
        //try{
            if(resource%2 == 0){
                printer.print("PAR");
                //f = new FileInputStream("Par");
                //while((c = f.read()) != -1)
                //    os.write(c);
            }
            else{
                printer.print("IMPAR");
                //f = new FileInputStream("Impar");
                //while((c = f.read()) != -1)
                //    os.write(c);
            }
            printer.close();
        //}catch(IOException e){
        //    System.err.println("Erro ao responder a solicitacao");
        //}
    }
}
