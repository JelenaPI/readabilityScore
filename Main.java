package readability;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfSentences;
        int numberOfWords = 0;
        int characters = 0;
        try {
            String text = readFileAsString(args[0]);
            int numberOfSyllablse = 0;
            int polysyllables = 0;
            String[] sentences = text.split("[.?!]");
            numberOfSentences = sentences.length;
            int syllables;
            for (String word : text.split("\\s+")) {
                numberOfWords++;
                characters += word.length();
                syllables = CountSyllable.count(word);
                numberOfSyllablse += syllables;
                if (syllables > 2) {
                    polysyllables++;
                }
            }
            double avc = 100 * characters / numberOfWords;
            double avs = 100 *  numberOfSentences / numberOfWords;

            double automatedReadabilityIndex = 4.71 * characters / numberOfWords + 0.5 * numberOfWords / numberOfSentences - 21.43;

            double  fleschKincaid = 0.39 * numberOfWords / numberOfSentences + 11.8 * numberOfSyllablse / numberOfWords - 15.59;

            double  smog = 1.043 * Math.sqrt((float)polysyllables * 30 / numberOfSentences) + 3.1291;

            double  colemanLiau  = avc * 0.0588 - avs * 0.296 - 15.8;

            System.out.println("The text is:");
            System.out.println(text);
            System.out.println("Words: " + numberOfWords);
            System.out.println("Sentences: " + numberOfSentences);
            System.out.println("Characters: " + characters);
            System.out.println("Syllables: " + numberOfSyllablse);
            System.out.println("Polysyllables: " + polysyllables);
            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            String input = sc.next();
            switch (input) {
                case "all":
                    System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).\n",
                            automatedReadabilityIndex,
                            countRange(automatedReadabilityIndex));
                    System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).\n",
                            fleschKincaid,
                            countRange(fleschKincaid));
                    System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).\n",
                            smog,
                            countRange(smog));
                    System.out.printf("Coleman–Liau index: %.2f (about %s-year-olds).\n",
                            colemanLiau,
                            countRange(colemanLiau));
                    break;
                case "ARI":
                    System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).",
                            automatedReadabilityIndex,
                            countRange(automatedReadabilityIndex));

                    break;
                case "FK":
                    System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).",
                            fleschKincaid,
                            countRange(fleschKincaid));
                    break;
                case "SMOG":
                    System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).",
                            smog,
                            countRange(smog));
                    break;
                case "CL":
                    System.out.printf("Coleman–Liau index: %.2f (about %s-year-olds).",
                            colemanLiau,
                            countRange(colemanLiau));

                    break;
            }
             } catch (FileNotFoundException e) {
            System.out.println("No file found ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String countRange (double score) {
        String range;
        if (score <= 1) {
            range = "6";
        } else if (score <= 2) {
            range = "7";
        } else if (score <= 3) {
            range = "9";
        } else if (score <= 4) {
            range = "10";
        } else if (score <= 5) {
            range = "11";
        } else if (score <= 6) {
            range = "12";
        } else if (score <= 7) {
            range = "13";
        } else if (score <= 8) {
            range = "14";
        } else if (score <= 9) {
            range = "15";
        } else if (score <= 10) {
            range = "16";
        } else if (score <= 11) {
            range = "17";
        } else if (score <= 12) {
            range = "18";
        } else if (score <= 13) {
            range = "24";
        } else {
            range = "24+";
        }
        return range;
    }

     public static String readFileAsString(String fileName) throws IOException {
         return new String(Files.readAllBytes(Paths.get(fileName)));
     }

}