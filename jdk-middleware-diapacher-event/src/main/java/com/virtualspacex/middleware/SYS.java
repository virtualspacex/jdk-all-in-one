package com.virtualspacex.middleware;

public enum SYS{
	ON(EventPriority.important),
	OFF(EventPriority.important);

	EventPriority eventPriority;

	SYS(EventPriority priority) {
		eventPriority = priority;
	}

	public EventPriority getEventPriority(){
		return eventPriority;
	}
}
