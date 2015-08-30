package entidades;

/**
 * Created by cgallo on 31/07/15.
 */
public class Caballo extends Pieza{

    private Caballo(){}

    public Caballo(String color, Partida partida){
        setColor(color);
        setNombre('C');
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
        if(hastaX != desdeX - 1 && hastaX != desdeX + 1 && hastaX != desdeX + 2 && hastaX != desdeX - 2)
            return false;
        if(hastaY != desdeY - 2 && hastaY != desdeY + 2 && hastaY != desdeY - 1 && hastaY != desdeY + 1)
            return false;

        return true;
    }

}
