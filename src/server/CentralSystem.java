package server;
import java.util.List;
import java.util.ArrayList;
import users.Subscriber;

public class CentralSystem implements IContext {
	//==================
	//Instance Variables
	
	private int port = 6666;
	private int bport = 6667;
	public ArrayList<TCPServer> serveurs = new ArrayList<TCPServer>();
	List<Subscriber> subscriberList=new ArrayList<Subscriber>();
	
	//==================
	//Constructors
		
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
