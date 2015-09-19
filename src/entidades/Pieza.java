package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import java.util.Objects;
import appExceptions.appException;

public abstract class Pieza {

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    private Posicion posicion;

    public void setFueMovida(boolean fueMovida) {
        this.fueMovida = fueMovida;
    }
    public boolean isFueMovida() {
        return fueMovida;
    }
    private boolean fueMovida;

    private String color;

    public abstract String getNombre();

    public abstract boolean isGameOver();

    private Partida partida;

    public void setFueMovida() {
        this.fueMovida = true;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public String getColor() {
        return color;
    }

    protected void setColor(String color) {
        this.color = color;
    }

    public boolean esMovimientoValido(char hastaX, int hastaY) throws appException{

        // verifico que por lo no se nos caiga del tablero
        if (!('a'<=hastaX && hastaX<='h') || !(1<=hastaY && hastaY<=8)) {
            throw new appException("La pieza se te cae del tablero.");
        }

        //verifico que la pieza se mueva de una posición a otra
        char desdeX = getPosicion().getX();
        int desdeY = getPosicion().getY();
        if (desdeX == hastaX && desdeY == hastaY) throw new appException("La pieza no se movió en el tablero.");

        //verifico que haya una pieza en el origen y que sea el turno de este color
        String turno = partida.getTurno();
        try {
            if (!Objects.equals(turno, this.getColor())) throw new appException("Mueve el jugador " + turno);
        } catch (NullPointerException e){
            throw new appException("No hay pieza en la posición desde donde intentás mover", e);
        }

        //verifico que en la posición destino no haya una pieza propia
        try {
            Pieza piezaDestino = partida.getTablero().get(new Posicion(hastaX, hastaY));
            if (Objects.equals(this.getColor(), piezaDestino.getColor()))
                throw new appException("Hay una pieza propia en la posición hacia donde intentás mover");
        } catch (NullPointerException e){
            //esta excepcion se lanza porque no hay pieza en la casilla destino,
            // por lo tanto es correcto el movimiento y no hay que manejarla.
        }
        return true;
    }

    public void moverPieza(char hastaX, int hastaY){
        Posicion posicionActual = getPosicion();
        Posicion nuevaPosicion = new Posicion(hastaX, hastaY);
        getPartida().getTablero().remove(posicionActual);
        getPartida().getTablero().put(nuevaPosicion, this);
        setPosicion(nuevaPosicion);
        if (!isFueMovida()) { setFueMovida(true); }
    }
}

