package entidades;

/**
 * Created by cgallo on 31/07/15.
 */
public class Peon extends Pieza{

    public boolean isFueMovida() {
        return fueMovida;
    }

    public void setFueMovida(boolean fueMovida) {
        this.fueMovida = fueMovida;
    }

    private boolean fueMovida;

    private Peon(){}

    public Peon(String color, Partida partida){
        setColor(color);
        setNombre('P');
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
            throw new Exception("No es un movimiento válido.");
        }
        // hacia adelante de a dos si no se movió la pieza
        // si se movió, de a uno hacia adelante
        // si come, uno adelante a la derecha o uno adelante a la izquierda
        //ah, me acabo de dar cuenta que el adelante de las blancas es distinto del de las negras jeje
        if (getColor() == "blanco" && (!fueMovida && desdeX == hastaX && hastaY - desdeY == 2) ||
            (desdeX == hastaX && hastaY - desdeY == 1) || (Math.abs(Character.getNumericValue(hastaX) - Character.getNumericValue(desdeX)) == 1 &&
                    desdeY - desdeX == 1)) return true;
        if (getColor() == "negro" && (!fueMovida && desdeX == hastaX && desdeY - hastaY == 2) ||
            (desdeX == hastaX && hastaY - desdeY == 1) || (Math.abs(Character.getNumericValue(hastaX) - Character.getNumericValue(desdeX)) == 1 &&
                    desdeY - desdeX == 1)) return true;


        throw new Exception("No es un movimiento válido.");
    }

}