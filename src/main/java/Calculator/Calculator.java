package Calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        double num1, num2, result;
        String choice = null;
        //BaseClass base = new BaseClass();
        ScientificCalculator sciCalc = new ScientificCalculator();
        TaxCalculator taxCalc = new TaxCalculator();
        Scanner sc = new Scanner(System.in);
        
        // Menu options for user
        System.out.println("Choose Calculator Type:");
        System.out.println("1. Scientific Calculator");
        System.out.println("2. Tax Calculator");
        int calculatorChoice = sc.nextInt();
        

        System.out.println("Java Calculator");

         do
        {
            try {
                System.out.print("Enter first number: ");
                num1 = sc.nextDouble();

                System.out.print("Enter second number: ");
                num2 = sc.nextDouble();

                System.out.print("Enter operator (+, -, *, /, 2Add, 3Add ) or type 'exit' to quit: ");
                choice = sc.next();

                if (choice.equalsIgnoreCase("exit"))
                {
                    System.out.println("Exiting the calculator...");
                    break;
                }

//               if(calculatorChoice == 1)
//               {
//            	   switch (choice) {
//                   case "+":
//                       result = base.addition(num1, num2);
//                       System.out.println("Result: " + result);
//                       break;
//                   case "-":
//                       result = base.subtract(num1, num2);
//                       System.out.println("Result: " + result);
//                       break;
//                   case "*":
//                       result = base.multiple(num1, num2);
//                       System.out.println("Result: " + result);
//                       break;
//                   case "/":
//                       result = base.divide(num1, num2);
//                       System.out.println("Result: " + result);
//                       break;
//                   default:
//                       throw new IllegalArgumentException("Invalid operator! Please try again.");
//            	   }
//               }
               else if(calculatorChoice == 1)
               {
            	   switch (choice) {
                   case "+":
                       result = sciCalc.addition(num1, num2);
                       System.out.println("Result: " + result);
                       break;
                   case "-":
                       result = sciCalc.subtract(num1, num2);
                       System.out.println("Result: " + result);
                       break;
                   case "*":
                       result = sciCalc.multiple(num1, num2);
                       System.out.println("Result: " + result);
                       break;
                   case "/":
                       try {
                           result = sciCalc.divide(num1, num2);
                       } catch (ArithmeticException e) {
                           System.err.println("Error: " + e.getMessage());
                       }
                       break;
                   case "2Add":
                       result = sciCalc.squareNAddTwoNumbers(num1, num2);
                       System.out.println("Result: " + result);
                       break;
                   case "3Add":
                       result = sciCalc.cubeNAddTwoNumbers(num1, num2);
                       System.out.println("Result: " + result);
                       break;
                   default:
                       System.err.println("Invalid operation.");
                       return;
               }
            	   
               }
               else if(calculatorChoice == 2)
               {
            	   switch (choice) {
                   case "+":
                       result = taxCalc.addition(num1, num2); 
                       System.out.println("Result: " + result);
                       break;
                   case "-":
                       result = taxCalc.subtract(num1, num2);
                       System.out.println("Result: " + result);
                       break;
                   case "*":
                       result = taxCalc.multiple(num1, num2);
                       System.out.println("Result: " + result);
                       break;
                   case "/":
                       try {
                           result = taxCalc.divide(num1, num2);
                           System.out.println("Result: " + result);
                       } catch (ArithmeticException e) {
                           System.err.println("Error: " + e.getMessage());
                       }
                       break;
                   case "squareNAddTwoNumbers":
                       System.out.println("Tax calculator does not support square operation.");
                       break;
                   case "cubeNAddTwoNumbers":
                       System.out.println("Tax calculator does not support cube operation.");
                       break;
                   default:
                       System.err.println("Invalid operation.");
                       return;
               }
           } else {
               System.out.println("Invalid calculator type.");
               return;
           }
                
                System.out.print("Do you want to perform another calculation? (Y/N): ");
                choice = sc.next();
                if(choice .equalsIgnoreCase("N"))
                {
                	System.out.println("Thanks for using calculator.");
                }
                
            } catch (ArithmeticException e) 
            {
                System.err.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e)
            {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e)
            {
                System.err.println("Invalid input! Please enter valid numbers.");
                sc.nextLine(); 
            }
        }
         while (choice.equalsIgnoreCase("Y"));
         sc.close();
    }
}
