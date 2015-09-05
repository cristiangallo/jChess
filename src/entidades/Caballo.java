package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Caballo extends Pieza{

    private Caballo(){}

    public Caballo(String color, Partida partida){
        setColor(color);
        setNombre('C');
        setPartida(partida);
    }

    @Override
    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws appException{
        // verificar que por lo menos no se nos caiga del tablero y si la posición destino
        // está ocupada que sea una pieza contraria
        super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);

        if(hastaX != desdeX - 1 && hastaX != desdeX + 1 && hastaX != desdeX + 2 && hastaX != desdeX - 2)
            throw new appException("El movimiento que querés realizar no es válido.");
        if(hastaY != desdeY - 2 && hastaY != desdeY + 2 && hastaY != desdeY - 1 && hastaY != desdeY + 1)
            throw new appException("El movimiento que querés realizar no es válido.");

        return true;
    }

}
