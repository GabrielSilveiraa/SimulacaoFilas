import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import org.yaml.snakeyaml.Yaml;

public class FilaExecucao {

	static Fila filaAtual;
	static Fila filaInicial;
	static ArrayList<Evento> eventosExecutados = new ArrayList<>();
	static double time = 0;
	static int maxEventos = 40;
	static ArrayList<Fila> filas;
	static MetodoCongruenteLinear congruenteLinear = new MetodoCongruenteLinear(0.5, 1.0, 15.3, 6.8);
	static ArrayList<Evento> eventos = new ArrayList<>();
    MetodoCongruenteLinear mcl = null;
    static double lastTime = 0.0;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Config config = new Config();
		
		filas = new ArrayList<>();
				
		//Lendo as filas do config fila
		for(Fila f: config.getFilas())
		{
			filas.add(f);
			System.out.println(f.toString());
			System.out.println("Proximo: " + f.draftNext());
		}
		filaInicial = filas.get(0);

		
		//Lendo os eventos que ja foram pre estabelecidos no config file
		for(Evento e: config.getEventos())
		{
			eventos.add(e);
		}
		Collections.sort(eventos);
		Evento eventoAExecutar = null;
		
		lastTime = eventos.get(0).time;
		
		//Retirei o maximo para 100 pq
		for(int i = 0; i<maxEventos; i++) {			
			
			//Verificar eventos agendados para executar o que tiver menor tempo
			eventoAExecutar = eventos.get(0);
			filaAtual = eventoAExecutar.fila;

			executaEvento(eventoAExecutar);
			eventos.remove(0);
			
			if(eventosExecutados.get(i).fila.getName().equals("F4"))
			lastTime = eventosExecutados.get(i).time;
			System.out.println("Fila: " + eventosExecutados.get(i).fila.getName() + " - " + eventosExecutados.get(i).time + " - " + eventosExecutados.get(i).tipo.toString() + "- Pessoas na fila: " + eventosExecutados.get(i).fila.count + " - Diferenca: " + eventosExecutados.get(i).fila.diferenca);
		}
		System.out.println("Tempos");
		for(Fila f: config.getFilas())
		{
			System.out.println("Fila: " + f.getName() + " - " + f.times.toString());
		}
		
	}

	public static void executaEvento(Evento eventoAExecutar) {
		filaAtual = eventoAExecutar.fila;
		time = eventoAExecutar.time;		
		switch(eventoAExecutar.tipo) {
		case CHEGADA:
			chegada(eventoAExecutar);
			break;
		case SAIDA:
			saida(eventoAExecutar);
			break;
		}
		eventosExecutados.add(eventoAExecutar);
	}
	
	public static void chegada(Evento eventoAExecutar) {		
		if(filaAtual.count < filaAtual.maxCapacity) {
			filaAtual.incrementaTempoFilaChegada(eventoAExecutar.time);
			if(filaAtual.count <= filaAtual.servers)
				agendaSaida();	
			agendaChegada();
		} else {
			saida(eventoAExecutar);
		}
	}
	
	
	public static void saida(Evento eventoAExecutar) {
		if(filaAtual.count != 0)
			filaAtual.incrementaTempoFilaSaida(eventoAExecutar.time);
		if(filaAtual.count >= filaAtual.servers) {
			agendaSaida();
		}
		
		//Variavel para controle do proximo sorteado (de acordo com probabilidade)
		String proximoSorteado = null;
		
		if(!eventoAExecutar.fila.target.isEmpty()) {	
			proximoSorteado = eventoAExecutar.fila.draftNext();
			for(Fila aux: filas) {
				if(proximoSorteado.equals(aux.getName())) {
					Evento chegada = new Evento(TipoEvento.CHEGADA, time, aux);
					eventos.add(chegada);
					Collections.sort(eventos);
				}
			}
		}
	}
	
	public static void agendaSaida() {
		double timeEvento = (filaAtual.maxService - filaAtual.minService) * getTime() + filaAtual.minService + time;
		Evento saida = new Evento(TipoEvento.SAIDA, timeEvento, filaAtual);
		eventos.add(saida);
		Collections.sort(eventos);
	}
	
	public static void agendaChegada() {
		double timeEvento = (filaInicial.maxArrival - filaInicial.minArrival) * getTime() + filaInicial.minArrival + time;
		Evento chegada = new Evento(TipoEvento.CHEGADA, timeEvento, filaInicial);
		eventos.add(chegada);
		Collections.sort(eventos);
	}
	
	public static double getTime() {

		return congruenteLinear.next();
	}

	
}
