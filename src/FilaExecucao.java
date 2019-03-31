import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FilaExecucao {

	static Fila fila;
	static ArrayList<Evento> eventosExecutados = new ArrayList<>();
	static double time = 0;
	static int maxEventos = 10;
	static ArrayList<Fila> filas;
	
	static ArrayList<Evento> eventos = new ArrayList<>();
	
	public static void main(String[] args) {
		Fila filaInicial = new Fila();
		filas = new ArrayList<>();
		filas.add(filaInicial);
		Evento eventoInicial = new Evento(TipoEvento.CHEGADA, 2.5, filaInicial);
		eventos.add(eventoInicial);
		for(int i = 0; i<maxEventos; i++) {
			executaEvento();
			System.out.println(eventosExecutados.get(i).time + " - " + eventosExecutados.get(i).tipo.toString());
		}
	}

	public static void executaEvento() {
		Evento eventoAExecutar = null;
		
		for(int i = 0; i < eventos.size(); i++) {
			//Verificar eventos agendados para executar o que tiver menor tempo
			if(eventoAExecutar == null) {
				eventoAExecutar = eventos.get(i);
			}
			if(eventoAExecutar.time > eventos.get(i).time) {
				eventoAExecutar = eventos.get(i);
			}
		}
		fila = eventoAExecutar.fila;
		time = eventoAExecutar.time;
		switch(eventoAExecutar.tipo) {
		case CHEGADA:
			chegada();
			break;
		case SAIDA:
			saida();
			break;
		}
		eventos.remove(eventoAExecutar);
		eventosExecutados.add(eventoAExecutar);
	}
	
	public static void chegada() {
		if(fila.count < fila.maxCapacity) {
			fila.count++;
			if(fila.count <= 1) {
				agendaSaida();
			}
		}
		agendaChegada();
	}
	
	public static void saida() {
		fila.count--;
		if(fila.count >= 1) {
			agendaSaida();
		}
	}
	
	public static void agendaSaida() {
		double timeEvento = (5 - 3) * getTime() + 3 + time;
		Evento saida = new Evento(TipoEvento.SAIDA, timeEvento, fila);
		eventos.add(saida);
		Collections.sort(eventos);
	}
	
	public static void agendaChegada() {
		double timeEvento = (3 - 2) * getTime() + 2 + time;
		Evento chegada = new Evento(TipoEvento.CHEGADA, timeEvento, fila);
		eventos.add(chegada);
		Collections.sort(eventos);
	}
	
	public static double getTime() {
		return new Random().nextDouble();
	}
}
