package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.Instructions.*;


public class CMethodBody extends CMemberBody{
    
    private static final long serialVersionUID = 1L;

	public CMethodBody (CVariableDeclarations aDecls, CInstructions aInstrs){ 
        super (aDecls, aInstrs);
	}

	

    public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, String aMixinName, String aMethodName, boolean override){ 
        o.println("// start of declaration of method "+aMixinName+"."+aMethodName);
        o.println("{ int offset = AllSequence.getMixinOffsetByName(\"" + aMixinName + "\")+"+String.valueOf(env.getMixin(aMixinName).getMethodOffset(aMethodName)) +";");
        
        if (override){
            o.println(" aObjectBody[offset] = new CMagdaMethodOverloaded ( (CMagdaMethod) aObjectBody[offset]) {");
            
            o.println(" public CMagdaObject Execute(CMagdaObject aSelf, CMagdaObject[] params) { ");
		    super.GenCode(o, env, h, true);
		    o.println(" }; // end of declaration of method "+aMixinName+"."+aMethodName);
	    }
        else{
            o.println(" aObjectBody[offset] = (CMagdaMethod)(CMagdaObject aSelf, CMagdaObject[] params) -> {");
		    super.GenCode(o, env, h, true);
        }
    }


};
