package com.humanMind.server;

import java.util.HashMap;
import java.util.Map;

public class ServerInUser {

	private Map<String, ServerLobbyThreadIOController> inUser = new HashMap<>();
	
	private ServerInUser() {
		
	}
	
	public static ServerInUser getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final ServerInUser INSTANCE = new ServerInUser();
	}
	
	public Map<String, ServerLobbyThreadIOController> getInUser() {
		return this.inUser;
	}
	
	public void setInUser(String id, ServerLobbyThreadIOController s) {
		inUser.put(id, s);
	}
	
	public void setOutUser(String id) {
		inUser.remove(id);
	}
	
	public int getSize() { 
		return inUser.size();
	}
	
}
