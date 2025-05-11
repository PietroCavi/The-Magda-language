package Magda.ProgramTree.MixinExpressions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;

public class CMixinExpressionVoid implements IMixinExpression
{	
	public CMixinExpressionVoid ()
	{ 
	}

	public CType GetType (CMethodEnvironment env)
	{ return new CType(false);
	}
        
        public CType GetNativeType (CMethodEnvironment env) throws CTypeError
        { return GetType(env);
        }

        public String toString()
        { return "void";
        }
        
	public void print(java.io.PrintStream o)
        { o.print(this);
	}

        public void GenCodeForMixinExpression (java.io.PrintStream o, CEnvironment env, CGenCodeHelper h)
	{ //
	}

};