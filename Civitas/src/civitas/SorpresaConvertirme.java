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
public class SorpresaConvertirme extends Sorpresa {

    SorpresaConvertirme(String texto, int valor) {
        super(texto,valor);
    }
    
    @Override
    void aplicarAJugador(int iactual, ArrayList<Jugador> todos){
        super.informe(iactual,todos);
        Jugador j = new Jugador(todos.get(iactual).getNombre());
        todos.set(iactual, j);
        
    }
    
    @Override
    void informe(int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
        super.toString();
    }
    
    
    @Override 
    public String toString(){
        return super.toString() + "convertirse en jugador especulador. ";
    }

    
}
