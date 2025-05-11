package Magda.ProgramTree.Declarations;

import Magda.ProgramTree.*;
import Magda.Compiler.*;

public class CGlobalDeclarations extends CProgramElemVector<IDeclaration>
{

    public void CheckTypes()
	{ CEnvironment env = new CEnvironment(this);
	  for (int i=0; i<size(); i++)
		{ get(i).CheckTypes(env);
		}
	}

	public void GenCode (java.io.PrintStream o, CGenCodeHelper h)
	{	CEnvironment env = new CEnvironment(this);
		for (int i=0; i<size(); i++)
		{ get(i).GenCode(o, env, h);
		}
	}

};