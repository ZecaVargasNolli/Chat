
package Model;

import java.util.ArrayList;
import java.util.List;

public class OnlineUserControl {
    
    private List<Session> sessionUsu;
    
    private static OnlineUserControl instance;

    private OnlineUserControl() {
        this.sessionUsu = new ArrayList<Session>();
    }
    
    public static synchronized OnlineUserControl getInstance() {
        if (instance == null) {
            instance = new OnlineUserControl();
        }
        return instance;
    }
    
    public Session getSessionUser(Usuario usu) {
        Session retorno = null;
        for (Session session : this.sessionUsu) {
           if (usu.getId() == session.getUsu().getId()) {
               retorno = session;
               break;
           } 
        }
        return retorno;
    }
    
    public void addSessionUser(Usuario usu) {
        this.sessionUsu.add(new Session(usu));
    }

    public List<Session> getSessionUsu() {
        return sessionUsu;
    }

}
