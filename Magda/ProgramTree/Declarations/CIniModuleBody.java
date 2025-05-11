package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.Instructions.*;


public class CIniModuleBody extends CMemberBody
{

	public CIniModuleBody (CVariableDeclarations aDecls, CInstructions aInstrs)
	{ super (aDecls, aInstrs);
	}

        public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, String aMixinName, CSourceInitializationParameters InputParams)
       { 	o.println(" new CMagdaIniModule () { public void Execute(CMagdaObject aSelf, CMagdaIniModules ModulesToExecute, CMagdaIniParams IniParams) ");
                o.println(" { CMagdaObject[] params = new CMagdaObject["+InputParams.size() +"];  //preparing list of input parameters");
		for (int i=0; i<InputParams.size(); i++)
		{ o.println(" params["+i+"] = IniParams.removeParamValue(\""+InputParams.get(i).MixinName+"\",\""+InputParams.get(i).ParName+"\");");
		}
		super.GenCode(o, env, h, false);
		o.println(" // end of IniModule of "+aMixinName);
   }


};