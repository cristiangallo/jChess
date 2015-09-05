package appExceptions;

/**
 * Created by cgallo on 04/09/15.
 */
public class appException extends Exception{

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

    public appException(String message, Throwable cause){

        this.message=message;
        this.cause=cause;

    }

    public appException(String message){

        this.message=message;

    }

}
