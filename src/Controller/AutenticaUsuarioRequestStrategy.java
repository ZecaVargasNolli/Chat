
package Controller;

import Model.ServerRequest;
import Model.Usuario;
import Persistencia.UsuarioDao;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AutenticaUsuarioRequestStrategy implements TrataRequestStrategy {

    private ServerRequest request;
    private Socket conexao;
    private Usuario usuAutenticado;
    
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
        this.usuAutenticado = this.getUsuarioAutenticacao();
        
        
    }

    @Override
    public void respondeRequest() {
        try {
            OutputStream out = this.conexao.getOutputStream();
           
            Gson gson = new Gson();
            Usuario retorno = null;
            if (this.usuAutenticado != null) {
                retorno = this.usuAutenticado;
            }
            else {
                retorno = new Usuario();
                retorno.setId(-1);
            }
            String objJsonRetorno = gson.toJson(retorno);
            String strRetorno =  objJsonRetorno.length()+"\n"+objJsonRetorno;
            out.write(strRetorno.getBytes());
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        
      
    }
    
    private Usuario getUsuarioAutenticacao() {
        Usuario usuAutenticado = null;
        UsuarioDao usuDao = new UsuarioDao();
        Vector registros = usuDao.selectEmailSenha(this.request.getUsuRequest().getEmail(), this.request.getUsuRequest().getSenha());
        
        Iterator it = registros.iterator();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        while (it.hasNext()) {
            Usuario usu = (Usuario) it.next();
            usuarios.add(usu);
        }
        
        if (usuarios.size() == 1) {
            usuAutenticado = usuarios.get(0);
        }
        
        return usuAutenticado;
    }
    
}
