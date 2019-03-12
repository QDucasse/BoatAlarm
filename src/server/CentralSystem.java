package server;
import java.util.List;

import protocols.AdminProtocol;
import protocols.StateProtocol;
import protocols.UserProtocol;
import protocols.TestProtocol;

import java.util.ArrayList;
import users.Subscriber;

public class CentralSystem implements IContext {
	//==================
	//Instance Variables
	
	private int main_port  = 6666;
	private int boat_port  = 6667;
	private int user_port  = 6668;
	private int admin_port = 6669;
	public List<TCPServer> servers = new ArrayList<TCPServer>();
	List<Subscriber> subscriberList=new ArrayList<Subscriber>();
	
	//==================
	//Constructors
	
	public CentralSystem(List<Subscriber> subscriberList) {	
		this.subscriberList=subscriberList;
		servers.add(new TCPServer(this, new TestProtocol() , main_port ));
		servers.add(new TCPServer(this, new UserProtocol() , user_port ));
		servers.add(new TCPServer(this, new AdminProtocol() , admin_port ));
		servers.add(new TCPServer(this, new StateProtocol() , boat_port ));
		for(TCPServer s : servers ) {
				s.start();
		}
	}
	
	//==================
	//Getters & Setters

	public List<Subscriber> getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(List<Subscriber> subscriberList) {
		this.subscriberList = subscriberList;
	}

	//==================
	//Methods

	public void addSubscriber(Subscriber subscriber){
		this.subscriberList.add(subscriber);
	}
	
	public void deleteSubscriber(Subscriber subscriber){
		this.subscriberList.remove(subscriber);
	}
	
}
