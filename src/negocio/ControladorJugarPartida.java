package negocio;

import appExceptions.appGameOver;
import dataDB.*;
import entidades.*;
import appExceptions.*;
import java.util.ArrayList;

/**
 * Created by cgallo on 04/08/15.
 */
public class ControladorJugarPartida {

    private CatalogoJugadores catalogoJugadores = CatalogoJugadores.getInstance();

    public Jugador getJugadorBlancoActual() {
        return jugadorBlancoActual;
    }

    public Jugador getJugadorNegroActual() {
        return jugadorNegroActual;
    }

    private Jugador jugadorBlancoActual;
    private Jugador jugadorNegroActual;
    private Partida partidaActual;

    private Jugador getJugadorByDni(int dni){
        Jugador jugador = catalogoJugadores.getByDni(dni);
        return jugador;
    }

    public Jugador buscarJugadorBlanco(int dni){
        jugadorBlancoActual = getJugadorByDni(dni);
        return jugadorBlancoActual;
    }

    public Jugador buscarJugadorNegro(int dni){
        this.jugadorNegroActual = getJugadorByDni(dni);
        return this.jugadorNegroActual;
    }

    public Partida hayPartidaPendiente() throws appException {
        if (jugadorBlancoActual == null) throw new appException("No se definió jugador blanco.");
        if (jugadorNegroActual == null) throw new appException("No se definió jugador negro.");
        partidaActual = DBPartida.getPartidaPendiente(jugadorBlancoActual, jugadorNegroActual);
        return partidaActual;
    }

    public Partida iniciarPartida() throws appException {
        if (jugadorBlancoActual == null) throw new appException("No se definió jugador blanco.");
        if (jugadorNegroActual == null) throw new appException("No se definió jugador negro.");
        partidaActual = new Partida(jugadorBlancoActual, jugadorNegroActual);
        DBPartida.save(partidaActual);
        return partidaActual;
    }

    public void addJugador (int dni, String nombre, String apellido) throws appException {
        if ( nombre ==null || apellido==null ) throw new appException("Falta nombre o apellido");
        if(catalogoJugadores.getByDni(dni) == null){
            Jugador jugador = new Jugador(dni, nombre, apellido);
            catalogoJugadores.save(jugador);
        }
    }

    public Pieza moverPieza (char desdeX, int desdeY, char hastaX, int hastaY) throws appException, appGameOver{
        Pieza pieza = null;
        try {
            pieza = partidaActual.moverPieza(desdeX, desdeY, hastaX, hastaY);
            DBPieza.updateTablero(partidaActual);
        } catch (appGameOver e) {
            DBPartida.delete(partidaActual);
            throw new appGameOver("Jaque mate");
        }

        return pieza;
    }

    public String getTurno(){
        return partidaActual.getTurno();
    }

}
