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
        super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);

        if (Math.abs(desdeX - hastaX) == 1 && Math.abs(desdeY - hastaY) == 2) return true;

        if (Math.abs(desdeX - hastaX) == 2 && Math.abs(desdeY - hastaY) == 1) return true;

        throw new appException("El movimiento que querés realizar no es válido.");
    }
}
