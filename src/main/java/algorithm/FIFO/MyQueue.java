package algorithm.FIFO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @packageName algorithm.FIFO
 * @date 2020/1/17 13:52
 * @Description 为了实现队列，我们可以使用动态数组和指向队列头部的索引。
 * <a href="https://leetcode-cn.com/explore/learn/card/queue-stack/216/queue-first-in-first-out-data-structure/863/"></a>
 * @Versin 1.0
 */
// "static void main" must be defined in a public class.

public class MyQueue {
    // store elements
    private List<Integer> data;
    // a pointer to indicate the start position
    private int p_start;

    public MyQueue() {
        data = new ArrayList<Integer>();
        p_start = 0;
    }

    /**
     * Insert an element into the queue. Return true if the operation is successful.
     */
    public boolean enQueue(int x) {
        data.add(x);
        return true;
    }

    ;

    /**
     * Delete an element from the queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        p_start++;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        return data.get(p_start);
    }

    /**
     * Checks whether the queue is empty or not.
     */
    public boolean isEmpty() {
        return p_start >= data.size();
    }
};

class Main {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enQueue(5);
        q.enQueue(3);
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
    }
}
