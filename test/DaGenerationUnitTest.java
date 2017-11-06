/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dagenerator.DaGenerator;
import dagenerator.DaReader;
import dagenerator.exceptions.ConditionCleanCodeException;
import dagenerator.exceptions.NotUniqueProgramException;
import dagenerator.models.CodeNode;
import dagenerator.utils.UnicodeBOMInputStream;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;



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
            FileInputStream fis = new FileInputStream("C:\\Users\\matth\\Dropbox\\DAGEnerator\\daSimulation-25-10.txt");
            UnicodeBOMInputStream ubis = new UnicodeBOMInputStream(fis);
            InputStreamReader isr = new InputStreamReader(ubis);
            BufferedReader br = new BufferedReader(isr);

            ubis.skipBOM();

            node = generator.parse(br);

            DaReader.toTXT(node);

        } catch (IOException ex) {
            Logger.getLogger(DaGenerationUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotUniqueProgramException ex) {
            Logger.getLogger(DaGenerationUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConditionCleanCodeException ex) {
            Logger.getLogger(DaGenerationUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
