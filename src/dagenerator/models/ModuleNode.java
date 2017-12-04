/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator.models;

import dagenerator.utils.HtmlConstants;
import dagenerator.utils.TextConstants;

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
        this.name = name;
        input = inputString.split(",");
        output = outputString.split(",");
    }

    @Override
    public String toString() {
        return "Module : " + name + " with input params " + String.join(", ", input) + " and output params " + String.join(", ", output);
    }


    public String getUpLayer() {
        String upLayer = TextConstants.upper_left_module_angle;

        if(name == null)
            name = "Module Name";

        for(int i = 0; i<name.length()+4;i++){
            upLayer += TextConstants.repetitive_module_bar;
        }

        upLayer += TextConstants.upper_right_module_angle;

        if(input != null && input.length != 0){
            upLayer +=  TextConstants.io_module_arrow;
        }
        for(String in : input){
            upLayer += in + ", ";
        }

        upLayer = upLayer.substring(0, upLayer.length() - 2);

        return upLayer;
    }

    public String getMiddleLayer() {
        String middleLayer = TextConstants.vertical_bar + "  " + name + "  " + TextConstants.vertical_bar;

        return middleLayer;
    }

    public String getBottomLayer() {
        String bottomLayer = TextConstants.lower_left_module_angle;

        for(int i = 0; i<name.length()+4;i++){
            bottomLayer += TextConstants.repetitive_module_bar;
        }

        bottomLayer += TextConstants.lower_right_module_angle;

        if(output != null && output.length != 0){
            bottomLayer +=  TextConstants.io_module_arrow;
        }
        for(String out : output){
            bottomLayer += out + ", ";
        }

        bottomLayer = bottomLayer.substring(0, bottomLayer.length() - 2);

        return bottomLayer;
    }

    public String getHtmlUpLayer() {
        String upLayer = HtmlConstants.upper_left_module_angle;

        if(name == null)
            name = "Module Name";

        for(int i = 0; i<name.length()+4;i++){
            upLayer += HtmlConstants.repetitive_module_bar;
        }

        upLayer += HtmlConstants.upper_right_module_angle;

        if(input != null && input.length != 0){
            upLayer +=  String.format(HtmlConstants.io_module_arrow, Arrays.toString(input));
        }

        return upLayer;
    }

    public String getHtmlMiddleLayer() {
        String middleLayer = HtmlConstants.left_vertical_module_bar+ "  " + name + "  " + HtmlConstants.right_vertical_module_bar;

        return middleLayer;
    }

    public String getHtmlBottomLayer() {
        String bottomLayer = HtmlConstants.lower_left_module_angle;

        for(int i = 0; i<name.length()+4;i++){
            bottomLayer += HtmlConstants.repetitive_module_bar;
        }

        bottomLayer += HtmlConstants.lower_right_module_angle;

        if(output != null && output.length != 0){
            bottomLayer +=  String.format(HtmlConstants.io_module_arrow, Arrays.toString(output));
        }

        return bottomLayer;
    }
}
