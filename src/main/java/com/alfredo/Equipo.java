package com.alfredo;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Equipo {

	
	private String nombre;
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private int contadorPartidas = 0;
	
	 Equipo(String nombre){
		this.nombre = nombre;
	
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	// agregar jugador
	public void agregarJugador(Jugador j) {

	    if(jugadores.size() >= 5) {
	        System.out.println("No se pueden agregar más jugadores al equipo");
	        
	    }

	    for(Jugador jugador : jugadores) {

	        if(jugador.getId() == j.getId()) {
	            System.out.println("No se puede añadir jugador, ID repetido");
	            
	        }

	        if(jugador.getRol().equalsIgnoreCase(j.getRol())) {
	            System.out.println("El rol ya está ocupado");
	        
	        }
	    }

	    if(j.getNick().trim().isEmpty()) {
	        System.out.println("El nick está vacío");
	       
	    }

	    if(j.getKills() < 0 || j.getDeaths() < 0 || j.getAssists() < 0) {
	        System.out.println("No puede haber estadísticas negativas");
	       
	    }

	    jugadores.add(j);
	    System.out.println("Jugador " + j.getNick() + " añadido correctamente");
	}

	public void mostrarJugadores() {

		if(jugadores.isEmpty()) {
			System.out.println("El equipo está vacío");
			return;
		}

		for(Jugador j : jugadores) {
			System.out.println(j);
		}
	}
	
	public void guardarJugadores() {
		try(ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("jugadores.dat"))){
			for (Jugador j : jugadores) {
				oos.writeObject(j);
			}
			System.out.println("jugadores guardados en fichero");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void cargarJugadores() {

	    File f = new File("jugadores.dat");

	    if (!f.exists()) {
	        System.out.println("No hay fichero guardado aún");
	        return;
	    }
	    // limpiamos el fichero de datos antes de cargarlos
	    jugadores.clear();

	    try (ObjectInputStream ois =
	         new ObjectInputStream(new FileInputStream(f))) {

	        while (true) {
	            Jugador j = (Jugador) ois.readObject();
	            jugadores.add(j);
	        }

	    } catch (EOFException e) {
	        System.out.println("Jugadores cargados correctamente");
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	public void leerFichero() {

	    File f = new File("jugadores.dat");

	    if (!f.exists()) {
	        System.out.println("No hay fichero");
	        return;
	    }

	    try (ObjectInputStream ois =
	         new ObjectInputStream(new FileInputStream(f))) {

	        while (true) {
	            Jugador j = (Jugador) ois.readObject();
	            System.out.println(j);
	        }

	    } catch (EOFException e) {
	        System.out.println("Fin del fichero");
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	public void buscarJugador(int id) {

	    for (Jugador j : jugadores) {
	        if (j.getId() == id) {
	            System.out.println("Jugador encontrado:");
	            System.out.println(j);
	            
	        }
	    }

	    System.out.println("Jugador no encontrado");
	}

	public void eliminarJugador(int id) {

		for(int i=0;i<jugadores.size();i++) {

			if(jugadores.get(i).getId()==id) {
				System.out.println("Jugador eliminado:");
				System.out.println(jugadores.get(i));
				jugadores.remove(i);
				
			}
		}

	    System.out.println("No se encontró ningún jugador con ID " + id);
	}

	public void actualizarStats(int id, int k, int d, int a) {

	    for (Jugador j : jugadores) {

	        if (j.getId() == id) {

	            if (k < 0 || d < 0 || a < 0) {
	                System.out.println("No se pueden sumar estadísticas negativas");
	                
	            }

	            j.setKills(j.getKills()+k);
	            j.setDeaths(j.getDeaths()+d);
	            j.setAssists(j.getAssists()+a);

	            System.out.println("Estadísticas actualizadas:");
	            System.out.println(j);
	           
	        }
	    }

	    System.out.println("Jugador no encontrado");
	}

	public void listarPorRol(String rol) {

		for(Jugador j : jugadores) {

			if(j.getRol().equalsIgnoreCase(rol)) {
				System.out.println(j);
			}
		}
	}

	
	public void simularPartida() {

	    Random r = new Random();

	    contadorPartidas++;

	    ArrayList<Partida> historial = leerHistorial();

	    for (Jugador j : jugadores) {

	        int k = r.nextInt(15);
	        int d = r.nextInt(15);
	        int a = r.nextInt(20);

	        j.setKills(j.getKills() + k);
	        j.setDeaths(j.getDeaths() + d);
	        j.setAssists(j.getAssists() + a);

	        System.out.println(j.getNick() +
	                " +Kills:" + k +
	                " +Deaths:" + d +
	                " +Assists:" + a);

	        historial.add(new Partida(contadorPartidas, j.getNick(), k, d, a));
	    }

	    guardarHistorial(historial);
	}
	private void guardarHistorial(ArrayList<Partida> lista) {

	    try (ObjectOutputStream oos =
	         new ObjectOutputStream(new FileOutputStream("partidas.dat"))) {

	        for (Partida p : lista) {
	            oos.writeObject(p);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	private ArrayList<Partida> leerHistorial() {

	    ArrayList<Partida> lista = new ArrayList<>();

	    File f = new File("partidas.dat");

	    if (!f.exists()) return lista;

	    try (ObjectInputStream ois =
	         new ObjectInputStream(new FileInputStream(f))) {

	        while (true) {
	            lista.add((Partida) ois.readObject());
	        }

	    } catch (EOFException e) {
	        // fin normal
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	public void leerHistorialPartidas() {

	    File f = new File("partidas.dat");

	    if (!f.exists()) {
	        System.out.println("No hay historial");
	        return;
	    }

	    try (ObjectInputStream ois =
	         new ObjectInputStream(new FileInputStream(f))) {

	        while (true) {
	            System.out.println((Partida) ois.readObject());
	        }

	    } catch (EOFException e) {
	        System.out.println("Fin del historial");
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	public void estadisticasEquipo() {

		int totalKills=0;
		int totalDeaths=0;
		int totalAssists=0;

		for(Jugador j : jugadores) {

			totalKills += j.getKills();
			totalDeaths += j.getDeaths();
			totalAssists += j.getAssists();
		}

		System.out.println("Kills totales: " + totalKills);
		System.out.println("Deaths totales: " + totalDeaths);
		System.out.println("Assists totales: " + totalAssists);
	}

	public void mostrarMVP() {

	    ArrayList<Jugador> aux = new ArrayList<>();

	    if (jugadores.isEmpty()) {
	        System.out.println("No hay jugadores");
	        return;
	    }

	    // Copiar todos los jugadores al auxiliar
	    aux.addAll(jugadores);

	    // Ordenar el auxiliar (no tocamos la lista original)
	    Collections.sort(aux);

	    // Mostrar el auxiliar completo
	    System.out.println("Lista ordenada de jugadores:");
	    for (Jugador j : aux) {
	        System.out.println(j);
	    }

	    // Mostrar MVP (primer jugador del auxiliar)
	    System.out.println("MVP del equipo:");
	    System.out.println(aux.get(0));
	}

	public void top3KDA() {

	    if (jugadores.isEmpty()) {
	        System.out.println("No hay jugadores");
	        return;
	    }

	    // Crear lista auxiliar y copiar todos los jugadores
	    ArrayList<Jugador> aux = new ArrayList<>();
	    aux.addAll(jugadores);

	    // Ordenar la lista auxiliar (no tocamos la original)
	    Collections.sort(aux);

	    System.out.println("TOP 3 jugadores por KDA:\n");

	    // Mostrar los 3 primeros del auxiliar
	    for (int i = 0; i < 3 && i < aux.size(); i++) {

	        Jugador j = aux.get(i);

	        System.out.println((i + 1) + ". " +
	                j.getNick() +
	                " - KDA: " +
	                j.calcularKDA());
	    }
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + "]";
	}
}
