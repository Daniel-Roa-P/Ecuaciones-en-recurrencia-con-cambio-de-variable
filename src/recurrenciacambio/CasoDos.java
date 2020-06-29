
package recurrenciacambio;

public class CasoDos extends Casos{

    @Override
    protected String obtenerFormula(double a, double b) {
        
        this.a = a;
        this.b = b;
        
        raices[0] = calcularRaiz( 1, (-1 * Math.pow(2, a)) - (b) , ( Math.pow(2, a) * b ), 1);
        raices[1] = calcularRaiz( 1, (-1 * Math.pow(2, a)) - (b) , ( Math.pow(2, a) * b ), -1);
        
        System.out.println(raices[0] + " " + raices[1]);
        
        soluciones[0] = 1.0;
        soluciones[1] = calcularInicial();
        
        calcularMatriz();
        
        coeficientes = eliminacionGauss(matriz, soluciones);
        
        System.out.println(coeficientes[0] + " " + coeficientes[1]);
        
        String cadena = "";
        
        for( int i = 0; i<2; i++ ){
        
            if(raices[0] == raices[1]){
            
                cadena = cadena + "( " + formato.format( coeficientes[i]) + " *(lg(n)^" + i + ") *n^" + formato.format( (Math.log10(raices[i])/Math.log10(2)))  + " ) + " ;
            
            } else {
                
                cadena = cadena + "( " + formato.format( coeficientes[i]) + " *n^" + formato.format( (Math.log10(raices[i])/Math.log10(2))) + " ) + " ;
            
                
            }
            
        }

        return cadena.substring(0, cadena.length() - 2);
        
    }

    @Override
    protected void calcularMatriz() {
        
        for(int i = 0; i<2; i++){
            
            for(int j = 0; j<2; j++){
            
                if(raices[0] == raices[1]){
                
                    matriz[i][j] = Math.pow(1.0 + i, (Math.log10(raices[j])/Math.log10(2)) ) * (Math.pow( (Math.log10(1.0 + i)/Math.log10(2)) , j));
                    
                } else {
                    
                    matriz[i][j] = Math.pow(1.0 + i, (Math.log10(raices[j])/Math.log10(2)));
                    
                }
                                
            }
            
        }
    
    }

    @Override
    protected double calcularInicial() {
    
        return Math.pow(2, a) + b;

    }
    
}
