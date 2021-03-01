package actividad3;

public class Casilla {

	//Atributos
	private char pieza;
	
	//Constructor por defecto
	public Casilla() {
		pieza = ' ';
	}
	
	//Metodos fundamentales
		//pieza
		public char getPieza() {
			return pieza;
		}
		public void setPieza(char pieza) {
			this.pieza = pieza;
		}
	//Metodos heredados
	 public String toString() {
		 return pieza+"";
	 }
	 
}
