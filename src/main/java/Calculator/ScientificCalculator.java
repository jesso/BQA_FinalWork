package Calculator;

public class ScientificCalculator extends BaseClass 
{
	
		int square = 2 , cube =3;
	    public double squareNAddTwoNumbers(double num1, double num2) 
	    {
	        return (Math.pow(num1, square)) + (Math.pow(num2, square));
	    }


	    public double cubeNAddTwoNumbers(double num1, double num2)
	    {
	        return (Math.pow(num1, cube)) +( Math.pow(num2, cube));
	    }

}
