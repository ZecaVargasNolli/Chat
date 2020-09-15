/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class UsuarioContatosDao {
    private Connection conn;
    private Statement stm;

    public UsuarioContatosDao() {
        this.conn = null;
        this.stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:dbSqlLite/banco.db");
            /*jdbc:sqlite:DIRETORIO_ARQUIVO_BANCO*/
            this.stm = this.conn.createStatement();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void initDb() {
        try {
            // Remove e cria a tabela a cada execução. Mero exemplo
            this.stm.executeUpdate("DROP TABLE IF EXISTS contato");
            
            String sqlTbContato = " CREATE TABLE contato ("
                                + "    id_usuario INTEGER NOT NULL ,"
                                + "    id_contato INTEGER NOT NULL,"
                                + "    PRIMARY KEY (id_usuario, id_contato),"
                                + "    FOREIGN KEY (id_usuario) REFERENCES usuario (id),"
                                + "    FOREIGN KEY (id_contato) REFERENCES usuario (id)"
                                + " );";
            
            this.stm.executeUpdate(sqlTbContato);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public boolean insertContatoDoUsuario(Usuario usuario, Usuario contatoDoUsuario) {
        boolean resultado = true;
         try {
            Statement stm = this.conn.createStatement();
            
            stm.executeUpdate("INSERT INTO contato "
                            + "       (id_usuario, id_contato) "
                            + "VALUES ('" + usuario.getId() + "', '" + contatoDoUsuario.getId() + "')");
        }
        catch (SQLException e) {
            e.printStackTrace();
            resultado = false;
        }
        return resultado;
    }
     
    public boolean deleteContatoDoUsuario(Usuario usuario, Usuario contatoDoUsuario) {
        boolean resultado = true;
         try {
            Statement stm = this.conn.createStatement();
            stm.executeUpdate("DELETE FROM contato WHERE id_usuario = '" + usuario.getId() + "' AND id_contato = '" + contatoDoUsuario.getId() + "' ");
            
        }
        catch (SQLException e) {
            e.printStackTrace();
            resultado = false;
        }
        return resultado;
    }
    
    public Vector selectContatosDoUsuario(Usuario usuario) {
        Vector lista = new Vector();
        ResultSet result;
        try {
            Statement stm = this.conn.createStatement();
            String sqlContatos = "SELECT usuario.id, "
                               + "       usuario.senha, "
                               + "       usuario.email, "
                               + "       usuario.apelido, "
                               + "       usuario.ano_nascimento "
                               + "  FROM contato "
                          + " INNER JOIN usuario  "
                               + "    ON contato.id_contato = usuario.id "
                               + "   AND contato.id_usuario = '" + usuario.getId() + "' ";

            result = stm.executeQuery(sqlContatos);
            
            while (result.next()) {
                Usuario usu = new Usuario();
                usu.setId(result.getInt("id"));
                usu.setSenha(result.getString("senha"));
                usu.setEmail(result.getString("email"));
                usu.setApelido(result.getString("apelido"));
                usu.setAnoNascimento(result.getInt("ano_nascimento"));
                lista.add(usu);
            }
             
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
