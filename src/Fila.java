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
	//Se goes to for -1, ela vai embora
	int goesTo;
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
	public int getGoesTo() {
		return goesTo;
	}
	public void setGoesTo(int goesTo) {
		this.goesTo = goesTo;
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
	
	@Override
	public String toString() {
		return "Fila [id=" + id + ", name=" + name + ", maxCapacity=" + maxCapacity + ", count=" + count + ", servers="
				+ servers + ", minArrival=" + minArrival + ", maxArrival=" + maxArrival + ", minService=" + minService
				+ ", maxService=" + maxService + ", goesTo=" + goesTo + ", target=" + target + ", probability="
				+ probability + "]";
	}
	
	
	
}
