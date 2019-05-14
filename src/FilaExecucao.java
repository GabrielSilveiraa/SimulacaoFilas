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
	static int maxEventos = 100;
	static ArrayList<Fila> filas;
	
	static ArrayList<Evento> eventos = new ArrayList<>();
	
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
		
		
		//teste
		/*
		Fila filaInicial = new Fila();	
		filas.add(filaInicial);
		Evento eventoInicial = new Evento(TipoEvento.CHEGADA, 2.5, filaInicial);
		eventos.add(eventoInicial);
		*/
		
		//Lendo os eventos que ja foram pre estabelecidos no config file
		for(Evento e: config.getEventos())
		{
			eventos.add(e);
		}
		Collections.sort(eventos);
		Evento eventoAExecutar = null;
		
		//Retirei o maximo para 100 pq
		for(int i = 0; i<100; i++) {
			
//			if(i > 100)
//				return;
			
			//Verificar eventos agendados para executar o que tiver menor tempo
			eventoAExecutar = eventos.get(0);
			executaEvento(eventoAExecutar);
			eventos.remove(0);
			System.out.println("Fila: " + eventoAExecutar.fila.getName() + " - " + eventosExecutados.get(i).time + " - " + eventosExecutados.get(i).tipo.toString());
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
			filaAtual.count++;
			if(filaAtual.count <= filaAtual.servers) {
				agendaSaida();
			}
		}
		agendaChegada();
	}
	
	
	public static void saida(Evento eventoAExecutar) {
		filaAtual.count--;
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
		//TODO
		//Tem que chamar o random que fizemos
		return new Random().nextDouble();
	}

	
}
