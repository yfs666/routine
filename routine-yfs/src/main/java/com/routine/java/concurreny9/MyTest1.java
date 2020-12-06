package com.routine.java.concurreny9;

import java.util.concurrent.Executors;

/**
 * int corePoolSize 线程池当中所一直维护的线程数量，如果线程池处于任务调度空闲期间，那么该线程并不会被回收掉
 * int maximumPoolSize 线程池中所维护的线程数的最大数
 * long keepAliveTime 超过了核心线程数后的线程经过多少时间如果一直有处于空闲状态，那么超过这部分的线程将会被回收掉
 * TimeUnit unit  时间单位
 * BlockingQueue<Runnable> workQueue 向线程池所提交的任务位于的阻塞队列，它的实现有多种方式
 * ThreadFactory threadFactory 线程工厂，用于创建新的线程并被线程池所管理，默认线程工厂所创建的线程都是用户线程，且优先级是正常优先级
 * RejectedExecutionHandler handler 表示当线程池中的线程都在忙于执行任务且阻塞队列已经满了的情况下，新来的任务该如何被对待和处理
 * 有一下四种策略
 * AbortPolicy：直接抛出一个运行期异常
 * DiscardPolicy：默默地丢弃掉提交的任务，什么都不做且不抛异常
 * DiscardOldestPolicy：丢弃掉阻塞队列中存放时间最久的任务（队列头元素），并且为当前所提交的任务留出一个队列中的空闲空间，以便将其放进到队列中
 * CallerRunsPolicy：直接由提交任务的线程来运行这个提交的任务
 *
 * 在线程池中，最好将偏向锁的标记关闭
 *
 *
 *
 * 对于线程池来说，其提供了execute与submit方法两种方式向线程提交任务
 * 总体来说，submit方法可以替代execute方法，因为它既可以接收Callable任务，也可以接收Runnable任务
 *
 * 关于线程池的总体策略
 * 1、如果线程池中正在执行的线程数小于核心线程数，那么线程池就会优先选择创建新的线程
 * 2、如果线程池中正在执行的线程数大于等于核心线程数，那么线程池就会优先选择对提交的任务进行阻塞排队，而非创建新的线程
 * 3、如果提交的任务无法加入到阻塞队列中，那么线程池就会创建新的线程，从阻塞队列中拉取新的任务执行；如果创建的线程数超过了最大线程数，那么就会执行拒绝策略
 *
 * 关于线程池任务的提交总结
 * 1、两种提交方式：submit、execute
 * 2、submit有三种方式，无论哪种方式，最终都是将传递进来的任务转换为Callable对象进行处理
 * 3、当Callable对象构造完毕后，最终都会调用Executors接口中声明的execute方法进行统一处理
 *
 *
 * 对于线程池来说，有两个状态需要维护
 * 1、线程池本身的状态，ctl的高3位表示
 * 2、线程池中所运行着的线程的数量，ctl的低29位表示
 *
 * 线程池的5种状态
 * 1、RUNNING：线程池可以接收新的任务提交，并且还可以正常处理阻塞队列中的任务
 * 2、SHUTDOWN：不再接收新的任务提交，不过线程池可以处理阻塞队列中的任务
 * 3、STOP：不再接收新的任务，同时还会丢弃阻塞队列中的既有任务；此外，它还会中断正在处理中的任务
 * 4、TIDYING：所有的任务都执行完毕后（同时也涵盖了阻塞队列中的任务），当前线程池中的活动的线程数量降为0，将会调用terminated方法
 * 5、TERMINATED：线程的终止状态，当terminated方法执行完毕后，线程池将会处于该状态之下
 *
 * RUNNING -> SHUTDOWN 当调用shutdown方法后，finalize方法也会调用shutdown方法，此方法是隐式调用的
 * RUNNING、SHUTDOWN -> STOP ：当调用了线程池的shutdownNow方法时
 * SHUTDOWN -> TIDYING：在线程池与阻塞队列均变成空时
 * STOP -> TIDYING：在线程池变为空时
 * TIDYING -> TERMINATED：在terminated方法被执行完毕时
 */
public class MyTest1 {
    public static void main(String[] args) {
//        Thread
//        Executors.newFixedThreadPool()
    }
}
