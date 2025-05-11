package Magda.Compiler;

import java.util.*;
import Magda.ProgramTree.Declarations.*;

public class CPolyApplicationValues extends Vector<CPolyApplicationValue>
{

	public int indexOfParam (CPolymorphismParam aParam)
	{
		for (int i=0; i<size(); i++)
		  if (get(i).param == aParam)
		    return i;
		return -1;
	}
};