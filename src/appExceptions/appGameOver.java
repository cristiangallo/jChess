package appExceptions;

/**
 * Created by cgallo on 24/09/15.
 */
public class appGameOver extends Exception{
    private static final long serialVersionUID = 1L;
    private String message;

    @Override
    public Throwable getCause() {
        return cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private Throwable cause;

    public appGameOver(String message, Throwable cause){
        this.message=message;
        this.cause=cause;
    }

    public appGameOver(String message){
        this.message=message;
    }
}
