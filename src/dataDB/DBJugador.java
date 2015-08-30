package dataDB;

import conexionDB.ConexionDB;
import entidades.Jugador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cgallo on 04/08/15.
 */

public class DBJugador {

    public static Jugador getByDni(int dni) {

        Jugador jugador = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{

            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select dni, nombre, apellido from jugadores where dni = ?"
            );

            stmt.setInt(1, dni);
            rs = stmt.executeQuery();

            if(rs != null && rs.next()){
                jugador = new Jugador(rs.getInt("dni"), rs.getString("nombre"), rs.getString("apellido"));
            }

        } catch(SQLException e){

            e.printStackTrace();

        } finally{

            try{

                if(rs != null) rs.close();
                if(stmt != null) stmt.cancel();

            }catch(SQLException e){

                e.printStackTrace();

            }

            ConexionDB.getInstancia().releaseConexion();

        }

        return jugador;

    }
}
