package Magda.ProgramTree.Expressions;


public class CBinaryExpressionLeq extends CBinaryExpression
{	
	public CBinaryExpressionLeq (IExpression aMethodTarget, IExpression param)
	{ super( aMethodTarget, "Comparable", "leq", param);
	}

};