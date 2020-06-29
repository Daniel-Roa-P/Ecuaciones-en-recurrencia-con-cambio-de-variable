
package recurrenciacambio;

public class CasoUno extends Casos {
    
    @Override
    public String obtenerFormula(double a, double b){
        
        this.a = a;
        this.b = b;
        
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
    
    @Override
    public double calcularInicial(){
        
        return a + b;
        
    }
    
    @Override
    public void calcularMatriz(){
        
        for(int i = 0; i<2; i++){
            
            for(int j = 0; j<2; j++){
            
                matriz[i][j] = Math.pow(1.0 + i, (Math.log10(raices[j])/Math.log10(2)));
            
            }
            
        }
        
    }
    
}
