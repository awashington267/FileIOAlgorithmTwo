import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.BufferPoolMXBean;
import java.util.Scanner;

public class Main {

    private final static FileInput cardAccts = new FileInput("C:\\Users\\Anthony\\IdeaProjects\\Movie Rating Example\\movie_cards.csv");
    private final static FileInput cardPurchases = new FileInput("C:\\Users\\Anthony\\IdeaProjects\\Movie Rating Example\\movie_purchases.csv");
    private final static FileInput movieRating = new FileInput("C:\\Users\\Anthony\\IdeaProjects\\Movie Rating Example\\movie_rating.csv");
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        String line;
        String[] fields;
        double[] rating = new double[1];

        int[] nums = new int[2];
        System.out.format("%8s  %-18s %6s %6s\n","Account","Name", "Movies", "Points");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            findPurchases(fields[0], nums);
            movieRating(fields[0]);
            System.out.format("00%6s  %-18s  %2d   %4d\n",fields[0],fields[1], nums[0], nums[1], rating[1]);
        }
    }

    public static void findPurchases(String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
        String line;
        String[] fields;
        boolean done = false;
        while (((line = cardPurchases.fileReadLine()) != null) && !(done)) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
                nums[0]++;
                nums[1] += Integer.parseInt(fields[2]);
            }

        }
    }

    public static double movieRating(String acct){
        double[] rating = new double[2];
        String fields[];
        String line;
        boolean done = false;

        while (((line = movieRating.fileReadLine()) != null) && !(done)) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
            rating[0]++;
            rating[1] += Integer.parseInt(fields[1]);

            }

        }
        rating[1] = rating[1] / rating[0];

        return(rating[1]);

    }

}