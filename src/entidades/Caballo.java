package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Caballo extends Pieza{

    private static final String NOMBRE = "C";

    public String getNombre() {
        return NOMBRE;
    }

    private Caballo(){}

    public Caballo(String color, Partida partida, Posicion posicion){
        setColor(color);
        setPartida(partida);
        setPosicion(posicion);
    }

    @Override
    public boolean esMovimientoValido(char hastaX, int hastaY) throws appException{
        super.esMovimientoValido(hastaX, hastaY);

        char desdeX = this.getPosicion().getX();
        int desdeY = this.getPosicion().getY();

        if (Math.abs(desdeX - hastaX) == 1 && Math.abs(desdeY - hastaY) == 2) return true;

        if (Math.abs(desdeX - hastaX) == 2 && Math.abs(desdeY - hastaY) == 1) return true;

        throw new appException("El movimiento que querés realizar no es válido.");
    }
}
