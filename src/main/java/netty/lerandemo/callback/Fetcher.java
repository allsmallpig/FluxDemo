package netty.lerandemo.callback;

/**
 * @author
 * @packageName netty.lerandemo
 * @date 2020/1/12 11:32
 * @Description
 * @Versin 1.0
 */
public interface Fetcher {

    void fetchData(FetcherCallback callback);
}
