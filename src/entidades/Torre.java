package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Torre extends Pieza{

    public boolean isFueMovida() {
        return fueMovida;
    }

    public void setFueMovida() {

        this.fueMovida = true;
    }

    private boolean fueMovida = false;

    private Torre(){}

    public Torre(String color, Partida partida){
        setColor(color);
        setNombre('T');
        setPartida(partida);
    }

    @Override
    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws appException{

        super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);

        //desplazamiento horizontal o vertical
        if(desdeX == hastaX || desdeY == hastaY)
            return true;

        throw new appException("El movimiento que querés realizar no es válido.");
    }
}