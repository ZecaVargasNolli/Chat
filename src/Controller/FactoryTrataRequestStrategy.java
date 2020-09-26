/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

public class FactoryTrataRequestStrategy {
    
    public static TrataRequestStrategy getTrataRequest(int iCodRequest) {
        TrataRequestStrategy strategy = null;
        switch(iCodRequest) {
            case 1:
                strategy = new AutenticaUsuarioRequestStrategy();
                break;
            case 2:
                strategy = new AtualizaUsuarioRequestStrategy();
                break;
            case 3:
                strategy = new InsereUsuarioRequestStrategy();
                break;
            case 4:
                strategy = new AddContatoRequestStrategy();
                break;
            case 5:
                strategy = new RemoveContatoRequestStrategy();
                break;
            case 6:
                strategy = new ConsultaUsuarioEmailRequestStrategy();
                break;
            case 7:
                strategy = new ConsultaContatosRequestStrategy();
                break;
            case 8:
                strategy = new ConsultaContatosOnlineRequestStrategy();
                break;
        }
        return strategy;
    }
    
}
