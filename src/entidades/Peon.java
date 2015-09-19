package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Peon extends Pieza{
    private static final String NOMBRE = "P";

    public String getNombre() {
        return NOMBRE;
    }


    private Peon(){}

    public Peon(boolean fueMovida, String color, Partida partida, Posicion posicion){
        setColor(color);
        setPartida(partida);
        setPosicion(posicion);
        setFueMovida(fueMovida);
    }

    public boolean isGameOver(){ return false; }

    @Override
    public boolean esMovimientoValido(char hastaX, int hastaY) throws appException{
        // verificar que por lo menos no se nos caiga del tablero y si la posición destino
        // está ocupada que sea una pieza contraria
        super.esMovimientoValido(hastaX, hastaY);

        char desdeX = this.getPosicion().getX();
        int desdeY = this.getPosicion().getY();


        // hacia adelante de a dos si no se movió la pieza
        // si se movió, de a uno hacia adelante
        // si tiene uno adelante topa como los cordobeses
        // si come, uno adelante a la derecha o uno adelante a la izquierda
        //ah, me acabo de dar cuenta que el adelante de las blancas es distinto del de las negras jeje
        Pieza piezaEnDestino = getPartida().getTablero().get(new Posicion(hastaX, hastaY));

        if (getColor().equals("blanco")){
            // avanza vertical de a 1
            if (desdeX == hastaX && hastaY - desdeY == 1 && piezaEnDestino == null) return true;
            // avanza vertical de a 2, debo verificar que no haya piezas en las dos posiciones intermedias
            Pieza piezaEnElMedio = getPartida().getTablero().get(new Posicion(hastaX, 3));
            if (!isFueMovida() && desdeX == hastaX && hastaY - desdeY == 2
                    && piezaEnElMedio == null && piezaEnDestino == null) return true;

            // avanza en diagonal para comer
            if ( Math.abs(hastaX - desdeX) == 1 && hastaY - desdeY == 1 && piezaEnDestino != null ) return true;
        } else {
            if (desdeX == hastaX && desdeY - hastaY == 1 && piezaEnDestino == null) return true;
            Pieza piezaEnElMedio = getPartida().getTablero().get(new Posicion(hastaX, 6));
            if (!isFueMovida() && desdeX == hastaX && desdeY - hastaY == 2
                    && piezaEnElMedio == null && piezaEnDestino == null) return true;
            if ( Math.abs(hastaX - desdeX) == 1 && desdeY - hastaY == 1 && piezaEnDestino != null ) return true;
        }

        throw new appException("No es un movimiento válido.");
    }
}