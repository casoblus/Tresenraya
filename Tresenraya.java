import java.util.Scanner;
public class Tresenraya 
{
		static int[] fichas = {3,3};
		// JUGADOR
		// SWITCH_PLAYER
		private static int SWITCH_PLAYER( int player )
		{
			if( player == 1 )
			{
				System.out.println( "Juega B." );
				return 2;
			}
			System.out.println( "Juega A." );
			return 1;
		}
		// CHECK_WINS
		private static boolean CHECK_WINS( int player, int[][] tab )
		{
			// Empiezo por la casilla del centro
			if ( tab[1][1] == player ) // La pieza central pertenece al jugador
			{
				if ( tab[0][1] == player && tab[2][1] == player ) 
				{
					System.out.println( "Jugador "+player+" gana." );
					return true;
				} 
				else if ( tab[1][0] == player && tab[1][2] == player )
				{
					System.out.println( "Jugador "+player+" gana." );
					return true;
				}
				else if ( tab[0][0] == player && tab[2][2] == player )
				{
					System.out.println( "Jugador "+player+" gana." );
					return true;
				}
				else if ( tab[0][2] == player && tab[2][0] == player )
				{
					System.out.println( "Jugador "+player+" gana." );
					return true;
				}
				
					
			} else {
				// Solo quedan cuatro posibilidades
				if ( tab[0][1] == player ) 
				{
					if ( tab[0][0] == player && tab[0][2] == player )
					{
							System.out.println( "Jugador "+player+" gana." );
							return true;
					}
				} 
				else if ( tab[1][0] == player )
				{
					if ( tab[0][0] == player && tab[2][0] == player )
					{
							System.out.println( "Jugador "+player+" gana." );
							return true;
					}
				}
				else if ( tab[2][1] == player )
				{
						if ( tab[2][0] == player && tab[2][2] == player )
					{
							System.out.println( "Jugador "+player+" gana." );
							return true;
					}
				}
				else if ( tab[1][2] == player )
				{
					if ( tab[0][2] == player && tab[2][2] == player )
					{
							System.out.println( "Jugador "+player+" gana." );
							return true;
					}
				}
			}
			return false;
		}
		// PUT_FICHA
		private static void PUT_FICHA( int player, int[][] tab )
		{
			boolean isBusy;
			int fila, columna;

			Scanner sc = new Scanner( System.in );
			
			// Si la casilla está ocupada vuelve a pedir coordenadas
			do {
				System.out.println( "fila: " );
				fila = sc.nextInt();
				System.out.println( "columna: " );
				columna = sc.nextInt();
				isBusy = true;
				if ( fila >= 0 && fila < 3 )
				{
					if ( columna >= 0 && columna < 3 )
					{
						if( tab[ fila ][ columna ] != 0 )
						{
							isBusy = true;
							System.out.println( "Casilla inválida. Prueba de nuevo." );
						}
						else 
						{ 
							isBusy = false;
						}
					}
				} 
			} while( isBusy );
			
			// Pone la ficha
			if ( fichas[ player-1 ] != 0 )
			{
				tab[ fila ][ columna ] = player;
				fichas[ player-1 ]--; 
			} else {
				
				// Pide ficha a mover
				int remFila, remColumna;
				// Elimina la ficha seleccionada

				System.out.println( "Introduzca ficha a mover: " );
				do {
					do {
						do {	
							System.out.println( "fila: " );
							remFila = sc.nextInt();
							System.out.println( "columna: " );
							remColumna = sc.nextInt();
							// Se repite mientras que la casilla no 
							// contenga una ficha perteneciente al jugador
						} while ( remColumna < 0 || remColumna > 2 );
					} while ( remFila < 0 || remFila > 2 );
				} while ( tab[ remFila ][ remColumna ] != player );
				
				tab[ remFila ][ remColumna ] = 0;
				tab[ fila ][ columna ] = player;
			}
		}
		// CONTINUE_PLAYING
		private static boolean CONTINUE_PLAYING()
		{
			Scanner sc = new Scanner( System.in );
			System.out.println( "¿Volver a jugar? [S|N]" );
			String play = sc.nextLine();
			boolean response = false;
			int nest = 0;
			
			switch( play )
			{	
				case "s":
				case "S":
						response = true; // Nuevo juego
						break;
				case "n":
				case "N":
						response = false; // Termina
						break;

				// En cualquier otro caso...
				default: 
						// Se vuelve a llamar a la funcion CONTIUE_PLAYING()
						// en nest se almacena el numero de veces que se llama 
						// a la función de manera recursiva.
						nest++;
						if ( nest < 3 ) 
						{
							response = CONTINUE_PLAYING();
						} 
						else if ( nest < 6 )  // a partir de tres niveles de recursividad se advierte al usuario.
						{
							System.out.println( "Preste atención. Responda S o N." );
							response = CONTINUE_PLAYING();
						} 
						else  // Si el usuario se equivoca más de 5 veces, el juego termina.
						{
							System.out.println( "No voy a seguir con esto... ¡Adios!" );
							response = false;
						}
						break;
			}
			return response;
		}
		
		// MAIN_CONTROLLER
		public static void main( String[] args )
		{
			do {
					// Define el tablero
				int[][] tab = {
					{ 0, 0, 0 },
					{ 0, 0, 0 },
					{ 0, 0, 0 }
				};
				
				// Se establece el jugador 2
				// en el primer bucle en el do-while se intercambiará por el 1
				int player = 2;
			
				do {
						// Imprime el tablero
					System.out.println("\t"+tab[0][0]+"-"+tab[0][1]+"-"+tab[0][2]);
					System.out.println("\t|\\|/|");
					System.out.println("\t"+tab[1][0]+"-"+tab[1][1]+"-"+tab[1][2]);
					System.out.println("\t|/|\\|");
					System.out.println("\t"+tab[2][0]+"-"+tab[2][1]+"-"+tab[2][2]);
					
					// Intercambia el jugador
					player = SWITCH_PLAYER( player );
					
					// Pone ficha
					PUT_FICHA( player, tab );

					// Se comprueba si hay ganador
				} while ( CHECK_WINS( player, tab ) == false );
				
				// Seguir jugando?
			} while ( CONTINUE_PLAYING() );
		}
}
