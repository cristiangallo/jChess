package entidades;

/**
 * Created by cgallo on 31/07/15.
 */
public class Posicion {
    private char x;
    private int y;

    public char getX() {
        return x;
    }

    public void setX(char x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private Posicion(){}

    public Posicion(char x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        //return Character.hashCode(getX())*Integer.hashCode(getY());
        return Character.getNumericValue(getX() * getY());
    }

    @Override
    public boolean equals (Object posicion){
        return (posicion instanceof Posicion
                && this.getX()==((Posicion)posicion).getX()
                && this.getY()==((Posicion)posicion).getY());
    }

}