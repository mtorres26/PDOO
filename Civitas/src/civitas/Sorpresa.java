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
public abstract class Sorpresa {
    private String texto;
    private int valor;
    private MazoSorpresas mazo = new MazoSorpresas();

    protected Sorpresa(String texto, int valor) {
        this.texto = texto;
        this.valor = valor;
    }
    
  
    void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Se "
        + "le aplica una sorpresa a: "+todos.get(actual).getNombre());
    }

    public int getValor() {
        return valor;
    }
    
    @Override
    public String toString(){
        return texto;
    } 

    abstract void aplicarAJugador(int iactual, ArrayList<Jugador> todos);
}
