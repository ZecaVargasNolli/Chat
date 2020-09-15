/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ServerRequest;
import java.net.Socket;

public interface TrataRequestStrategy {
    
    public void setRequest(ServerRequest req);
    public void setConexao(Socket conexao);
    public void processaRequest();
    public void respondeRequest();
    
}
