package actividad3;

public class Tablero {

	//Atributos
	private Casilla[][] tablero;
	
	//Constructor por defecto
	public Tablero () {
		tablero = new Casilla[4][4];
		inicializar();
	}
	
	//Constructor con parametros
	public Tablero(int fila, int columna){
		tablero = new Casilla[fila][columna];
		inicializar();
	}
	
	//Metodos fundamentales
		//tablero 
		public Casilla[][] getTablero(){
			return tablero;
		}
			//(Patron delegacion)
		public char getPiezaCasilla(int fila, int columna) {
			return tablero[fila][columna].getPieza();
		}
		public void setPiezaCasilla(int fila, int columna,char pieza) {
			tablero[fila][columna].setPieza(pieza);
		}
	//Metodos añadidos
	private void inicializar() {		
		for(int i = 0; i <tablero.length; i++) {
			for(int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = new Casilla();
			}
		}
	}
		
	public boolean colorcarPieza(char pieza, int fila, int columna) {
		boolean colocado = false;
		if(getPiezaCasilla(fila,columna) == ' ') {
			setPiezaCasilla(fila,columna,pieza);
			colocado = true;
		}
		return colocado;
	}

	public boolean comprobarLleno() {
		boolean lleno = true;
		for(int i = 0; i <tablero.length && lleno; i++) {
			for(int j = 0; j < tablero[0].length && lleno; j++) {
				if(tablero[i][j].getPieza() == ' ') {
					lleno = false;
				}		
			}
		}
		return lleno;
	}
}
