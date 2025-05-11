package Magda.ProgramTree.Instructions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.Declarations.CSourceInitializationParameters;
import Magda.ProgramTree.Expressions.*;


public class CIniModuleSuperInstruction extends CInstruction{
    
    private static final long serialVersionUID = 1L;
	
    CInitializationOfParams params;

	public CIniModuleSuperInstruction (CInitializationOfParams aparams){ 
        params = aparams;
	}

	public void CheckTypes (CInstrEnvironment env) throws CTypeError{  
        super.CheckTypes(env);
        CSourceInitializationParameters op = env.currentIniModule.OutputParams;
           
        for (int i=0; i<params.size();i++)
            if (!op.containsParam(params.get(i).MixinName, params.get(i).ParamName) )
                throw new CTypeError("Parameter "+params.get(i).MixinName+"."+ params.get(i).ParamName+" not an output parameter of iniModule");
        //
        for (int i=0; i<op.size(); i++)
            if (!params.containsParam(op.get(i).MixinName, op.get(i).ParName))
                throw new CTypeError("Parameter "+op.get(i).MixinName+"."+ op.get(i).ParName+" is an output parameter of iniModule, but was not supplied in super call");
        //
        for (int i=0; i<params.size(); i++){ 
            CType type1= params.get(i).Expr.GetType(env);
	        CType type2= env.getMixin(params.get(i).MixinName).getIniParamType(env, params.get(i).ParamName);
	        if (!type1.isSubTypeOf( env, type2))
 		        throw new CTypeError ("Cannot assign "+ type1+ " to "+type2 );
        }   
    }

	public void print(java.io.PrintStream o){ 
        o.print ("super [");
	    params.print(o);
	    o.println("];");
	}

	public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h){ 
        super.GenCode(o, env, h); 
	    o.println("if (ModulesToExecute.size() >0)");
        o.println(" { CMagdaIniModule mod = ModulesToExecute.remove(0); ");
	    
        int temp=-1;
	    for (int i=0; i<params.size(); i++){ 
            CInitializationOfParam param = params.get(i);
	        if (temp==-1)
                temp = h.getTemp();
	        param.Expr.GenCode(o, env, h, temp);
	        o.println("IniParams.putParamValue(\""+param.MixinName+"\",\""+param.ParamName+"\","+ h.tempAcc(temp)+");");
	    }
        
        o.println("  mod.Execute(aSelf, ModulesToExecute, IniParams);");
	    o.println("}");

        /*
	    int temp= h.getTemp();
	    Expr.GenCode(o,env,h, temp);
	    o.println("return "+ h.tempAcc(temp)+";");
        */
    }

}
