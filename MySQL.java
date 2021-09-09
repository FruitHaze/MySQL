package de.schwitzerlimit.lobbysystem.mysql;


import de.schwitzerlimit.lobbysystem.methoden.Var;

import java.sql.*;

public class MySQL {

    public static Connection c;

    public static void connect(){

        try{
            c = DriverManager.getConnection("jdbc:mysql://" + Var.host + ":" + Var.port + "/" + Var.db + "?autoReconnect=true",Var.user,Var.pw);
            System.out.println("[MySQL]");
            System.out.println("Das LobbySystem hat sich Verbunden");
        } catch(SQLException e){
            System.out.println("[MySQL] Connection failed.");
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        if(c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Update(String qry) {
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            disconnect();
            connect();
        }
    }

    public static ResultSet query(String qry){

        ResultSet rs = null;
        try{

            Statement st = c.createStatement();
            rs = st.executeQuery(qry);

        }catch(SQLException e){

            connect();
            System.err.println(e);

        }
        return rs;
    }
}

