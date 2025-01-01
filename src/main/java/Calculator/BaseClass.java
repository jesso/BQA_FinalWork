package Calculator;

public class BaseClass {
	
	public  double addition(double num1 , double num2)
	{
		return num1+num2;
	}
	
	public  double subtract(double num1 , double num2)
	{
		return num1-num2;
	}
	
	public  double multiple(double num1 , double num2)
	{
		return num1 * num2;
	}
	
	public  double divide(double num1, double num2)
	{
		if (num2 == 0)
		{
		 throw new ArithmeticException("Error! Number not divisible by Zero");
		}
		return num1 / num2;
	}
}
