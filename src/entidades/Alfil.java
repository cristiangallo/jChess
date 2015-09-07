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

        // los desplazamientos en x e y deben ser idénticos
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