package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Torre extends Pieza{

    private static final String NOMBRE = "T";

    public String getNombre() {
        return NOMBRE;
    }

    private Torre(){}

    public Torre(String color, Partida partida, Posicion posicion){
        setColor(color);
        setPartida(partida);
        setPosicion(posicion);
    }

    public boolean isGameOver(){ return false; }

    @Override
    public boolean esMovimientoValido(char hastaX, int hastaY) throws appException{

        super.esMovimientoValido(hastaX, hastaY);

        //desplazamiento vertical
        char desdeX = this.getPosicion().getX();
        int desdeY = this.getPosicion().getY();
        if( desdeX == hastaX ) {
            for (int y = desdeY + 1; y < hastaY; y++){
                Pieza piezaEnElMedio = getPartida().getTablero().get(new Posicion(desdeX, y));
                if (piezaEnElMedio != null) throw new appException("Hay una pieza en el medio.");
            }
            return true;
        }
        //desplazamiento horizontal
        if( desdeY == hastaY ) {
            for (char x = (char)(desdeX + 1); x < hastaX; x++){
                Pieza piezaEnElMedio = getPartida().getTablero().get(new Posicion(x, desdeY));
                if (piezaEnElMedio != null) throw new appException("Hay una pieza en el medio.");
            }
            return true;
        }

        throw new appException("El movimiento que querés realizar no es válido.");
    }
}