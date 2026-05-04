package Esport;

import java.util.Scanner;

public class AppMainEsport {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Equipo miEquipo = new Equipo("Vinicius Esports");
		
		// usamos el metodo para agregar jugadores para que se hagan las comprobaciones
		/*miEquipo.agregarJugador(new Jugador(1,"DjMario","TOP",5,2,7));
		miEquipo.agregarJugador(new Jugador(2,"Mbappe","JUNGLE",3,3,10));
		miEquipo.agregarJugador(new Jugador(3,"Grizzie","MID",10,1,5));
		miEquipo.agregarJugador(new Jugador(4,"Julio Alberto","ADC",8,2,6));
		miEquipo.agregarJugador(new Jugador(5,"Ángelita","SUPPORT",1,4,12));*/
		
		 	Jugador j1 = new Jugador(1, "DjMario", "TOP", 5, 2, 7);
	        Jugador j2 = new Jugador(2, "Mbappe", "JUNGLE", 3, 3, 10);
	        Jugador j3 = new Jugador(3, "Grizzie", "MID", 10, 1, 5);
	        Jugador j4 = new Jugador(4, "Julio Alberto", "ADC", 8, 2, 6);
	        Jugador j5 = new Jugador(5, "Aricita", "SUPPORT", 1, 4, 12);

	       
	        miEquipo.agregarJugador(j1);
	        miEquipo.agregarJugador(j2);
	        miEquipo.agregarJugador(j3);
	        miEquipo.agregarJugador(j4);
	        miEquipo.agregarJugador(j5);

		int opcion;

		do {

			System.out.println("\n------ GESTIÓN EQUIPO ESPORTS ------");
			System.out.println("1. Listar jugadores");
			System.out.println("2. Buscar jugador por ID");
			System.out.println("3. Eliminar jugador");
			System.out.println("4. Actualizar estadísticas");
			System.out.println("5. Listar por rol");
			System.out.println("6. Simular partida");
			System.out.println("7. Estadísticas equipo");
			System.out.println("8. Mostrar MVP");
			System.out.println("9. Top 3 KDA");
			System.out.println("10. Guardar jugadores en fichero");
			System.out.println("11. Cargar jugadores desde fichero");
			System.out.println("12. Leer fichero de jugadores");
			System.out.println("13. Ver historial de partidas");
			System.out.println("0. Salir");

			opcion = sc.nextInt();
			sc.nextLine();

			switch(opcion) {

			case 1:
				miEquipo.mostrarJugadores();
				break;

			case 2:
			    System.out.print("ID: ");
			    int id = sc.nextInt();
			    miEquipo.buscarJugador(id);
			    break;


			case 3:
				System.out.print("ID: ");
				id = sc.nextInt();
				miEquipo.eliminarJugador(id);
				break;

			case 4:
				System.out.print("ID jugador: ");
				id = sc.nextInt();

				System.out.print("Kills extra: ");
				int k = sc.nextInt();

				System.out.print("Deaths extra: ");
				int d = sc.nextInt();

				System.out.print("Assists extra: ");
				int a = sc.nextInt();

				miEquipo.actualizarStats(id,k,d,a);
				break;

			case 5:
				System.out.print("Rol: ");
				String rol = sc.nextLine();
				miEquipo.listarPorRol(rol);
				break;

			case 6:
				miEquipo.simularPartida();
				break;

			case 7:
				miEquipo.estadisticasEquipo();
				break;

			case 8:
				miEquipo.mostrarMVP();
				break;

			case 9:
				miEquipo.top3KDA();
				break;
			case 10:
				miEquipo.guardarJugadores();
				break;
			case 11:
			    miEquipo.cargarJugadores();
			    break;
			case 12:
				miEquipo.leerFichero();
				break;
			case 13: 
				miEquipo.leerHistorialPartidas();
				break;

			case 0:
				System.out.println("Saliendo...");
				break;

			default:
				System.out.println("Opción inválida");
			}

		} while(opcion != 0);

		sc.close();
	}
}

