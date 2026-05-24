import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String file = "data/placement.csv";
        String line;

        double highestPackage = 0;
        String topCompany = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            // Skip header row
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String company = data[0];
                String branch = data[1];
                String hired = data[2];
                String packageLpa = data[3];

                System.out.println("Company: " + company);
                System.out.println("Branch: " + branch);
                System.out.println("Students Hired: " + hired);
                System.out.println("Average Package: " + packageLpa + " LPA");
                System.out.println("---------------------------");

                double packageValue = Double.parseDouble(packageLpa);

                if (packageValue > highestPackage) {
                    highestPackage = packageValue;
                    topCompany = company;
                }
            }

            br.close();

            System.out.println("\nTop Paying Company: " + topCompany);
            System.out.println("Highest Package: " + highestPackage + " LPA");

        } catch (IOException e) {

            System.out.println("Error reading file");
        }
    }
}