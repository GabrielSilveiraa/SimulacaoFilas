public class MetodoCongruenteLinear {    
    private double mutant, mult, max, constant;
    
    public MetodoCongruenteLinear(double seed, double max, double mult, double constant) {
        this.mutant = seed;
        this.max = max;
        this.mult = mult;
        this.constant = constant;
    }
    
    public double next() {
        this.mutant = (this.constant + this.mult * this.mutant) % max;
        
        return this.mutant;
    }
}
