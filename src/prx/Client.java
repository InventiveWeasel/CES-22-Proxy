/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author Gabriel Brito
 */
public class Client {
    public static void main(String[] args){
        Socket client;
        try{
            client = new Socket("127.0.0.1", 3122);
        }catch(IOException e){
            System.err.println("Falha na conexão do client");
        }
    }
}
