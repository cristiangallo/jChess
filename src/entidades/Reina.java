package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Reina extends Pieza{

    private Reina(){}

    public Reina(String color, Partida partida){
        setColor(color);
        setNombre('Q');
        setPartida(partida);
    }

    @Override
    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws appException{
        // verificar que por lo menos no se nos caiga del tablero y si la posición destino
        // está ocupada que sea una pieza contraria
        super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);

        // desplazamiento horizontal o vertical
        if(desdeX == hastaX || desdeY == hastaY)
            return true;

        // en diagonal, los desplazamientos en X e Y deben ser iguales
        if(hastaX - desdeX == hastaY - desdeY)
            return true;

        throw new appException("El movimiento que querés realizar no es válido.");
    }

}
