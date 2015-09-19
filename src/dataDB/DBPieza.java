package dataDB;

import conexionDB.ConexionDB;
import entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by cgallo on 19/09/15.
 */
public class DBPieza {

    public static void updateTablero(Partida partida){

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "delete from piezas where partida_id = ?"
            );
            stmt.setInt(1, partida.getId());
            stmt.executeUpdate();
            saveTablero(partida);
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


    public static void saveTablero(Partida partida){

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "insert into piezas(fueMovida, color, nombre, partida_id, posicionX, posicionY) " +
                            "values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
            );
            HashMap<Posicion, Pieza> tablero = partida.getTablero();
            for (Pieza pieza: tablero.values()) {
                stmt.setBoolean(1, pieza.isFueMovida());
                stmt.setString(2, pieza.getColor());
                stmt.setString(3, pieza.getNombre());
                stmt.setInt(4, partida.getId());
                stmt.setString(5, String.valueOf(pieza.getPosicion().getX()));
                stmt.setInt(6, pieza.getPosicion().getY());
                stmt.execute();
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

    public static HashMap<Posicion, Pieza> getTablero(Partida partida){
        HashMap<Posicion, Pieza> tablero = new HashMap<Posicion, Pieza>();
        Posicion posicion = null;
        Pieza pieza = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select id, fueMovida, color, nombre, posicionX, posicionY from piezas where partida_id = ?"
            );
            stmt.setInt(1, partida.getId());
            rs = stmt.executeQuery();
            while(rs != null && rs.next()){
                posicion = new Posicion(rs.getString("posicionX").charAt(0), rs.getInt("posicionY"));
                switch (rs.getString("nombre")){
                    case "T":
                        pieza = new Torre(rs.getBoolean("fueMovida"), rs.getString("color"), partida, posicion);
                        break;
                    case "C":
                        pieza = new Caballo(rs.getBoolean("fueMovida"), rs.getString("color"), partida, posicion);
                        break;
                    case "A":
                        pieza = new Alfil(rs.getBoolean("fueMovida"), rs.getString("color"), partida, posicion);
                        break;
                    case "R":
                        pieza = new Rey(rs.getBoolean("fueMovida"), rs.getString("color"), partida, posicion);
                        break;
                    case "D":
                        pieza = new Reina(rs.getBoolean("fueMovida"), rs.getString("color"), partida, posicion);
                        break;
                    case "P":
                        pieza = new Peon(rs.getBoolean("fueMovida"), rs.getString("color"), partida, posicion);
                        break;
                }
                tablero.put(posicion, pieza);
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
        return tablero;
    }
}
