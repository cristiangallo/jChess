package entidades;

/**
 * Created by cgallo on 31/07/15.
 */
public abstract class Pieza {
    // no hace falta saber si una pieza está en juego o no porque las vamos a eliminar
    // private boolean enJuego;
    private String color;

    private Character nombre;

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    private Partida partida;

    public Character getNombre() {
        return nombre;
    }

    public void setNombre(Character nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean esMovimientoValido(char desdeX, int desdeY, char hastaX, int hastaY) throws Exception{
        // verificar que por lo menos no se nos caiga del tablero y si la posición destino
        // está ocupada que sea una pieza contraria

        if (!('a'<=desdeX && desdeX<='h') || !('a'<=hastaX && hastaX<='h') ||
                !(1<=desdeY && desdeY<=8) || !(1<=hastaY && hastaY<=8)) {
            throw new Exception("La pieza queda fuera del tablero.");
        }

        if (desdeX == hastaX && desdeY == hastaY) throw new Exception("La pieza no se movió en el tablero.");

        return true;
    }

}

