package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.*;

public final class Rey extends Pieza{
    private static final String NOMBRE = "R";

    public String getNombre() {
        return NOMBRE;
    }

    private Rey(){}

    public Rey(boolean fueMovida, String color, Partida partida, Posicion posicion){
        setColor(color);
        setPartida(partida);
        setPosicion(posicion);
        setFueMovida(fueMovida);
    }

    public boolean isGameOver() throws appGameOver { throw new appGameOver("Jaque mate"); }

    @Override
    public boolean esMovimientoValido(char hastaX, int hastaY) throws appException{
        super.esMovimientoValido(hastaX, hastaY);
        char desdeX = this.getPosicion().getX();
        int desdeY = this.getPosicion().getY();

        // la distancia de la posición origen a la destino debe ser 1 o raíz de 2
        int distancia = (int) Math.abs((Math.abs((hastaX - desdeX))) - Math.abs((hastaY - desdeY)));
        if(distancia == 1 || distancia == 0){
            return true;
        }
        throw new appException("El movimiento que querés realizar no es válido.");
    }

}