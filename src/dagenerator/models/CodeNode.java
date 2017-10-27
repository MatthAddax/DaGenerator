/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matthieu
 */
public abstract class CodeNode{	

    private CodeNode parent;

    private List<CodeNode> childs;

    public CodeNode(CodeNode parent){
            this.parent = parent;
    }

    public List<CodeNode> getChilds(){
            return childs;
    }

    public void addChild(CodeNode child){
        if(childs==null)
            childs = new ArrayList();
        
        childs.add(child);
    }

    public void removeChild(int position){
            childs.remove(position);
    }

    public void setChilds(List<CodeNode> childs){
            this.childs = childs;
    }

    public CodeNode getParent() {
        return parent;
    }

    public void setParent(CodeNode parent) {
        this.parent = parent;
    }

    
    
    @Override
    public abstract String toString();
}