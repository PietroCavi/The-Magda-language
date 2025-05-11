package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;

import java.util.*;

public class CNewMethodDeclarations extends CProgramElemVector<INewMethodDeclaration>{
    
    private static final long serialVersionUID = 1L;
   	
    public void setMixin (CMixinDeclaration container){ 
        for (int i=0; i<size(); i++)
		    get(i).setMixin(container);
	}

}
