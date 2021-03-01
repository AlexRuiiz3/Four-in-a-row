package actividad3;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		Validacion validaciones = new Validacion();
		
		int opcion = 0, fila = 0, columna = 0, modoJuego = 0;
		int contPiezaJ1 = 0;
		int contPiezaJ2 = 0;
		char piezaJ1 = ' ', piezaJ2 = 'O';
		boolean J1Ganador = false, J2Ganador = false, tableroLleno = false;
		Tablero tablero = null;
		
		System.out.println("Bienvenido al 4 en raya \nLa partida va a comenzar");
		
		modoJuego = validaciones.obtenerMostrarMenuModoJuego(teclado); //Obtener modo de juego(int)
		
		opcion = validaciones.obtenerMostrarMenuSeleccionarPiezas(teclado); //Obtener opcion de como se quieren las piezas
		switch(opcion) {
		
		case 1: //Piezas por defecto
			piezaJ1 = 'X';
			//La pieza de J2(Ya sea maquina o otro jugador) ya tiene un valor asignado(O) arriba en la declaracion de variables 
		break;
		
		case 2: //Piezas personalizadas
			System.out.println("J1 ingrese su pieza");
			piezaJ1 = teclado.next().charAt(0);
			
			if(modoJuego == 1) { //Modo juevo vs la maquina
				if(piezaJ1 == 'O') { //Si el J1 introdujo O que es la pieza por defecto de J2(La maquina en este caso)
					piezaJ2 = 'X';	//Esta toma la siguiente pieza
				} //En el otro caso en el que el usuario ingrese una pieza != de O el valor de J2 seria de O que es 
				  //el asignado en la declaracion de variables
			}else { //Modo Juego vs otro jugador
				System.out.println("J2 ingrese su pieza(Recuerde debe ser distinto al de J1)");
				piezaJ2 = validaciones.leerValidarCaracter(teclado, piezaJ1);
			}
		break;
		}
		
		opcion = validaciones.obtenerMostrarMenuFormaTablero(teclado); //Obtener opcion de en que tipo de tablero se quiere jugar
		switch(opcion) {
		
		case 1: //Tablero por defecto
			tablero = new Tablero();
		break;
		
		case 2: //Tablero personalizado
			System.out.println("Se procedera a leer el numero de filas y columnas que desea que tenga su tablero personalizado"
					+ "\n¡¡RECUERDE!! La suma de ambos debe de ser mayor que 6 sino es asi se generara un tablero por defecto 4x4");
			System.out.println("Ingrese el numero de filas que desee");
			fila = teclado.nextInt();
			System.out.println("Ingrese el numero de columnas que desee");
			columna = validaciones.leerValidarSegundaDimensionTablero(teclado, fila);
	
			tablero = new Tablero(fila,columna);
			
		break;

		}
		Gestora.mostrarTablero(tablero); //Mostrar tablero
		
		switch(modoJuego) { //Segun el modo juego elegido antes
		case 1: //Modo vs la maquina
			
			do {
				System.out.println("J1 es tu turno");
				
				contPiezaJ1++;
				J1Ganador = Gestora.iteracionColocarPiezaJugador(teclado, tablero, piezaJ1, contPiezaJ1);//Se coloca
													//una pieza del Jugador 1 y se obtiene un booleano que indica si 
													//esa pieza le sirvio para ganar
				
				tableroLleno = tablero.comprobarLleno(); //Se comprueba si despues de que J1 ingrese una pieza el tablero se lleno o no
				if(!J1Ganador && !tableroLleno) { //Si el J1 al introducir su pieza no ha ganado y ademas el tablero no se lleno
					System.out.println("Tu oponente esta eligiendo una posicion...");
					contPiezaJ2++;
					J2Ganador = Gestora.iteracionColocarPiezaPNJ(tablero, piezaJ2, contPiezaJ2);//Se coloca
													//una pieza del Jugador 2 y se obtiene un booleano que indica si 
													//esa pieza le sirvio para ganar
					tableroLleno = tablero.comprobarLleno(); //Se vuelve a comprobar si esta lleno por que ahora introdujo una pieza la maquina
				}
				Gestora.mostrarTablero(tablero); //Mostrar Tablero
			}while(!J1Ganador && !J2Ganador && !tableroLleno); //Mientras no haya ganado nadie y siga habiendo espacio en el tablero
		break;
		
		case 2: //Modo vs otro jugador
			do {
				System.out.println("J1 es tu turno");
				contPiezaJ1++;
				J1Ganador = Gestora.iteracionColocarPiezaJugador(teclado, tablero, piezaJ1, contPiezaJ1);
				tableroLleno = tablero.comprobarLleno(); 
				
				if(!J1Ganador && !tableroLleno) {
					System.out.println("J2 es tu turno");
					contPiezaJ2++;
					J2Ganador = Gestora.iteracionColocarPiezaJugador(teclado, tablero, piezaJ2, contPiezaJ2);
					tableroLleno = tablero.comprobarLleno(); 
				}
				Gestora.mostrarTablero(tablero);
			}while(!J1Ganador && !J2Ganador && !tableroLleno); //Mientras no haya ganado nadie y siga habiendo espacio en el tablero
		break;
		}
		
		//Comporbaciones para mostrar quien fue el ganador 
		if(J1Ganador) {
			System.out.println("J1 Es el ganador");
		}else {
			if(J2Ganador) {
				System.out.println("J2 Es el ganador");
			}else {
				System.out.println("¡Empate!");
			}
		}
		teclado.close(); //Se cierra el teclado
	}
}
