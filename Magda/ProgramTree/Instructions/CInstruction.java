package Magda.ProgramTree.Instructions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;


public abstract class CInstruction implements IInstruction{
    
    private static final long serialVersionUID = 1L;

    public int PosInProgram;
    public String ProgramFile;

    public void CheckTypes (CInstrEnvironment env){ 
        CTypeError.LineNo = PosInProgram;
        CTypeError.ProgramFile = ProgramFile;
    }

    public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h){ 
        o.println("MagdaLineNo= "+String.valueOf(PosInProgram)+";  MagdaProgramFile = \""+ ProgramFile +"\"; // Position in source: "+ProgramFile+"."+String.valueOf(PosInProgram) );
        h.resetTemp();
    }

}
