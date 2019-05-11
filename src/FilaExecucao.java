import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FilaExecucao {

	static Fila fila;
	static ArrayList<Evento> eventosExecutados = new ArrayList<>();
	static double time = 0;
	static int maxEventos = 100;
	static ArrayList<Fila> filas;
	
	static ArrayList<Evento> eventos = new ArrayList<>();
	
	public static void main(String[] args) {
		Fila filaInicial = new Fila();
		filas = new ArrayList<>();
		filas.add(filaInicial);
		Evento eventoInicial = new Evento(TipoEvento.CHEGADA, 2.5, filaInicial);
		eventos.add(eventoInicial);
		Evento eventoAExecutar = eventoInicial;
		for(int i = 0; i<maxEventos; i++) {
			//Verificar eventos agendados para executar o que tiver menor tempo
			eventoAExecutar = eventos.get(i);
			executaEvento(eventoAExecutar);
			System.out.println(eventosExecutados.get(i).time + " - " + eventosExecutados.get(i).tipo.toString());
		}
	}

	public static void executaEvento(Evento eventoAExecutar) {
		fila = eventoAExecutar.fila;
		time = eventoAExecutar.time;
		switch(eventoAExecutar.tipo) {
		case CHEGADA:
			chegada();
			break;
		case SAIDA:
			saida(eventoAExecutar);
			break;
		}
		eventosExecutados.add(eventoAExecutar);
	}
	
	public static void chegada() {
		if(fila.count < fila.maxCapacity) {
			fila.count++;
			if(fila.count <= fila.servers) {
				agendaSaida();
			}
		}
		agendaChegada();
	}
	
	public static void saida(Evento eventoAExecutar) {
		eventos.remove(eventoAExecutar);
		fila.count--;
		if(fila.count >= fila.servers) {
			agendaSaida();
		}
		if(eventoAExecutar.fila.goesTo != 0) {
			for(Fila fila: filas) {
				if(fila.id == eventoAExecutar.fila.goesTo) {
					Evento chegada = new Evento(TipoEvento.CHEGADA, time, fila);
					eventos.add(chegada);
					Collections.sort(eventos);
				}
			}
		}
		
	}
	
	public static void agendaSaida() {
		double timeEvento = (fila.maxService - fila.minService) * getTime() + fila.minService + time;
		Evento saida = new Evento(TipoEvento.SAIDA, timeEvento, fila);
		eventos.add(saida);
		Collections.sort(eventos);
	}
	
	public static void agendaChegada() {
		double timeEvento = (fila.maxArrival - fila.minArrival) * getTime() + fila.minArrival + time;
		Evento chegada = new Evento(TipoEvento.CHEGADA, timeEvento, fila);
		eventos.add(chegada);
		Collections.sort(eventos);
	}
	
	public static double getTime() {
		//TODO
		//Tem que chamar o random que fizemos
		return new Random().nextDouble();
	}
}
