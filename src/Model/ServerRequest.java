package Model;

public class ServerRequest {
    
    private int tipoRequest;
    private Usuario usuRequest;
    private Usuario usuAlteracao;
    

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
    
}
