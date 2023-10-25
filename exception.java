public class exception extends Exception{
    private final int errorCode;

    public exception(String message, int errorCode){
        super(message);
        this.errorCode = errorCode;

    }
    public int getErrorCode(){
        return errorCode;
    }
}
