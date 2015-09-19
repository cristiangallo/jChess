package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Rey extends Pieza{
    private static final String NOMBRE = "R";

    public static boolean isGAMEOVER() {
        return GAMEOVER;
    }

    private static final boolean GAMEOVER = true;

    public String getNombre() {
        return NOMBRE;
    }

    private Rey(){}

    public Rey(String color, Partida partida, Posicion posicion){
        setColor(color);
        setPartida(partida);
        setPosicion(posicion);
    }

    public boolean isGameOver(){ return true; }

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