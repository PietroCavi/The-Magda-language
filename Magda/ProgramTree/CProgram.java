package Magda.ProgramTree;

import Magda.Compiler.*;
import Magda.ProgramTree.Declarations.*;
import Magda.ProgramTree.Instructions.*;

public class CProgram implements IProgramElem
{
  public CGlobalDeclarations Decls;
  public CInstructions Instrs;

  public CProgram(CGlobalDeclarations aDecls, CInstructions aInstrs)
  { Decls = aDecls;
    Instrs = aInstrs;
  }

  public void print(java.io.PrintStream o)
  { Decls.print(o);
    Instrs.print(o);
  }

  public void CheckTypes()
  { Decls.CheckTypes ();
    Instrs.CheckTypes( new CInstrEnvironment(Decls, null, new CVariableDeclarations(), new CParameterDeclarations())  );
  }

  public void GenCode (java.io.PrintStream o, CGenCodeHelper h)
  { o.println(" //----------------------------------------------------------- Begining of generated code");
    o.println(" package Magda.CompiledCode;");
    o.println(" import Magda.mtj.*;");
          o.println(" public class MagdaProgram{ ");
    o.println("   static int MagdaLineNo=0; static String MagdaProgramFile; ");
          o.println(" public static void main(String args[]){ ");
          o.println(" try{ CMagdaMixin curMixin=null;");
    Decls.GenCode(o, h);
    o.println();
    o.println(" /* -----begining of main program instructions */ ");
          o.println(" CMagdaObject[] temp = new CMagdaObject[100];");
          Instrs.GenCode(o,
          new CInstrEnvironment(Decls, null, new CVariableDeclarations(), new CParameterDeclarations()),
         h );
    o.println("} catch (Exception e)");
    o.println("{ System.err.println(\"\\nError in Magda program at line:\"+String.valueOf(MagdaLineNo)+\" of file \"+ MagdaProgramFile +\":\"); ");
    o.println("  System.err.println( e.toString() ); ");
    o.println("  e.printStackTrace( System.err ); ");
    o.println("}");
    o.println("}}");

  }
};