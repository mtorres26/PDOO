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
public class SorpresaPagarCobrar extends Sorpresa {

    SorpresaPagarCobrar(String texto, int valor) {
        super(texto,valor);
    }
        
    void aplicarAJugador(int iactual, ArrayList<Jugador> todos){
        super.informe(iactual,todos);
        todos.get(iactual).modificarSaldo(super.getValor());
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
        super.toString();
    }
    
    @Override
    public String toString(){
        return super.toString() + "pagar o cobrar. ";
    }
}
