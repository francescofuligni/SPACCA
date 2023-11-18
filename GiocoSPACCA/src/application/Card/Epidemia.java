package application.Card;

	import application.Player.Player;

	public class Epidemia extends Card{

		private boolean flag = false;
		
		public Epidemia(int c) {
			super(c);
		}
		
		@Override
		public void effect(Player p) {
			this.flag = true;
			int contatore = 0;
			
			/*do {
				if() //DA FINIRE 
			}
			
		 */
		}
		
		public boolean setFlag(boolean b) {
			this.flag = b;
			
			return this.flag;
		}
		
		public boolean getFlag() {
			return this.flag;
		}
	}

