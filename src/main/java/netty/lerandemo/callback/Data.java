package netty.lerandemo.callback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author
 * @packageName netty.lerandemo
 * @date 2020/1/12 11:40
 * @Description
 * @Versin 1.0
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Data {

    private int n;
    private int m;
}
