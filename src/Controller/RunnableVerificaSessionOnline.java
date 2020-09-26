package Controller;

import Model.OnlineUserControl;
import Model.Session;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giancarlo
 */
public class RunnableVerificaSessionOnline implements Runnable {
    
    private OnlineUserControl contrllerSession;

    public RunnableVerificaSessionOnline() {
        this.contrllerSession = OnlineUserControl.getInstance();
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        while (true) {            
            Date dateAtual = Calendar.getInstance().getTime(); 
            int horaMinSegAtual = Integer.parseInt(sdf.format(dateAtual));
            List<Session> sessoes = this.contrllerSession.getSessionUsu();
            for (Session sesssionAtual : sessoes) {
                int difRequisicao = (horaMinSegAtual - sesssionAtual.getUltimaReq());
                if (difRequisicao > 3) {
                    sesssionAtual.setOnline(false);
                }
            }
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
