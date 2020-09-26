/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ServerRequest;
import java.net.Socket;

/**
 *
 * @author Giancarlo
 */
public class ConsultaUsuarioEmailRequestStrategy implements TrataRequestStrategy {

    private ServerRequest request;
    private Socket conexao;
    

    @Override
    public void setRequest(ServerRequest req) {
        this.request = req;
    }

    @Override
    public void setConexao(Socket conexao) {
        this.conexao = conexao;
    }

    @Override
    public void processaRequest() {
        
    }

    @Override
    public void respondeRequest() {
        
    }
    
}
