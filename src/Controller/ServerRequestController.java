/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ServerRequest;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRequestController implements Runnable{

    private Socket conexao;

    public ServerRequestController(Socket conexao) {
        this.conexao = conexao;
    }
    
    private ServerRequest getRequest() {
        ServerRequest request =  null;
        Gson gson = new Gson();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
            
            String strQtdCarcters = buffer.readLine().trim();
            int qtdCaracteres = Integer.parseInt(strQtdCarcters);
            char caracteresRequisicao[] =  new char[qtdCaracteres];
            
            buffer.read(caracteresRequisicao, 0, qtdCaracteres);
            String JsonRequisicao = String.valueOf(caracteresRequisicao);
            request = gson.fromJson(JsonRequisicao, ServerRequest.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }
    
    private void fechaConexao() {
        try {
            this.conexao.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        ServerRequest request = this.getRequest();
        TrataRequestStrategy strategy = FactoryTrataRequestStrategy.getTrataRequest(request.getTipoRequest());
        strategy.setRequest(request);
        strategy.setConexao(this.conexao);
        strategy.processaRequest();
        strategy.respondeRequest();
        this.fechaConexao();
        
        
    }
    
}
