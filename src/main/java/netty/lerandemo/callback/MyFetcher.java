package netty.lerandemo.callback;

/**
 * @author
 * @packageName netty.lerandemo
 * @date 2020/1/12 11:38
 * @Description
 * @Versin 1.0
 */
public class MyFetcher implements Fetcher {

    final Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    @Override
    public void fetchData(FetcherCallback callback) {
        try {
            callback.onData(data);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

}