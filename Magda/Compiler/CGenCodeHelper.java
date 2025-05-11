package Magda.Compiler;

public class CGenCodeHelper
{

	int tempCnt=0;

	public void resetTemp()
	{ tempCnt=0;
	}

	public int getTemp()
	{ return tempCnt++;
	}

	public String tempAcc(int tempNo)
	{ return "temp["+String.valueOf (tempNo)+"]";
	}
};