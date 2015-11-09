import java.util.ArrayList;
import java.util.List;


public class Person {

	private String firstName;
	private String lastName;
	private String currentStatus;
	private String image;
	private int indexVauleInArray;
	private List<Person> friendsList = new ArrayList<Person>();
	
	public Person(){
		
	}
	
	public Person(String firstName, String lastName, String currentStatus, String image) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentStatus = currentStatus;
		this.image = image;
	}
	
	
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public void addAFriend(Person user) {
		friendsList.add(user);
		System.out.println("\n* " + user.firstName + " " + user.lastName + " is now your friend! *");
	}
	
	// not tested
	public void removeFriend(Person user) {
		friendsList.remove(user);
		System.out.println("\n* " + user.firstName + " " + user.lastName + " is no longer your friend! :( *");
	}
	
	// needs to iterate through friends
	public void getFriendsList() { //List<Person>
		System.out.println("\nFriends List:");
		if (!friendsList.isEmpty()) {
			for (Person friend: friendsList) {
				System.out.println("Name:\t"  + friend.getFirstName() + " " + friend.getLastName());
			}
		} 
		
		else {
			System.out.println("You haven't added any friends yet");
		}
	}
	
	// used for dummy data only
	public void setFriendsList(List<Person> friendsList) {
		this.friendsList = friendsList;
	}
	
	public int getIndexVauleInArray() {
		return indexVauleInArray;
	}

	public void setIndexVauleInArray(int indexVauleInArray) {
		this.indexVauleInArray = indexVauleInArray;
	}
	
	
	
}
