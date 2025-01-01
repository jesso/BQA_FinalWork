package Calculator;

public class TaxCalculator extends BaseClass{
	
	private static final double TAX_RATE = 0.13;

    @Override
    public double addition(double num1, double num2) {
        double result = num1 + num2;
        return result + (result * TAX_RATE);
    }

    @Override
    public double subtract(double num1, double num2) {
        double result = num1 - num2;
        return result + (result * TAX_RATE);
    }

    public double addition(double num1, double num2, boolean isFloat) {
        if (isFloat) {
            return num1 + num2;
        }
        return addition(num1, num2);
    }
}
