package entidades;

import java.util.HashMap;
import java.util.Objects;
import appExceptions.appException;
import appExceptions.appGameOver;


/**
 * Created by cgallo on 31/07/15.
 */

public class Partida {

    public int getId() { return id; }

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

    public void setTablero(HashMap<Posicion, Pieza> tablero) {
        this.tablero = tablero;
    }

    private HashMap<Posicion, Pieza> tablero = new HashMap<>();

    public HashMap<Posicion, Pieza> getTablero() {
        return tablero;
    }

    private Partida() {}

    // constructor para partida guardada
    public Partida(int id, Jugador jugadorBlanco, Jugador jugadorNegro, String turno) {
        this.id = id;
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        this.turno = turno;
    }

    public Partida(Jugador jugadorBlanco, Jugador jugadorNegro) {
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;

        // inicializar el tablero
        Posicion posicion = new Posicion('a', 1);
        tablero.put(posicion, new Torre(false, "blanco", this, posicion));
        posicion = new Posicion('b', 1);
        tablero.put(posicion, new Caballo(false, "blanco", this, posicion));
        posicion = new Posicion('c', 1);
        tablero.put(posicion, new Alfil(false, "blanco", this, posicion));
        posicion = new Posicion('d', 1);
        tablero.put(posicion, new Rey(false, "blanco", this, posicion));
        posicion = new Posicion('e', 1);
        tablero.put(posicion, new Reina(false, "blanco", this, posicion));
        posicion = new Posicion('f', 1);
        tablero.put(posicion, new Alfil(false, "blanco", this, posicion));
        posicion = new Posicion('g', 1);
        tablero.put(posicion, new Caballo(false, "blanco", this, posicion));
        posicion = new Posicion('h', 1);
        tablero.put(posicion, new Torre(false, "blanco", this, posicion));
        posicion = new Posicion('a', 8);
        tablero.put(posicion, new Torre(false, "negro", this, posicion));
        posicion = new Posicion('b', 8);
        tablero.put(posicion, new Caballo(false, "negro", this, posicion));
        posicion = new Posicion('c', 8);
        tablero.put(posicion, new Alfil(false, "negro", this, posicion));
        posicion = new Posicion('d', 8);
        tablero.put(posicion, new Rey(false, "negro", this, posicion));
        posicion = new Posicion('e', 8);
        tablero.put(posicion, new Reina(false, "negro", this, posicion));
        posicion = new Posicion('f', 8);
        tablero.put(posicion, new Alfil(false, "negro", this, posicion));
        posicion = new Posicion('g', 8);
        tablero.put(posicion, new Caballo(false, "negro", this, posicion));
        posicion = new Posicion('h', 8);
        tablero.put(posicion, new Torre(false, "negro", this, posicion));
        for (char x = 'a'; x <='h'; x++){
            posicion = new Posicion(x, 2);
            tablero.put(posicion, new Peon(false, "blanco", this, posicion));
            posicion = new Posicion(x, 7);
            tablero.put(posicion, new Peon(false, "negro", this, posicion));
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pieza moverPieza (char desdeX, int desdeY, char hastaX, int hastaY) throws appException, appGameOver {
        Pieza pieza = tablero.get(new Posicion(desdeX, desdeY));
        try {
            //si el movimiento es válido muevo la pieza
            if (pieza.esMovimientoValido(hastaX, hastaY)){
                try{
                    Pieza piezaDestino = tablero.get(new Posicion(hastaX, hastaY));
                    piezaDestino.isGameOver();
                } catch (NullPointerException e){
                    // no hay nadie allí mové tranquilo
                }
                pieza.moverPieza(hastaX, hastaY);
                cambiarTurno();
            }
        } catch (NullPointerException e){
            throw new appException("No hay pieza en la posición desde donde intentás mover", e);
        }
        return pieza;
    }
}