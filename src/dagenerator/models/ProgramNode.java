/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator.models;

public class ProgramNode extends CodeNode{
    private String name;

    public ProgramNode(String name){
            super(null);
            this.name = name;
    }

    private String getName(){
            return name;
    }

    private void setName(String name){
            this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}