package dpcsys.consumption.frame.exception;

/**
 * @author : lijianjun
 * @version V1.0
 * @Description: Exception : session out time
 * @Company:Justin
 * @date: 2015年11月16日 上午10:24:06
 */
@SuppressWarnings("serial")
public class SessionTimeException extends RuntimeException {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SessionTimeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     *
     */
    public SessionTimeException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public SessionTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public SessionTimeException(Throwable cause) {
        super(cause);
    }

}
