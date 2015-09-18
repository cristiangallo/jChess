package negocio;

import dataDB.*;
import entidades.*;
import appExceptions.appException;
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

    private Partida getPartidaPendiente(){
        Partida partidaPendiente = null;
        ArrayList<Partida> partidasPendientes = jugadorBlancoActual.getPartidasPendientes(jugadorNegroActual);
        if (!(partidasPendientes.isEmpty())){
            partidaPendiente = partidasPendientes.get(0);
        }
        return partidaPendiente;
    }

    public Partida iniciarPartida() throws appException {
        if (jugadorBlancoActual == null) throw new appException("No se definió jugador blanco.");
        if (jugadorNegroActual == null) throw new appException("No se definió jugador negro");
        Partida partida = getPartidaPendiente();
        if (partida == null){
            partidaActual = new Partida(jugadorBlancoActual, jugadorNegroActual);

            jugadorBlancoActual.addPartida(partidaActual);
        }
        else{
            partidaActual = partida;
        }
        return partidaActual;
    }

    public void addJugador (Jugador jugador) {
        if(catalogoJugadores.getByDni(jugador.getDni()) == null){
            catalogoJugadores.save(jugador);
        }
    }

    public Pieza moverPieza (char desdeX, int desdeY, char hastaX, int hastaY) throws appException{
        Pieza pieza = partidaActual.moverPieza(desdeX, desdeY, hastaX, hastaY);
        return pieza;
    }

    public String getTurno(){
        return partidaActual.getTurno();
    }

}
