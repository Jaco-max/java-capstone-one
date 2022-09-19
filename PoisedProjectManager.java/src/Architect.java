import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Architect implements Person{
	private String name;
	private String surname;
	private String jobTitle;
	private String email;
	private String contactNumber;
	private String address;
	
	private String projectNumber;
	
	//Create Person Object
	public Architect(String name, String surname, String jobTitle,
				  String email, String contactNumber, String address,
				  String projectNumber) {
		this.name = name;
		this.surname = surname;
		this.jobTitle = jobTitle;
		this.email = email;
		this.contactNumber = contactNumber;
		this.address = address;
		this.projectNumber = projectNumber;
	}
	
	//overloaded constructor
	public Architect() {
		
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
		
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	@Override
	public String toStr() {
		String displayString = "Architect Name: "
							   + name + "\n"
							   + "Architect Surname: "
							   + surname + "\n"
							   + "Job Title: "
							   + jobTitle + "\n"
							   + "email address: "
							   + email + "\n"
							   + "Contact Number: "
							   + contactNumber + "\n"
							   + "Contractor Address: "
							   + address + "\n"
							   + "Project Number: "
							   + projectNumber;
		return displayString;
	}
	
	// method used to read data from the textfile and create a list of Architect objects
	@Override
	public static ArrayList <Architect> readTextFile(String fileName) {
		
		/*Create arrayLists to be used when reading information from
		 the textfile into a meaningful data type: projectList
		 which is an arrayList within an arrayList*/
		ArrayList <String> fileLines = new ArrayList();
		ArrayList <String> tempList = new ArrayList<String>();
		ArrayList <Architect> personList = new ArrayList();
		ArrayList <ArrayList<String>> personsList = new ArrayList();
		
		// Open the textfile and read the lines into fileLines
		try {
			File projectUpdate = new File(fileName); 
			Scanner updateScanner = new Scanner(projectUpdate);	
			
			// Create temporary ArrayList to store project to be updated  
			while (updateScanner.hasNext()){
				fileLines.add(updateScanner.next());
			}
			updateScanner.close();
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		String tempArr[] = null;
		String tempLine = "";
		Architect tempPerson = new Architect();
		
		
		/* Assign each line in fileLines to tempLine,
		Split the line at each # and assign to array tempValues,
		add each value in tempValues to arrayList tempList*/
		try {
			
			for(int i = 0 ;i < fileLines.size(); i++) {
				
				tempLine = fileLines.get(i);
				tempArr = tempLine.split("#");
				
				/*tempPerson gets a new instance of Person with each 
				iteration of the text file*/
				tempPerson = new Architect(tempArr[0].replaceAll("_"," "),
										tempArr[1].replaceAll("_"," "),
										tempArr[2].replaceAll("_"," "),
										tempArr[3].replaceAll("_"," "),
										tempArr[4].replaceAll("_"," "),
										tempArr[5].replaceAll("_"," "),
										tempArr[6].replaceAll("_"," ")); 

				personList.add(tempPerson);
			}		
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return (personList);
	}
	
	// method used to write architect ArrayList to textfile
	public static void writeToFile(ArrayList <Architect> tempList, String fileName) {
		
		try {
			
			File personData = new File(fileName);
			PrintWriter personDataWriter = new PrintWriter(personData);
			personDataWriter.print(""); 
			
			/* iterate through the arraylist and write
			 * object on a new line in the textfile
			 */
			for(int b = 0; b < tempList.size(); b++) {
				
				personDataWriter.write(tempList.get(b).getName().replaceAll(" ","_") + "#");
				personDataWriter.write(tempList.get(b).getSurname().replaceAll(" ","_") + "#");
				personDataWriter.write(tempList.get(b).getJobTitle().replaceAll(" ","_") + "#");
				personDataWriter.write(tempList.get(b).getEmail().replaceAll(" ","_") + "#");
				personDataWriter.write(tempList.get(b).getContactNumber().replaceAll(" ","_") + "#");
				personDataWriter.write(tempList.get(b).getAddress().replaceAll(" ","_") + "#");
				personDataWriter.write(tempList.get(b).getProjectNumber().replaceAll(" ","_") + "#" + "\n");
		
			}
			
			personDataWriter.close();
		}
		
		catch(Exception e) {
			System.out.println("File not found");
		}
	}

	// method used to update an Architect object in the textfile
	public static ArrayList<Architect> toUpdate(String architectName) {
		
		// create a list of architect objects using readTextFile
		ArrayList <Architect> tempList = readTextFile("architectData.txt");
		boolean updated = false;
		Scanner input = new Scanner(System.in);
		
		try {
			
			/* iterate through each architect object and check if
			 * it is the object to be updated
			 */
			for(int i = 0 ; i < tempList.size(); i++) {
				
				if((tempList.get(i).getName().equals(architectName))) {
					
					while(updated == false) {
						
					String newInfo ="";
					System.out.println("Please select which value "+
										"you would like to update:\n"+
										"1.  Architect Name\n" +
										"2.  Architect Surname\n" +
										"3.  Architect email\n" +
										"4.  Architect contact number\n" +
										"5.  Architect Address\n" +
										"6.  Project Number\n" +
										"7.  Done\n");	
						
					int menuOption = input.nextInt();
						
					//Remove \n buffer created by nextInt
					input.nextLine();
					
					// update the attribute chosen by the user
					switch(menuOption) {
					case 1:
						System.out.println("Please enter the new Architect Name:");
							
						newInfo = input.nextLine();
							
						tempList.get(i).setName(newInfo);
						break;
					case 2:
						System.out.println("Please enter the new Architect Surname:");
						
						newInfo = input.nextLine();
							
						tempList.get(i).setSurname(newInfo);
						break;
					case 3:
						System.out.println("Please enter the new Architect email address:");
						
						newInfo = input.nextLine();
						
						tempList.get(i).setEmail(newInfo);
						break;
					case 4:
						System.out.println("Please enter the new Architect contact number:");
						
						newInfo = input.nextLine();
							
						tempList.get(i).setContactNumber(newInfo);
						break;
					case 5:
						System.out.println("Please enter the new Architect Address:");
							
						newInfo = input.nextLine();
						tempList.get(i).setAddress(newInfo);
						break;
					case 6:
						System.out.println("Please enter the new Project Number:");
							
						newInfo = input.nextLine();
							
						tempList.get(i).setProjectNumber(newInfo);
					case 7: System.out.println("Architect Updated!");
							
						updated = true;
						break;
					}						
					}
				}
				
			}
		}
		
		catch(Exception e) {
			System.out.println("Architect not found");

		}
	
	return(tempList);
	
	}

}
