package com.virtualspacex.batch.configuration;

public class Metadata {

	private final String version;
	
	private final String name;
	
	private final String id;
	
	public Metadata(String version, String name, String id) {
		this.version = version;
		this.name = name;
		this.id = id;
	};
	
	public String version() {
		return this.version;
	}
	
	public String name() {
		return this.name;
	}
	
	public String id() {
		return this.id;
	}
}
