package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import java.util.HashMap;
import java.util.Objects;
import appExceptions.appException;


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
/*
    public Partida(int id, Jugador blanco, Jugador negro) {
        this.id = id;
        this.jugadorBlanco = blanco;
        this.jugadorNegro = negro;
    }
*/
    public Partida(Jugador jugadorBlanco, Jugador jugadorNegro) {
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        //this.turno = jugadorBlanco;
        // inicializar el tablero
        tablero.put(new Posicion('a', 1), new Torre("blanco", this));
        tablero.put(new Posicion('b', 1), new Caballo("blanco", this));
        tablero.put(new Posicion('c', 1), new Alfil("blanco", this));
        tablero.put(new Posicion('d', 1), new Rey("blanco", this));
        tablero.put(new Posicion('e', 1), new Reina("blanco", this));
        tablero.put(new Posicion('f', 1), new Alfil("blanco", this));
        tablero.put(new Posicion('g', 1), new Caballo("blanco", this));
        tablero.put(new Posicion('h', 1), new Torre("blanco", this));
        tablero.put(new Posicion('a', 8), new Torre("negro", this));
        tablero.put(new Posicion('b', 8), new Caballo("negro", this));
        tablero.put(new Posicion('c', 8), new Alfil("negro", this));
        tablero.put(new Posicion('d', 8), new Rey("negro", this));
        tablero.put(new Posicion('e', 8), new Reina("negro", this));
        tablero.put(new Posicion('f', 8), new Alfil("negro", this));
        tablero.put(new Posicion('g', 8), new Caballo("negro", this));
        tablero.put(new Posicion('h', 8), new Torre("negro", this));
        for (char x = 'a'; x <='h'; x++){
            tablero.put(new Posicion(x, 2), new Peon("blanco", this));
            tablero.put(new Posicion(x, 7), new Peon("negro", this));
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTablero(HashMap<Posicion, Pieza> tablero) {
        this.tablero = tablero;
    }

    public Pieza moverPieza (char desdeX, int desdeY, char hastaX, int hastaY) throws appException{
        Pieza pieza = tablero.get(new Posicion(desdeX, desdeY));
        pieza.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);
        tablero.put(new Posicion(hastaX, hastaY), pieza);
        tablero.remove(new Posicion(desdeX, desdeY));
        cambiarTurno();


        //todo esto lo moví dentro de la pieza porque es responsabilidad de ella
        /*
        try {

            Pieza piezaTmp = tablero.get(new Posicion(hastaX, hastaY));
            if (pieza.getColor() == piezaTmp.getColor()){
                throw new Exception("Hay una pieza propia en esa posición");
            }
        } catch (appException e){
            // si no hay nada en la posición puedo mover la pieza

        }

        try {
            pieza = tablero.get(new Posicion(desdeX, desdeY));
            if (!Objects.equals(turno, pieza.getColor())) throw new Exception("Mueve el jugador " + turno);
            pieza.getColor();
            System.out.println(turno);
            System.out.println(pieza.getNombre() + pieza.getColor());
        } catch (NullPointerException e){
            throw new Exception("No hay pieza en la posición inicial");
        }

        try {
            pieza.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);
            Pieza piezaTmp = tablero.get(new Posicion(hastaX, hastaY));
            if (pieza.getColor() == piezaTmp.getColor()){
                throw new Exception("Hay una pieza propia en esa posición");
            }
        } catch (NullPointerException e){
            // si no hay nada en la posición puedo mover la pieza
            tablero.put(new Posicion(hastaX, hastaY), pieza);
            tablero.remove(new Posicion(desdeX, desdeY));
            cambiarTurno();
        }
        */
        return pieza;
    }

}