package Controller;

import Model.ServerRequest;
import Model.ServerResponse;
import Model.Usuario;
import Persistencia.UsuarioContatosDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Giancarlo
 */
public class AddContatoRequestStrategy implements TrataRequestStrategy {
    
    private ServerRequest request;
    private Socket conexao;
    private boolean execInsertContato;
    
    
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
        Usuario usu     = this.request.getUsuRequest();
        Usuario contato = this.request.getUsuAlteracao();
        
        UsuarioContatosDao usuContDao = new UsuarioContatosDao();
        this.execInsertContato = usuContDao.insertContatoDoUsuario(usu, contato);
        
    }

    @Override
    public void respondeRequest() {
        try {
            OutputStream out = this.conexao.getOutputStream();
            Gson gson = new Gson();
            
            ServerResponse resp = new ServerResponse();
            resp.setOk(this.execInsertContato);
            
            String objJsonRetorno = gson.toJson(resp);
            String strRetorno =  objJsonRetorno.length()+"\n"+objJsonRetorno;
            out.write(strRetorno.getBytes());
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
