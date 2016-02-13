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
		private static boolean CHECK_WINS()
		{
			return false;
		}
		// REMOVE_FICHA
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
					System.out.println( "fila: " );
					remFila = sc.nextInt();
					System.out.println( "columna: " );
					remColumna = sc.nextInt();
					// Se repite mientras que la casilla no 
					// contenga una ficha perteneciente al jugador
				} while ( tab[ remFila ][ remColumna ] != player );
				
				tab[ remFila ][ remColumna ] = 0;
				tab[ fila ][ columna ] = player;
			}
		}
		
		// MAIN_CONTROLLER
		public static void main( String[] args )
		{

			int[][] tab = {
					{ 0, 0, 0 },
					{ 0, 0, 0 },
					{ 0, 0, 0 }
			};

			int player = 2;
			
			do {
				System.out.println("\t"+tab[0][0]+"-"+tab[0][1]+"-"+tab[0][2]);
				System.out.println("\t|\\|/|");
				System.out.println("\t"+tab[1][0]+"-"+tab[1][1]+"-"+tab[1][2]);
				System.out.println("\t|/|\\|");
				System.out.println("\t"+tab[2][0]+"-"+tab[2][1]+"-"+tab[2][2]);
				player = SWITCH_PLAYER( player );
				
				PUT_FICHA( player, tab );

			} while ( CHECK_WINS() == false );
		}
}
