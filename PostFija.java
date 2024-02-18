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

    public static String CambiarAPostfija(String operacion) {
        PilaADT<Character> pilaOp = new PilaA<>();
        StringBuilder sb = new StringBuilder();
        ArrayList<Character> operandos = new ArrayList<>();

        for (int i = 0; i < operacion.length(); i++) {
            char caracter = operacion.charAt(i);

            if (Character.isDigit(caracter) || caracter == '.' || caracter == '!') {
                // Si es un dígito, un punto decimal o un signo de exclamación, agregamos el operando completo a sb
                int j = i;
                // Tratamos el dígito o el punto decimal normalmente
                while (j < operacion.length() && (Character.isDigit(operacion.charAt(j)) || operacion.charAt(j) == '.' || operacion.charAt(j) == '!')) {
                    sb.append(operacion.charAt(j));
                    j++;
                }
                // Movemos el índice 'i' al final del operando
                i = j - 1;
                sb.append(" "); // Añadimos un espacio al final para separar los operandos
            } else {
                // Si es un operador o paréntesis, lo procesamos como antes
                if (caracter == '(') {
                    pilaOp.push(caracter);
                } else if (caracter == ')') {
                    while (!pilaOp.isEmpty() && pilaOp.peek() != '(') {
                        sb.append(pilaOp.pop());
                        sb.append(" "); // Añadimos un espacio entre operadores
                    }
                    pilaOp.pop(); // Quitar el paréntesis de apertura de la pila
                } else {
                    while (!pilaOp.isEmpty() && precedencia(pilaOp.peek()) >= precedencia(caracter)) {
                        sb.append(pilaOp.pop());
                        sb.append(" "); // Añadimos un espacio entre operadores
                    }
                    pilaOp.push(caracter);
                }
            }
        }

        // Concatenamos los operandos acumulados al resultado final
        for (char c : operandos) {
            sb.append(c);
            sb.append(" "); // Añadimos un espacio entre operandos
        }

        // Procesamos cualquier operador restante en la pila
        while (!pilaOp.isEmpty()) {
            sb.append(pilaOp.pop());
            sb.append(" "); // Añadimos un espacio entre operadores
        }

        return sb.toString();
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
