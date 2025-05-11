package Magda.ProgramTree;

import java.util.*;

public class CProgramElemVector<E extends IProgramElem> extends Vector<E> implements IProgramElem
{

    protected void printOne(java.io.PrintStream o, int i)
	{ get(i).print(o);
	}

    public void print(java.io.PrintStream o)
	{ for (int i=0; i<size() ; i++)
      printOne(o,i);
	}

}