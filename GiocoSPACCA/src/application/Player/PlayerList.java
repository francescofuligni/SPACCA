package application.Player;

public class PlayerList {

	private class Node {		// classe privata

		private Node previous;
		private PlayerInGame player;
		private Node next;
		
		public Node(Node previous, PlayerInGame player, Node next) {		// nel caso di tre giocatori
			this.previous = previous;
			this.player = player;
			this.next = next;
		}

		public Node getPrevious() {
			return previous;
		}
		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		public PlayerInGame getPlayer() {
			return player;
		}
		// il setPlayer non serve
		
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	private Node head;
	
	public PlayerList(PlayerInGame player) {
		this.head = new Node(null, player, null);
	}
	
	public void add(PlayerInGame player) {			// aggiunge il giocatore in fondo alla lista
		
		Node current = head;
		while(current.getNext()!=head) {
			current = current.getNext();
		}
		
		Node newNode = new Node(current, player, head);
		current.setNext(newNode);
		head.setPrevious(newNode);
	}
	
	public void remove(PlayerInGame player) {		// rimuove un giocatore specifico
		
		Node current = head;
		while(!current.getPlayer().equals(player)) {		// CICLA ALL'INFINITO SE player NON E' NELLA LISTA
			current = current.getNext();
		}
		current.getPrevious().setNext(current.getNext());
		current.getNext().setPrevious(current.getPrevious());	
	}
}
