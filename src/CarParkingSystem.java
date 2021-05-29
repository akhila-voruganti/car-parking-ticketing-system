import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarParkingSystem {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner fileNameScanner,commandScanner;
		try{
			//read input file to execute commands
			System.out.println("Please enter the name of the input file with extension:");
			fileNameScanner = new Scanner(System.in);
			String inputFileName = fileNameScanner.nextLine();
			//check if it is a text file
			if(!inputFileName.endsWith(".txt")){
				System.out.println("Enter File name with Extension");
				fileNameScanner.close();
				throw new RuntimeException("Input file name/format is wrong.");
			}
			fileNameScanner.close();
			//read the commands from the text file
			File inputCommandFile = new File(inputFileName);
			if(!inputCommandFile.exists()){
				throw new FileNotFoundException(inputFileName+" is not found");
			}
			commandScanner = new Scanner(inputCommandFile);
			List<String> inputCommands = new ArrayList<String>();
			//execute the commands in sequence
			while(commandScanner.hasNextLine()==true){
				inputCommands.add(commandScanner.nextLine());
			}
			commandScanner.close();
			//initialize the car parking with the given size 
			String carParkingSystemInit = inputCommands.get(0);
			String[] params=carParkingSystemInit.split(" ");
			if(params[0].compareTo("Create_parking_lot")!= 0){
				throw new RuntimeException("Parking lot is not initialized.Please provide Create_parking_lot with size as first command");
			}
			CarParking cp= new CarParking(Integer.parseInt(params[1]));
			//perform all the commands in sequence
			for(int i=1;i<inputCommands.size();i++){
				cp.perform(inputCommands.get(i));
			}

		}catch(Exception e){
			System.out.println("Error Occured");
			e.printStackTrace();
		}
		
	}
}
