package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import java.util.Objects;
import appExceptions.appException;

public abstract class Pieza {

    private boolean fueMovida;

    public boolean isFueMovida() {
        return fueMovida;
    }

    public void setFueMovida() {
        this.fueMovida = true;
    }

    protected boolean gameOver = false;

    private String color;

    private Character nombre;

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    private Partida partida;

    public Character getNombre() {
        return nombre;
    }

    public void setNombre(Character nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws appException{

        // verifico que por lo no se nos caiga del tablero
        if (!('a'<=desdeX && desdeX<='h') || !('a'<=hastaX && hastaX<='h') ||
                !(1<=desdeY && desdeY<=8) || !(1<=hastaY && hastaY<=8)) {
            throw new appException("La pieza se te cae del tablero.");
        }

        //verifico que la pieza se mueva de una posición a otra
        if (desdeX == hastaX && desdeY == hastaY) throw new appException("La pieza no se movió en el tablero.");

        //verifico que haya una pieza en el origen y que sea el turno de este color
        String turno = partida.getTurno();
        Pieza pieza = partida.getTablero().get(new Posicion(desdeX, desdeY));
        try {
            if (!Objects.equals(turno, pieza.getColor())) throw new appException("Mueve el jugador " + turno);
        } catch (NullPointerException e){
            throw new appException("No hay pieza en la posición desde donde intentás mover", e);
        }

        //verifico que en la posición destino no haya una pieza propia
        try {
            Pieza piezaDestino = partida.getTablero().get(new Posicion(hastaX, hastaY));
            if (Objects.equals(pieza.getColor(), piezaDestino.getColor()))
                throw new appException("Hay una pieza propia en la posición hacia donde intentás mover");
        } catch (NullPointerException e){
            //esta excepcion se lanza porque no hay pieza en la casilla destino,
            // por lo tanto es correcto el movimiento y no hay que manejarla.
        }
        return true;
    }
}

