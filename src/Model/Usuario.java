/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Usuario {
    
    private int id;
    private String apelido;
    private String email;
    private String senha;
    private int anoNascimento;
    private String ipAtual;
    private int portaAtual;
    
    public int getPortaAtual() {
        return portaAtual;
    }

    public void setPortaAtual(int portaAtual) {
        this.portaAtual = portaAtual;
    }
    
    public String getIpAtual() {
        return ipAtual; //String ipDaMaquina = InetAddress.getLocalHost().getHostAddress(); 
    }

    public void setIpAtual(String ipAtual) {
        this.ipAtual = ipAtual;
    }
    
    public boolean temIp() {
        if (this.ipAtual != null) {
            if (!this.ipAtual.trim().equals("")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean temPorta() {
        return this.portaAtual != 0;
    }
    
    public boolean isOnline() {
        return this.temIp() && this.temPorta();
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    
}
