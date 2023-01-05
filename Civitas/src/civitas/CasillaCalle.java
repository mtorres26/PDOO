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
public class CasillaCalle extends Casilla {  
    
    static final private float FACTORALQUILERCALLE = 1.0f;
    static final private float FACTORALQUILERCASA = 1.0f;
    static final private float FACTORALQUILERHOTEL = 4.0f;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioBaseAlquiler;
    private float precioCompra;
    private float precioEdificar;
    private Jugador propietario;
    
    public CasillaCalle(){
        super();
        this.numCasas=0;
        this.numHoteles=0;
        this.precioBaseAlquiler=0;
        this.precioCompra=0;
        this.precioEdificar=0;
        this.propietario=null;
        this.numCasas = 0;
        this.numHoteles=0;
    }
    
    public CasillaCalle(String nombre){
        super(nombre);
        this.numCasas=0;
        this.numHoteles=0;
        this.precioBaseAlquiler=0;
        this.precioCompra=0;
        this.precioEdificar=0;
        this.propietario=null;
        this.numCasas = 0;
        this.numHoteles=0;
    }
    
    public CasillaCalle(String nombre, float precioCompra, 
            float precioEdificar, float precioBaseAlquiler){
        super(nombre);
        this.precioCompra = precioCompra;
        this.precioEdificar = precioEdificar;
        this.precioBaseAlquiler = precioBaseAlquiler;
        this.propietario=null;
        this.numCasas = 0;
        this.numHoteles=0;        
    }
    
    public void actualizaPropietarioPorConversion(Jugador especulador){
        this.propietario = especulador;
    }
    
    public int cantidadCasasHoteles(){
        return numCasas + numHoteles;
    }  
    
    boolean construirCasa(Jugador jugador){
        if(this.getNumCasas() < 4){
            if(jugador.getSaldo() >= this.precioEdificar){
                jugador.paga(precioEdificar);
            }
            numCasas++;
            return true;
        }
        return false;
    }
    
    boolean construirHotel(Jugador jugador){
        if(this.getNumHoteles() < 4 && this.getNumCasas() == 4){
            if(jugador.getSaldo() >= this.precioEdificar){
                jugador.paga(precioEdificar);
            }
            this.numHoteles++;
            return true;
        }
        return false;
    }
    
    boolean comprar(Jugador jugador){
        if(jugador.puedoGastar(this.precioCompra)){
            jugador.comprar(this);
            propietario = jugador;
            return true;
        }
        else{
            return false;
        }
    }
    
    boolean derruirCasas(int n, Jugador jugador){
        boolean derruir = false;
        if (esEsteElPropietario(jugador) && this.numCasas >= n){
            derruir = true;
            this.numCasas -= n;
        }
        return derruir;
    }
    
    public boolean esEsteElPropietario(Jugador jugador){
        return jugador == this.propietario;
    }
    
    public int getNumCasas(){
        return numCasas;
    }
    
    public int getNumHoteles(){
        return numHoteles;
    }
    
    float getPrecioAlquilerCompleto() {
        float precioAlq;
        precioAlq = precioBaseAlquiler * ((FACTORALQUILERCASA * numCasas) 
            + (numHoteles * FACTORALQUILERHOTEL));
        return precioAlq;
    }
    
    float getPrecioCompra(){
        return precioCompra;
    }
        
    float getPrecioEdificar(){
        return precioEdificar;
    }
    
    Jugador getPropietario(){
        return propietario;
    }
    
    @Override
    void informe(int iactual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(iactual, todos)) {
            Diario.getInstance().ocurreEvento("El jugador " 
            + todos.get(iactual).getNombre() 
            + " ha caido en la calle: " + this.getNombre());
        }
    }
    
    protected void recibeJugador_calle(int iactual, ArrayList<Jugador> todos){  
        this.informe(iactual,todos);
        if(!this.tienePropietario()){
            todos.get(iactual).puedeComprarCasilla();
        }
        else{
            tramitarAlquiler(todos.get(iactual));
        }
    }
    
    public boolean tienePropietario(){
        return this.propietario != null;
    }
    
    public void tramitarAlquiler(Jugador jugador){
        if(this.tienePropietario()){
            if(!this.esEsteElPropietario(jugador)){
                jugador.pagaAlquiler(getPrecioAlquilerCompleto());
                this.propietario.recibe(this.getPrecioAlquilerCompleto());
            }
        }
    }
    
    @Override
    public String toString() {
        return "Calle: " + this.nombre 
        + ". Precio de compra: " + this.precioCompra
        + ". Precio de edificación: " + this.precioEdificar 
        + ". Precio de alquiler: " + this.precioBaseAlquiler
        + ". Número de casas: " + this.numCasas 
        + ". Número de hoteles: " + this.numHoteles
        + ". Propietario: " + this.propietario.getNombre();
    }
    
    
}
