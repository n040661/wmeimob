package com.wmeimob.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadPool {
	public static ThreadPoolExecutor threadPool = null;
	
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(ThreadPool.class);

	/** 好处在于没有大小限制 */
	public static LinkedBlockingQueue<Runnable> linkQueue = new LinkedBlockingQueue<Runnable>();
	
	/** 有大小限制 */
	public static ArrayBlockingQueue<Runnable> arrayQueue = new ArrayBlockingQueue<Runnable>(20);
	
	public static List<Integer> count = new ArrayList<Integer>();

	static {
		// 最小保留线程数 最大线程数 空闲时间 空闲时间单位 列队实现 
		threadPool = new ThreadPoolExecutor(1000, 1200, 10, TimeUnit.SECONDS, arrayQueue);
	}
	
	public static void addQueue(Runnable rub){
//		log.info("---------------------添加多线程任务！---------------------");
		threadPool.execute(rub);
	}
	
}
