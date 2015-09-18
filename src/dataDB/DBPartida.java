package dataDB;

/**
 * Created by cgallo on 04/08/15.
 */

import conexionDB.ConexionDB;
import entidades.Partida;
import entidades.Pieza;
import entidades.Posicion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class DBPartida {

    public static void save(Partida partida) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "insert into partidas(jugador_blanco_id, jugador_negro_id, turno) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, partida.getJugadorBlanco().getDni());
            stmt.setInt(2, partida.getJugadorNegro().getDni());
            stmt.setString(3, partida.getTurno());
            stmt.execute();
            rs = stmt.getGeneratedKeys();
            if(rs != null && rs.next()){
                int partida_id = rs.getInt(1);
                partida.setId(partida_id);
                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "insert into piezas(fueMovida, gameOver, color, nombre, partida_id, posicionX, posicionY) values (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
                );

                for (Map.Entry<Posicion, Pieza> pieza: partida.getTablero()) {
                    stmt.setInt(1, partida.getJugadorBlanco().getDni());
                    stmt.setInt(2, partida.getJugadorNegro().getDni());
                    stmt.setString(3, partida.getTurno());
                    stmt.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.cancel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionDB.getInstancia().releaseConexion();
        }
    }
}
