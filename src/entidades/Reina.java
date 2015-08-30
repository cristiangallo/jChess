package entidades;

/**
 * Created by cgallo on 31/07/15.
 */
public class Reina extends Pieza{

    private Reina(){}

    public Reina(String color, Partida partida){
        setColor(color);
        setNombre('Q');
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

        // desplazamiento horizontal
        if(desdeX == hastaX)
            return true;

        // desplazamiento vertical
        if(desdeY == hastaY)
            return true;

        // en diagonal, los desplazamientos en X e Y deben ser iguales
        if(hastaX - desdeX == hastaY - desdeY)
            return true;

        return false;
    }

}
