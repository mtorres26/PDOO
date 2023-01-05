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
public class Tablero {
    private ArrayList<Casilla> casillas= new ArrayList<>();
    private boolean porSalida;
        
    
    public Tablero() {
        Casilla salida = new Casilla("Salida");
        casillas.add(salida);
        porSalida=false;
    }
    
    void añadeCasilla(Casilla casilla){
        casillas.add(casilla);
    }
    
    
    boolean computarPorSalida(){
        if(this.porSalida == true){
            porSalida=false;
            return true;
        }
        return false;
    }
    
    private boolean correcto(int numCasilla){
        return numCasilla >= 0 && numCasilla <= casillas.size();
    }
    
    public Casilla getCasilla(int numCasilla){
        if(correcto(numCasilla)){
            return casillas.get(numCasilla);
        }
        else{
            return null;
        }
    }
    
     
    public ArrayList<Casilla> getCasillas(){
        return casillas;
    }
    
    int nuevaPosicion(int actual, int tirada){
        int pos = actual + tirada;
        if(pos >= casillas.size()){
            pos = pos % casillas.size();
            this.porSalida = true;
        }
        return pos;
    }

      
}
