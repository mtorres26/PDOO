/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author migue
 */
public class CasillaSorpresa extends Casilla {
    private MazoSorpresas mazo;
    private Sorpresa sorpresa;
    private ArrayList<Jugador> jugadores;
    private SorpresaConvertirme convertirme;
    private SorpresaPorCasaHotel porcasahotel;
    private SorpresaPagarCobrar pagarcobrar;
    
    CasillaSorpresa(){
        super();
    }
    
    CasillaSorpresa(String nombre, MazoSorpresas mazo){
        super(nombre);
        this.mazo = mazo;
    }
    
    @Override
    void informe(int iactual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(iactual, todos)) {
            Diario.getInstance().ocurreEvento("El jugador " 
            + todos.get(iactual).getNombre() 
            + " ha caido en una casilla sorpresa. ");
        }
    }
    
    protected void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos){
        sorpresa = mazo.siguiente();
        this.informe(iactual, todos);
        sorpresa.aplicarAJugador(iactual,todos);
    }
    
    @Override
    public String toString(){
        return " Tipo de sorpresa: " + this.sorpresa + ". ";
    }
}
