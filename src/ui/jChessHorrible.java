package ui;

import entidades.*;
import negocio.ControladorJugarPartida;

/**
 * Created by cgallo on 03/08/15.
 */
public class jChessHorrible {

    public static void main(String[] args) {
        Pieza torre = new Torre("negro", new Partida(new Jugador(), new Jugador()), new Posicion('a',1));
        FormMasterChess formMasterChess = new FormMasterChess();

    }
}
