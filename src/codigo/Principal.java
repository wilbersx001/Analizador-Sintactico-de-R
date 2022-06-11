/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Charly Ponce
 */
public class Principal {
    public static void main(String[] args) throws Exception {
        System.out.println (new File ("").getAbsolutePath ());
        String rutap = new File ("").getAbsolutePath ()+"/";
        String ruta1 = rutap +"src/codigo/Lexer.flex";
        String ruta2 = rutap+"src/codigo/LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", rutap+"src/codigo/Sintax.cup"};
        generar(ruta1, ruta2, rutaS);
    }
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        System.out.println (new File (".").getAbsolutePath ());
        String rutap = new File ("").getAbsolutePath ()+"/";
        Path rutaSym = Paths.get(rutap+"src/codigo/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get(rutap+"sym.java"), 
                Paths.get(rutap+"src/codigo/sym.java")
        );
        Path rutaSin = Paths.get(rutap+"src/codigo/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get(rutap+"Sintax.java"), 
                Paths.get(rutap+"src/codigo/Sintax.java")
        );
    }
}
