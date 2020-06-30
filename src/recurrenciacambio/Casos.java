
package recurrenciacambio;

import java.text.DecimalFormat;

public abstract class Casos {
    
    protected double a;
    protected double b;
    protected double [] soluciones = new double[2];
    protected double [] raices = new double[2];
    protected double [] coeficientes = new double[2];
    protected double [] [] matriz = new double [2][2];
    protected double [] [] copiaMatriz = new double [2][2];
    protected DecimalFormat formato = new DecimalFormat("###.####");
    
    public double[] eliminacionGauss(double[][] A, double[] b) {
        
        int n = b.length;

        for (int p = 0; p < n; p++) {

            int max = p;
            
            for (int i = p + 1; i < n; i++) {
                
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
                
            }
            
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double t = b[p]; b[p] = b[max]; b[max] = t;

            for (int i = p + 1; i < n; i++) {
                
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                
                for (int j = p; j < n; j++) {
                    
                    A[i][j] -= alpha * A[p][j];
                    
                }
            }
        }

        double[] x = new double[n];
        
        for (int i = n - 1; i >= 0; i--) {
            
            double sum = 0.0;
            
            for (int j = i + 1; j < n; j++) {
                
                sum += A[i][j] * x[j];
                
            }
            
            x[i] = (b[i] - sum) / A[i][i];
            
        }
        
        return x;
        
    }
    
    public double calcularRaiz(double a, double b , double c, int signo){
    
        double raiz = (-b + signo*( Math.sqrt( (Math.pow(b, 2)) - (4*a*c) ) ))/(2*a);
        
        return raiz;
        
    }
    
    protected abstract String obtenerFormula (double a, double b);    
    protected abstract String obtenerProcedimiento (double a, double b);  
    protected abstract void calcularMatriz ();
    protected abstract double calcularInicial();
    
}
