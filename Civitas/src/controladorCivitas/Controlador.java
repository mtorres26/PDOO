/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorCivitas;
import civitas.CivitasJuego;
import GUI.*;
import civitas.OperacionJuego;
import civitas.OperacionInmobiliaria;
import civitas.GestionInmobiliaria;
import civitas.Diario;

/**
 *
 * @author migue
 */
public class Controlador {
    private CivitasJuego juego;
    private CivitasView vista;

    public Controlador(CivitasJuego juego, CivitasView vista) {
        this.juego = juego;
        this.vista = vista;
    }
    
    public void juega(){
        
        boolean fin = juego.finalDelJuego();
        if(!fin){
            vista.actualiza();
            vista.pausa();
            OperacionJuego op = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(op);
            if(op != OperacionJuego.PASAR_TURNO){
                Diario diario = Diario.getInstance();
                vista.mostrarEventos();
            }
            fin = juego.finalDelJuego();
            if (!fin){
                if (op == OperacionJuego.COMPRAR){
                Respuesta respuesta = vista.comprar();
                if(respuesta == Respuesta.SI){
                    juego.comprar();
                    juego.siguientePasoCompletado(op);
                }
                else{
                    juego.siguientePasoCompletado(op);
                }
                }
                else if(op == OperacionJuego.GESTIONAR){
                    OperacionInmobiliaria opInmob = vista.elegirOperacion();
                    if(opInmob != OperacionInmobiliaria.TERMINAR){
                        GestionInmobiliaria gestion = new GestionInmobiliaria(vista.elegirPropiedad(),opInmob);
                        OperacionInmobiliaria opInmob2 = gestion.getOperacion();
                        if(opInmob2 == OperacionInmobiliaria.TERMINAR){
                            vista.getJuego().siguientePasoCompletado(op);
                        }
                        else if (opInmob2 == OperacionInmobiliaria.CONSTRUIR_CASA){
                            vista.getJuego().construirCasa(vista.getJuego().getJugadorActual().getCasillaActual());
                        }
                        else{
                            vista.getJuego().construirHotel(vista.getJuego().getJugadorActual().getCasillaActual());
                        }
                        vista.getJuego().siguientePasoCompletado(op);
                            
                    }
                }
                else{
                    System.out.println("ERROR. ");
                }
            }
            else{
                System.out.println("Fin del juego. ");
            }
        }
        
        

    }
    
}
