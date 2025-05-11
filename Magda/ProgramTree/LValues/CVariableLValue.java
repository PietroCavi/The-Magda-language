package Magda.ProgramTree.LValues;
import Magda.ProgramTree.*;
import Magda.Compiler.*;

public class CVariableLValue implements ILValue
{ String VarName;
  
  public CVariableLValue (String aVarName)
  { VarName = aVarName;
  }
  
  public CType GetType (CInstrEnvironment env)
  { CType res = env.findParamOrVariableType(VarName);
    if (res == null)
      throw new Error ("Identifier "+VarName+" not declared ");
    return res;
    
  }

	public void print(java.io.PrintStream o)
	{ o.print(VarName);
	}

   public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h)
   {  int i = env.getVariableOffset(VarName);
	   if (i>=0)
	   { o.println("localVars["+ String.valueOf(i)+"]");
	   } else
	   { i  = env.getParameterOffset(VarName);
		 if (i<0)
		   throw new Error ("Identifier "+VarName+" not declared ");
		 o.println("params["+ String.valueOf(i)+"]");
	   }

   }
};