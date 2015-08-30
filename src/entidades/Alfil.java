package entidades;

/**
 * Created by cgallo on 31/07/15.
 */
public class Alfil extends Pieza{

    private Alfil(){}

    public Alfil(String color, Partida partida){
        setColor(color);
        setNombre('A');
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
        if(hastaX - desdeX == hastaY - desdeY)
            return true;

        return false;
    }

}