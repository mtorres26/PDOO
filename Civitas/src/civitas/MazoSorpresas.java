/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package civitas;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author migue
 */
public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresas = new ArrayList<>();
    private boolean barajada;
    private int usadas;
    private boolean debug;
    
    private void init(){
        barajada = false;
        usadas = 0;
    }
    
    MazoSorpresas(boolean debug) {
        this.init();
        this.debug = debug;
        if(debug == true){
            Diario.getInstance().ocurreEvento("El modo debug"
                + " se ha activado. ");
        }
    }
    
    MazoSorpresas (){
        this.init();
        this.debug = false;
    }
    void alMazo(Sorpresa s){
        if(barajada==false){
            sorpresas.add(s);
        }
    }
            
    Sorpresa siguiente(){
        if(barajada == false || usadas == sorpresas.size()){
            if(debug == false){
                Collections.shuffle(sorpresas);
                usadas = 0;
                barajada = true;
            }
        }
        Sorpresa s = sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(s);
        usadas++;
        return s;
    }
}
