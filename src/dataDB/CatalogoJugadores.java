package dataDB;

import conexionDB.ConexionDB;
import entidades.Jugador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cgallo on 04/08/15.
 */
public class CatalogoJugadores {

    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    private static CatalogoJugadores instancia;

    public static CatalogoJugadores getInstance() {
        if(instancia == null){
            instancia = new CatalogoJugadores();
        }
        return instancia;
    }
    // hago privado el constructor para que nadie pueda instanciar
    private CatalogoJugadores() {}

    public ArrayList<Jugador> getJugadores() {
        Jugador jugador = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        if (jugadores.isEmpty()) {
            try {

                stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                        "select dni, nombre, apellido from jugadores"
                );

                rs = stmt.executeQuery();

                while (rs != null && rs.next()) {
                    jugador = new Jugador(rs.getInt("dni"), rs.getString("nombre"), rs.getString("apellido"));
                    jugadores.add(jugador);
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
        return jugadores;

    }

    public Jugador getJugadorByDni(int dni) {
        Jugador jugador = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "select dni, nombre, apellido from jugadores where dni = ?"
            );
            stmt.setInt(1, dni);
            rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                jugador = new Jugador(rs.getInt("dni"), rs.getString("nombre"), rs.getString("apellido"));
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

        return jugador;

    }


    public void addJugador(Jugador jugador) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{

            stmt = ConexionDB.getInstancia().getConexion().prepareStatement(
                    "insert into jugadores(dni, nombre, apellido) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS
            );

            stmt.setInt(1, jugador.getDni());
            stmt.setString(2, jugador.getNombre());
            stmt.setString(3, jugador.getApellido());
            stmt.execute();

            rs = stmt.getGeneratedKeys();


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




    }
}
