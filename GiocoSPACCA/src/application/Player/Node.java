package application.Player;

public class Node {

	private Player current;
	private Player next;
	private Player previus;
	
	public Node(Player pre, Player cur, Player next) {
		this.previus = pre;
		this.current = cur;
		this.next = next;
	}
	
	public Node(Player cur, Player next) { //nel caso di due giocatori
		this.current = cur;
		this.next = next;
	}
	
	public Player getPrevious() {
		return this.previus;
	}
	
	public Player getCurrent() {
		return this.current;
	}
	
	public Player getNext() {
		return this.next;
	}
}
