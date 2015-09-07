package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Torre extends Pieza{

    private Torre(){}

    public Torre(String color, Partida partida){
        setColor(color);
        setNombre('T');
        setPartida(partida);
    }

    @Override
    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws appException{

        super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);

        //desplazamiento vertical
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