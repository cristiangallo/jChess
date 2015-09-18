package dataDB;

import entidades.Jugador;

/**
 * Created by cgallo on 04/08/15.
 */
public class CatalogoJugadores {

    private static CatalogoJugadores instancia;

    public static CatalogoJugadores getInstance() {
        if(instancia == null){
            instancia = new CatalogoJugadores();
        }
        return instancia;
    }
    // hago privado el constructor para que nadie pueda instanciar
    private CatalogoJugadores() {}

    public Jugador getByDni(int dni) {
        Jugador jugador = DBJugador.getByDni(dni);
        return jugador;
    }

    public void save(Jugador jugador) {
        DBJugador.save(jugador);
    }
}
