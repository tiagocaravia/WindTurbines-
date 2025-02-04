import java.util.Scanner; //for reading keyboard input and Files
import java.io.BufferedReader; //Fast way to read large files and other data streams
import java.io.FileReader; //used when using BufferedReader to read a File
import java.io.BufferedWriter; //Fast way to write large files and other data streams
import java.io.FileWriter; //used when using BufferedWriter to write a File
import java.io.IOException; //Exception that can be thrown by BufferedReader and BufferedWriter
import java.util.ArrayList;


public class WindTurbines{

    public static ArrayList<String[]> readDataFromFile(String filename) throws IOException {
        FileReader file = new FileReader(filename);
        BufferedReader myFile = new BufferedReader(file);

        ArrayList<String[]> csvdata = new ArrayList<String[]>();
        String[] row;
        String line = myFile.readLine();
        while (line != null) {
            row = line.split(",");
            csvdata.add(row);
            line = myFile.readLine();
        }
        myFile.close();
        return csvdata;
    }



    public static void writeDataToFile(ArrayList<String[]> csvdata, String filename) throws IOException {
        FileWriter file = new FileWriter(filename);
        BufferedWriter myFile = new BufferedWriter(file);
        for (String[] row : csvdata) {
            myFile.write(String.join(",", row) + "\n");
        }
        myFile.close();
    }


    public static int countWindTurbines(ArrayList<String[]> windData) {
        int sum = 0;
        //go through all but the first row (which is the headings)
        for (String[] row : windData.subList(1, windData.size())) {
        String numTurbines = row[9];
        numTurbines = numTurbines.replace("\"", "");
        int value = Integer.parseInt(numTurbines);
        sum += value;
        }
        return sum;
    }

    public static String StateWithMostTurbine(ArrayList<String[]> windData){
        ArrayList<String> checkedStates = new ArrayList<>();
        String mostFrequentState = null;
        int maxCount = 0;

        for (int i = 0; i < windData.size(); i++) {
            String currentState = windData.get(i)[0];
            if (checkedStates.contains(currentState)) {
                continue;
            }
            checkedStates.add(currentState);

            int count = 0;

            for (int j = 0; j < windData.size(); j++) {
                if (windData.get(j)[0].equals(currentState)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentState = currentState;
            }
        }
        return mostFrequentState;


    }
   


    public static void main(String[] args) {
        ArrayList<String[]> windData = new ArrayList<String[]>();
        Scanner keyboard = new Scanner(System.in);
        String input = "";
        while (!input.equals("Q")) {
            System.out.println("1. Read Data From File");
            System.out.println("2. Add a wind turbine location");
            System.out.println("3. Total Number of Turbines");
            System.out.println("4. State with most Turbines");
            System.out.println("5. Save Data");
            System.out.println("Q. Quit");
            input = keyboard.nextLine();
            input = input.toUpperCase();

            if (input.equals("1")) {
                System.out.print("filename: ");
                String filename = keyboard.nextLine();
                try {
                    windData = readDataFromFile(filename);
                } catch (IOException e) {
                    System.out.println("An error occurred: " + e);
                }
            } else if (input.equals("2")) {
                String[] row = new String[12];
                System.out.print("State: ");
                row[0] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("County: ");
                row[1] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Year: ");
                row[2] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Turbine Capacity: ");
                row[3] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Hub Height: ");
                row[4] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Rotor Diameter: ");
                row[5] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Swept_Area: ");
                row[6] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Total Height: ");
                row[7] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Project Capacity: ");
                row[8] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Number Turbines: ");
                row[9] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Latitude: ");
                row[10] = "\"" + keyboard.nextLine() + "\"";
                System.out.print("Longitude: ");
                row[11] = "\"" + keyboard.nextLine() + "\"";             

                windData.add(row);
             }
             
             else if (input.equals("3")) {                
                System.out.println("This data set contains " + countWindTurbines(windData) + " wind turbines.");
            }   

            else if (input.equals("4")) {                
                System.out.println("The state with the most turbines is " + StateWithMostTurbine(windData) + ".");
            }   
            
             else if (input.equals("5")) {
                System.out.print("filename: ");
                String filename = keyboard.nextLine();
                try {
                    writeDataToFile(windData,filename);
                    System.out.println("Saved.");
                } catch (IOException e) {
                    System.out.println("An error occurred: " + e);
                }
            }
        }
    }



}

 

