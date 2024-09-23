package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Mecanico;
import vista.frmMecanico;

//3- Heredar de la clase que detecta las acciones
public class ctrlMecanico implements MouseListener, KeyListener{
    
    //1- Mandar a llamar a las otras capas (modelo y vista)
    private Mecanico modelo;
    private frmMecanico vista;
   
    //2- Crear el constructor
    public ctrlMecanico(Mecanico modelo, frmMecanico vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.btnAgregar.addMouseListener(this);   
        modelo.Mostrar(vista.jtbMecanicos);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbMecanicos.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnAgregar){
            modelo.setNombre_Mecanico(vista.txtNombre.getText());
            modelo.setEdad_Mecanico(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Mecanico(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Mecanico(vista.txtCorreo.getText());
            modelo.Guardar();
            modelo.Mostrar(vista.jtbMecanicos);
        }
        
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbMecanicos);
            modelo.Mostrar(vista.jtbMecanicos);
        }
        
        if(e.getSource() == vista.jtbMecanicos){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualizar){
            modelo.setNombre_Mecanico(vista.txtNombre.getText());
            modelo.setEdad_Mecanico(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Mecanico(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Mecanico(vista.txtCorreo.getText());
            modelo.Actualizar(vista.jtbMecanicos);
            modelo.Mostrar(vista.jtbMecanicos);
        }
        
       
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
