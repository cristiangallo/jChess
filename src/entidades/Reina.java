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
        super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);

        // desplazamiento vertical u horizontal como la torre
        if( desdeX == hastaX ) {
            for (int y = desdeY + 1; y < hastaY; y++){
                Pieza piezaEnElMedio = getPartida().getTablero().get(new Posicion(desdeX, y));
                if (piezaEnElMedio != null) throw new appException("Hay una pieza en el medio.");
            }
            return true;
        }
        if( desdeY == hastaY ) {
            for (char x = (char)(desdeX + 1); x < hastaX; x++){
                Pieza piezaEnElMedio = getPartida().getTablero().get(new Posicion(x, desdeY));
                if (piezaEnElMedio != null) throw new appException("Hay una pieza en el medio.");
            }
            return true;
        }

        // desplazamientos identicos en modulo como el alfil
        if (Math.abs(hastaX - desdeX) == Math.abs(hastaY - desdeY)) {
            int y = desdeY;
            for (char x = (char)(desdeX + 1); x < hastaX; x++){
                if (hastaY - desdeY > 0) {
                    y++;
                } else {
                    y--;
                }
                Pieza piezaEnElMedio = getPartida().getTablero().get(new Posicion(x, y));
                if (piezaEnElMedio != null) throw new appException("Hay una pieza en el medio.");
            }

            y = desdeY;
            for (char x = (char)(desdeX - 1); hastaX < x; x--){
                if (hastaY - desdeY > 0) {
                    y++;
                } else {
                    y--;
                }
                Pieza piezaEnElMedio = getPartida().getTablero().get(new Posicion(x, y));
                if (piezaEnElMedio != null) throw new appException("Hay una pieza en el medio.");
            }
            return true;
        }

        throw new appException("El movimiento que querés realizar no es válido.");
    }

}
