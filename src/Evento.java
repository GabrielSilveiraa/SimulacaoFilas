public class Evento implements Comparable<Evento>{
	TipoEvento tipo;
	double time;
	
	public Evento(TipoEvento tipo, double time) {
		this.tipo = tipo;
		this.time = time;
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
}
