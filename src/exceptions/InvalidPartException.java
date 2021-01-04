package exceptions;

public class InvalidPartException extends Exception{
    public InvalidPartException(){
        super("Can't change default Part constructor");
    }
}
