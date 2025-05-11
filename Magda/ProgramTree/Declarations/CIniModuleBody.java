package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.Instructions.*;


public class CIniModuleBody extends CMemberBody{
    
    private static final long serialVersionUID = 1L;

	public CIniModuleBody (CVariableDeclarations aDecls, CInstructions aInstrs){ 
        super (aDecls, aInstrs);
	}

    public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, String aMixinName, CSourceInitializationParameters InputParams){ 	
        o.println(" (aSelf, ModulesToExecute, IniParams) -> ");
        o.println(" { CMagdaObject[] params = new CMagdaObject["+InputParams.size() +"];  //preparing list of input parameters");
		
        for (int i=0; i<InputParams.size(); i++){ 
            o.println(" params["+i+"] = IniParams.removeParamValue(\""+InputParams.get(i).MixinName+"\",\""+InputParams.get(i).ParName+"\");");
		}
		
        super.GenCode(o, env, h, false);
		o.println("};");
        o.println(" // end of IniModule of "+aMixinName);
    }


};
