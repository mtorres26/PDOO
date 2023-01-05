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
public class SorpresaPorCasaHotel extends Sorpresa {
    
    
    SorpresaPorCasaHotel(String texto, int valor) {
        super(texto,valor);
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
        super.toString();
    }

    @Override
    void aplicarAJugador(int iactual, ArrayList<Jugador> todos) {
        super.informe(iactual,todos);
        todos.get(iactual).modificarSaldo(super.getValor()*todos.get(iactual).cantidadCasasHoteles());    }
    
}
