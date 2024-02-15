/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

/**
 *
 * @author valen
 */
public interface Pila <T> {
    public void push(T dato);
    public T pop();
    public boolean isEmpty();
    public T peek();
}
