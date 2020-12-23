package com.routine.java.concurreny6;

/**
 * CAS
 * 1、synchronized关键字与Lock等锁机制都是悲观锁：无论做什么操作，都需要先上锁，再执行后面的操作，确保了接下来的所有操作都是在该线程执行
 * 2、乐观锁：线程在操作之前预先不做任何处理，直接执行；当在最后执行变量更新的时候，当前线程需要有一种机制来确保变量没有被修改，CAS是乐观锁的一种重要的实现方式
 *
 * CAS本身是由硬件指令来提供支持的，也就是说硬件中是通过一个原子指令来实现比较交换的；因此CAS是可以确保变量操作的原子性的
 *
 *
 * 对于CAS来说，其操作数主要涉及到如下三个
 * 1、需要被操作的内存值V
 * 2、需要进行比较的值A
 * 3、需要进行写入的值B
 *
 * 只有当V==A的时候，CAS才会通过原子操作的手段来将V的值更新为B
 *
 * 关于CAS的限制
 * 1、循环开销问题：并发量大的情况下回导致线程一直自旋，浪费cpu资源
 * 2、只能保证一个变量的原子操作：可以通过AtomicReference来实现多个变量的原子操作
 * 3、ABA问题：加一个版本号变量，版本号顺序不可逆
 */
public class MyTest1 {
    public static void main(String[] args) {

    }
}