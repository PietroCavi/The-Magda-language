package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.ProgramTree.MixinExpressions.*;
import Magda.Compiler.*;

public class CLetDeclaration implements IDeclaration
{ 
  public String varName; 
  public IMixinExpression boundExpr;
  public CLetDeclaration (String avarName, IMixinExpression aboundExpr)
  { varName = avarName;
    boundExpr = aboundExpr;
  }

  public void print(java.io.PrintStream o)
  { o.print("Let "+varName+ "= (" );
    boundExpr.print(o);
    o.println(")");
  }

  public CType GetType (CMethodEnvironment env)
  { return boundExpr.GetType(env);
  }
  
  public CType GetNativeType (CMethodEnvironment env)
  { return boundExpr.GetNativeType(env);
  }
        


  public void CheckTypes(CEnvironment env)
  { //nothing
  }
  public void GenCode (java.io.PrintStream o, CEnvironment env, CGenCodeHelper h)
  {//nothing
  }


  public String getName()
	{ return varName;
	}

  public void GenCodeForMixinExpression (java.io.PrintStream o, CEnvironment env, CGenCodeHelper h)
	{ boundExpr.GenCodeForMixinExpression(o, env, h);
	}

}