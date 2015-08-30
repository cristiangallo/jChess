package entidades;

/**
 * Created by cgallo on 31/07/15.
 */
public class Rey extends Pieza{
    public boolean isFueMovida() {
        return fueMovida;
    }

    public void setFueMovida(boolean fueMovida) {
        this.fueMovida = fueMovida;
    }

    private boolean fueMovida;

    private boolean gameOver = true;

    private Rey(){}

    public Rey(String color, Partida partida){
        setColor(color);
        setNombre('R');
        setPartida(partida);
    }

    @Override
    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws Exception{
        // esta variable booleana la usamos por legibilidad del código ya que por ahí es
        // menos code readeable por !f
        boolean isOk = true;
        // verificar que por lo menos no se nos caiga del tablero y si la posición destino
        // está ocupada que sea una pieza contraria
        if (super.esMovimientoValido(desdeX, desdeY, hastaX, hastaY) != isOk){
            return false;
        }
        // supongo que la distancia debería ser raíz de 2 = 1² + 1² pero estoy usando char para las x...
        // if(Math.sqrt(Math.pow(Math.abs((hastaX - desdeX)),2)) + Math.pow(Math.abs((hastaY - desdeY)), 2) != Math.sqrt(2)){
        //    return false;
        //}
        return true;
    }

}