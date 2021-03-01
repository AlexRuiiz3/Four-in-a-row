package actividad3;
import java.util.Scanner;

public class Validacion {
	
	public int leerValidarNumero(Scanner teclado, int minimo, int maximo) {
		int numero = 0;
		
		do{
			numero = teclado.nextInt();
			
			if (numero < minimo || numero > maximo) {
				System.out.println("ERROR, El valor que se introduzca tiene que estar entre "+minimo+"-"+maximo);
			}
		}while(numero < minimo || numero > maximo);
		
		return numero;
	}
	
	public int leerValidarSegundaDimensionTablero(Scanner teclado, int primeraDimension) {
		int segundaDimension = 0;
		
		do{
			segundaDimension = teclado.nextInt();
			
			if (segundaDimension + primeraDimension < 7) {
				System.out.println("ERROR, Valor invalido ("+primeraDimension+"+"+segundaDimension+") < 7" );
			}
		}while(segundaDimension + primeraDimension < 7);
		
		return segundaDimension;
	}
	
	public char leerValidarCaracter(Scanner teclado, int original) {
		char caracter = ' ';
		
		do{
			caracter = teclado.next().charAt(0);
			
			if (caracter == original) {
				System.out.println("ERROR, Ese caracter no es valido, por favor vuelva a intentarlo" );
			}
		}while(caracter == original);
		
		return caracter;
	}
	
	public int obtenerMostrarMenuFormaTablero(Scanner teclado) {

		System.out.println("Seleccione en que tablero desea empezar a jugar");
		System.out.println("1)Tablero por defecto");
		System.out.println("2)Crear mi propio tablero");
		return leerValidarNumero(teclado, 1, 2);
	}
	
	public int obtenerMostrarMenuSeleccionarPiezas(Scanner teclado) {

		System.out.println("Seleccione como quiere que sean las piezas del juego");
		System.out.println("1)Piezas por defecto");
		System.out.println("2)Piezas personalizadas");
		return leerValidarNumero(teclado, 1, 2);
	}
	
	public int obtenerMostrarMenuModoJuego(Scanner teclado) {

		System.out.println("Ingrese el modo de juego que desee");
		System.out.println("1) VS PNJ");
		System.out.println("2) VS otro jugador");
		return leerValidarNumero(teclado, 1, 2);
	}

}
