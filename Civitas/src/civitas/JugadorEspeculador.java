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
public class JugadorEspeculador extends Jugador {
    
    private static final int FACTORESPECULADOR = 2;
    
    
    JugadorEspeculador(Jugador j){
        super(j);
        actualizaPropiedadesPorConversion(this);
    }
    
    @Override
    boolean paga(float cantidad){
        boolean paga = false;
        if(super.puedoGastar(cantidad/JugadorEspeculador.FACTORESPECULADOR)){
            super.paga(cantidad/JugadorEspeculador.FACTORESPECULADOR);
            paga = true;
        }
        return paga;
    }

    public int getCASASMAX() {
        return super.getCasasMax() * JugadorEspeculador.FACTORESPECULADOR;
    }

    public int getHOTELESMAX() {
        return super.getHotelesMax() * JugadorEspeculador.FACTORESPECULADOR;
    }
    
    
    
    @Override
    public String toString() {
        return super.toString()+"Es especulador";
    }
    
    
}
