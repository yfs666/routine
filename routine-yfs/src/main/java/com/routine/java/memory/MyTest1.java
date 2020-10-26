package com.routine.java.memory;

/**
 * 虚拟机栈 ：是线程私有的一部分内存空间，里面的数据是栈帧 stack frame
 * 程序计数器：program counter，记录的是线程执行的行号，也是线程私有的内存信息
 * 本地方法栈：native method stack，主要用于执行本地方法
 * 堆(Heap)：JVM管理的最大一块内存空间，与之相关比较多的就是垃圾收集器，使用分代收集算法，
 * 所以堆空间也基于这一点进行了相应的划分，新生代、老年代。Eden空间 From Survivor空间，To Survivor 空间
 * 方法区（Method Area）：存储元信息，永久代（Permanent Generation），从jdk1.8开始，已经彻底废弃了永久代，使用元空间（Meta space）
 * 运行时常量池：方法区的一部分内容。
 * 直接内存：Direct Memory，与java NIO密切相关，JVM是通过DirectByteBuffer来操作直接内存
 */
public class MyTest1 {
}
