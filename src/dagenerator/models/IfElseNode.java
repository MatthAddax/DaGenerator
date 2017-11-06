/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator.models;

/**
 *
 * @author Matthieu
 */
public class IfElseNode extends CodeNode{

    public IfElseNode(CodeNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "if_else_node";
    }
    
}
