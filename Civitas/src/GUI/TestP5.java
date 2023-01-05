/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author migue
 */
package GUI;
import java.util.ArrayList;
import controladorCivitas.*;
import civitas.*;

public class TestP5 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CivitasView vista = new CivitasView();
        Dado.createInstance(vista);
        Dado.getInstance().setDebug(Boolean.FALSE);
        CapturaNombres capturanombres = new CapturaNombres(vista,true);
        ArrayList<String> nombres = new ArrayList<>();
        nombres = capturanombres.getNombres();
        CivitasJuego juego = new CivitasJuego(nombres, false);
        Controlador controlador = new Controlador(juego,vista);
        vista.setCivitasJuego(juego); 
        
        while(!juego.finalDelJuego()){
            controlador.juega();
        }
        
    }
    
        
}
