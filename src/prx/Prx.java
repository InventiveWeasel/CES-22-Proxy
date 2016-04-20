package prx;

import java.io.*;
import java.net.*;

/**
 *
 * @author Gabriel Brito
 */
public class Prx {
    //O proxy eh servidor e cliente ao mesmo tempo
    Socket socClient, socServer;
    InputStream isClient;
    BufferedReader isServer;
    OutputStream osServer, osClient;
    
    int resource;
    static int PROXY_PORT = 4000;
    static int SERVER_PORT = 3122;
    
    public static void main(String[] args) {
        try{
            ServerSocket s = new ServerSocket(PROXY_PORT);
            System.out.println("Proxy iniciado na porta "+Integer.toString(PROXY_PORT));
            System.out.println("Porta = "+s.getLocalPort());
            Prx proxy = new Prx(SERVER_PORT, PROXY_PORT, "127.0.0.1", s.accept());
            
            System.out.println("Cliente conectado do IP "+s.getInetAddress().getHostAddress());
            
            String clientRequest = proxy.getClientRequest();
            proxy.sendClientRequest(clientRequest);
            
        }catch (IOException e){
            System.err.println("Erro no proxy");
        }
    }
    
    Prx(int serverPort, int proxyPort, String localIP, Socket s){
        try{
            socServer = s;
            socClient = new Socket(localIP, serverPort);
            
            isClient = socClient.getInputStream();
            osClient = socClient.getOutputStream();
            
            isServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
            osServer = s.getOutputStream();
        }catch(IOException e){
            System.err.println("Erro na criacao do proxy");
        }
    }
    
    String getClientRequest(){
        String resource = "";
        try{
            String message;
            while((message=isServer.readLine()) != null){
                if(message.equals(""))
                    break;
                System.err.println(message);
                resource = resource + message;
            }
        }catch(IOException e){
            System.err.println("Erro ao receber a requisição");
        }
        return resource+"\n\n";
    }
    
    void sendClientRequest(String message){
        PrintStream printer = new PrintStream(osClient);
        printer.print(message);
    }
}
