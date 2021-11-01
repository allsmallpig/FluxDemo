package rxjava;

import groovy.util.logging.Slf4j;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * @author ltz
 * @version V1.0
 * @date 2021/1/22 14:22
 * @Description
 * @email goodmanalibaba@foxmail.com
 */
@Slf4j
public class RxJava {

    Logger log = LoggerFactory.getLogger(RxJava.class);

    public static void main(String[] args) {

        @NonNull Observable<Integer> integerObservable = Single.just(3).toObservable();
        integerObservable.forEach(integer -> System.out.println("integer = " + integer));

        Flux<Object> empty = Flux.empty();
        //产生一个发射器
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> emitter.onNext(555)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull  Disposable d) {
                System.out.println("onSubscribe = " );
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext = "+integer );
            }

            @Override
            public void onError(@NonNull  Throwable e) {
                System.out.println("onError = " );
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete = " );
            }
        });

    }
}
