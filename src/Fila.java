import java.util.ArrayList;

public class Fila {
	int id;	
	String name;
	int maxCapacity = 4;
	int count = 0;
	int servers;
	int minArrival;
	int maxArrival;
	int minService;
	int maxService;

	ArrayList<String> target = new ArrayList<String>();
	ArrayList<Double> probability = new ArrayList<Double>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public int getServers() {
		return servers;
	}
	public void setServers(int servers) {
		this.servers = servers;
	}
	public int getMinArrival() {
		return minArrival;
	}
	public void setMinArrival(int minArrival) {
		this.minArrival = minArrival;
	}
	public int getMaxArrival() {
		return maxArrival;
	}
	public void setMaxArrival(int maxArrival) {
		this.maxArrival = maxArrival;
	}
	public int getMinService() {
		return minService;
	}
	public void setMinService(int minService) {
		this.minService = minService;
	}
	public int getMaxService() {
		return maxService;
	}
	public void setMaxService(int maxService) {
		this.maxService = maxService;
	}
	public ArrayList<String> getTarget() {
		return target;
	}
	public void addTarget(String target) {
		this.target.add(target);
	}
	public ArrayList<Double> getProbability() {
		return probability;
	}
	public void addProbability(Double probability) {
		this.probability.add(probability);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	//M�todo para sortear o proximo, ele fica em um array ate o math random vir menor que a probabilidade
	public String draftNext()
	{
		if(target.size() == 0)
			return null;
		
		
		if(target.size() == 1)
			return target.get(0);		
		
		for(int i = 0; i < target.size(); i++) {
			if(Math.random() <= probability.get(i))
				return target.get(i);
		}	
		
		return this.draftNext();
	}
	
	
	@Override
	public String toString() {
		return "Fila [id=" + id + ", name=" + name + ", maxCapacity=" + maxCapacity + ", count=" + count + ", servers="
				+ servers + ", minArrival=" + minArrival + ", maxArrival=" + maxArrival + ", minService=" + minService
				+ ", maxService=" + maxService + ", target=" + target + ", probability="
				+ probability + "]";
	}
	
	
	
}
