import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {

	public static void main(String[] args) {

		
		System.out.println("Welcome to Poised Project Manager");
		
		Scanner userInputSc = new Scanner(System.in);
		int mainMenuOption = 0;
		int projectMenuOption = 0;
		int clientMenuOption = 0;
		int contractorMenuOption = 0;
		int architectMenuOption = 0;
		Date currentDate = Calendar.getInstance().getTime();
		Calendar todayDate = Calendar.getInstance();
		
		todayDate.setTime(currentDate);
		
		String userInputStr = "";
		String finalised = null;
		
		
		// Create arraylists for each object type
		ArrayList<Project> projectList =  new ArrayList<Project>();
		
		projectList = Project.readTextFile("projectData.txt");
		
		ArrayList<Client> clientList = new ArrayList<Client>();
		
		clientList = Client.readTextFile("personData.txt");
		
		ArrayList<Contractor> contractorList = new ArrayList<Contractor>();
		
		contractorList = Contractor.readTextFile("contractorData.txt");
		
		ArrayList<Architect> architectList = new ArrayList<Architect>();
		
		architectList = Architect.readTextFile("architectData.txt");
		
		ArrayList<Project> completedProjectList =  new ArrayList<Project>();
		
		boolean mainMenuExit = false;
		boolean clientMenuExit = false;
		boolean contractorMenuExit = false;
		boolean architectMenuExit = false;
		boolean personMenuExit = false;
		boolean projectMenuExit = false;	
		
		/* Create and use only one scanner and close when done
		 * Write only when necessary "Would you like to save?..."
		 */
		
		while (mainMenuExit == false) {
			
			mainMenuOption = 0;
			projectMenuOption = 0;
			clientMenuOption = 0;
			contractorMenuOption = 0;
			
			System.out.println("Please select one of the following options:\n" + 
								"1. Manage Projects\n" +
								"2. Manage Clients\n" + 
								"3. Manage Contractors\n" + 
								"4. Manage Architects\n" + 
								"5. Exit Program");
			mainMenuOption = 0;
			mainMenuOption = userInputSc.nextInt();
			
			userInputSc.nextLine();
			
			switch(mainMenuOption) {
		
			
			case 1 : //main menu
				
				projectMenuExit = false;
				while(projectMenuExit == false) {
					
					mainMenuOption = 0;
					projectMenuOption = 0;
					clientMenuOption = 0;
					contractorMenuOption = 0;
					
					System.out.println("Please select an option:\n");
					System.out.println("1-- Add a new project");
					System.out.println("2-- Update a project");
					System.out.println("3-- Finalise a project");
					System.out.println("4-- Show incomplete projects");
					System.out.println("5-- Show overdue projects");
					System.out.println("6-- Display a project");
					System.out.println("7-- Back to main Menu");
					
					projectMenuOption = 0;
					projectMenuOption = userInputSc.nextInt();
					
					userInputSc.nextLine();
					
					
					Project newBuilding = new Project();
			
					switch(projectMenuOption) {
					case 1: //sub menu
				
						Calendar dueDate = Calendar.getInstance(); 
						String customerName, projectNumber,projectName, buildingType, 
						buildingAddress, erfNumber, deadline, totalCost, totalPaid;
			
						customerName = projectNumber = projectName = buildingType = 
								buildingAddress = erfNumber = deadline = totalCost = 
								totalPaid = "";
				

			
						// Input from the user is assigned to the project object, inside a try block
						try {	
							System.out.println("_ _ Please capture details for the new "
									+ "project below _ _\n");
					
							//Shift the nextline so that customerName gets the correct input
							userInputSc.nextLine();
							
							System.out.println("- Please enter the Customer Name:\n");
							customerName = userInputSc.nextLine();	
				
							System.out.println("- Please enter the project number"
									+ " for the new project:");
							projectNumber = userInputSc.nextLine();	
				
							System.out.println("- Please enter the name for "
									+ "the project:");
							projectName = userInputSc.nextLine();
							
							System.out.println("- Please enter the type of building:");
							buildingType = userInputSc.nextLine();
					
							System.out.println("- Please enter the address of the building:");
							buildingAddress = userInputSc.nextLine();
					
							System.out.println("- Please enter the ERF number for the project:");
							erfNumber = userInputSc.nextLine();	
					
							System.out.println("- Please enter the deadline for the project (yyyy mm dd) :");
							deadline = userInputSc.nextLine();
							String deadlineDate[] = deadline.split(" ");
							
							int year = Integer.parseInt(deadlineDate[0]);
							int month = Integer.parseInt(deadlineDate[0]);
							int day = Integer.parseInt(deadlineDate[0]);
							
							dueDate.set(year,month,day);
							
							System.out.println(deadlineDate[0]+ " DEADLINE DATE THINGY");
							System.out.println(deadlineDate[1]+ " DEADLINE DATE THINGY");
							System.out.println(deadlineDate[2]+ " DEADLINE DATE THINGY");
							
					
							System.out.println("- Please enter the total fee for the project:");
							totalCost = userInputSc.nextLine();
					
							System.out.println("- Please enter the total fee paid to date:");
							totalPaid = userInputSc.nextLine();
					
					
							finalised = "false";
					
					
			
						} catch(Exception e) {
							System.out.println("Incorrect Input");
						}
			
						if (projectName == ""){
							projectName = buildingType + " " + customerName; 
						}
				
						//Create project object
						try {
							newBuilding.setCustomerName(customerName);
							newBuilding.setProjectNumber(projectNumber);
							newBuilding.setProjectName(projectName);
							newBuilding.setBuildingType(buildingType);
							newBuilding.setProjectAddress(buildingAddress);
							newBuilding.setProjectERF(erfNumber);
							newBuilding.setDeadline(dueDate);
							newBuilding.setTotalFee(totalCost);
							newBuilding.setTotalPaid(totalPaid);
							newBuilding.setFinalised(finalised);
				
							System.out.println("New Building created!\n");
							System.out.println(newBuilding.toStr());
					
							projectList.add(newBuilding);
					
							Project.writeToFile(projectList,"projectData.txt");
			
						} catch (Exception e) {
							System.out.println("Project could not be created!\n");
						}
		
						break;
			
					case 2: //sub menu
			
						ArrayList<Project> tempList =  new ArrayList<Project>();
			
						System.out.println("Please enter the project number of " +
										"the project to update: ");
						userInputStr = "";
			
						userInputStr = userInputSc.next();
			
						System.out.println(userInputStr + "userInputStr");
		
						tempList = Project.toUpdate(userInputStr);
						projectList = tempList;
			
			
						break;
						
					case 3: //sub menu 
						userInputStr = "";
						System.out.println("Please enter the Project number to Finalise");
				
					userInputStr = userInputSc.next();
					Project.finaliseProject(userInputStr);
				
					break;
			
					case 4: //sub menu 
						System.out.println("Below are all the unfinished Projects");
			
					try {
						for (int i = 0; i < projectList.size(); i ++) {
							System.out.println(projectList.get(i).toStr());
							}						
					}
			
					catch(Exception e) {
						System.out.println("Oh no, an error occured :(");
					}
			
					break;
					
					case 5: System.out.println("Below are all overdue projects: \n");

					for(int i = 0; i < projectList.size(); i ++) {
						
						if(projectList.get(i).getDeadline().before(todayDate)) {
							System.out.println(projectList.get(i).toStr());
						}
					}
					break;
					
					case 6:	//sub menu		
						boolean projectFound = false;
				
						while(projectFound == false) {
							System.out.println("Enter the Project number to display");
							userInputStr = userInputSc.nextLine();
				
							for (int i = 0; i < projectList.size(); i++) {
								
								if (projectList.get(i).getProjectNumber().contains(userInputStr)){
									System.out.println(projectList.get(i).toStr());
									projectFound = true;
									break;
								}			
							}
							if(projectFound == false) {
								System.out.println("This project does not exist :(");
							}
						}				
					break;
			
					case 7: //sub menu
					Project.writeToFile(projectList,"projectData.txt");
					projectMenuExit = true;					
					break;
			
					}
				}
			break;
				
			case 2 : //main menu
				
			clientMenuExit = false;
			clientMenuOption =0;
			while(clientMenuExit == false) {
			System.out.println("Please select an option:\n");
			System.out.println("1-- Add a new Client");
			System.out.println("2-- Update a Client");
			System.out.println("3-- Display a Client");
			System.out.println("4-- Back to Main Menu");
			
				clientMenuOption = userInputSc.nextInt();
				
				//Remove \n buffer
				userInputSc.nextLine();
				
				
				
				switch(clientMenuOption) {
			
				case 1 : //sub menu
					Client newClient = new Client(); 
					String[] clientInfo = new String[7]; 
			
					System.out.println("Please enter the Client Name");
					clientInfo[0] = userInputSc.nextLine();
					System.out.println("Please enter the Client Surname");
					clientInfo[1] = userInputSc.nextLine();
					
					//Person is a client
					clientInfo[2] = "Client";
					System.out.println("Please enter the Client email address");
					clientInfo[3] = userInputSc.nextLine();
					System.out.println("Please enter the Client contact number");
					clientInfo[4] = userInputSc.nextLine();
					System.out.println("Please enter the Client Address");
					clientInfo[5] = userInputSc.nextLine();
					
					/*boolean projExists = false;
					String tempStr = "";
					
					while(projExists == false) {
						System.out.println("Please enter the Project Number of the Client");
						tempStr = userInputSc.nextLine();
						projExists = Project.checkProjNumber(tempStr);
					}*/
					
					clientInfo[6] = userInputSc.nextLine();
					
					newClient.setName(clientInfo[0]);
					newClient.setSurname(clientInfo[1]);
					newClient.setJobTitle(clientInfo[2]);
					newClient.setEmail(clientInfo[3]);
					newClient.setContactNumber(clientInfo[4]);
					newClient.setAddress(clientInfo[5]);
					newClient.setProjectNumber(clientInfo[6]);
					
					
					clientList.add(newClient);
					
					Client.writeToFile(clientList,"personData.txt");
					System.out.println("Client successfully added");
					
					break;
				
				case 2: //sub menu
					ArrayList<Client> tempList =  new ArrayList<Client>();
					
					System.out.println("Please enter the client name to update ");
					userInputStr = "";
		
					userInputStr = userInputSc.nextLine();
		
					System.out.println(userInputStr + "userInputStr");
	
					tempList = Client.toUpdate(userInputStr);
					clientList = tempList;
					Client.writeToFile(clientList,"personData.txt");
					userInputSc.reset();
					break;
				
				case 3: //sub menu
					boolean clientFound = false;
					
					while(clientFound == false) {
						System.out.println("Enter the Client to display");
						userInputStr = userInputSc.nextLine();
						
						System.out.println(userInputStr + "This is the user input");
			
						for (int i = 0; i < clientList.size(); i++) {
							
							if (clientList.get(i).getName().contains(userInputStr)){
								System.out.println(clientList.get(i).toStr());
								clientFound = true;
								break;
							}			
						}
						if(clientFound == false) {
							System.out.println("This client does not exist :(");
						}
					}
					
					break;
					
				case 4: //sub menu
					System.out.println("4-- Back to Main Menu");
					//Client.writeToFile(personList);
					clientMenuExit = true;
					break;
				
				}
			}
			

			
			break;
			
			case 3 : //main menu
				contractorMenuExit = false;
				contractorMenuOption =0;
				while(contractorMenuExit == false) {
				System.out.println("Please select an option:\n");
				System.out.println("1-- Add a new contractor");
				System.out.println("2-- Update a contractor");
				System.out.println("3-- Display a contractor");
				System.out.println("4-- Back to Main Menu");				
				contractorMenuOption = userInputSc.nextInt();
					
					//Remove \n buffer
					userInputSc.nextLine();
					
					switch(contractorMenuOption) {
				
					case 1 : //sub menu
						Contractor newContractor = new Contractor(); 
						String[] contractorInfo = new String[7]; 
						
						System.out.println("Please enter the contractor Name");
						contractorInfo[0] = userInputSc.nextLine();
						System.out.println("Please enter the contractor Surname");
						contractorInfo[1] = userInputSc.nextLine();
						
						//Person is a contractor
						contractorInfo[2] = "contractor";
						System.out.println("Please enter the contractor email address");
						contractorInfo[3] = userInputSc.nextLine();
						System.out.println("Please enter the contractor contact number");
						contractorInfo[4] = userInputSc.nextLine();
						System.out.println("Please enter the contractor Address");
						contractorInfo[5] = userInputSc.nextLine();
						System.out.println("Please enter the Project Number of the contractor");
						contractorInfo[6] = userInputSc.nextLine();
						
						newContractor.setName(contractorInfo[0]);
						newContractor.setSurname(contractorInfo[1]);
						newContractor.setJobTitle(contractorInfo[2]);
						newContractor.setEmail(contractorInfo[3]);
						newContractor.setContactNumber(contractorInfo[4]);
						newContractor.setAddress(contractorInfo[5]);
						newContractor.setProjectNumber(contractorInfo[6]);						
						contractorList.add(newContractor);
						
						Contractor.writeToFile(contractorList,"contractorData.txt");
						System.out.println("Contractor successfully added");
						
						break;
					
					case 2: //sub menu
						ArrayList<Client> tempList =  new ArrayList<Client>();
						
						System.out.println("Please enter the client name to update ");
						userInputStr = "";			
						userInputStr = userInputSc.nextLine();						
						tempList = Client.toUpdate(userInputStr);
						clientList = tempList;
						Client.writeToFile(clientList,"personData.txt");
						
						break;
					
					case 3: //sub menu
						boolean contractorFound = false;
						
						while(contractorFound == false) {
							
							System.out.println("Enter the contractor to display");
							userInputStr = userInputSc.nextLine();							
							System.out.println(userInputStr + "This is the user input");
				
							for (int i = 0; i < clientList.size(); i++) {
								if (contractorList.get(i).getName().contains(userInputStr)){
									System.out.println(contractorList.get(i).toStr());
									contractorFound = true;
									break;
								}			
							}
							if(contractorFound == false) {
								System.out.println("This contractor does not exist :(");
							}
						}
						
						break;
					case 4: //sub menu
						System.out.println("4-- Back to Main Menu");
						contractorMenuExit = true;
						break;
					
					}
				}
				
			break;
			
			case 4 : //main menu
				
				architectMenuExit = false;
				architectMenuOption = 0;
				
				while(architectMenuExit == false) {
					System.out.println("Please select an option:\n");
					System.out.println("1-- Add a new architect");
					System.out.println("2-- Update a architect");
					System.out.println("3-- Display a architect");
					System.out.println("4-- Back to Main Menu");
				
					architectMenuOption = userInputSc.nextInt();
					
					//Remove \n buffer
					userInputSc.nextLine();
										
					switch(architectMenuOption) {
				
					case 1 : //sub menu 
						Architect newArchitect = new Architect(); 
						String[] architectInfo = new String[7]; 
				
						System.out.println("Please enter the contractor Name");
						architectInfo[0] = userInputSc.nextLine();
						System.out.println("Please enter the contractor Surname");
						architectInfo[1] = userInputSc.nextLine();
						
						//Person is a architect
						architectInfo[2] = "architect";
						System.out.println("Please enter the architect email address");
						architectInfo[3] = userInputSc.nextLine();
						System.out.println("Please enter the architect contact number");
						architectInfo[4] = userInputSc.nextLine();
						System.out.println("Please enter the architect Address");
						architectInfo[5] = userInputSc.nextLine();
						System.out.println("Please enter the Project Number of the architect");
						architectInfo[6] = userInputSc.nextLine();
												
						newArchitect.setName(architectInfo[0]);
						newArchitect.setSurname(architectInfo[1]);
						newArchitect.setJobTitle(architectInfo[2]);
						newArchitect.setEmail(architectInfo[3]);
						newArchitect.setContactNumber(architectInfo[4]);
						newArchitect.setAddress(architectInfo[5]);
						newArchitect.setProjectNumber(architectInfo[6]);
						
						architectList.add(newArchitect);
						
						Architect.writeToFile(architectList,"architectData.txt");
						
						System.out.println("Architect successfully added");
						
						break;
					
					case 2: //sub menu
						ArrayList<Architect> tempList =  new ArrayList<Architect>();
						
						System.out.println("Please enter the Architect name to update ");
						userInputStr = "";
						userInputStr = userInputSc.nextLine();

						tempList = Architect.toUpdate(userInputStr);
						
						architectList = tempList;
						Architect.writeToFile(architectList, "architectData.txt");
						
						break;
					
					case 3: //sub menu
						boolean architectFound = false;
						
						while(architectFound == false) {
							System.out.println("Enter the architect to display");
							userInputStr = userInputSc.nextLine();
				
							for (int i = 0; i < architectList.size(); i++) {
								if (architectList.get(i).getName().contains(userInputStr)){
									System.out.println(architectList.get(i).toStr());
									architectFound = true;
									break;
								}			
							}
						
							if(architectFound == false) {
								System.out.println("This architect does not exist :(");
							}
						}
						
						break;
						
					case 4: //sub menu
						System.out.println("4-- Back to Main Menu");
						architectMenuExit = true;
						
						break;
					}
				}
			break;
			
			case 5 : //main menu
				System.out.println("Bye :(");
				mainMenuExit = true;
				userInputSc.close();
				System.exit(0);
				mainMenuExit = true;
				break;
			}
		}
		
		
		userInputSc.close();
	}
}
