package Magda.ProgramTree.Expressions;


public class CBinaryExpressionEqual extends CBinaryExpression
{	
	public CBinaryExpressionEqual (IExpression aMethodTarget, IExpression param)
	{ super( aMethodTarget, "Object", "equals", param);
	}

};