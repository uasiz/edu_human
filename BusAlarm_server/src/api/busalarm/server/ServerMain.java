package api.busalarm.server;

import api.busalarm.log.StartLog;

public class ServerMain {
	ServerMain(){
		new SocketHub();
		new StartLog();
	}
}
