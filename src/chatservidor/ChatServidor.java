/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatservidor;

import Controller.ServerRequestController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(56000);
        server.setReuseAddress(true);
        while (true) {
            try {
                Socket conn = server.accept();
                ServerRequestController controller = new ServerRequestController(conn);
                Thread th = new Thread(controller);
                th.start();
            }
            catch (Exception ex) {  
                ex.printStackTrace();
            }
        }
            
    }
    
}
