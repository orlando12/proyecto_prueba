package conexion;

import java.sql.*;


/**
 *
 * @author Giovanni Tzec
 */
public class Conexion {
    private Connection con;

    public Conexion() 
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/reserva", "root", "admin");
            System.out.println("Conectado con exito");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("??????????????????????????????????????????????");
            System.err.println("Algo a salido mal al conectar a la base: " + e);
            System.out.println("T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T_T");
        }
    }
    public boolean ejecutar(String sql) {
        try {
            Statement st = con.createStatement();
            boolean res = st.execute(sql);
            desconectar();
            return res;
        } catch (SQLException ex) {
            System.err.println("No se puedo ejecutar la consulta:" + ex);
            return false;
        }
    }
    public ResultSet consultar(String sql) {
        try {
            Statement st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("No se pudieron obtener resultados al consultar:" + ex);
        }
        return null;
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println("No se pudo desconectar :" + ex);
        }
    }

    public Connection getConexion() {
        return con;
    }
    
}
