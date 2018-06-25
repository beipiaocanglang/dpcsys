package dpcsys.consumption.frame.exception;

/**
 * @author : lijianjun
 * @version V1.0
 * @Description: Exception : Illegal Url
 * @Company:Justin
 * @date: 2015年11月16日 上午10:23:49
 */
@SuppressWarnings("serial")
public class IllegalUrlException extends RuntimeException {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IllegalUrlException(String message) {
        super(message);
        this.message = message;
    }

    /**
     *
     */
    public IllegalUrlException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public IllegalUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public IllegalUrlException(Throwable cause) {
        super(cause);
    }

}
