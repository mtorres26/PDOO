/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

/**
 *
 * @author migue
 */
public class GestionInmobiliaria {
    private int propiedad;
    private OperacionInmobiliaria operacion;

    public GestionInmobiliaria(int ip, OperacionInmobiliaria gest) {
        this.propiedad = ip;
        this.operacion = gest;
    }
    
    public OperacionInmobiliaria getOperacion(){
        return this.operacion;
    }
    
    public int getPropiedad(){
        return this.propiedad;
    }
    
    
}
