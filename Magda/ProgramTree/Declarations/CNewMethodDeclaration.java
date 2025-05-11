package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.ProgramTree.MixinExpressions.*;
import Magda.Compiler.*;

public class CNewMethodDeclaration extends CInterfaceMethodDeclaration
{

	CMethodBody Body;

	public CNewMethodDeclaration (IMixinExpression aResType, String aMethodName, CParameterDeclarations aPars, CMethodBody aBody, int aPosInCode, String aProgramFile)
	{ super( aResType, aMethodName, aPars, aPosInCode, aProgramFile);
	  Body = aBody;
	}

    public void print(java.io.PrintStream o)
	{ o.println(" new method "+MethodName+ "(");
	  ResType.print(o);
	  Pars.print(o);
	  o.println(")");
	  Body.print(o);
	}

    CInstrEnvironment BuildEnv (CMethodEnvironment env)
	{ return new CInstrEnvironment (env.Decls, env.CurrentMixin, Body.Decls, Pars, this);
	}

	public void CheckTypes(CMethodEnvironment env)
	{ super.CheckTypes(env);
	  Body.CheckTypes( BuildEnv(env) );
	}

    public void GenCode (java.io.PrintStream o, CMethodEnvironment env, CGenCodeHelper h)
    { 	Body.GenCode(o, BuildEnv(env), h, env.CurrentMixin.MixinName, MethodName, false);
    }
};