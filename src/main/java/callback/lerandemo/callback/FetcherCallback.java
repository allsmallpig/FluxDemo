package callback.lerandemo.callback;

/**
 * @author litiezhu
 * @packageName netty.lerandemo
 * @date 2020/1/12 11:40
 * @Description
 */
public interface FetcherCallback {

    void onData(Data data) throws Exception;

    void onError(Throwable cause);
}
