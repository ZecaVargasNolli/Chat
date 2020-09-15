
package Model;

import java.util.ArrayList;
import java.util.List;

public class OnlineUserControl {
    
    private List<Usuario> usuarios;
    
    private static OnlineUserControl instance;

    private OnlineUserControl() {
        this.usuarios = new ArrayList<Usuario>();
    }
    
    public static synchronized OnlineUserControl getInstance() {
        if (instance == null) {
            instance = new OnlineUserControl();
        }
        return instance;
    }
    
    
}
