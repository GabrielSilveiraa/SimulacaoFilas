public class Evento implements Comparable<Evento>{
	
	TipoEvento tipo;
	Fila fila;
	double time;
	
	public Evento(TipoEvento tipo, double time, Fila fila) {
		this.tipo = tipo;
		this.time = time;
		this.fila = fila;
	}

	@Override
	public int compareTo(Evento o) {
		if(o.time < this.time) {
			return 1;
		}
		if(o.time > this.time) {
			return -1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "fila: " + fila.name + ", tempo: "+time + ", tipo: "+tipo;
	}
}
