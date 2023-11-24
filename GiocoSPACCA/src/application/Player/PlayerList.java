package application.Player;

import java.util.ArrayList;

public class PlayerList {

	private class Node {

		private PlayerInGame current;
		private PlayerInGame next;
		private PlayerInGame previous;
		
		public Node(PlayerInGame pre, PlayerInGame cur, PlayerInGame next) {
			this.previous = pre;
			this.current = cur;
			this.next = next;
		}
		
		public Node(PlayerInGame cur, PlayerInGame next) { //nel caso di due giocatori
			this.current = cur;
			this.next = next;
		}
		
		public PlayerInGame getPrevious() {
			return this.previous;
		}
		
		public PlayerInGame getCurrent() {
			return this.current;
		}
		
		public PlayerInGame getNext() {
			return this.next;
		}
		
		public void setPrevius(PlayerInGame p) {
			this.previous = p;
		}
		
		public void setNext(PlayerInGame p) {
			this.next=p;
		}
	}

	private ArrayList<Node> list;
	
	public PlayerList(PlayerInGame p1, PlayerInGame p2) {
		Node n1 = new Node(p1,p2);
		Node n2 = new Node(p2,p1);
		this.list = new ArrayList<Node>(2);
		this.list.add(n1);
		this.list.add(n2);

		
	}
	
	public PlayerList(PlayerInGame p1, PlayerInGame p2, PlayerInGame p3) {
		Node n1 = new Node(p3,p1,p2);
		Node n2 = new Node(p1,p2,p3);
		Node n3 = new Node(p2,p3,p1);
		this.list = new ArrayList<Node>(3);
		this.list.add(n1);
		this.list.add(n2);
		this.list.add(n3);
	}
	
	public PlayerList(PlayerInGame p1, PlayerInGame p2, PlayerInGame p3,PlayerInGame p4) {
		Node n1 = new Node(p4,p1,p2);
		Node n2 = new Node(p1,p2,p3);
		Node n3 = new Node(p2,p3,p4);
		Node n4 = new Node(p3,p4,p1);
		this.list = new ArrayList<Node>(4);
		this.list.add(n1);
		this.list.add(n2);
		this.list.add(n3);
		this.list.add(n4);
	}
	
	public ArrayList<Node> getList() {
		return this.list;
	}

	
	public Node getIndex(int i) {
		return this.list.get(i);
	}
	
	
	public PlayerList removePlayer(PlayerInGame p) {
	
		PlayerList players;
		
		for(int i=0; i<this.list.size(); i++) {
			if(this.list.get(i).getCurrent()==p) {
				this.list.remove(i);
				break;
			}
		}
		
		if(this.list.size() == 2)
	    	players = new PlayerList(this.list.get(0).getCurrent(),this.list.get(1).getCurrent());
		else //perche questo metodo restituisce per forza di cose o un PL da 2 giocatori o da 3, non sono possibili gli altri casi
	    	players = new PlayerList(this.list.get(0).getCurrent(),this.list.get(1).getCurrent(),this.list.get(2).getCurrent());
	    
		return players;

	
	}
	
	//VANNO AGGIUNTE FUNZINALITA' DINAMICHE PER RENDERLA FRUIBILE IN GAME
	
	
}
