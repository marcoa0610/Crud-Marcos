package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmMecanico;


public class Mecanico {
    //Paremtros 
    private String uuid_Mecanico;
    private String Nombre_Mecanico;
    private int Edad_Mecanico;
    private double Peso_Mecanico;
    private String Correo_Mecanico;
    
    //Getters y Setters

    public String getUuid_Mecanico() {
        return uuid_Mecanico;
    }

    public void setUuid_Mecanico(String uuid_Mecanico) {
        this.uuid_Mecanico = uuid_Mecanico;
    }

    public String getNombre_Mecanico() {
        return Nombre_Mecanico;
    }

    public void setNombre_Mecanico(String Nombre_Mecanico) {
        this.Nombre_Mecanico = Nombre_Mecanico;
    }

    public int getEdad_Mecanico() {
        return Edad_Mecanico;
    }

    public void setEdad_Mecanico(int Edad_Mecanico) {
        this.Edad_Mecanico = Edad_Mecanico;
    }

    public double getPeso_Mecanico() {
        return Peso_Mecanico;
    }

    public void setPeso_Mecanico(double Peso_Mecanico) {
        this.Peso_Mecanico = Peso_Mecanico;
    }

    public String getCorreo_Mecanico() {
        return Correo_Mecanico;
    }

    public void setCorreo_Mecanico(String Correo_Mecanico) {
        this.Correo_Mecanico = Correo_Mecanico;
    }
    
    ////////////////////////3- Métodos 
   public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addMecanico = conexion.prepareStatement("INSERT INTO tbMecanico(UUID_Mecanico, Nombre_Mecanico, Edad_Mecanico, Peso_Mecanico, Correo_Mecanico) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addMecanico.setString(1, UUID.randomUUID().toString());
            addMecanico.setString(2, getNombre_Mecanico());
            addMecanico.setInt(3, getEdad_Mecanico());
            addMecanico.setDouble(4, getPeso_Mecanico());
            addMecanico.setString(5, getCorreo_Mecanico());
 
            //Ejecutar la consulta
            addMecanico.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
   
   public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = claseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"Nombre_Mecanico", "Edad_Mecanico", "Peso_Mecanico", "Correo_Mecanico"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbVisitas");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("Nombre_Mecanico"), 
                    rs.getString("Edad_Mecanico"), 
                    rs.getInt("Peso_Mecanico"), 
                    rs.getString("Correo_Mecanico")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    } 
   
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbMecanico where UUID_Mecanico = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void cargarDatosTabla(frmMecanico vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbMecanicos.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbMecanicos.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbMecanicos.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbMecanicos.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTb = vista.jtbMecanicos.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTb = vista.jtbMecanicos.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTb);
            vista.txtCorreo.setText(CorreoDeTb);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbMecanico set Nombre_Mecanico= ?, Edad_Mecanico = ?, Peso_Mecanico = ?, Correo_Mecanico = ? where UUID_paciente = ?");
                updateUser.setString(1, getNombre_Mecanico());
                updateUser.setInt(2, getEdad_Mecanico());
                updateUser.setDouble(3, getPeso_Mecanico());
                updateUser.setString(4, getCorreo_Mecanico());
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
}
