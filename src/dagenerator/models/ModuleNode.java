/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator.models;

import java.util.Arrays;

/**
 *
 * @author Matthieu
 */
public class ModuleNode extends CodeNode{
    private String name;
    private String[] input;
    private String[] output;

    public ModuleNode(CodeNode parent, String name, String inputString, String outputString) {
        super(parent);
        
        input = inputString.split(",");
        output = outputString.split(",");
    }

    @Override
    public String toString() {
        return "Module : " + name + " with input params " + String.join(", ", input) + " and output params " + String.join(", ", output);
    }
    
    
}
