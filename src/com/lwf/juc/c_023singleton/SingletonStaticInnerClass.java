/**
 * 线程安全的单例模式：
 * 
 * 阅读文章：http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * 
 * 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 * 因为内部静态类是要在有引用了以后才会装载到内存的。所以在你第一次调用getInstance()之前，
 * SingletonHolder是没有被装载进来的，只有在你第一次调用了getInstance()之后，
 * 里面涉及到了return SingletonHolder.instance; 产生了对SingletonHolder的引用，
 * 内部静态类的实例才会真正装载。这也就是懒加载的意思。
 * https://blog.csdn.net/jingzi123456789/article/details/80481493
 *
 *
 */
package com.lwf.juc.c_023singleton;

import java.util.Arrays;

public class SingletonStaticInnerClass {
	
	private SingletonStaticInnerClass() {
		System.out.println("single");
	}
	
	private static class Inner {
		private static SingletonStaticInnerClass s = new SingletonStaticInnerClass();
	}
	
	public static SingletonStaticInnerClass getSingle() {
		return Inner.s;
	}
	
	public static void main(String[] args) {
		Thread[] ths = new Thread[200];
		for(int i=0; i<ths.length; i++) {
			ths[i] = new Thread(()->{
				System.out.println(SingletonStaticInnerClass.getSingle());
			});
		}
		
		Arrays.asList(ths).forEach(o->o.start());
	}
	
}

/**
 * 通过静态内部类的方式实现单例模式是线程安全的，同时静态内部类不会在Singleton类加载时就加载，而是在调用getInstance()方法时才进行加载，达到了懒加载的效果。
 *
 * 似乎静态内部类看起来已经是最完美的方法了，其实不是，可能还存在反射攻击或者反序列化攻击。且看如下代码：
 *
 * public static void main(String[] args) throws Exception {
 *     Singleton singleton = Singleton.getInstance();
 *     Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
 *     constructor.setAccessible(true);
 *     Singleton newSingleton = constructor.newInstance();
 *     System.out.println(singleton == newSingleton);//false
 * }
 *
 * 通过结果看，这两个实例不是同一个，这就违背了单例模式的原则了。
 *
 * 除了反射攻击之外，还可能存在反序列化攻击的情况。如下：
 *
 * 引入依赖：
 *
 * <dependency>
 *     <groupId>org.apache.commons</groupId>
 *     <artifactId>commons-lang3</artifactId>
 *     <version>3.8.1</version>
 * </dependency>
 * 这个依赖提供了序列化和反序列化工具类。
 *
 * Singleton类实现java.io.Serializable接口。
 *
 * 如下：
 *
 * public class Singleton implements Serializable {
 *
 *     private static class SingletonHolder {
 *         private static Singleton instance = new Singleton();
 *     }
 *
 *     private Singleton() {
 *
 *     }
 *
 *     public static Singleton getInstance() {
 *         return SingletonHolder.instance;
 *     }
 *
 *     public static void main(String[] args) {
 *         Singleton instance = Singleton.getInstance();
 *         byte[] serialize = SerializationUtils.serialize(instance);
 *         Singleton newInstance = SerializationUtils.deserialize(serialize);
 *         System.out.println(instance == newInstance);//false
 *     }
 *
 * }
 *https://www.cnblogs.com/happy4java/p/11206105.html
 */
