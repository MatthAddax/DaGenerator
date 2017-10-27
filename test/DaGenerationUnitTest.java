/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dagenerator.DaGenerator;
import dagenerator.exceptions.ConditionCleanCodeException;
import dagenerator.exceptions.NotUniqueProgramException;
import dagenerator.models.CodeNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthieu
 */
public class DaGenerationUnitTest {
    CodeNode node;
    DaGenerator generator;
    public DaGenerationUnitTest() {
        generator = new DaGenerator();
    }
    
    @Test
    public void GoodDaTesting(){
        try {
            node = generator.parse(readFile("C:\\Users\\Matthieu\\Dropbox\\DAGEnerator\\daSimulation-25-10.txt", Charset.defaultCharset()));
            
            System.out.println("NODE : " + node);
            /**
             L'arbre foire, voir comment retourner au parent originel au début et s'assurer d'avoir tous les enfants à chaque fois
            */
        } catch (IOException ex) {
            Logger.getLogger(DaGenerationUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotUniqueProgramException ex) {
            Logger.getLogger(DaGenerationUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConditionCleanCodeException ex) {
            Logger.getLogger(DaGenerationUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static String readFile(String path, Charset encoding) throws IOException 
    {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded, encoding);
    }
}
