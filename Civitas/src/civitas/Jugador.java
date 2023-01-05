/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;
import java.util.ArrayList;
import java.lang.Float;

/**
 *
 * @author migue
 */
public class Jugador implements Comparable {
    static final protected int CASASMAX = 4;
    static final protected int CASASPORHOTEL = 4;
    static protected int HOTELESMAX = 4;
    static final protected float PasoPorSalida = 1000;
    static private float SaldoInicial = 7500;
    private int casillaActual;
    private String nombre;
    protected boolean puedeComprar;
    private float saldo;
    private boolean especulador;
    public ArrayList<CasillaCalle> propiedades = new ArrayList<>();
   
    public Jugador(){
        this.casillaActual=0;
        this.nombre="";
        this.puedeComprar=false;
        this.saldo= this.SaldoInicial;
        this.propiedades = null;
        this.especulador = false;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.casillaActual=0;
        this.puedeComprar=true;
        this.saldo= this.SaldoInicial;
        this.propiedades = null;
        this.especulador = false;
    }
    
    public Jugador(ArrayList<CasillaCalle> propiedades, int casillaActual, String nombre, boolean puedeComprar, float saldo) {
        this.casillaActual = casillaActual;
        this.nombre = nombre;
        this.puedeComprar = puedeComprar;
        this.saldo = saldo;
        this.propiedades = null;
        this.especulador = false;
    }
    
    public Jugador(Jugador otro){
        this.casillaActual = otro.casillaActual;
        this.nombre = otro.nombre;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
        this.propiedades = otro.propiedades;
    }
    
    void actualizaPropiedadesPorConversion(Jugador jugador){
        for(int i = 0; i < propiedades.size(); i++){
            propiedades.get(i).actualizaPropietarioPorConversion(jugador);
        }
    }

    int cantidadCasasHoteles(){
        int casashoteles=0;
        for(int i = 0; i < propiedades.size(); i++){
            casashoteles += propiedades.get(i).getNumCasas() + propiedades.get(i).getNumHoteles();
        }
        return casashoteles;
    }
    
    public int compareTo(Jugador otro){
        return Float.compare(this.saldo,otro.getSaldo());
    }
    
    boolean comprar(CasillaCalle titulo){
        boolean comprar = false;
        if(!titulo.tienePropietario()){
                if(this.puedoGastar(titulo.getPrecioCompra())){
                comprar = titulo.comprar(this);
                this.propiedades.add(titulo);
                Diario.getInstance().ocurreEvento("El jugador: "
                    + this.getNombre() + " compra la propiedad: " 
                    + titulo.getNombre() + ". ");
                puedeComprar = false;
                }
                else{
                Diario.getInstance().ocurreEvento("El jugador: "+this.getNombre()
                        + " no tiene suficiente saldo para comprar: " + titulo + ". ");
                }
        }
        Diario.getInstance().ocurreEvento("La casilla tiene propietario. ");
        return comprar;
    }
    
    boolean construirCasa(int ip){
        boolean construct = false;
        if(this.existeLaPropiedad(ip)){
            CasillaCalle propiedad = propiedades.get(ip);
            if(puedoEdificarCasa(propiedad)){
                propiedad.construirCasa(this);
                construct=true;
                Diario.getInstance().ocurreEvento("El jugador: "
                + this.getNombre() +" se construye una casa en: "+ip);
            } 
        }
        return construct;
    }
    
    boolean construirHotel(int ip){
        boolean construct = false;
        if(existeLaPropiedad(ip)){
            CasillaCalle propiedad = propiedades.get(ip);
            if(puedoEdificarHotel(propiedad)){
                propiedad.construirHotel(this);
                construct = true;
                propiedad.derruirCasas(this.getCasasPorHotel(), this);
                Diario.getInstance().ocurreEvento("El jugador: "
                +this.getNombre() + " se construye un hotel en la propiedad: "
                + ip + ". ");
            }   
        }
        
        return construct;
    }
    
    
    protected JugadorEspeculador convertirme(){
        JugadorEspeculador convertido = new JugadorEspeculador(this);
        this.setEspeculador(true);
        return convertido;
    }
    
    boolean enBancarrota(){
        return this.saldo<0;
    }
    
    boolean esPropietario(CasillaCalle calle) {
        boolean prop = false;
        for(int i = 0; i<propiedades.size(); i++){
            if(propiedades.get(i).getNombre() == calle.getNombre()){
                prop = true;
            }
        }
        return prop;
    }
    
    private boolean existeLaPropiedad(int ip){
        return this.propiedades.get(ip) != null;
    }
    
    protected int getCasasMax(){
        return CASASMAX;
    }
    
    int getCasasPorHotel(){
        return CASASPORHOTEL;
    }
    
    public int getCasillaActual(){
        return casillaActual;
    }

    public boolean getEspeculador() {
        return especulador;
    }
    
    protected int getHotelesMax(){
        return HOTELESMAX;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    private float getPremioPasoSalida(){
        return this.PasoPorSalida;
    }
    
    public ArrayList<CasillaCalle> getPropiedades(){
        return propiedades;
    }
    
    boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    public float getSaldo(){
        return saldo;
    }
        
    boolean modificarSaldo(float cantidad){
        this.saldo+=cantidad;
        Diario.getInstance().ocurreEvento("Se modifica el saldo. ");
        return true;
    }
    
    boolean moverACasilla(int c){
        this.casillaActual=c;
        this.puedeComprar=false;
        Diario.getInstance().ocurreEvento("El jugador se mueve de casilla. ");
        return true;
    }
    
    boolean paga(float cantidad){
        return this.modificarSaldo(cantidad * (-1));
    }
    
    boolean pagaAlquiler(float cantidad){
        return this.paga(cantidad);
    }
    
    boolean pasaPorSalida(){
        this.recibe(this.getPremioPasoSalida());
        Diario.getInstance().ocurreEvento("El jugador " + 
            this.getNombre() + "pasa por la casilla de salida. ");
        return true;
    }
    
    protected boolean puedeComprarCasilla(){
        this.puedeComprar=true;
        return puedeComprar;
    }
    
    protected boolean puedoEdificarCasa(CasillaCalle propiedad){
        boolean puede = false;
            if (puedoGastar(propiedad.getPrecioEdificar()) 
                && propiedad.getNumCasas() < this.getCasasMax()){
            puede = true;
        }
        return puede;
    }
    
    protected boolean puedoEdificarHotel(CasillaCalle propiedad){
        boolean puede = false;
        if(puedoGastar(propiedad.getPrecioEdificar())
            && propiedad.getNumHoteles() < this.getHotelesMax()){
            if(propiedad.getNumCasas() >= this.getCasasPorHotel()){
                puede = true;
            }
        }
        return puede;
    }
    
    protected boolean puedoGastar(float precio){
        if(precio > 0 && saldo >= precio){
            return true;
        }
        else{
            return false;
        }
    }
    
    boolean recibe(float cantidad){
        return this.modificarSaldo(cantidad);
    }

    public void setEspeculador(boolean especulador) {
        this.especulador = especulador;
    }
    
    
    boolean tieneAlgoQueGestionar(){
        return !propiedades.isEmpty();
    }
    
    @Override
    public String toString(){
        String calleActual = null;
        switch (this.casillaActual) {
            case 0:
                calleActual = "Salida";
                break;
            case 1:
                calleActual = "C/ Rodrigo Domínguez";
                break;
            case 2:
                calleActual = "C/ Vinicius Junior";
                break;
            case 3:
                calleActual = "C/ Chete";
                break;
            case 4:
                calleActual = "C/ Agustín de la Paz";
                break;
            case 5:
                calleActual = "Sorpresa nº1";
                break;
            case 6:
                calleActual = "C/ Luis Ordoñez";
                break;
            case 7:
                calleActual = "C/ Cristiano Ronaldo";
                break;
            case 8:
                calleActual = "Sorpresa nº2";
                break;
            case 9:
                calleActual = "C/ Marcelo Vázquez";
                break;
            case 10:
                calleActual = "Descanso";
                break;
            case 11:
                calleActual = "C/ Miguel Ramón";
                break;
            case 12: 
                calleActual = "C/ Jorge D'Alessandro";
                break;
            case 13:
                calleActual = "C/ Guillermo Aranda";
                break;
            case 14: 
                calleActual = "Sorpresa nº3";
                break;
            case 15:
                calleActual = "C/ Olula del Rio";
                break;
            case 16:
                calleActual = "C/ Kylian Mbappé";
                break;
            case 17:
                calleActual = "Sorpresa nº4";
                break;
            case 18:
                calleActual = "C/ Santiago López";
                break;
            case 19:
                calleActual = "C/ Florentino Perez";
                break;
            default:
                calleActual = "¿?";
                break;
        }
        
        return "Jugador: " + this.getNombre() 
                + ", casilla actual: " 
                + calleActual + ". ";
    }

    
}
