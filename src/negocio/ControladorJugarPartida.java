package negocio;


import dataDB.CatalogoJugadores;
import entidades.*;

import java.util.ArrayList;

/**
 * Created by cgallo on 04/08/15.
 */
public class ControladorJugarPartida {

    private CatalogoJugadores catalogoJugadores = CatalogoJugadores.getInstance();

    private Jugador jugadorBlancoActual;
    private Jugador jugadorNegroActual;
    private Partida partidaActual;

    private Jugador getJugadorByDni(int dni){
        Jugador jugador = catalogoJugadores.getJugadorByDni(dni);
        return jugador;
    }

    public ArrayList<Jugador> getJugadores(){
        ArrayList<Jugador> jugadores = catalogoJugadores.getJugadores();
        return jugadores;
    }

    public Jugador buscarJugadorBlanco(int dni){
        jugadorBlancoActual = getJugadorByDni(dni);
        // System.out.println(jugadorBlancoActual.getDni());
        return jugadorBlancoActual;
    }

    public Jugador buscarJugadorNegro(int dni){
        this.jugadorNegroActual = getJugadorByDni(dni);
        return this.jugadorNegroActual;
    }

    private Partida getPartidaPendiente(){
        Partida partidaPendiente = null;
        ArrayList<Partida> partidasPendientes = jugadorBlancoActual.getPartidasPendientesJugadorBlanco(jugadorNegroActual);
        if (!(partidasPendientes.isEmpty())){
            partidaPendiente = partidasPendientes.get(0);
        }
        return partidaPendiente;
    }

    public Partida iniciarPartida() throws NullPointerException{
        Partida partida = null; // = getPartidaPendiente();
        if (jugadorBlancoActual == null) throw new NullPointerException("No hay jugador blanco");
        if (jugadorNegroActual == null) throw new NullPointerException("No hay jugador negro");
        if (partida == null){
            partidaActual = new Partida(jugadorBlancoActual, jugadorNegroActual);
        }
        else{
            partidaActual = partida;
        }
        return partidaActual;
    }

    public void addJugador (Jugador jugador) {
        if(catalogoJugadores.getJugadorByDni(jugador.getDni()) == null){
            catalogoJugadores.addJugador(jugador);
        } else {
            // catalogoJugadores.updateJugador(jugador);
        }
    }

    public Pieza moverPieza (char desdeX, int desdeY, char hastaX, int hastaY) throws NullPointerException, Exception{

        Pieza pieza = partidaActual.moverPieza(desdeX, desdeY, hastaX, hastaY);


        /*
        try {
            pieza = partidaActual.getTablero().get(new Posicion(desdeX, desdeY));
            System.out.println(pieza.getNombre());
        } catch (NullPointerException e){
            throw new Exception("No hay pieza en la posición inicial");
        }

        try {
            pieza.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);
            Pieza piezaTmp = partidaActual.getTablero().get(new Posicion(hastaX, hastaY));
            if (pieza.getColor() == piezaTmp.getColor()){
                throw new Exception("Hay una pieza propia en esa posición");
            }
        } catch (NullPointerException e){
            // si no hay nada en la posición puedo mover la pieza
            partidaActual.getTablero().put(new Posicion(hastaX, hastaY), pieza);
            partidaActual.getTablero().remove(new Posicion(desdeX, desdeY));
        } /*catch (Exception e){
            throw new Exception("La pieza queda fuera del tablero.");
        }*/


        return pieza;
    }

    public String getTurno(){
        return partidaActual.getTurno();
    }

}
