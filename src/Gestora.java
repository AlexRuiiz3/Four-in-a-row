package actividad3;

import java.util.Scanner;

public class Gestora {

	public static void mostrarTablero(Tablero tablero) {
		for(Casilla[] casillas : tablero.getTablero()) {
			System.out.print("|");
			for(Casilla casilla : casillas) {
				System.out.print(" "+casilla+" |");
			}
			System.out.println("\n");
		}	
	}
	
	//fila y columna hacen referencia al lugar donde se coloco la ultima pieza, para partir desde ahi
	public static boolean comprobarGanador(Tablero tablero,int fila,int columna) {
		boolean ganador = false;
		char pieza = tablero.getTablero()[fila][columna].getPieza();
		int piezasConsecutivas = 1,auxFila = fila,auxColumna = columna;
		
		//Comprobar diagonal arriba izquierda
		while(--auxFila >= 0 && --auxColumna >= 0 && tablero.getTablero()[auxFila][auxColumna].getPieza() == pieza) {
			piezasConsecutivas++;
		}
		if(piezasConsecutivas < 4) { //Si no se han encontrado 4 piezas iguales se pasa a comprobar otro lado
			piezasConsecutivas = 1; auxFila = fila; auxColumna = columna;
			//Comprobar diagonal abajo derecha
			while(++auxFila < tablero.getTablero().length && ++auxColumna < tablero.getTablero()[0].length && tablero.getTablero()[auxFila][auxColumna].getPieza() == pieza) {
				piezasConsecutivas++;
			}
			if(piezasConsecutivas < 4) {
				piezasConsecutivas = 1; auxFila = fila; auxColumna = columna;
				//Comprobar diagonal arriba derecha
				while(--auxFila >= 0 && ++auxColumna < tablero.getTablero()[0].length && tablero.getTablero()[auxFila][auxColumna].getPieza() == pieza) {
					piezasConsecutivas++;
				}
				if(piezasConsecutivas < 4) {
					piezasConsecutivas = 1; auxFila = fila; auxColumna = columna;
					//Comprobar diagonal abajo izquierda
					while(++auxFila < tablero.getTablero().length && --auxColumna >= 0 && tablero.getTablero()[auxFila][auxColumna].getPieza() == pieza) {
						piezasConsecutivas++;
					}
					if(piezasConsecutivas < 4) {
						piezasConsecutivas = 1; auxFila = fila; auxColumna = columna;
						//Comprobar misma columna arriba
						while(--auxFila >= 0 && tablero.getTablero()[auxFila][auxColumna].getPieza() == pieza) {
							piezasConsecutivas++;
						}
						if(piezasConsecutivas < 4) {
							piezasConsecutivas = 1; auxFila = fila; auxColumna = columna;
							//Comprobar misma columna abajo
							while(++auxFila < tablero.getTablero().length && tablero.getTablero()[auxFila][auxColumna].getPieza() == pieza) {
								piezasConsecutivas++;
							}
							if(piezasConsecutivas < 4) {
								piezasConsecutivas = 1; auxFila = fila; auxColumna = columna;
								//Comprobar misma fila izquierda
								while(--auxColumna >= 0 && tablero.getTablero()[auxFila][auxColumna].getPieza() == pieza) {
									piezasConsecutivas++;
								}
								if(piezasConsecutivas < 4) {
									piezasConsecutivas = 1; auxFila = fila; auxColumna = columna;
									//Comprobar misma fila derecha
									while(++auxColumna < tablero.getTablero()[0].length && tablero.getTablero()[auxFila][auxColumna].getPieza() == pieza) {
										piezasConsecutivas++;
									}
								}
							}
						}
					}
				}
			}
		}

		if(piezasConsecutivas == 4) {
			ganador = true;
		}

		return ganador;
	}
	
	public static boolean iteracionColocarPiezaPNJ(Tablero tablero, char pieza, int piezasColocadas) {
		boolean ganar = false;
		int filaAleatoria = 0, columnaAleatoria = 0;
		//Generar posicion aleatoria para la pieza de la maquina
		do {
			filaAleatoria = (int) (Math.random() * tablero.getTablero().length);
			columnaAleatoria = (int) (Math.random() * tablero.getTablero()[0].length);
			
		}while( !(tablero.colorcarPieza(pieza, filaAleatoria, columnaAleatoria)) );
		
		if(piezasColocadas >= 4) { //Si se ha colocado 4 o mas piezas es que ya se puede dar la posibilidad de ganar, entonces se llama al metodo que lo comprueba
			ganar = Gestora.comprobarGanador(tablero, filaAleatoria, columnaAleatoria);
		}
		
		return ganar;
	}
	
	public static boolean iteracionColocarPiezaJugador(Scanner teclado,Tablero tablero, char pieza, int piezasColocadas) {
		Validacion validaciones = new Validacion();
		int fila = 0, columna = 0;
		boolean piezaColocada = false, ganar = false;
		
		//Se leera y validara fila y columna y si en esa posicion se puede colocar la pieza 
		do { 
			System.out.println("Ingrese una fila");
			fila = validaciones.leerValidarNumero(teclado, 1, tablero.getTablero().length);
			System.out.println("Ingrese una columna");
			columna = validaciones.leerValidarNumero(teclado, 1, tablero.getTablero()[0].length);
			piezaColocada = tablero.colorcarPieza(pieza, fila-1, columna-1);
			
			if(!piezaColocada) {
				System.out.println("No fue posible colorcar una pieza, ya habia una en ese lugar");
			}
		}while(!piezaColocada);

		if(piezasColocadas >= 4) { //Si se ha colocado 4 o mas piezas es que ya se puede dar la posibilidad de ganar, entonces se llama al metodo que lo comprueba
			ganar = Gestora.comprobarGanador(tablero, fila - 1, columna - 1);
		}
		
		return ganar;
	}
}
