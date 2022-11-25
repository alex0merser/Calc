import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var sc = new Scanner(System.in);
        var expression = sc.nextLine();
        System.out.println(calc(expression));
        sc.close();
    }
    public static String calc(String input) {
        var allItems = new AllItems();

        var arrayInput = input.split(" ");

        if (arrayInput.length != 3){
            throw new RuntimeException();
        }

        var isOperator = allItems.fitOperator(arrayInput[1]);
        var operand1 = allItems.numOrRimNum(arrayInput[0]);
        var operand2 = allItems.numOrRimNum(arrayInput[2]);

        var operator = allItems.getOperator(arrayInput[1]);
        var op1 = arrayInput[0];
        var op2 = arrayInput[2];

        if (allItems.whatSolution(operand1, isOperator, operand2).equals("nums")){
            return allItems.numsSolver(op1, operator, op2);
        } else if (allItems.whatSolution(operand1, isOperator, operand2).equals("rimNums")) {
            var num = allItems.rimNumsSolver(op1, operator, op2);
            return allItems.convertToRim(num);
        }else{
            throw new RuntimeException();
        }
    }
}

class AllItems{
    String[] nums = { "NaN", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    String[] rimNums = { "NaN", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
    String[] rimTenNums = { "NaN", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C" };
    String[] operators = { "+", "-", "*", "/" };
    public boolean fitOperator(String element){
        for (String s: operators) {
            if (s.equals(element)){
                return true;
            }
        }
        throw  new RuntimeException("The element is not suitable");
    }
    public String getOperator(String element){
        for (String s: operators) {
            if (s.equals(element)){
                return element;
            }
        }
        throw  new RuntimeException("The element is not suitable");
    }
    public String numOrRimNum(String element){
        for (String s: nums) {
            if(s.equals(element)){
                return "nums";
            }
        }
        for (String s: rimNums) {
            if(s.equals(element)){
                return "rimNums";
            }
        }
        throw  new RuntimeException("The element is not suitable");
    }
    public String whatSolution(String operand1, boolean isOperator, String operand2){
        if ( operand1.equals("nums")
                && operand2.equals("nums")
                && isOperator){
            return "nums";
        } else if ( operand1.equals("rimNums")
                && operand2.equals("rimNums")
                && isOperator){
            return "rimNums";

        } else{
            throw  new RuntimeException("The element is not suitable");
        }
    }
    public String numsSolver(String operand1, String operator, String operand2){
        int num1 = 0;
        int num2 = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i].equals(operand1)){
                num1 = i;
            }
            if(nums[i].equals(operand2)){
                num2 = i;
            }
        }
        switch (operator) {
            case "+":
                return String.valueOf(num1 + num2);
            case "-":
                return String.valueOf(num1 - num2);
            case "*":
                return String.valueOf(num1 * num2);
            case "/":
                return String.valueOf(num1 / num2);
            default:
                throw new RuntimeException();
        }
    }
    public int rimNumsSolver(String operand1, String operator, String operand2){
        int num1 = 0;
        int num2 = 0;
        for(int i = 0; i < nums.length; i++){
            if(rimNums[i].equals(operand1)){
                num1 = i;
            }
            if(rimNums[i].equals(operand2)){
                num2 = i;
            }
        }
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                throw new RuntimeException();
        }
    }
    public String convertToRim(int num){
        if (num <= 0 || num > 100){
            throw new RuntimeException();
        }else if(num <= 10){
            for (int i = 0; i < rimNums.length; i++){
                if(num == i){
                    return rimNums[i];
                }
            }
        }else if(num == 100){
            return rimTenNums[rimTenNums.length - 1];
        }else{
            int tenNum = num / 10;
            int oneNum = num % 10;
            String tensNum = null;
            String onesNum = null;
            for (int i = 0; i < rimTenNums.length; i++){
                if(tenNum == i){
                    tensNum = rimTenNums[i];
                }
            }
            for (int i = 0; i < rimNums.length; i++){
                if(oneNum == i){
                    onesNum = rimNums[i];
                }
            }
            if (oneNum % 10 == 0)
                return tensNum;
            else
                return  tensNum + onesNum;
        }
        throw new RuntimeException();
    }
}