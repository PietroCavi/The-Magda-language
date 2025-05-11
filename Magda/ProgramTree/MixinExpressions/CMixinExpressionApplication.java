package Magda.ProgramTree.MixinExpressions;

import Magda.Compiler.*;
import Magda.ProgramTree.Declarations.*;

public class CMixinExpressionApplication implements IMixinExpression
{  
	String MixinName;
	String ParamName;
	IMixinExpression value;

	 public CMixinExpressionApplication (String aMixinName, String aParamName, IMixinExpression avalue)
	{ MixinName = aMixinName;
	  ParamName = aParamName;
	  value = avalue;
	}

	public CType GetType (CMethodEnvironment env)
	{ CType Val = value.GetType(env);
	  CPolymorphismParam par = env.getMixin(MixinName).polyPars.findByName(ParamName);
	  //
	  if (!Val.isSubTypeOf(env, par.GetBoundingType(env)))
		throw new CTypeError ("Polymporphism param not within bounds");
	  //
	  return new CType(env, par, Val );
	}
        
        public CType GetNativeType (CMethodEnvironment env) throws CTypeError
        { return new CType(false);
        }

        public String toString()
        { return "with "+MixinName+"."+ParamName + " := "+ value;
        }
        
	public void print(java.io.PrintStream o)
        { o.print(this);
	}

    public void GenCodeForMixinExpression (java.io.PrintStream o, CEnvironment env, CGenCodeHelper h)
	{ //
	}

   
};