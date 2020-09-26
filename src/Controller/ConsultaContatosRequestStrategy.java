/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ServerRequest;
import Model.ServerResponse;
import Model.Usuario;
import Persistencia.UsuarioContatosDao;
import Persistencia.UsuarioDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Giancarlo
 */
public class ConsultaContatosRequestStrategy implements TrataRequestStrategy {

    private ServerRequest request;
    private Socket conexao;
    private List<Usuario> usuariosRetorno;
    

    @Override
    public void setRequest(ServerRequest req) {
        this.request = req;
    }

    @Override
    public void setConexao(Socket conexao) {
        this.conexao = conexao;
    }
    
    private List<Usuario> ConverteVectorParaUsuario(Vector listaUsu) {
        Iterator it = listaUsu.iterator();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        while (it.hasNext()) {
            Usuario usu = (Usuario) it.next();
            usuarios.add(usu);
        }
        return usuarios;
    }

    @Override
    public void processaRequest() {
         UsuarioContatosDao usuContDao = new UsuarioContatosDao();
         this.usuariosRetorno = this.ConverteVectorParaUsuario(usuContDao.selectContatosDoUsuario(this.request.getUsuRequest()));
         
    }

    @Override
    public void respondeRequest() {
        try {
            OutputStream out = this.conexao.getOutputStream();
           
            Gson gson = new Gson();
            
            ServerResponse resposta = new ServerResponse();
            resposta.setOk(true);
            resposta.setListaUsu(this.usuariosRetorno);
           
            String objJsonRetorno = gson.toJson(resposta);
            String strRetorno =  objJsonRetorno.length()+"\n"+objJsonRetorno;
            out.write(strRetorno.getBytes());
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
