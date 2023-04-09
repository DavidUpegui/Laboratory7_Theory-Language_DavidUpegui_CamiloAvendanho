package codigo;

import java.util.Stack;

public class CalculadoraStrings {

    public static void main(String[] args) throws Exception {
        String expresion = "1*(90/3+4)*2";
        int resultado = evaluar(expresion);
        System.out.println("El resultado de la expresion " + expresion + " es: " + resultado);
    }

    public static int evaluar(String expresion) throws BadExpresionException {
        try {
            validateParenthesis(expresion);
            Stack<Integer> pilaNumeros = new Stack<>();
            Stack<Character> pilaOperadores = new Stack<>();

            for (int i = 0; i < expresion.length(); i++) {
                char caracter = expresion.charAt(i);

                if (Character.isDigit(caracter)) {
                    int numero = 0;
                    while (i < expresion.length() && Character.isDigit(expresion.charAt(i))) {
                        numero = numero * 10 + Character.getNumericValue(expresion.charAt(i));
                        i++;
                    }
                    i--;
                    pilaNumeros.push(numero);
                } else if (caracter == '(') {
                    pilaOperadores.push(caracter);
                } else if (caracter == ')') {
                    while (pilaOperadores.peek() != '(') {
                        int resultadoParcial = aplicarOperador(pilaNumeros, pilaOperadores.pop());
                        pilaNumeros.push(resultadoParcial);
                    }
                    pilaOperadores.pop();
                } else if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') {
                    while (!pilaOperadores.empty() && prioridadOperador(pilaOperadores.peek()) >= prioridadOperador(caracter)) {
                        int resultadoParcial = aplicarOperador(pilaNumeros, pilaOperadores.pop());
                        pilaNumeros.push(resultadoParcial);
                    }
                    pilaOperadores.push(caracter);
                }
            }
            while (!pilaOperadores.empty()) {
                int resultadoParcial = aplicarOperador(pilaNumeros, pilaOperadores.pop());
                pilaNumeros.push(resultadoParcial);
            }

            return pilaNumeros.pop();
        } catch (ArithmeticException | BadExpresionException e) {
            throw e;

        } catch (Exception e) {
            throw new BadExpresionException();
        }
    }

    public static int aplicarOperador(Stack<Integer> pilaNumeros, char operador) {
        int numero2 = pilaNumeros.pop();
        int numero1 = pilaNumeros.pop();
        int resultado = 0;
        switch (operador) {
            case '+':
                resultado = numero1 + numero2;
                break;
            case '-':
                resultado = numero1 - numero2;
                break;
            case '*':
                resultado = numero1 * numero2;
                break;
            case '/':
                try {
                resultado = numero1 / numero2;
                break;
            } catch (ArithmeticException e) {
                throw e;
            }

        }
        return resultado;
    }

    public static int prioridadOperador(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    public static void validateParenthesis(String str) throws BadExpresionException {
        int parenthesis = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                parenthesis++;
            } else if (c == ')') {
                parenthesis--;
            }
        }
        if (parenthesis != 0) {
            System.out.println("Tiró el error de parentesis");
            throw new BadExpresionException();
        }
    }

}
