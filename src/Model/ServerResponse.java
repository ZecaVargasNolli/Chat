/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

public class ServerResponse {
    
    private boolean ok;
    private List<Usuario> listaUsu;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Usuario> getListaUsu() {
        return listaUsu;
    }

    public void setListaUsu(List<Usuario> listaUsu) {
        this.listaUsu = listaUsu;
    }
}
