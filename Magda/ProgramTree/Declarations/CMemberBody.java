package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.Instructions.*;


// the BODY of a Method or a Initialization module

public class CMemberBody implements IProgramElem
{

    CVariableDeclarations Decls;
	CInstructions Instrs;

	public CMemberBody (CVariableDeclarations aDecls, CInstructions aInstrs)
	{ Decls = aDecls;
  	  Instrs = aInstrs;
	}

    public void print(java.io.PrintStream o)
	{ Decls.print(o);
	  o.println("");
	  o.println("begin");
	  Instrs.print(o);
	  o.println("end;");
	}

	public void CheckTypes(CInstrEnvironment env)
	{ Instrs.CheckTypes(env);
	  Decls.CheckTypes(env);
	}
	

       public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, boolean returnNull)
       {o.println(" CMagdaObject[] temp = new CMagdaObject[100];");
        o.println(" CMagdaObject[] localVars = new CMagdaObject ["+String.valueOf(Decls.size()) + "];");          
        o.println(" String OldProgramFile = MagdaProgramFile; int OldMagdaLineNo =  MagdaLineNo; try { ");
        Instrs.GenCode( o, env, h);
        o.println(" } finally { MagdaProgramFile= OldProgramFile; MagdaLineNo= OldMagdaLineNo; } ");
        if (returnNull)
          o.println(" return null;");
        o.println(" }; };");
   }


};