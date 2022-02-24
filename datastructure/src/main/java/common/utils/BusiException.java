package common.utils;

/**
 * @author zhaipz
 * @ClassName: BusiException
 * @Description: 统一异常处理类
 * @date 2022/2/24 10:01
 */
public class BusiException extends RuntimeException {

    protected String errCode;
    protected String channel;
    protected String errMsg;

    public BusiException(String channel,String errCode,String message) {
        super(" dir: " + channel + " errCode: " + errCode + " errMsg: " + message);
        this.channel = channel;
        this.errCode = errCode;
        this.errMsg = message;
    }
}
