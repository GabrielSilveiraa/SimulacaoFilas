import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FilaExecucao {

	static int fila = 0;
	static ArrayList<Evento> eventosExecutados = new ArrayList<>();
	static double time = 0;
	static int maxEventos = 100;
	static ArrayList<Fila> filas;
	
	static ArrayList<Evento> eventos = new ArrayList<>();
	
	public static void main(String[] args) {
		Evento eventoInicial = new Evento(TipoEvento.CHEGADA, 2.5);
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
		if(fila < 4) {
			fila++;
			if(fila <= 1) {
				agendaSaida();
			}
		}
		agendaChegada();
	}
	
	public static void saida() {
		fila--;
		if(fila >= 1) {
			agendaSaida();
		}
	}
	
	public static void agendaSaida() {
		double timeEvento = (5 - 3) * getTime() + 3 + time;
		Evento saida = new Evento(TipoEvento.SAIDA, timeEvento);
		eventos.add(saida);
		Collections.sort(eventos);
		for(Evento evento: eventos) {
			System.out.println(evento.time);
		}
	}
	
	public static void agendaChegada() {
		double timeEvento = (3 - 2) * getTime() + 2 + time;
		Evento chegada = new Evento(TipoEvento.CHEGADA, timeEvento);
		eventos.add(chegada);
		Collections.sort(eventos);
		for(Evento evento: eventos) {
			System.out.println(evento.time);
		}
	}
	
	public static double getTime() {
		return new Random().nextDouble();
	}
}
