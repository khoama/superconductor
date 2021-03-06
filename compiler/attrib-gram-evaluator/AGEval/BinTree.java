package AGEval;

import java.util.ArrayList;

public class BinTree {

	/**
	 * @param args
	 * @throws InvalidGrammarException 
	 */
	public static void main(String[] args) throws InvalidGrammarException {
		// TODO Auto-generated method stub

		
        IFace Start = new IFace("Start");
        Start.addField("w", "int");
        Start.addField("x", "int");
        Start.addField("y", "int");
        Start.addAttribute("h", "int");
        Start.addPassiveField("align", "string");
        Start.addPassiveField("bgColor", "string");
        Start.addPassiveField("borderColor", "string");
        Start.addPassiveField("borderWidth", "int", "1");
        Start.addPassiveField("clipsChildren", "bool");
        Start.addPassiveField("clipsSelf", "bool");
        Start.addPassiveField("lgradPCs", "string");
        Start.addPassiveField("lgradStart", "string");
        Start.addPassiveField("lgradStop", "string");
        Start.addPassiveField("opacity", "float", "1.0f");
        Start.addPassiveField("rgradCenter", "string");
        Start.addPassiveField("rgradFP", "string");
        Start.addPassiveField("rgradPCs", "string");
        Start.addPassiveField("rgradRadius", "string");
        Start.addPassiveField("visible", "boolean", "true");
        Start.addPassiveField("z", "int");
        Start.addPassiveField("cornerxRadius","float");
        Start.addPassiveField("corneryRadius","float");
        Start.addPassiveField("image","string");

        IFace TreeNode = new IFace("TreeNode");
        TreeNode.addField("h", "int");
        TreeNode.addAttributesOfType("int", "fringeSize", "x", "y", "w", "subtreeh");
        TreeNode.addPassiveField("align", "string");
        TreeNode.addPassiveField("bgColor", "string");
        TreeNode.addPassiveField("bold", "boolean");
        TreeNode.addPassiveField("borderColor", "string");
        TreeNode.addPassiveField("borderWidth", "int", "1");
        TreeNode.addPassiveField("clipsChildren","bool");
        TreeNode.addPassiveField("clipsSelf","bool");
        TreeNode.addPassiveField("font", "string");
        TreeNode.addPassiveField("italic", "boolean");
        TreeNode.addPassiveField("lgradPCs", "string");
        TreeNode.addPassiveField("lgradStart", "string");
        TreeNode.addPassiveField("lgradSNode", "string");
        TreeNode.addPassiveField("opacity", "float", "1.0f");
        TreeNode.addPassiveField("rgradCenter", "string");
        TreeNode.addPassiveField("rgradFP", "string");
        TreeNode.addPassiveField("rgradPCs", "string");
        TreeNode.addPassiveField("rgradRadius", "string");
        TreeNode.addPassiveField("shape", "string");
        TreeNode.addPassiveField("text", "string");
        TreeNode.addPassiveField("textColor", "string");
        TreeNode.addPassiveField("textWeight", "int", "50");     
        TreeNode.addPassiveField("underline", "boolean");
        TreeNode.addPassiveField("visible", "boolean", "true");  
        TreeNode.addPassiveField("z", "int");
        TreeNode.addPassiveField("cornerxRadius","float");
        TreeNode.addPassiveField("corneryRadius","float");
        TreeNode.addPassiveField("fontSize","int");
        TreeNode.addPassiveField("image","string");
        
        IFace EdgeFace = new IFace("EdgeFace");
        EdgeFace.addPassiveField("shape", "string", "\"edge\"");
        EdgeFace.addPassiveField("borderWidth", "int", "1");
        EdgeFace.addAttributesOfType("int", "x", "y", "w", "h");

        Class Top = new Class("Top", Start);
        Top.addChild("root", TreeNode);
        Top.set("root@x", "x");
        Top.set("root@y", "y");
        Top.set("root@w", "w");
        Top.set("h", "root@subtreeh");

        Class Binary = new Class("Binary", TreeNode);
        Binary.addField("hstep", "int");
        Binary.addAttributesOfType("int", "split", "toBot", "lefth", "righth", "bottomY", "leftoffset", "rightoffset", "center", "lCenter", "rCenter", "lefty", "righty");
        Binary.addChild("left", TreeNode);
        Binary.addChild("right", TreeNode);
        Binary.addChild("lEdge", EdgeFace);
        Binary.addChild("rEdge", EdgeFace);
        Binary.set("lefth", "left@h");
        Binary.set("righth", "right@h");
        Binary.apply("sum", "bottomY", "y", "h");
        Binary.apply("bin_tobot", "toBot", "bottomY", "hstep", "left@h", "right@h");
        Binary.apply("sub", "left@y", "toBot", "lefth");
        Binary.apply("sub", "right@y", "toBot", "righth");
        Binary.apply("sub", "leftoffset", "left@y", "bottomY");
        Binary.apply("sub", "rightoffset", "right@y", "bottomY");
        Binary.apply("bin_subtreeh", "subtreeh", "left@subtreeh", "leftoffset", "right@subtreeh", "rightoffset", "hstep", "h");
        Binary.apply("sum", "fringeSize", "left@fringeSize", "right@fringeSize");
        Binary.apply("bin_split", "split", "w", "left@fringeSize", "fringeSize");
        Binary.set("left@x", "x");
        Binary.apply("sum", "right@x", "x", "split");
        Binary.set("left@w", "split");
        Binary.apply("sub", "right@w", "w", "split");
        Binary.apply("bin_center", "center", "x", "w");
        Binary.apply("bin_center", "lCenter", "left@x", "left@w");
        Binary.apply("bin_center", "rCenter", "right@x", "right@w");
        Binary.setAllTo("center", "lEdge@x", "rEdge@x");
        Binary.setAllTo("bottomY", "lEdge@y", "rEdge@y");
        Binary.set("lefty", "left@y");
        Binary.set("righty", "right@y");
        Binary.set("lEdge@w", "lCenter");
        Binary.set("lEdge@h", "lefty");
        Binary.set("rEdge@w", "rCenter");
        Binary.set("rEdge@h", "righty");

        Class Leaf = new Class("Leaf", TreeNode);
        Leaf.addField("one", "int", "1");
        Leaf.set("fringeSize", "one");
        Leaf.set("subtreeh", "h");
        
        Class Edge = new Class("Edge", EdgeFace);
        Edge.addPassiveField("visible", "boolean", "true");

        ArrayList<IFace> inter = new ArrayList<IFace>();
        ArrayList<Class> classes = new ArrayList<Class>();
        inter.add(Start); inter.add(TreeNode); inter.add(EdgeFace);
        classes.add(Top); classes.add(Binary); classes.add(Leaf); classes.add(Edge);
        
        /*
         * actually call
         */
        
        
        
        AttributeGrammar grm = new AttributeGrammar("binTree", 
        		"/Users/lmeyerov/Research/parallelbrowser/bbbrowser/projects/osqDemo/binTree/", "QtAleNode", "QtTree", classes, inter, false, false);
        grm.run(false);
	}

}
