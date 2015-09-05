package entidades;

/**
 * Created by cgallo on 31/07/15.
 */

import appExceptions.appException;

public final class Rey extends Pieza{

    private boolean fueMovida;

    public boolean isFueMovida() {
        return fueMovida;
    }

    public void setFueMovida() {

        this.fueMovida = true;
    }

    private Rey(){}

    public Rey(String color, Partida partida){
        setColor(color);
        setNombre('R');
        setPartida(partida);
        gameOver = true;
    }

    @Override
    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws appException{
        // verificar que por lo menos no se nos caiga del tablero y si la posición destino
        // está ocupada que sea una pieza contraria
        super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY);
        // supongo que la distancia debería ser raíz de 2 = 1² + 1² pero estoy usando char para las x...
        // if(Math.sqrt(Math.pow(Math.abs((hastaX - desdeX)),2)) + Math.pow(Math.abs((hastaY - desdeY)), 2) != Math.sqrt(2)){
        //    return false;
        //}
        return true;
    }

}