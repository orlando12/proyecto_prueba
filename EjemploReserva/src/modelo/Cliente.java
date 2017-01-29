
package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Giovanni Tzec
 */
public class Cliente {
    private int codigo;
    private String nombre;
    private String telefono;
    private int edad;
    private float sueldo;

    public Cliente() {
    }

    public Cliente(int codigo, String nombre, String telefono, int edad, float sueldo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.sueldo = sueldo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }
    public boolean insertarCliente(Cliente cli) {
        try {
            Conexion cone = new Conexion();
            String sql = "insert into cliente values(0,?,?,?,?)";
            PreparedStatement pst = cone.getConexion().prepareStatement(sql);
            pst.setObject(1, cli.getNombre());
            pst.setObject(2, cli.getEdad());
            pst.setObject(3, cli.getSueldo());
            pst.setObject(4,cli.getTelefono() );
            pst.execute();
            pst.close();
            cone.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }   
    public boolean editarCliente(Cliente cli) {
            Conexion cone = new Conexion();
            String sql = "update cliente set nombre = '"+ cli.getNombre()+"',"
                    + "edad ="+cli.getEdad()+",sueldo="+cli.getSueldo()+ ",telefono='"+cli.getTelefono()+"'"+ "where codCliente ="+cli.getCodigo();
            return cone.ejecutar(sql);
    }
    
    public boolean eliminarCliente(int id) {
        Conexion cone = new Conexion();
        String sql = "delete from cliente where codCliente = " + id;
        return cone.ejecutar(sql);
        
    }
    
    public ArrayList<Cliente> mostrarCliente() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
           Conexion cone = new Conexion();
            String sql = "select * from cliente";
            ResultSet rs = cone.consultar(sql);
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setCodigo(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setEdad(rs.getInt(3));
                cli.setSueldo(rs.getFloat(4));
                cli.setTelefono(rs.getString(5));
                lista.add(cli);
            }
            cone.desconectar();
        } catch (SQLException ex) {
            System.err.println("Error al mostrar Clientes:" + ex);
        }
        return lista;
    }
    
    
}
