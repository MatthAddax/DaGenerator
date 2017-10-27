/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator.models;

public class LineNode extends CodeNode{
    private String line;
	
    public LineNode(CodeNode parent, String line){
            super(parent);
            setLine(line);
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    
    @Override
    public String toString() {
        return line;
    }
}