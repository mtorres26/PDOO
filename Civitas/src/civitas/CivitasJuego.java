/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;
import java.util.ArrayList;
import GUI.Dado;
import java.util.Collections;
/**
 *
 * @author migue
 */
public class CivitasJuego {
    private int indiceJugadorActual;
    private GestorEstados gestorestados;
    private ArrayList<Jugador> jugadores;
    private Tablero tablero = new Tablero();
    private MazoSorpresas mazo;
    private EstadosJuego estadosjuego;
    private Dado dado = Dado.getInstance();
    
    
    public CivitasJuego(ArrayList<String> nombres, boolean debug) {
        jugadores = new ArrayList<>();
        for(int i = 0; i< nombres.size(); i++){
            Jugador jugador = new Jugador(nombres.get(i));
            jugadores.add(jugador);
        }
        gestorestados = new GestorEstados();
        gestorestados.estadoInicial();
        mazo = new MazoSorpresas(debug);
        this.indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
        this.inicializaTablero(mazo);
        this.inicializaMazoSorpresas();
        this.estadosjuego = EstadosJuego.INICIO_TURNO;
    }
    
    private void avanzaJugador(){
        Jugador jugadorActual = this.getJugadorActual();
        int posicionActual = jugadorActual.getCasillaActual();
        int tirada = dado.tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla;
        casilla= tablero.getCasilla(posicionNueva);
        contabilizarPasosPorSalida(jugadorActual);
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores); 
    }

    
    
    public boolean comprar(){
        boolean res;
        Jugador jugadorActual = this.getJugadorActual();
        int numCasillaActual = jugadorActual.getCasillaActual();
        CasillaCalle casilla = (CasillaCalle)tablero.getCasilla(numCasillaActual);
        res = jugadorActual.comprar(casilla);
        return res;
    }
    
    public boolean construirCasa(int ip){
        Jugador j = jugadores.get(this.indiceJugadorActual);
        if (j.esPropietario((CasillaCalle) tablero.getCasilla(ip))){
            j.construirCasa(ip);
            CasillaCalle calle = (CasillaCalle) tablero.getCasilla(ip);
            calle.construirCasa(j);
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean construirHotel(int ip){
        Jugador j = jugadores.get(this.indiceJugadorActual);
        if (j.esPropietario((CasillaCalle) tablero.getCasilla(ip))){
            j.construirHotel(ip);
            CasillaCalle calle = (CasillaCalle) tablero.getCasilla(ip);
            calle.construirHotel(j);
            return true;
        }
        else{
            return false;
        }
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        if(tablero.computarPorSalida()){
            jugadorActual.pasaPorSalida();
        }
    }
    
    public boolean finalDelJuego(){
        boolean fin = false;
        for(int i=0; i<jugadores.size();i++){
            if(jugadores.get(i).enBancarrota()){
                fin = true;
            }
        }
        return fin;
    }

    public GestorEstados getGestorestados() {
        return gestorestados;
    }
    
    
    
    public int getIndiceJugadorActual(){
        return indiceJugadorActual;
    }
    
    public Jugador getJugadorActual(){
        Jugador j = jugadores.get(indiceJugadorActual);
        return j;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return this.jugadores;
    }
    
    public Tablero getTablero(){
        return this.tablero;
    }
    
    private void inicializaMazoSorpresas(){
        Sorpresa pc = new SorpresaPagarCobrar("Sorpresa tipo: PAGAR O COBRAR",100);
        mazo.alMazo(pc);
        Sorpresa c = new SorpresaConvertirme("Sorpresa tipo: CONVERTIRSE EN ESPECULADOR",200);
        mazo.alMazo(c);
        Sorpresa pch = new SorpresaPorCasaHotel("Sorpresa tipo: POR CASA HOTEL",300);
        mazo.alMazo(pch);
        Sorpresa pc2 = new SorpresaPagarCobrar("Sorpresa tipo: PAGAR O COBRAR",400);
        mazo.alMazo(pc2);
        
    }
    
    private void inicializaTablero(MazoSorpresas mazo){
        this.mazo= mazo;
        tablero.añadeCasilla(new CasillaCalle("C/ Rodrigo Domínguez",69,42,30));
        tablero.añadeCasilla(new CasillaCalle("C/ Vinicius Junior",80,50,50));
        tablero.añadeCasilla(new CasillaCalle("C/ Chete",70,60,50));
        tablero.añadeCasilla(new CasillaCalle("C/ Agustín de la Paz",90,60,70));
        //EXAMEN
        tablero.añadeCasilla(new Avenida("Avenida Salobreña",130,200,150,30));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa nº1",mazo));
        tablero.añadeCasilla(new CasillaCalle("C/ Luis Ordoñez",100,80,60));
        tablero.añadeCasilla(new CasillaCalle("C/ Cristiano Ronaldo",120,80,40));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa nº2",mazo));
        tablero.añadeCasilla(new CasillaCalle("C/ Marcelo Vázquez",80,60,60));
        tablero.añadeCasilla(new Casilla("Descanso"));
        //EXAMEN
        tablero.añadeCasilla(new Avenida("Avenida Parque Almunia",150,200,180,80));
        tablero.añadeCasilla(new CasillaCalle("C/ Miguel Ramón",100,80,70));
        tablero.añadeCasilla(new CasillaCalle("C/ Jorge D'Alessandro",80,50,50));
        tablero.añadeCasilla(new CasillaCalle("C/ Guillermo Aranda",140,80,90));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa nº3",mazo));
        tablero.añadeCasilla(new CasillaCalle("C/ Olula del Rio",100,100,80));
        tablero.añadeCasilla(new CasillaCalle("C/ Kylian Mbappé",80,50,50));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa nº4",mazo));
        tablero.añadeCasilla(new CasillaCalle("C/ Santiago López",100,80,60));
        tablero.añadeCasilla(new CasillaCalle("C/ Florentino Perez",200,100,120));
        
        
    }
    
    public void mostrarTablero(){
        for (int i = 0; i<tablero.getCasillas().size(); i++){
            System.out.println(tablero.getCasilla(i).toString());
        }
    }
    
    private void pasarTurno(){
        this.indiceJugadorActual++;
        indiceJugadorActual = (indiceJugadorActual + 1 ) % jugadores.size();
    }
    
    public ArrayList<Jugador> ranking(){
        //Collections.sort(jugadores);
        return jugadores;
    }
    
    public OperacionJuego siguientePaso(){
        Jugador jugadorActual = this.getJugadorActual();
        OperacionJuego operacionjuego = gestorestados.siguienteOperacion(jugadorActual, estadosjuego);
        if(operacionjuego == OperacionJuego.PASAR_TURNO){
            pasarTurno();
            siguientePasoCompletado(operacionjuego);
        }
        else if(operacionjuego == OperacionJuego.AVANZAR){
            avanzaJugador();
            siguientePasoCompletado(operacionjuego);
        }
        return operacionjuego;
    }
    
    public void siguientePasoCompletado(OperacionJuego operacion){
        this.estadosjuego = this.gestorestados.siguienteEstado(this.getJugadorActual(), estadosjuego, operacion);
    }
    
    @Override
    public String toString(){
        return "El ranking ha quedado: " + this.ranking() + ". ";
    }
}
