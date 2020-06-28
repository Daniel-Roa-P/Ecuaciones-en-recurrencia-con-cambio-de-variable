
package recurrenciacambio;

import java.text.DecimalFormat;

public class CasoUno {

    private double a;
    private double b;
    private double [] soluciones = new double[2];
    private double [] raices = new double[2];
    private double [] coeficientes = new double[2];
    private double [] [] matriz = new double [2][2];
    private DecimalFormat formato = new DecimalFormat("###.####");
    
    
    CasoUno(double a, double b){
    
        this.a = a;
        this.b = b;
        
    }
    
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
    
    public String obtenerFormula(){
        
        raices[0] = calcularRaiz( 1, (b*-1) - 1 , b, 1);
        raices[1] = calcularRaiz( 1, (b*-1) - 1 , b, -1);
        
        System.out.println(raices[0] + " " + raices[1]);
        
        soluciones[0] = 1.0;
        soluciones[1] = calcularInicial();
        
        calcularMatriz();
        
        coeficientes = eliminacionGauss(matriz, soluciones);
        
        String cadena = "";
        
        for( int i = 0; i<2; i++ ){
        
            cadena = cadena + "( " + formato.format( coeficientes[i]) + "*n^" + formato.format( (Math.log10(raices[i])/Math.log10(2))) + " ) + " ;
            
        }

        return cadena.substring(0, cadena.length() - 2);
        
    }
    
    public double calcularInicial(){
        
        return a + b;
        
    }
    
    public void calcularMatriz(){
        
        matriz[0][0] = Math.pow(1.0, (Math.log10(raices[0])/Math.log10(2)));
        matriz[0][1] = Math.pow(1.0, (Math.log10(raices[1])/Math.log10(2)));
        matriz[1][0] = Math.pow(2.0, (Math.log10(raices[0])/Math.log10(2)));
        matriz[1][1] = Math.pow(2.0, (Math.log10(raices[1])/Math.log10(2)));
        
    }
    
    public double calcularRaiz(double a, double b , double c, int signo){
    
        double raiz = (-b + signo*( Math.sqrt( (Math.pow(b, 2)) - (4*a*c) ) ))/(2*a);
        
        return raiz;
        
    }
    
    
}
