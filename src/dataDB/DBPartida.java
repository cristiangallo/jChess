package dataDB;

/**
 * Created by cgallo on 04/08/15.
 */

import conexionDB.ConexionDB;
import entidades.Jugador;
import entidades.Partida;
import entidades.Pieza;
import entidades.Posicion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class DBPartida {

    public static Partida getPartidaPendiente(Jugador jugadorBlanco, Jugador jugadorNegro){
        Partida partidaPendiente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, jugador_blanco_id, jugador_negro_id, turno from partidas where jugador_blanco_id = ? " +
                            "and jugador_negro_id = ?"
            );
            stmt.setInt(1, jugadorBlanco.getDni());
            stmt.setInt(2, jugadorNegro.getDni());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                int partida_id = rs.getInt("id");
                partidaPendiente = new Partida(partida_id, jugadorBlanco, jugadorNegro, rs.getString("turno"));
                partidaPendiente.setTablero(DBPieza.getTablero(partidaPendiente));
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
        return partidaPendiente;
    }

    public static void save(Partida partida) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "insert into partidas(id, jugador_blanco_id, jugador_negro_id, turno) values (0, ?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, partida.getJugadorBlanco().getDni());
            stmt.setInt(2, partida.getJugadorNegro().getDni());
            stmt.setString(3, partida.getTurno());
            stmt.execute();
            rs = stmt.getGeneratedKeys();
            if(rs != null && rs.next()){
                partida.setId(rs.getInt(1));
                DBPieza.saveTablero(partida);
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

    public static void delete(Partida partida) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "delete from partidas where id = ?;");
            stmt.setInt(1, partida.getId());
            stmt.executeUpdate();
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
