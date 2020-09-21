/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ServerRequest;
import Model.ServerResponse;
import Persistencia.UsuarioDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Giancarlo
 */
public class InsereUsuarioRequestStrategy implements TrataRequestStrategy {
    
    private ServerRequest request;
    private Socket conexao;
    private boolean executouInsert = false;
    
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
        UsuarioDao usuDao = new UsuarioDao();
        this.executouInsert = usuDao.insertUsuario(this.request.getUsuRequest());
    }

    @Override
    public void respondeRequest() {
         try {
            OutputStream out = this.conexao.getOutputStream();
            Gson gson = new Gson();
            
            ServerResponse resp = new ServerResponse();
            resp.setOk(this.executouInsert);
            
            String objJsonRetorno = gson.toJson(resp);
            String strRetorno =  objJsonRetorno.length()+"\n"+objJsonRetorno;
            out.write(strRetorno.getBytes());
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
