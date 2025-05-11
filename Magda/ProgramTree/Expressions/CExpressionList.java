package Magda.ProgramTree.Expressions;
import Magda.ProgramTree.*;

import java.util.*;

public class CExpressionList extends CProgramElemVector<IExpression>
{  
	public void printOne(java.io.PrintStream o, int i)
	{ super.printOne(o,i);
	  if (i != (size()-1) )
	    o.print(",");
	}

   public void GenCodeForParams (java.io.PrintStream o, Magda.Compiler.CInstrEnvironment env, Magda.Compiler.CGenCodeHelper h)
   { String temp2="{ CMagdaObject[] ParamsToPass = {";
      for (int i=0; i<size(); i++)
      { int TargetLoc = h.getTemp();
        temp2 = temp2+(i>0?",":"")+h.tempAcc(TargetLoc);
        get(i).GenCode(o, env, h, TargetLoc);
      }
      o.println(temp2+"};");

   }

};