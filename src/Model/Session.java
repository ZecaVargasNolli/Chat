package Model;

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
    
    
    
    
}
