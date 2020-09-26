package Model;

public class ServerRequest {
    
    private int tipoRequest;
    private Usuario usuRequest;
    private Usuario usuAlteracao;
    private String ipAtual;
    private int portaAtual;
    

    public Usuario getUsuAlteracao() {
        return usuAlteracao;
    }

    public void setUsuAlteracao(Usuario usuAlteracao) {
        this.usuAlteracao = usuAlteracao;
    }
    
    public int getTipoRequest() {
        return tipoRequest;
    }

    public void setTipoRequest(int tipoRequest) {
        this.tipoRequest = tipoRequest;
    }

    public Usuario getUsuRequest() {
        return usuRequest;
    }

    public void setUsuRequest(Usuario usuRequest) {
        this.usuRequest = usuRequest;
    }

    public String getIpAtual() {
        return ipAtual;
    }

    public void setIpAtual(String ipAtual) {
        this.ipAtual = ipAtual;
    }

    public int getPortaAtual() {
        return portaAtual;
    }

    public void setPortaAtual(int portaAtual) {
        this.portaAtual = portaAtual;
    }    

}
