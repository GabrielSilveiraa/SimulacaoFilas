import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.gson.Gson;


public class Config {

	Map<String, Object> obj = null;
	Fila[] filas;
	ArrayList<Evento> eventos = new ArrayList<Evento>();
	int rndnumbersPerSeed = 0;
	int seeds = 0;

	public Config() throws FileNotFoundException {

		Yaml yaml = new Yaml();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.yaml");
		obj = yaml.load(inputStream);

		Gson gson = new Gson();

		String teste = gson.toJson(obj.get("queues"));

		filas = gson.fromJson(teste, Fila[].class);

		String network = gson.toJson(obj.get("network"));

		Rede[] rede = gson.fromJson(network, Rede[].class);

		for (Rede r : rede) {
			for (Fila f : filas) {
				if (f.getName().equals(r.source)) {
					f.addTarget(r.getTarget());
					f.addProbability(r.getProbability());
				}
			}
		}	
		
		rndnumbersPerSeed = Integer.parseInt(gson.toJson(obj.get("rndnumbersPerSeed")));
		
		seeds = Integer.parseInt(gson.toJson(obj.get("seeds")));		
		
		String chegada = gson.toJson(obj.get("arrivals"));
		
		Chegada[] chegadas = gson.fromJson(chegada, Chegada[].class);
		
		for(Chegada value: chegadas)
		{
			for (Fila f : filas) {
				if(f.getName().equals(value.getName()))
					eventos.add(new Evento(TipoEvento.CHEGADA, value.getTime(), f));
			}
		}
		

	}

	public Config getConfig() {
		return this;
	}

	public Fila[] getFilas() {
		return filas;
	}	
	
	public int getRndnumbersPerSeed() {
		return rndnumbersPerSeed;
	}

	public void setRndnumbersPerSeed(int rndnumbersPerSeed) {
		this.rndnumbersPerSeed = rndnumbersPerSeed;
	}

	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
	}	

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}
	
	
	
	@Override
	public String toString() {
		return "Config [obj=" + obj + ", filas=" + Arrays.toString(filas) + ", eventos=" + eventos
				+ ", rndnumbersPerSeed=" + rndnumbersPerSeed + ", seeds=" + seeds + "]";
	}



	public static class Mapper {

		List<Fila> filas;

		public List<Fila> getFilas() {
			return filas;
		}

		public void setFilas(List<Fila> filas) {
			this.filas = filas;
		}

		public Mapper() {
		}

		@Override
		public String toString() {
			return "Mapper [filas=" + filas + "]";
		}

	}

	public class Rede {
		String source;
		String target;
		double probability;

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}

		public double getProbability() {
			return probability;
		}

		public void setProbability(double probability) {
			this.probability = probability;
		}

		@Override
		public String toString() {
			return "Rede [source=" + source + ", target=" + target + ", probability=" + probability + "]";
		}		
		
	}
	
	public class Chegada 
	{
		String name;
		Double time;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getTime() {
			return time;
		}
		public void setTime(Double time) {
			this.time = time;
		}
		
		@Override
		public String toString() {
			return "Chegadas [name=" + name + ", time=" + time + "]";
		}
		
		
	}

}