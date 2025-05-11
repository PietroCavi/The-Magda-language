package Magda.ProgramTree.LValues;
import Magda.ProgramTree.*;
import Magda.Compiler.*;

public class CFieldLValue implements ILValue{
    
    private static final long serialVersionUID = 1L;
        
    String MixinName, FieldName;

    public CFieldLValue (String aMixinName, String aFieldName){ 
        MixinName = aMixinName;
        FieldName = aFieldName;
    }

    public CType GetType (CInstrEnvironment env){ 
        if (!env.CurrentMixin.GetType(env).isSubTypeOf ( env, CType.createCType(env, env.getMixin(MixinName) )) )
  	        throw new CTypeError("Current object doesn't contain a "+MixinName+" layer");
	    //
	    return env.getMixin(MixinName).getFieldParamType(env, FieldName);
    }

    public void print(java.io.PrintStream o){ 
        o.print("this."+MixinName+"."+ FieldName);
    }

	public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h){ 
        int place = env.getMixin(MixinName).getFieldParamOffset(FieldName);
        o.print("aSelf.getStateHolderByName(\""+MixinName+"\","+String.valueOf(place)+").Value ");
	}
};
