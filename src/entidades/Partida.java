package entidades;

import java.util.HashMap;
import java.util.Objects;
import appExceptions.appException;
import dataDB.DBPartida;


/**
 * Created by cgallo on 31/07/15.
 */

public class Partida {

    private int id;

    public Jugador getJugadorBlanco() {
        return jugadorBlanco;
    }

    public void setJugadorBlanco(Jugador jugadorBlanco) {
        this.jugadorBlanco = jugadorBlanco;
    }

    public Jugador getJugadorNegro() {
        return jugadorNegro;
    }

    public void setJugadorNegro(Jugador jugadorNegro) {
        this.jugadorNegro = jugadorNegro;
    }

    private String turno = "blanco";

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void cambiarTurno(){
        if (Objects.equals(turno, "blanco")) turno="negro"; else turno="blanco";
    }

    private Jugador jugadorBlanco = null;

    private Jugador jugadorNegro = null;

    private HashMap<Posicion, Pieza> tablero = new HashMap<>();

    public HashMap<Posicion, Pieza> getTablero() {
        return tablero;
    }

    private Partida() {}

    // constructor para partida guardada
    public Partida(int id, Jugador jugadorBlanco, Jugador jugadorNegro, String turno, HashMap<Posicion, Pieza> tablero) {
        this.id = id;
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        this.turno = turno;
        this.tablero.putAll(tablero);
    }

    public Partida(Jugador jugadorBlanco, Jugador jugadorNegro) {
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;

        // inicializar el tablero
        Posicion posicion = new Posicion('a', 1);
        tablero.put(posicion, new Torre("blanco", this, posicion));
        posicion = new Posicion('b', 1);
        tablero.put(posicion, new Caballo("blanco", this, posicion));
        posicion = new Posicion('c', 1);
        tablero.put(posicion, new Alfil("blanco", this, posicion));
        posicion = new Posicion('d', 1);
        tablero.put(posicion, new Rey("blanco", this, posicion));
        posicion = new Posicion('e', 1);
        tablero.put(posicion, new Reina("blanco", this, posicion));
        posicion = new Posicion('f', 1);
        tablero.put(posicion, new Alfil("blanco", this, posicion));
        posicion = new Posicion('g', 1);
        tablero.put(posicion, new Caballo("blanco", this, posicion));
        posicion = new Posicion('h', 1);
        tablero.put(posicion, new Torre("blanco", this, posicion));
        posicion = new Posicion('a', 8);
        tablero.put(posicion, new Torre("negro", this, posicion));
        posicion = new Posicion('b', 8);
        tablero.put(posicion, new Caballo("negro", this, posicion));
        posicion = new Posicion('c', 8);
        tablero.put(posicion, new Alfil("negro", this, posicion));
        posicion = new Posicion('d', 8);
        tablero.put(posicion, new Rey("negro", this, posicion));
        posicion = new Posicion('e', 8);
        tablero.put(posicion, new Reina("negro", this, posicion));
        posicion = new Posicion('f', 8);
        tablero.put(posicion, new Alfil("negro", this, posicion));
        posicion = new Posicion('g', 8);
        tablero.put(posicion, new Caballo("negro", this, posicion));
        posicion = new Posicion('h', 8);
        tablero.put(posicion, new Torre("negro", this, posicion));
        for (char x = 'a'; x <='h'; x++){
            posicion = new Posicion(x, 2);
            tablero.put(posicion, new Peon("blanco", this, posicion));
            posicion = new Posicion(x, 7);
            tablero.put(posicion, new Peon("negro", this, posicion));
        }
        DBPartida.save(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pieza moverPieza (char desdeX, int desdeY, char hastaX, int hastaY) throws appException{
        Pieza pieza = tablero.get(new Posicion(desdeX, desdeY));
        try {
            //si el movimiento es válido muevo la pieza
            if (pieza.esMovimientoValido(hastaX, hastaY)){
                pieza.moverPieza(hastaX, hastaY);
                cambiarTurno();
            }
        } catch (NullPointerException e){
            throw new appException("No hay pieza en la posición desde donde intentás mover", e);
        }
        return pieza;
    }
}