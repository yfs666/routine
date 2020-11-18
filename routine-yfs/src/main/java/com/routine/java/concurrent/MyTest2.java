package com.routine.java.concurrent;

/**
 * 当我们使用synchronized关键字修饰代码块时，字节码层面上是通过monitorenter与monitorexit指令来实现的锁的获取释放动作
 * 当线程进入到monitorenter指令后，线程将会持有monitor对象，退出monitorenter后，monitor将释放monitor对象
 * <p>
 * 对于synchronized关键字修饰方法来说，并没有出现monitorenter和monitorexit指令，而是出现了一个ACC_SYNCHRONIZED标志
 * jvm使用了ACC_SYNCHRONIZED访问标志来区分一个方法是否为同步方法；当方法被调用的时候，调用指令会检查该方法是否拥有ACC_SYNCHRONIZED标志，
 * 如果有，那么执行线程将会先持有方法所在对象的monitor对象，然后再去执行方法体；在该方法执行期间，其他任何线程均无法在获得这个monitor对象，当线程执行完
 * 该方法后，他会释放掉这个monitor对象
 * <p>
 * jvm中的不同是基于进入与退出显示器对象（管程）（monitor）来实现的，每个对象实例都会有一个monitor对象，monitor对象会和java对象一同创建并销毁
 * Monitor对象是由c++实现的。
 * <p>
 * 当多个线程同时访问一段同步代码时，这些线程会被放到一个EntryList集合中，处于阻塞状态的 线程都会被放到该列表当中，接下来，当线程获取到对象的monitor时，
 * monitor是依赖于底层操作系统的mutex lock来实现互斥的，线程获取mutex成功，则会持有该mutex，这时其他线程就无法再获取到该mutex
 * <p>
 * 如果线程调用了wait方法，那么该线程就会释放掉所持有的mutex，并且该线程就会进入到wait set中，等待下一次被其他线程调用notify/notifyAll唤醒。如果当前线程顺利执行
 * 完毕方法，那么他也会释放掉所持有的mutex
 * <p>
 * 同步锁在这种实现方式当中，因为monitor是依赖于底层的操作系统实现，这样就存在用户态域内核态之间的切换，所以会增加性能开销。
 * <p>
 * 通过对象互斥锁的概念来保证共享数据操作的完整性，每个对象都对应于一个可称为【互斥锁】的标记，这个标记用于保证在任何时刻只能有一个线程访问该对象。
 * <p>
 * EntryList域Wait Set中的线程均处于阻塞状态，阻塞操作是由操作系统来完成的，在linux系统下通过pthread_nutex_lock函数实现的。
 * 线程被阻塞后便会进入到内核调度状态，这会导致系统在用户态与内核态之间来回切换，严重性能锁的性能。
 * <p>
 * 解决上述问题的办法便是自旋，其原理是：当发生对monitor的征用时，弱Onwner能够在很短时间内释放锁，则那些正在征用的线程就可以稍微等待一下，在
 * owner线程释放掉的线程可能会like获取到锁，从而避免系统阻塞。不过，当owner运行时间超过了临界值后，征用线程自旋一段时间后依然无法获取到锁，这时
 * 征用线程则会停止自旋而进入到阻塞状态。
 * 以上总体思想是：先自旋，不成功再进行阻塞，尽量降低则色的可能性，这对那些执行时间很短的代码块来说有极大的性能提升。显然自旋在多处理器上才有意义。
 * <p>
 * 互斥锁的属性：
 * 1、PTHREAD_MUTEX_TIMED_NP:这是属性的默认值，也就是普通锁。当一个线程加锁后，其余请求这把锁的线程将会形成一个等待队列，并且在解锁后按照
 * 优先级获取到锁。这种策略可以确保资源分配的公平性。
 * 2、PTHREAD_MUTEX_RECURSIVE_NP:嵌套锁，允许一个线程对通一把锁成功获取多次，并通过unlock解锁。如果是不通线程请求，则在加锁的线程解锁时重新进行竞争。
 * 3、PTHREAD_MUTEX_ERRORCHECK_NP:检错锁，如果一个线程请求同一个锁，则返回EDEADLK，否则与PTHREAD_MUTEX_TIMED_NP类型动作相同，这样就保证
 * 了当不允许多次加锁时不会出现最简单情况下的死锁。
 * 4、PTHREAD_MUTEX_ADAOTIVE_NP:适应锁，动作最简单的锁类型，仅仅等待解锁后重新竞争。
 */
public class MyTest2 {
}
