import java.util.Random;

public class SEMAApl1 {
    private static int ITERATIONS = 5;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodoCongruenteLinear mcl = new MetodoCongruenteLinear(0.5, 1.0, 15.3, 6.8);
        
        for (int i = 0; i < 1000; i++) {
            double number = mcl.next();
            
            System.out.println(number);
        }
    }
}
