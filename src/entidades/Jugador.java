package entidades;

import java.util.ArrayList;

/**
 * Created by cgallo on 31/07/15.
 */
public class Jugador {
    private int dni;
    private String nombre;
    private String apellido;

    public ArrayList<Partida> getPartidasPendientes() {
        return partidasPendientes;
    }

    public void setPartidasPendientes(ArrayList<Partida> partidasPendientes) {
        this.partidasPendientes = partidasPendientes;
    }

    private ArrayList<Partida> partidasPendientes;

    public Jugador(){}

    public Jugador(int dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getDni() {

        return dni;
    }
    public void setDni(int dni) {

        this.dni = dni;
    }
    public String getNombre() {

        return nombre;
    }
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }
    public String getApellido() {

        return apellido;
    }
    public void setApellido(String apellido) {

        this.apellido = apellido;
    }

    public ArrayList<Partida> getPartidasPendientes(Jugador jugadorNegro){
        ArrayList<Partida> partidasPendientes = null;
        for (Partida partida: this.partidasPendientes){
            // no hace falta preguntar si el jugador en cuestion es blanco porque el contricante es el negro
            if (partida.getJugadorNegro().getDni() == jugadorNegro.getDni()){
                partidasPendientes.add(partida);
            }
        }
        return partidasPendientes;
    }

    public void addPartida(Partida partida){

        partidasPendientes.add(partida);
    }
}
