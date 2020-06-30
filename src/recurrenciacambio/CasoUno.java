
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
        
        for(int i = 0; i<2; i++){
            
            for(int j = 0; j<2; j++){
            
                copiaMatriz[i][j] = matriz[i][j];
                
            }
            
        }
        
        coeficientes = eliminacionGauss(copiaMatriz, soluciones);
        
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
            
                System.out.println("i = " + i + "j = " + j + " es: " + matriz[i][j]);
                
            }
            
        }
        
    }

    @Override
    protected String obtenerProcedimiento(double a, double b) {
    
        String proceso = "F(n) = (" + a +") + (" + b +")*F(n/2)";
        
        proceso = proceso + "\n \nConsiderando que: n= 2^i";
        
        proceso = proceso + "\n \nF(2^i) = (" + a +") + (" + b +")*F(2^(i-1))";
        
        proceso = proceso + "\n \nF(2^i) - (" + b +")*F(2^(i-1)) = (" + a +")";
        
        proceso = proceso + "\n \nCambiando los subindices en función i, tenemos:";
        
        proceso = proceso + "\n \nF(i) - (" + b +")*F(i-1) = (" + a +")";
        
        proceso = proceso + "\n \nReemplazando i por i-1, y multiplicando por (-1) tenemos:";
        
        proceso = proceso + "\n \n(-1)*F(i-1) + (" + b +")*F(i-2) = (-" + a +")";
        
        proceso = proceso + "\n \nSumando la ecuaciones se convierte en homogenea:";
        
        proceso = proceso + "\n \nF(i) + (" + ((b*-1) - 1) +")*F(i-1) + ("+ b +")*F(i-2) = 0";
        
        proceso = proceso + "\n \nLas dos raices son (" + raices[0] + ", " + raices[1] + "):";
        
        proceso = proceso + "\n \nLa solucion general es:";
        
        proceso = proceso + "\n \nF(i) = C1*(("+raices[0]+"^i)) + C2*(("+raices[1]+"^i))";
        
        proceso = proceso + "\n \nReemplazando i por lg(n), tenemos:";
        
        proceso = proceso + "\n \nF(i) = C1*(("+raices[0]+"^lg(n))) + C2*(("+raices[1]+"^lg(n)))";
        
        proceso = proceso + "\n \nLas condiciones iniciales se calculan con:";
        
        proceso = proceso + "\n \nF(1) = 1";
        
        proceso = proceso + "\n \nF(2) = " + calcularInicial();
        
        proceso = proceso + "\n \nCon lo cual las ecuaciones para calcular C1 y C2 son:";
        
        proceso = proceso + "\n \nC1*((1^lg("+ raices[0] +"))) + C2*((1^lg("+ raices[1] +"))) = 1";
        
        proceso = proceso + "\n \nC1*((2^lg("+ raices[0] +"))) + C2*((2^lg("+ raices[1] +"))) = " + calcularInicial();
        
        proceso = proceso + "\n \nC1*( " + matriz[0][0] +" ) + C2*( " + matriz[0][1] +" )";
        
        proceso = proceso + "\n \nC1*( " + matriz[1][0] +" ) + C2*( " + matriz[1][1] +" )";
        
        proceso = proceso + "\n \nMatriz para Gauss";
        
        proceso = proceso + "\n \n[ " + matriz[0][0] + ", " + matriz[0][1] + ", " + 1 + " ]" ;
        
        proceso = proceso + "\n \n[ " + matriz[1][0] + ", " + matriz[1][1] + ", " + calcularInicial() + " ]" ;
        
        proceso = proceso + "\n \nResolviendo por eliminacion";
        
        proceso = proceso + "\n \nC1 = " + coeficientes[0];
        
        proceso = proceso + "\n \nC2 = " + coeficientes[1];
        
        proceso = proceso + "\n \nCon lo cual la solucion es: ";
        
        proceso = proceso + obtenerFormula( a,  b);
        
        return proceso;
       
    }
    
}
