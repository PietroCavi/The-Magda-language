package Magda.ProgramTree.Expressions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;

public class CInitializationOfParam implements IProgramElem
{	
	public String MixinName;
	public String ParamName;
	public IExpression Expr;

	public CInitializationOfParam (String aMixinName, String aParamName, IExpression aExpr)
	{ MixinName = aMixinName;
	  ParamName = aParamName;
	  Expr = aExpr;
	}

        public void print(java.io.PrintStream o)
        { o.print(" "+MixinName+"."+ParamName+":=");
          Expr.print(o);
    	  o.print(",");
	}
        
        public String toString()
        { return MixinName+"."+ParamName+ (Expr!=null ? ":=" +Expr : "");
        }

        public void CheckTypes (CInstrEnvironment env)
	{ Expr.GetType(env).CheckIsSubTypeOf( env, env.getMixin(MixinName).getIniParamType(env, ParamName) );
	}

        public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target)
	{ int localTarget = h.getTemp();
          Expr.GenCode(o, env, h, localTarget);
	  o.println(h.tempAcc(target)+ ".getStateHolderByName(\""+ MixinName +"\", "+String.valueOf( env.getMixin(MixinName).getFieldParamOffset(ParamName) ) +").Value = "+ h.tempAcc(localTarget)+";");
	}


}