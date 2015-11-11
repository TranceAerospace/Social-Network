import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Profile {
	private List<Person> users = new ArrayList<Person>();
	private Person mostCurrentUser = new Person();
	Scanner input = new Scanner(System.in);

	// create a new user profile
	private void createProfile() {
		String firstName;
		String lastName;
		String currentStatus;
		String image;
		
		System.out.println("-Please enter your first name: ");
		firstName = input.nextLine();
		
		System.out.println("-Now enter your last name: ");
		lastName = input.nextLine();
		
		System.out.println("-Set status: ");
		currentStatus = input.nextLine();
		
		System.out.println("-Lastly enter an image (* Enter any string, this is just a placeholder *): ");
		image = input.nextLine();
		
		Person newMember = new Person(firstName, lastName, currentStatus, image );
		
		users.add(newMember);
		newMember.setIndexVauleInArray(users.indexOf(newMember));
		mostCurrentUser = newMember;
	}
	
	//removes the last most added user and exits the system
	private void deleteUser(Person currentUser) {
		users.remove(currentUser);
		System.out.println("\nUser deleted");
		System.out.println("You no longer have access to the system, good bye!");
		System.exit(0);
	}
	
	// show the basic profile of the most recently added user
	private void showProfile() {
		int user = users.size()-1;
		Person userDisplay = users.get(user);
		System.out.println("\n\n\n------------------");
		System.out.println("Your Profile:");
		System.out.println("------------------");
		System.out.println("\nName:\t"  + userDisplay.getFirstName() + " " + userDisplay.getLastName() 
										+ "\nStatus:\t" + userDisplay.getCurrentStatus() + "\nImage:\t" + userDisplay.getImage());
		userDisplay.getFriendsList();
		
	}
	
	// display a profile that was searched for
	private void showProfile2(Person userDisplay) {
		input = new Scanner(System.in);
		String option2;
		System.out.println("\n------------------");
		System.out.println("User Profile:");
		System.out.println("------------------");
		System.out.println("\nName:\t"  + userDisplay.getFirstName() + " " + userDisplay.getLastName() 
										+ "\nStatus:\t" + userDisplay.getCurrentStatus() + "\nImage:\t" + userDisplay.getImage());
		userDisplay.getFriendsList();
		System.out.println("\n\n------------------");
		System.out.println("1) Add the user as a friend"
							+ "\n2) Back to my profile");
		
		option2 = input.nextLine();
		
		userInteration(userDisplay, option2);
		
	}
	
	// show a list of all users in the system
	private void displayUserList() {
		System.out.println("\n------------------");
		System.out.println("User List: ");
		System.out.println("------------------");
		for (Person user: users) {
			System.out.println("User: "  + user.getFirstName() + " " + user.getLastName()); // + "\nIndex: + users.indexOf(user)
			
		}
	}
	
	// full edit user menu's, didnt break this one up, thank goodness
	private void editUser(Person currentPerson) {
		input = new Scanner(System.in);
		String option;
		String changedVariable;
		System.out.println("------------------");
		System.out.println("1) Change first name" 
						+ "\n2) Change last name" 
						+ "\n3) Change Status" 
						+ "\n4) Change Image"
						+ "\n5) Back to profile");
		option = input.nextLine();
		
		switch (option) {
		case "1":
			System.out.println("\n------------------");
			System.out.println("Type in a new name");
			changedVariable = input.nextLine();
			currentPerson.setFirstName(changedVariable);
			break;
		case "2":
			System.out.println("\n------------------");
			System.out.println("Type in a new name");
			changedVariable = input.nextLine();
			currentPerson.setLastName(changedVariable);
			break;
		case "3":
			System.out.println("\n------------------");
			System.out.println("Enter your new status!");
			changedVariable = input.nextLine();
			currentPerson.setCurrentStatus(changedVariable);
			break;
		case "4":
			System.out.println("\n------------------");
			System.out.println("Enter your new status!");
			changedVariable = input.nextLine();
			currentPerson.setImage(changedVariable);
			break;
		case "5":
			break;
		default:
			break;
		}
		showProfile();
		profileOptions();
	}
	
	// user interaction when finding someone to add, also couldve been added to the other method, 
	// 			wanted to break it up though
	private void userInteration(Person passedUser, String option) {
		
		switch (option) {
		case "1": // add as friend
				mostCurrentUser.addAFriend(passedUser);	
			break;
		case "2":
			// back to my profile
			break;
		default:
			break;
		}
		showProfile();
		profileOptions();	
	}
	
	// main profile control option choices
		private String profileOptions() {
			input = new Scanner(System.in);
			String option;
			System.out.println("\n------------------");
			System.out.println("Enter an option: "
					+ "\n1) To edit your profile"
					+ "\n2) To search for a friend"
					+ "\n3) To browse current users"
					+ "\n4) Add another user"
					+ "\n5) To delete your profile"
					+ "\n6) To logout and quit the system\n");
			option = input.nextLine().trim();
			
			
			userOption(option);
			return option;
			
		}
	
	// switch for profile options, just wanted it broken up, couldve been placed in profile options
	private void userOption(String option) {
		
		switch (option) {
		case "1": // edit a user
			editUser(mostCurrentUser);
			break;
		case "2": // seach for a friend
			searchForUser();
			break;
		case "3": // browse currerent users
			displayUserList();
			profileOptions();
			break;
		case "4": // add another user to system
			createProfile();
			showProfile();
			profileOptions();
			break;
		case "5":
			deleteUser(mostCurrentUser);
			break;
		case "6":
			System.out.println("\n\nLogging out .... Good bye!");
			System.exit(0);
			break;
		default: //placeholder
			System.out.println("Option not found, Try again");
			profileOptions();
			break;
		}
		
		
	}
	
	
	// searching .... 
	private void searchForUser() {
		String searchedName;
		
		System.out.println("\nEnter the name of the person you are looking for");
		searchedName = input.next();
		
		// some crazy java 8 shit to find users posible placeholder till i adapt equals/contains.
		List<Person> foundUsers = users.stream().filter((Person)->Person.getFirstName() 
						.equalsIgnoreCase(searchedName)).collect(Collectors.toList());
		
		if (!(foundUsers.isEmpty())) {
			for (Person user: foundUsers) {
					showProfile2(user);
			}
			
		} else {
			System.out.println("\n\n<-- Could not find user, returning to your profile -->");
			showProfile();
			profileOptions();
		}
	}
	
	
	// main
	public static void main(String[] args) {
		Profile userProfile = new Profile();
		userProfile.dummyData();
		userProfile.createProfile();
		userProfile.showProfile();
		userProfile.profileOptions();
	}
	
	// i know theres a better way to do this just to tired to figure it out right now
	public void dummyData() {
		Person newPerson = new Person("Wil", "Burr", "divorced", "a");
		Person newPerson1 = new Person("Toucan", "Sam", "single", "b");
		Person newPerson2 = new Person("Cap'n", "Crunch", "it's complicated", "c");
		users.add(newPerson);
		users.add(newPerson1);
		users.add(newPerson2);	
	}
}

