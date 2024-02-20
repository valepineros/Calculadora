/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

import java.util.ArrayList;

/**
 *
 * @author valen
 */
public class PostFija {

    public static String[] CambiarAPostfija(String operacion) {
        ArrayList<String> elementos = new ArrayList<>();
        PilaADT<Character> pilaOp = new PilaA<>();

        for (int i = 0; i < operacion.length(); i++) {
            char caracter = operacion.charAt(i);

            if (Character.isDigit(caracter) || caracter == '.' || caracter == '!') {
                // Si es un dígito, un punto decimal o un signo de exclamación, agregamos el operando completo a elementos
                int j = i;
                StringBuilder operando = new StringBuilder();
                // Tratamos el dígito o el punto decimal normalmente
                while (j < operacion.length() && (Character.isDigit(operacion.charAt(j)) || operacion.charAt(j) == '.' || operacion.charAt(j) == '!')) {
                    operando.append(operacion.charAt(j));
                    j++;
                }
                // Movemos el índice 'i' al final del operando
                i = j - 1;
                elementos.add(operando.toString()); // Agregamos el operando al arreglo
            } else if (caracter == '(') {
                // Si es un paréntesis de apertura, lo agregamos a la pila de operadores
                pilaOp.push(caracter);
            } else if (caracter == ')') {
                // Si es un paréntesis de cierre, desapilamos los operadores de la pila y los agregamos a elementos
                while (!pilaOp.isEmpty() && pilaOp.peek() != '(') {
                    elementos.add(Character.toString(pilaOp.pop()));
                }
                // Quitamos el paréntesis de apertura de la pila
                pilaOp.pop();
            } else {
                // Si es un operador, desapilamos los operadores de la pila que tengan mayor o igual precedencia y los agregamos a elementos
                while (!pilaOp.isEmpty() && precedencia(pilaOp.peek()) >= precedencia(caracter)) {
                    elementos.add(Character.toString(pilaOp.pop()));
                }
                // Agregamos el operador actual a la pila de operadores
                pilaOp.push(caracter);
            }
        }

        // Desapilamos los operadores restantes de la pila y los agregamos a elementos
        while (!pilaOp.isEmpty()) {
            elementos.add(Character.toString(pilaOp.pop()));
        }

        // Convertimos el ArrayList a un arreglo de strings
        String[] resultado = new String[elementos.size()];
        resultado = elementos.toArray(resultado);

        return resultado;
    }
    
    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        } 
    }
    
    
}
