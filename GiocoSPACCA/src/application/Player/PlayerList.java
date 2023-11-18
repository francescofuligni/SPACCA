package application.Player;

import java.util.ArrayList;

public class PlayerList {

	private class Node {

		private Player current;
		private Player next;
		private Player previous;
		
		public Node(Player pre, Player cur, Player next) {
			this.previous = pre;
			this.current = cur;
			this.next = next;
		}
		
		public Node(Player cur, Player next) { //nel caso di due giocatori
			this.current = cur;
			this.next = next;
		}
		
		public Player getPrevious() {
			return this.previous;
		}
		
		public Player getCurrent() {
			return this.current;
		}
		
		public Player getNext() {
			return this.next;
		}
		
		public void setPrevius(Player p) {
			this.previous = p;
		}
		
		public void setNext(Player p) {
			this.next=p;
		}
	}

	private ArrayList<Node> list;
	
	public PlayerList(Player p1, Player p2) {
		Node n1 = new Node(p1,p2);
		Node n2 = new Node(p2,p1);
		this.list = new ArrayList<Node>(2);
		this.list.add(n1);
		this.list.add(n2);

		
	}
	
	public PlayerList(Player p1, Player p2, Player p3) {
		Node n1 = new Node(p3,p1,p2);
		Node n2 = new Node(p1,p2,p3);
		Node n3 = new Node(p2,p3,p1);
		this.list = new ArrayList<Node>(3);
		this.list.add(n1);
		this.list.add(n2);
		this.list.add(n3);
	}
	
	public PlayerList(Player p1, Player p2, Player p3,Player p4) {
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
	
	
	public PlayerList removePlayer(Player p) {
	
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
	
	
}
