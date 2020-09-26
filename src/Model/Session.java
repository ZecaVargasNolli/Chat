package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Giancarlo
 */
public class Session {
    
    private Usuario usu;
    private int ultimaReq;
    private boolean online;
    
    public Session(Usuario usu) {
        this.usu = usu;
        this.online = true;
        this.ultimaReq = 0;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public int getUltimaReq() {
        return ultimaReq;
    }

    public void setUltimaReq(int ultimaReq) {
        this.ultimaReq = ultimaReq;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    
    public void atualizaUltimaRequisicao() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        Date hora = Calendar.getInstance().getTime(); 
        String dataFormatada = sdf.format(hora);
        this.ultimaReq = Integer.parseInt(dataFormatada);
    }

}
