import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

public class Project {
	private String customerName;
	private String projectNumber;
	private String projectName;
	private String buildingType;
	private String projectAddress;
	private String projectERF;
	private Calendar deadline = Calendar.getInstance();
	
	private String totalFee;
	private String totalPaid;
	private String finalised;
	
	
	//Create Project Object
	public Project(String customerName, String projectNumber, String projectName, 
				   String buildingType, String projectAddress,
			       String projectERF, Calendar deadline, 
			       String totalFee, String totalPaid, String finalised) {
		this.customerName = customerName;
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.projectAddress = projectAddress;
		this.projectERF = projectERF;
		this.deadline = deadline;
		this.totalFee = totalFee;
		this.totalPaid = totalPaid;
		this.finalised = finalised;
	}
	
	//Overloaded constructor
	public Project() {
		
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getProjectNumber() {
		return projectNumber;
	}
	
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
		
	public String getProjectName() {
		return projectName;
	}	
	
	public void setProjectName(String newProjectName) {
		projectName = newProjectName;
	}
	
	public String getBuildingType() {
		return buildingType;
	}
	
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	
	public String getProjectAddress() {
		return projectAddress;
	}
	
	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}
	
	public String getProjectERF() {
		return projectERF;
	}
	
	public void setProjectERF(String projectERF) {
		this.projectERF = projectERF;
	}
	
	public Calendar getDeadline() {
		return deadline;
	}
	public void setDeadline( Calendar newDeadline) {
		deadline = newDeadline;
	}
	
	public String getTotalFee() {
		return totalFee;
	}
	
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	
	public String getTotalPaid() {
		return totalPaid;
	}
	
	public void setTotalPaid(String newTotalPaid) {
		totalPaid = newTotalPaid;
	}
	
	public String getFinalised() {
		return finalised;
	}
	
	public void setFinalised(String newFinalised) {
		finalised = newFinalised;
	}
	
	public void clear() {
		customerName = null;
		projectNumber = null;
		projectName = null;
		buildingType = null;
		projectAddress = null;
		projectERF = null;
		deadline = null;
		totalFee = null;
		totalPaid = null;
		finalised = null;
	}
	
	public String toStr() {
		String displayStr = "";
		displayStr = "Customer Name: " + customerName +"\n"
				   + "Project Number: " + projectNumber + "\n"
				   + "Project Name: " + projectName + "\n"
				   + "Building type: " + buildingType + "\n"
				   + "Project Address: " + projectAddress +  "\n"
				   + "Project ERF Number : " + projectERF + "\n"
				   + "Project Deadline: " + deadline.get(Calendar.DATE) + " "
				   						  +	deadline.get(Calendar.MONTH) + " "
				   						  +	deadline.get(Calendar.YEAR) +"\n"
				   + "Total Fee: R" + totalFee + "\n"
				   + "Total paid to date: R" + totalPaid + "\n";
		return displayStr;
	}
	
	/* method used to read contents from the textfile into an
	arraylist of project objects */
	public static ArrayList <Project> readTextFile(String fileName) {
		
		ArrayList <String> fileLines = new ArrayList<String>();
		ArrayList <Project> projList = new ArrayList<Project>();
		
		
		/* Open the textfile and read each line as a single index
		into a 2D ArrayList, fileLines*/
		try {
			File projectUpdate = new File(fileName); 
			Scanner updateScanner = new Scanner(projectUpdate);	
			
			// add each line from textfile into fileLines
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
		Project tempProj = new Project();
				
		/* Assign each index in fileLines to tempLine,
		Split the line at each # and assign to array tempValues,
		add each value in tempValues to arrayList tempList*/
		try {
			
			for(int i = 0 ; i < fileLines.size(); i++) {
				
				tempLine = fileLines.get(i);
				Calendar dueDate = Calendar.getInstance();
				String dueDateArr[] = new String[3] ;
				
				int dueYear = 0;
				int dueMonth = 0;
				int dueDay = 0;
				
				tempArr = tempLine.split("#");
				
				/*Calendar Object is created so it can be used
				when setting Project Values*/
				dueDateArr = tempArr[6].split("_");
				dueYear = Integer.parseInt(dueDateArr[0]);
				dueMonth = Integer.parseInt(dueDateArr[1]);
				dueDay = Integer.parseInt(dueDateArr[2]);
				dueDate.set(dueYear, dueMonth, dueDay);
			
				/*tempProj gets a new instance of Project with every iteration
				through the text file*/
				
				tempProj = new Project(tempArr[0].replaceAll("_"," "),
							tempArr[1].replaceAll("_"," "),
							tempArr[2].replaceAll("_"," "),
							tempArr[3].replaceAll("_"," "),
							tempArr[4].replaceAll("_"," "),
							tempArr[5].replaceAll("_"," "),
							dueDate,
							tempArr[7].replaceAll("_"," "),
							tempArr[8].replaceAll("_"," "),
							tempArr[9].replaceAll("_"," "));
				
				projList.add(tempProj);
				
			}		
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return (projList);
	}
	
	// Method used to update a project object in the textfile
	public static ArrayList<Project> toUpdate(String ProjectNumber) { 
		
		// create a list of project objects
		ArrayList <Project> tempList = readTextFile("projectData.txt");
		String ProjDel = "";
		boolean updated = true;
		
		try {
			
			for(int i = 0 ; i < tempList.size(); i++) {
				
				if((tempList.get(i).getProjectNumber().equals(ProjectNumber))) {
					
						while(updated != false) {

						Scanner input = new Scanner(System.in);
						String newInfo ="";
						System.out.println("Please select which value "+
										"you would like to update:\n"+
										"1.  Customer Name\n" +
										"2.  Project Number\n" +
										"3.  Project Name\n" +
										"4.  Building Type\n" +
										"5.  Project Address\n" +
										"6.  Project ERF Number\n" +
										"7.  Project Deadline\n" +
										"8.  Total Project Fee\n" +
										"9.  Total Fee Paid\n" +
										"10. Done");					
				
						int menuOption = input.nextInt();
						
						//Remove \n buffer created by nextInt
						input.nextLine();
						
						//create menu used to update specific attributes
						switch(menuOption) {
						case 1:
							System.out.println("Please enter the new Customer Name:");							
							newInfo = input.nextLine();							
							tempList.get(i).setCustomerName(newInfo);
							break;
							
						case 2:
							System.out.println("Please enter the new Project Number:");						
							newInfo = input.nextLine();							
							tempList.get(i).setProjectNumber(newInfo);
							break;
							
						case 3:
							System.out.println("Please enter the new Project Name:");						
							newInfo = input.nextLine();							
							tempList.get(i).setProjectName(newInfo);
							break;
							
						case 4:
							System.out.println("Please enter the new Building Type:");
						
							newInfo = input.nextLine();
							
							tempList.get(i).setBuildingType(newInfo);
							break;
						case 5:
							System.out.println("Please enter the new Project Address:");
							
							newInfo = input.nextLine();
							
							tempList.get(i).setProjectAddress(newInfo);
							break;
						case 6:
							System.out.println("Please enter the new Project ERF Number:");
							newInfo = input.nextLine();
							tempList.get(i).setProjectERF(newInfo);
							break;
							
						case 7:
							System.out.println("Please enter the new Project Deadline:");
							
							String newInfoDate[];
							Calendar newDueDate = Calendar.getInstance();
							
							int daysInt =0;
							int monthsInt = 0;
							int yearsInt = 0;
							
							newInfo = input.nextLine();
							newInfoDate = newInfo.split(" ");
							
							System.out.println("New date info" + newInfoDate.toString() + "\n");
							
							// parse new date values to variables
							yearsInt = Integer.parseInt(newInfoDate[0]);
							monthsInt = Integer.parseInt(newInfoDate[1]);
							daysInt = Integer.parseInt(newInfoDate[2]);
							
							// create calendar object
							newDueDate.set(yearsInt,monthsInt,daysInt);
							
							/* set the deadline of the project object 
							in the arraylist*/
							tempList.get(i).setDeadline(newDueDate);
							
							break;
						case 8:
							System.out.println("Please enter the new Project Fee:");
						
							newInfo = input.nextLine();
							
							tempList.get(i).setTotalFee(newInfo);
							break;
						case 9:
							System.out.println("Please enter the new Fee Paid:");
						
							newInfo = input.nextLine();
							
							tempList.get(i).setTotalPaid(newInfo);
							break;
						case 10: System.out.println("Project Updated!");
							updated = false;
							break;
						}
						}
				}	
			}			
		}
		
		catch(Exception e) {
			System.out.println("Project not found");

		}

	return(tempList);
	}
	
	// method used to write projList to the textfile
	public static void writeToFile(ArrayList <Project> tempList, String fileName) {
	
		try {
			File projectData = new File(fileName);
	
			PrintWriter dataWriter = new PrintWriter(projectData);
	
			dataWriter.print(""); 
	
	
	
			for(int b = 0; b < tempList.size(); b++) {
				dataWriter.write(tempList.get(b).getCustomerName().replaceAll(
								 " ","_") + "#");
				dataWriter.write(tempList.get(b).getProjectNumber().replaceAll(
								 " ","_") + "#");
				dataWriter.write(tempList.get(b).getProjectName().replaceAll(
								 " ","_") + "#");
				dataWriter.write(tempList.get(b).getBuildingType().replaceAll(
								 " ","_") + "#");
				dataWriter.write(tempList.get(b).getProjectAddress().replaceAll(
								 " ","_") + "#");
				dataWriter.write(tempList.get(b).getProjectERF().replaceAll(
								 " ","_") + "#");			
				dataWriter.write(tempList.get(b).getDeadline().get(Calendar.YEAR)
								+ "_"
								+ tempList.get(b).getDeadline().get(Calendar.MONTH) 
								+ "_" 
								+ tempList.get(b).getDeadline().get(Calendar.DATE) 
								+ "#");
				dataWriter.write(tempList.get(b).getTotalFee().replaceAll(" ","_") + "#");
				dataWriter.write(tempList.get(b).getTotalPaid().replaceAll(" ","_") + "#");
				dataWriter.write(tempList.get(b).getFinalised().replaceAll(" ","_") + "#\n");
		
			}
			dataWriter.close();
		}
		
		catch(Exception e) {
			System.out.println("File not found");
		}
	
	}
	
	// method used to finalise a project and write to finalised.txt
	public static void finaliseProject(String projectNumber) {
		
		//read existing lists from textfiles
		ArrayList<Project> projectList = readTextFile("projectData.txt");
		ArrayList<Project> finalisedProjectList = readTextFile("finalisedProjects.txt");
		ArrayList<Client> clientList = Client.readTextFile("personData.txt");
		
		// create blank list used to write updated list to textfile
		ArrayList<Project> newProjectList = new ArrayList<Project>();
		Project finalisedProject = new Project();
		
		double totalPaid = 0.0;
		double totalFee = 0.0;
		String customerContact = "";
		
		
		for(int i = 0; i < projectList.size(); i ++) {
			
			/* change values in project object that is finalised
			 * and add to finalisedProjectList
			 */
			if (projectList.get(i).getProjectNumber().contains(projectNumber)){
				
				projectList.get(i).setFinalised("true");
				finalisedProjectList.add(projectList.get(i));
				finalisedProject = projectList.get(i);
				totalPaid = Double.parseDouble(projectList.get(i).getTotalPaid());
				totalFee = Double.parseDouble(projectList.get(i).getTotalFee());
				
				// check if project is fully paid
				if (totalFee == totalPaid) {
					System.out.println("Project has been paid in full");
				}
				
				// print receipt as project is not fully paid
				else {
					for(int v = 0; v < clientList.size(); v ++) {
						if (clientList.get(v).getProjectNumber()
							.contains(finalisedProject.getProjectNumber().toString())) {
							customerContact = clientList.get(v).getContactNumber();
						}
						
					}
					
					double totalOwed = totalFee - totalPaid;
					System.out.println("Amount still owed : R" + totalOwed + 
									   "\n" +
									   "Customer Contact: " + customerContact);
				}
			}
			
			//add next unfinalised project to projectList
			else {
				newProjectList.add(projectList.get(i));
			}
		
		// write each project list to correct textfile
		Project.writeToFile(newProjectList, "projectData.txt");
		Project.writeToFile(finalisedProjectList, "finalisedProjects.txt");
		
		}	
		
	}
	
	/*public static boolean checkProjNumber(String projectNumber) {
		
		ArrayList<Project> projectList = Project.readTextFile("projectData.txt");
		boolean projExists = false;
		
		for (int i = 0; i < projectList.size(); i++) {
			if(projectList.get(i).getProjectNumber().contains(projectNumber)) {
				projExists = true;
			}
		}
	return(projExists);	
	}*/
	
}
