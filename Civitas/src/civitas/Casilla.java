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
public class Casilla {
    
    private String nombre;
        
    public Casilla() {
        this.nombre = "";
    }
    
    public Casilla(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    void informe(int iactual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(iactual, todos)) {
            Diario.getInstance().ocurreEvento("El jugador " 
            + todos.get(iactual).getNombre() 
            + " ha caido en una casilla de descanso. ");
        }
    }
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos) {
        return actual < todos.size();
    }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        this.informe(iactual, todos);
    }
    
    @Override
    public String toString(){
        return "Casilla de descanso. ";
    }
    
}

