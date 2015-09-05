package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Alfil extends Pieza{

    private Alfil(){}

    public Alfil(String color, Partida partida){
        setColor(color);
        setNombre('A');
        setPartida(partida);
    }

    @Override
    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws appException{
        super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);

        if (hastaX - desdeX != hastaY - desdeY)
            throw new appException("El movimiento que querés realizar no es válido.");

        return true;
    }

}