package application.Player;

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

	private Node[] list;
	
	public PlayerList(Player p1, Player p2) {
		Node n1 = new Node(p1,p2);
		Node n2 = new Node(p2,p1);
		this.list = new Node[]{n1,n2};
	}
	
	public PlayerList(Player p1, Player p2, Player p3) {
		Node n1 = new Node(p3,p1,p2);
		Node n2 = new Node(p1,p2,p3);
		Node n3 = new Node(p2,p3,p1);
		this.list = new Node[]{n1,n2,n3};
	}
	
	public PlayerList(Player p1, Player p2, Player p3,Player p4) {
		Node n1 = new Node(p4,p1,p2);
		Node n2 = new Node(p1,p2,p3);
		Node n3 = new Node(p2,p3,p4);
		Node n4 = new Node(p3,p4,p1);
		this.list = new Node[]{n1,n2,n3,n4};
	}
	
	public Node[] getList() {
		return this.list;
	}

	
	public Node getIndex(int i) {
		return this.list[i];
	}
	
	
}
