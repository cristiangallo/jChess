package entidades;

import java.util.ArrayList;

/**
 * Created by cgallo on 31/07/15.
 */
public class Jugador {
    private int dni;
    private String nombre;
    private String apellido;
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

    public ArrayList<Partida> getPartidasPendientesJugadorBlanco(Jugador jugadorNegro){
        ArrayList<Partida> partidasPendientes = null;

        for (Partida partida: partidasPendientes){
            // no hace falta preguntar si el jugador en cuestion es blanco porque el contricante es el negro
            if (partida.getJugadorNegro().getDni() == jugadorNegro.getDni()){
                partidasPendientes.add(partida);
            }
        }

        return partidasPendientes;
    }


    /*
    public final int PEONES = 8;
    public final int ALFILES = 2;
    public final int TORRES = 2;
    public boolean blanco;

    private List<Pieza> piezas = new ArrayList<Pieza>();



    public List<Pieza> getPiezas() {
        return piezas;
    }
*/
/*
    public void initializePieces(){
        if(this.blanco == true){
            for(int i=0; i<PEONES; i++){ // draw pawns
                piezas.add(new Peon(true,i,2));
            }
            piezas.add(new Torre(true, 0, 0));
            piezas.add(new Torre(true, 7, 0));
            piezas.add(new Alfil(true, 2, 0));
            piezas.add(new Alfil(true, 5, 0));
            piezas.add(new Caballo(true, 1, 0));
            piezas.add(new Caballo(true, 6, 0));
            piezas.add(new Reina(true, 3, 0));
            piezas.add(new Rey(true, 4, 0));
        }
        else{
            for(int i=0; i<PEONES; i++){ // draw pawns
                piezas.add(new Peon(true,i,6));
            }
            piezas.add(new Torre(true, 0, 7));
            piezas.add(new Torre(true, 7, 7));
            piezas.add(new Alfil(true, 2, 7));
            piezas.add(new Alfil(true, 5, 7));
            piezas.add(new Caballo(true, 1, 7));
            piezas.add(new Caballo(true, 6, 7));
            piezas.add(new Reina(true, 3, 7));
            piezas.add(new Rey(true, 4, 7));
        }

    }
*/
}
