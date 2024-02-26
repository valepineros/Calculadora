
import java.util.ArrayList;
import java.util.Arrays;


/** 
 *  Clase que representa una calculadora. La calculadora convierte una expresión de forma 
 * infija a notación postfija, y luego la evalua. En la clase se usan pilas para solucionar el
 * problema
 * La calculadora cuenta con  2 atributos: la entrada de la clauladora original y el 
 * el resultado. 
 * @author Valentina Romero, Regina Santin, Bernardo de la Torre, Jeronimo Torres, Luis Gomez
 */


public class Calculadora {

    
    // contructor vacio
    public Calculadora() {
        
    }

    /*
    * Se presentan métodos que revisan la sintaxis de la ecuacion a evaluarse. 
    */
    
    /*
    Método que revisa que la forma en la que los parentesis se expresan es adecuado
    Por cada parentesis derecho que tenga debe haber un parentesis izquierdo
    Se utilizan pilas para verificar que este se cumpla, si la pila se queda vacia
    nos regresa un true, confirmando que la ecuacion si esta balanceada correctamente
    *
    * @param cadena La expresion en forma infija
    * @return true si esta balanceada correctamente y false si no
    */   
    public static boolean estaBalanceado(String cadena) {
        boolean res = false;
        boolean valido = true;
        PilaADT pila = new PilaA();

        for (int i = 0; i < cadena.length() && valido; i++) {
            char c = cadena.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                pila.push(c);
            } 
            else if (c == ')' || c == '}' || c == ']') {
                try {
                    char top = (char) pila.pop();
                    if ((c == ')' && top != '(')) {
                        valido = false;
                    }
                } 
                catch (Exception e) {
                    valido = false;
                }
            }
        }
        if (pila.isEmpty() && valido) {
            res = true;
        }
        return res;
    }
    
    /*
    Método estático que verifica que los puntos decimales sean implementados de forma correcta.
    *
    *
    * @param cadena La expresion en forma infija
    * @return true si el uso de decimales es utilizado correctamente y false si hay error
    */

    public static boolean contienePuntosDecimalesValidos(String cadena) {
        int puntoCount = 0; // Contador de puntos decimales en el número actual
        boolean dentroNumero = false; // Indica si estamos dentro de un número
        boolean despuesPunto = false; // Indica si el punto decimal ya ha sido encontrado en el número

        for (char c : cadena.toCharArray()) {
            if (Character.isDigit(c)) {
                dentroNumero = true;
            } else if (c == '.') {
                if (puntoCount > 0 || despuesPunto || !dentroNumero) {
                    return false; // Más de un punto decimal, punto decimal consecutivo o punto decimal al principio/final del número
                }
                puntoCount++;
                despuesPunto = true;
            } else {
                dentroNumero = false; // Reiniciar estado cuando se encuentra un carácter que no es un dígito
                puntoCount = 0;
                despuesPunto = false;
            }
        }

        return true;
    }
    
    /*
    Método estático que checa que los operadores sean validos. Implementa el método que revisa
    que sean operadores o no y checa que la forma dada sea funcional
    * @param cadena La expresion en forma infija
    * @return true si los operadores pueden resolver la ecuacion y false si no
    */
    public static boolean operadoresValidos (String cadena){
        boolean res=true;
        boolean valido=true;
        
        for (int i=0; i<cadena.length()-1 && valido; i++){
            char actual=cadena.charAt(i);
            char sig=cadena.charAt(i+1);
            if(esOperador(actual) && esOperador(sig)){
                valido=false;
                res=false;
            }
                
        }
        
        return res;
    }
    
    /*
    Método que verifica que el character dado es un operador funcional
    * @param c El character de la ecuacion que estaba de forma infija
    * @return true si c si es un operador funcional y false si no
    */    
    
    
    private static boolean esOperador(char c){
        return c =='+' || c =='-' || c =='*' || c =='/' || c =='^' ;
    }

    
    /* 
     * Método que recibe un String en notacion fija y la regresa en notación postfija
    * @param cadena La cadena expresion en forma infija
    * @return un arreglo de String en notacion postfija
    */

   
    public static String[] cadenaArr(String cadena) {
        String[] cadenaArr = new String[cadena.length()]; // Se podría ajustar dependiendo de la longitud final
        StringBuilder cad = new StringBuilder();
        int j = 0;
    
        for (int i = 0; i < cadena.length(); i++) {
           char c = cadena.charAt(i);
        
        if (c == '(' || c == ')') {
            if (cad.length() > 0) {
                cadenaArr[j] = cad.toString();
                j++;
                cad.setLength(0);
            }
            cadenaArr[j] = String.valueOf(c);
            j++;
        } else if (esOperador(c)) {
            if (cad.length() > 0) {
                cadenaArr[j] = cad.toString();
                j++;
                cad.setLength(0);
            }
            cadenaArr[j] = String.valueOf(c);
            j++;
        } else { // Caracteres que no son operadores ni paréntesis
            cad.append(c);
        }
    }
    
    // Agregar el último elemento de cad si no está vacío
    if (cad.length() > 0) {
        cadenaArr[j] = cad.toString();
        j++;
    }
    
    // Redimensionar el arreglo al tamaño adecuado
    return Arrays.copyOf(cadenaArr, j);
    }

    public static boolean jerarquia(String a, String b){
        boolean res=false;
        char ac=a.charAt(0);
        char ab=b.charAt(0);
        int acp=0, acb=0;
        
        switch(ac){
            case '^': 
                acp=4;
                break;
            case '*':
                acp=3;
                break;
            case '/': 
                acp=3;
                break;
            case '+': 
                acp=2;
                break;
            case '-': 
                acp=2;
                break;
        }
        switch(ab){
            case '^': 
                acb=4;
                break;
            case '*':
                acb=3;
                break;
            case '/': 
                acb=3;
                break;
            case '+': 
                acb=2;
                break;
            case '-': 
                acb=2;
                break;
        }
        
        if(acp>acb)
            res=true;
        
        return res;
    }
    
    public static String[] infijaAPostfija(String[] in){
        String[] post=new String[in.length];
        PilaADT op=new PilaA();
        int i=0, j=0;
        
        while(i<in.length && !in[i].isEmpty()){
            if (in[i].equals("("))
                op.push(in[i]);
            else
                if(in[i].equals(")")){
                    while(!"(".equals((String)op.peek())){
                        post[j]=(String)op.pop();
                        j++;    
                    }
                    op.pop();
                }
                else
                    if(!esOperador(in[i].charAt(0))){
                        post[j]=in[i];
                        j++;
                    }
                    else{
                        while(!op.isEmpty() && jerarquia((String)op.peek(),in[i])){
                            post[j]=(String)op.pop();
                            j++;
                        }
                        op.push(in[i]);
                    }
            i++;          
        }
        while(!op.isEmpty()){
            post[j]=(String)op.pop();
            j++;
        }
        return post;
    }
    
    
    /* Método que evalua evaluar una expresión dada en notación postfija.
     * 
     */
    
     public static double evaluarPostfija(String[] expresion) {
        PilaADT <Double> pila = new PilaA();

        for (int i = 0; i < expresion.length; i++) {
            String token = expresion[i];
            if (esOperando(token)) {
                // Si es un operando, lo agregamos a la pila convertido a double
                pila.push(Double.parseDouble(token));
            } else {
                // Si es un operador, realizamos la operación correspondiente
                double operand2 = pila.pop();
                double operand1 = pila.pop();
                double resultado = operar(operand1, operand2, token);
                // Agregamos el resultado de la operación a la pila
                pila.push(resultado);
            }
        }

        // El resultado final estará en la cima de la pila
        return pila.pop();
    }

    private static boolean esOperando(String token) {
        // Verifica si el token es un operando (número)
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double operar(double operand1, double operand2, String operador) {
        // Realiza la operación correspondiente y devuelve el resultado
        switch (operador) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            case "^":
                return Math.pow(operand1, operand2);
            default:
                throw new ExcepcionCollectionVacia("Operador no válido: " + operador);
        }
    }
     

}
