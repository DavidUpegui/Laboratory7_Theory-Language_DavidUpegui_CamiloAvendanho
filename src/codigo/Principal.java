/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;

/**
 *
 * @author Charly Ponce
 */
public class Principal {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\PC\\Documents\\Universidad\\6_Semestre_20231 (01-02-23)\\Teoría de Lenguajes y Laboratorio\\Laboratorio7\\AnalizadorLexico\\src\\codigo\\Lexer.flex";
        generarLexer(ruta);
    }
 
    public static void generarLexer(String ruta){
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    }
}
