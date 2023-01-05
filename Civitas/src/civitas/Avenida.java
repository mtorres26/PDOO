/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author mtorres26
 */
public class Avenida extends CasillaCalle {
    
   
    private float sobrePrecio;  
    private static int NumAvenidas;
    
    public Avenida(){
        super();
        this.sobrePrecio = 0;
        Avenida.NumAvenidas++;
    }
    
    public Avenida(String nombre, float precioCompra, 
            float precioEdificar, float precioBaseAlquiler, float sobrePrecio){
        super(nombre, precioCompra, precioEdificar, precioBaseAlquiler);
        this.sobrePrecio = sobrePrecio;
        Avenida.NumAvenidas++;
    }
    
    @Override
    float getPrecioAlquilerCompleto() {
        float nuevoPrecio = super.getPrecioAlquilerCompleto();
        if(this.sobrePrecio < 10){
            this.sobrePrecio = 10;
        }
        else if(this.sobrePrecio > 90){
            this.sobrePrecio = 90;
        }
        
        nuevoPrecio = (nuevoPrecio + (nuevoPrecio * (this.sobrePrecio/100))) * this.getNumAvenidas();
        //Formula para aumentar el precio del alquiler segun el porcentaje "sobrePrecio"
        //y el número de avenidas.
        return nuevoPrecio;
    }
    
    public static int getNumAvenidas(){
        return Avenida.NumAvenidas;
    }

    public float getSobrePrecio() {
        return this.sobrePrecio;
    }
    
    @Override
    void informe(int iactual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(iactual, todos)) {
            Diario.getInstance().ocurreEvento("El jugador " 
            + todos.get(iactual).getNombre() 
            + " ha caido en la Avenida: " + this.getNombre());
        }
    }
    
    protected void recibeJugador_avenida(int iactual, ArrayList<Jugador> todos){  
        this.informe(iactual,todos);
        if(!this.tienePropietario()){
            todos.get(iactual).puedeComprarCasilla();
        }
        else{
            tramitarAlquiler(todos.get(iactual));
        }
    }
    
    @Override
    public String toString(){
        return "Avenida: " + this.getNombre();
    }
    
}
