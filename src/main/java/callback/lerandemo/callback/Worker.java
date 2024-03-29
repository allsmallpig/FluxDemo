package callback.lerandemo.callback;

/**
 * @author
 * @packageName netty.lerandemo
 * @date 2020/1/12 11:31
 * @Description
 * @Versin 1.0
 */
public class Worker {


    public void doWork() {
        Fetcher fetcher = new MyFetcher(new Data(1, 0));
        fetcher.fetchData(new FetcherCallback() {
            @Override

            public void onError(Throwable cause) {
                System.out.println("An error accour: " + cause.getMessage());
            }

            @Override
            public void onData(Data data) {
                System.out.println("Data received: " + data);
            }
        });

    }


    public static void main(String[] args) {
        Worker w = new Worker();
        w.doWork();

    }

}
