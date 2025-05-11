package Magda.ProgramTree.Declarations;

import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.MixinExpressions.*;

public class CAbstractMethodDeclaration extends CInterfaceMethodDeclaration
{



	public CAbstractMethodDeclaration (IMixinExpression aResType, String aMethodName, CParameterDeclarations aPars, int aPosInCode, String aProgramFile)
	{ super( aResType, aMethodName, aPars, aPosInCode, aProgramFile);
	}

	
    public void print(java.io.PrintStream o)
	{ o.println(" abstract method ");
	  ResType.print(o);
	  o.print(MethodName+ "(");
      Pars.print(o);
	  o.println(");");
	}



    public void GenCode (java.io.PrintStream o, CMethodEnvironment env, CGenCodeHelper h)
	{ o.println("//abstract method "+MethodName);
    }


};