/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.OnlineUserControl;
import Model.ServerRequest;
import Model.ServerResponse;
import Model.Session;
import Model.Usuario;
import Persistencia.UsuarioDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Giancarlo
 */
public class NotificaOnlineRequestStrategy implements TrataRequestStrategy {

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
        Usuario usuVerificar = this.request.getUsuRequest();
        OnlineUserControl onUsuCtrl = OnlineUserControl.getInstance();
        Session sessionUsuAux = null;
        Session sessionUsu = onUsuCtrl.getSessionUser(usuVerificar);
        /*verificando se o usuario ja possui sessão */
        if(sessionUsu != null) {
            sessionUsu.setOnline(true);
            sessionUsuAux = sessionUsu;
        }
        else {
            sessionUsuAux = onUsuCtrl.addSessionUser(usuVerificar);
        }
        
        /* matendo porta e ip atualizados */
        sessionUsuAux.getUsu().setPortaAtual(this.request.getPortaAtual());
        sessionUsuAux.getUsu().setIpAtual(this.request.getIpAtual());
        
        sessionUsuAux.atualizaUltimaRequisicao();
    }

    @Override
    public void respondeRequest() {
         try {
            OutputStream out = this.conexao.getOutputStream();
            Gson gson = new Gson();
            
            ServerResponse resp = new ServerResponse();
            resp.setOk(true);
            
            String objJsonRetorno = gson.toJson(resp);
            String strRetorno =  objJsonRetorno.length()+"\n"+objJsonRetorno;
            out.write(strRetorno.getBytes());
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

   
}
