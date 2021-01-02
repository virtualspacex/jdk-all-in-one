package com.virtualspacex.middleware.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadFactory;

import com.virtualspacex.middleware.handler.EventHandlerInterface;

public class EventCenter {
	static {
		ExecutorService executor = Executors.newCachedThreadPool(new MonitorThreadFactory());		
		executor.execute(new EventDispacherCenter());
		executor.execute(new EventHandlerDiapacher());
		executor.execute(new EventHandlerExecutor());
	}

	//接受事件
	public synchronized static boolean emit(Enum<?> event) {
		return EventDispacherCenter.emitEvent(event);
	}
	
	public synchronized static void register(Enum<?> event, EventHandlerInterface eventHandler) {
		EventHandlerWareHouse.register(event, eventHandler);
	}
}

class EventDispacherCenter implements Runnable{

	private static BlockingQueue<Enum<?>> eventQueue = new LinkedTransferQueue<>();

	public synchronized static boolean emitEvent(Enum<?> event){
		boolean ret = true;

		try{
			eventQueue.put(event);
		} catch (Exception e){
			ret = false;
		}

		return ret;
	}

	@Override
	public void run() {

		while(true){
			try{
				//取得事件处理器（多个）
				ArrayList<EventHandlerInterface> listeners = EventHandlerWareHouse.getHandlerList(eventQueue.take());
				if(null != listeners && 0 != listeners.size()){
					EventHandlerDiapacher.put(listeners);
				}
			} catch (Exception e){
				
			}
		}
	}
}

class EventHandlerDiapacher implements Runnable{
	private static BlockingQueue<EventHandlerInterface> eventWaitHandleQueue = new LinkedTransferQueue<>();

	public synchronized static void put(ArrayList<EventHandlerInterface> listeners) throws InterruptedException {
		//将事件处理器提交到事件处理器执行队列
		for(EventHandlerInterface eventHandler : listeners) {
			eventWaitHandleQueue.put(eventHandler);
		}
	}

	@Override
	public void run() {
		
		while(true){
			try{
				EventHandlerExecutor.executeEventHandler(eventWaitHandleQueue.take());
			} catch (Exception e){
				
			}
		}
	}
}

class EventHandlerExecutor implements Runnable{
	private static BlockingQueue<EventHandlerInterface> eventWaitExecuteQueue = new LinkedTransferQueue<>();

	public synchronized static void executeEventHandler(EventHandlerInterface eventHandler) throws InterruptedException {
		eventWaitExecuteQueue.put(eventHandler);
	}

	@Override
	public void run() {
		
		while(true){
			try{
				eventWaitExecuteQueue.take().recipet();
			} catch (Exception e){

			}
		}
	}
}

class EventHandlerWareHouse{

	private static HashMap<Enum<?>, ArrayList<EventHandlerInterface>> eventListeners = new HashMap<>();

	public synchronized static ArrayList<EventHandlerInterface> getHandlerList(Enum<?> event){
		return eventListeners.get(event);
	}

	public synchronized static void register(Enum<?> event, EventHandlerInterface eventHandler) {
		ArrayList<EventHandlerInterface> listeners = eventListeners.get(event);

		if(null == listeners) {
			listeners = new ArrayList<EventHandlerInterface>();
		}

		listeners.add(eventHandler);
		eventListeners.put(event, listeners);
	}
}

class MonitorThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
	
}
