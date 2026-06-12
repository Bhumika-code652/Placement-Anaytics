import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter company name to search: ");
    String searchCompany = sc.nextLine();

    boolean found = false;

        String file = "data/placement.csv";
        String line;

        double highestPackage = 0;
        String topCompany = "";
        int totalCompanies = 0;
        int totalStudents = 0;
        

        double totalPackage = 0;

        int maxHired = 0;
        String topHiringCompany = "";

        int cseCount = 0;
        int eceCount = 0;
        int itCount = 0;

        double lowestPackage = Double.MAX_VALUE;
        String lowestCompany = "";

        int premiumCompanies = 0;

        int eligibleStudents = 600; // total eligible students

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

                if (company.equalsIgnoreCase(searchCompany)) {

                    System.out.println("\n===== COMPANY FOUND =====");
                    System.out.println("Company: " + company);
                    System.out.println("Branch: " + branch);
                    System.out.println("Students Hired: " + hired);
                    System.out.println("Package: " + packageLpa + " LPA");

                    found = true; 
                }  
                 if (!found) {
                    System.out.println("\nCompany not found.");
                }
                sc.close(); 
               

               // System.out.println("Company: " + company);
               // System.out.println("Branch: " + branch);
                //System.out.println("Students Hired: " + hired);
               // System.out.println("Average Package: " + packageLpa + " LPA");
               // System.out.println("---------------------------");

                double packageValue = Double.parseDouble(packageLpa);

                if (packageValue > highestPackage) {
                    highestPackage = packageValue;
                    topCompany = company;
                }
                totalCompanies++;

                int hiredCount = Integer.parseInt(hired);
                totalStudents += hiredCount;

              
                totalPackage += packageValue;

                if (packageValue < lowestPackage) {
                    lowestPackage = packageValue;
                    lowestCompany = company;
                }

                if (packageValue > 10) {
                    premiumCompanies++;
                }

                if (hiredCount > maxHired) {
                    maxHired = hiredCount;
                    topHiringCompany = company;
                }

                if (branch.equals("CSE")) {
                    cseCount += hiredCount;
                }
                else if (branch.equals("ECE")) {
                    eceCount += hiredCount;
                }
                else if (branch.equals("IT")) {
                    itCount += hiredCount;
                }
                
              
            }

            br.close();

           // System.out.println("\nTop Paying Company: " + topCompany);
            //System.out.println("Highest Package: " + highestPackage + " LPA");

           // System.out.println("Lowest Paying Company: " + lowestCompany);
           // System.out.println("Lowest Package: " + lowestPackage + " LPA");
            
           // System.out.println("Companies offering More Than 10 LPA: " + premiumCompanies );

            double averagePackage = totalPackage / totalCompanies;

            System.out.println("\n===== ANALYTICS REPORT =====");

            System.out.println("Top Paying Company: " + topCompany);
            System.out.println("Highest Package: " + highestPackage + " LPA");

            System.out.println("Total Companies: " + totalCompanies);

            System.out.println("Total Students Hired: " + totalStudents);

            System.out.println("Average Package: " + averagePackage + " LPA");

            System.out.println("Top Hiring Company: " + topHiringCompany);
            System.out.println("Maximum Students Hired: " + maxHired);

            System.out.println("\nBranch-wise Hiring:");
            System.out.println("CSE Students Hired: " + cseCount);
            System.out.println("ECE Students Hired: " + eceCount);
            System.out.println("IT Students Hired: " + itCount); 
            
            double placementPercentage =
                  ((double) totalStudents / eligibleStudents) * 100;

            System.out.println("Placement Percentage: "
                  + placementPercentage + "%");

                 

        } catch (IOException e) {

            System.out.println("Error reading file");
        }
    }
}