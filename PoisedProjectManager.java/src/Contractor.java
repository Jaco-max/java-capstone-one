import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Contractor {
	private String name;
	private String surname;
	private String jobTitle;
	private String email;
	private String contactNumber;
	private String address;
	
	private String projectNumber;
	
	//Create Person Object
	public Contractor(String name, String surname, String jobTitle,
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
	
	public Contractor() {
		
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

	public String toStr() {
		String displayString = "Contractor Name: "
							   + name + "\n"
							   + "Contractor Surname: "
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
	
	public static ArrayList <Contractor> readTextFile(String fileName) {
		
		/*Create arrayLists to be used when reading information from
		 the textfile into a meaningful data type: projectList
		 which is an arrayList within an arrayList*/
		ArrayList <String> fileLines = new ArrayList();
		ArrayList <String> tempList = new ArrayList<String>();
		ArrayList <Contractor> personList = new ArrayList();
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
		Contractor tempPerson = new Contractor();
		
		
		/* Assign each line in fileLines to tempLine,
		Split the line at each # and assign to array tempValues,
		add each value in tempValues to arrayList tempList*/
		try {
			
			for(int i = 0 ;i < fileLines.size(); i++) {
				tempLine = fileLines.get(i);
				
				tempArr = tempLine.split("#");
				
				/*tempPerson gets a new instance of Person with each 
				iteration of the text file*/
				tempPerson = new Contractor(tempArr[0].replaceAll("_"," "),
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
	
	public static void writeToFile(ArrayList <Contractor> tempList, String fileName) {
		
		try {
			File personData = new File(fileName);
	
			PrintWriter personDataWriter = new PrintWriter(personData);
	
			personDataWriter.print(""); 
	
	
	
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
	
public static ArrayList<Contractor> toUpdate(String contractorName) {
		
		ArrayList <Contractor> tempList = readTextFile("contractorData.txt");
		String ProjDel = "";
		boolean updated = true;
		Scanner input = new Scanner(System.in);
		
		try {
			
			for(int i = 0 ; i < tempList.size(); i++) {
				
				if((tempList.get(i).getName().equals(contractorName))) {
					
						while(updated != false) {
						//System.out.println("ITEQUALS!!!!!");
						
						String newInfo ="";
						System.out.println("Please select which value "+
										"you would like to update:\n"+
										"1.  Contractor Name\n" +
										"2.  Contractor Surname\n" +
										"3.  Contractor email\n" +
										"4.  Contractor contact number\n" +
										"5.  Contractor Address\n" +
										"6.  Project Number\n" +
										"7.  Done\n");	
						
						int menuOption = input.nextInt();
						
						//Remove \n buffer created by nextInt
						input.nextLine();
						
						switch(menuOption) {
						case 1:
							System.out.println("Please enter the new Contractor Name:");
							
							newInfo = input.nextLine();
							
							tempList.get(i).setName(newInfo);
							break;
						case 2:
							System.out.println("Please enter the new Contractor Surname:");
						
							newInfo = input.nextLine();
							
							tempList.get(i).setSurname(newInfo);
							break;
						case 3:
							System.out.println("Please enter the new Contractor email address:");
						
							newInfo = input.nextLine();
							
							tempList.get(i).setEmail(newInfo);
							break;
						case 4:
							System.out.println("Please enter the new Contractor contact number:");
						
							newInfo = input.nextLine();
							
							tempList.get(i).setContactNumber(newInfo);
							break;
						case 5:
							System.out.println("Please enter the new Contractor Address:");
							
							newInfo = input.nextLine();
							
							tempList.get(i).setAddress(newInfo);
							break;
						case 6:
							System.out.println("Please enter the new Project Number:");
							
							newInfo = input.nextLine();
							
							tempList.get(i).setProjectNumber(newInfo);
						case 7: System.out.println("Contractor Updated!");
							
							updated = false;
							break;
						}
						
					}
				
				}	
			}
			
			}
		catch(Exception e) {
			System.out.println("Contractor not found");

		}
	return(tempList);
	}

}
