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


public class UsuarioDao {

    private Connection conn;

    public UsuarioDao() {
        this.conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:dbSqlLite/banco.db");
            /*jdbc:sqlite:DIRETORIO_ARQUIVO_BANCO*/
            Statement stm = this.conn.createStatement();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void initDb() {
        try {
            Statement stm = this.conn.createStatement();
            // Remove e cria a tabela a cada execução. Mero exemplo
            stm.executeUpdate("DROP TABLE IF EXISTS usuario");
            
            String sqlCriarTb =  "CREATE TABLE usuario ("
                               + "   id INTEGER PRIMARY KEY AUTOINCREMENT,"
                               + "   apelido VARCHAR(50) NOT NULL, "
                               + "   email VARCHAR(50) NOT NULL UNIQUE, "
                               + "   senha VARCHAR(50) NOT NULL, "
                               + "   ano_nascimento INTEGER NOT NULL "
                               + ");";
            
            stm.executeUpdate(sqlCriarTb);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean insertUsuario(Usuario usu) {
        boolean resultado = true;
         try {
            Statement stm = this.conn.createStatement();
            
            stm.executeUpdate("INSERT INTO usuario "
                            + "       (apelido, email, senha, ano_nascimento) "
                            + "VALUES ('"+usu.getApelido()+"', '"+usu.getEmail()+"', '"+usu.getSenha()+"', "+usu.getAnoNascimento()+")");
        }
        catch (SQLException e) {
            e.printStackTrace();
            resultado = false;
        }
        return resultado;
    }
    
    public boolean updateUsuario(Usuario usu){
        boolean resultado = true;
        try {
             Statement stm = this.conn.createStatement();

             String sqlUpdate = "UPDATE usuario "
                              + "   SET apelido = '" + usu.getApelido() + "', "
                              + "         email = '" + usu.getEmail() + "', "
                              + "ano_nascimento = '" + usu.getAnoNascimento() + "', " 
                              + "         senha = '" + usu.getSenha() + "' "
                              + "      WHERE id = '" + usu.getId()+ "'";
             stm.executeUpdate(sqlUpdate);
             
             
        }
        catch (SQLException e) {
            e.printStackTrace();
            resultado = false;
        }
        return resultado;
    }
    
    public boolean deleteUsuario(Usuario usu) {
        boolean resultado = true;
        try {
            Statement stm = this.conn.createStatement();
            String sqlDelete = "DELETE FROM usuario WHERE id = '"+ usu.getId() +"'";
            
            stm.executeUpdate(sqlDelete);
        }
        catch (SQLException e) {
            e.printStackTrace();
            resultado = false;
        }
        return resultado;
    }
    
    public Vector selectAll () {
        Vector lista = new Vector();
        ResultSet result;
        try {
            Statement stm = this.conn.createStatement();
            result = stm.executeQuery("SELECT * FROM usuario");
            
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
    
    public Vector selectById (int id) {
        Vector lista = new Vector();
        ResultSet result;
        try {
            Statement stm = this.conn.createStatement();
            result = stm.executeQuery("SELECT * FROM usuario WHERE id = " + id);
            
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
    
    public Vector selectByEmail (String email) {
        Vector lista = new Vector();
        ResultSet result;
        try {
            Statement stm = this.conn.createStatement();
            result = stm.executeQuery("SELECT * FROM usuario WHERE UPPER(email) like '%" + email.toUpperCase() + "%' " );
            
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
    
    public Vector selectEmailSenha (String email, String senha) {
        Vector lista = new Vector();
        ResultSet result;
        try {
            Statement stm = this.conn.createStatement();
            result = stm.executeQuery("SELECT * FROM usuario WHERE email = '" + email + "' AND senha = '" + senha + "'");
            
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


