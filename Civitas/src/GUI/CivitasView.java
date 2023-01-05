/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import vistaTextualCivitas.Vista;
import civitas.*;
import controladorCivitas.Respuesta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.JTextField;

/**
 *
 * @author migue
 */
public class CivitasView extends javax.swing.JFrame implements Vista {

    private CivitasJuego juego;
    private DiarioDialog diario = new DiarioDialog(this);
    private GestionarDialog gestion;
    private PropiedadDialog propiedad;
    
    /**
     * Creates new form CivitasView
     */
    public CivitasView() {
        initComponents();
    }
    
    public void setCivitasJuego(CivitasJuego obj){
        this.juego = obj;
        this.setTitle("CIVITAS");
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(new Dimension(1850,1080));
        completarTablero();
    }
    
    @Override
    public void actualiza() {
    jugadorPanel1.setJugador(juego.getJugadorActual());
    jugadorPanel1.setVisible(true);
    
    for(int i=0;i<juego.getTablero().getCasillas().size();i++){
    panelTablero.getComponent(i).setForeground(Color.red);
    }
    
    panelTablero.getComponent(juego.getJugadorActual().getCasillaActual()).setForeground(Color.black);
    this.casillaActualTextField.setText(juego.getJugadorActual().toString());
    repaint();
    revalidate();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jugadorPanel1 = new GUI.JugadorPanel();
        casillaActualLabel = new javax.swing.JLabel();
        casillaActualTextField = new javax.swing.JTextField();
        sigOperacionLabel = new javax.swing.JLabel();
        sigOperacionTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        panelTablero = new javax.swing.JPanel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new java.awt.FlowLayout());

        jugadorPanel1.setPreferredSize(new java.awt.Dimension(800, 310));
        getContentPane().add(jugadorPanel1);

        casillaActualLabel.setText("Casilla Actual: ");
        getContentPane().add(casillaActualLabel);

        casillaActualTextField.setEditable(false);
        getContentPane().add(casillaActualTextField);

        sigOperacionLabel.setText("Siguiente Operación: ");
        getContentPane().add(sigOperacionLabel);

        sigOperacionTextField.setEditable(false);
        getContentPane().add(sigOperacionTextField);
        getContentPane().add(jLabel1);

        panelTablero.setPreferredSize(new java.awt.Dimension(1080, 200));
        getContentPane().add(panelTablero);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void completarTablero(){
        panelTablero.setLayout(new GridLayout(10,2));
        
        for(int i = 0; i<juego.getTablero().getCasillas().size();i++){
            JLabel casilla = new JLabel(juego.getTablero().getCasilla(i).getNombre());
            casilla.setHorizontalAlignment(JTextField.CENTER);
            casilla.setPreferredSize(new Dimension(juego.getTablero().getCasilla(i).getNombre().length(),25));
            casilla.add(Box.createHorizontalGlue());
            panelTablero.add(casilla);
        }
        panelTablero.setVisible(true);
    }

    
    @Override
    public Respuesta comprar() {
        int opcion= 1-JOptionPane.showConfirmDialog(null, "¿Quieres comprar la " +
        "calle actual?", "Compra", JOptionPane.YES_NO_OPTION);
        
        if(opcion == 0){
            return Respuesta.NO;
        }
        else{
            juego.comprar();
            return Respuesta.SI;
        }
    }

    @Override
    public OperacionInmobiliaria elegirOperacion() {
        gestion = new GestionarDialog(this,true);
        return gestion.getGestionElegida();
    }

    @Override
    public int elegirPropiedad() {
        this.propiedad = new PropiedadDialog(this, true,juego.getJugadorActual());
        return propiedad.getPropiedadElegida();
    }

    public CivitasJuego getJuego() {
        return juego;
    }

    public JugadorPanel getJugadorPanel1() {
        return jugadorPanel1;
    }
    
    
    
    

    @Override
    public void mostrarSiguienteOperacion(OperacionJuego operacion) {
        this.sigOperacionTextField.setText(String.valueOf(juego.siguientePaso()));
        if(juego.siguientePaso() == OperacionJuego.AVANZAR){
            Dado.createInstance(this);
            Dado.getInstance().setVisible(true);
        }
        
    }

    @Override
    public void mostrarEventos() {
        if (!Diario.getInstance().getEventos().isEmpty()){
        DiarioDialog diarioD = new DiarioDialog(this); //crea la ventana del diario
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel casillaActualLabel;
    private javax.swing.JTextField casillaActualTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private GUI.JugadorPanel jugadorPanel1;
    private javax.swing.JPanel panelTablero;
    private javax.swing.JLabel sigOperacionLabel;
    private javax.swing.JTextField sigOperacionTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void pausa() {
        int val = JOptionPane.showConfirmDialog(null, "¿Continuar?", "Siguiente paso", JOptionPane.YES_NO_OPTION);
        if (val == 1) System.exit(0);
    }


}