import java.util.ArrayList;
import java.util.Scanner;

public class SumWithAutoboxing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        for (String part : parts) {
            numbers.add(Integer.parseInt(part));
        }
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        System.out.println("Sum of numbers: " + sum);
    }
}
