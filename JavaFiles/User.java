/*
This program will define the User class.


*/


public class User { 
   private int userId;
   private String firstName = "";
   private String lastName = "";
   private String username = "";
   private String password = "";
   private static int numberOfUsers = 0;
   
   protected static LinkedList userList = new LinkedList(); //provide a list of users in a LinkedList structure

   
//Constructor method that will use passed values to create a new account and log it into the database
   public User (String fName, String lName, String uname, String pass) {
   
   //Use the values passed into the call   
      this.firstName = fName;
      this.lastName = lName;
      this.username = uname;
      this.password = pass;
      this.userId = numberOfUsers + 1;
      numberOfUsers++;
      userList.addLastNode(userId);
      
  
  
}     
   
   public void login(){
      /*Use a select statement for the entered username and password. If the combination returns a row in the table, access the next screen.
               Otherwise, display an error screen.*/   
               
               
      //make part of the login activity
   }
   
   public void logout(){
      //Click a buttton, move to logout confirmation screen, make any necessary updates to the user's statistics. Maybe clear out the queue or something as a way of doing our own garbage collection.
      
      //Make part of the logout activity
   }
   
   public void viewAccount(){
   //Make calls to the different getter methods and display the correct information where it needs to go
   
   //This method might serve better as part of the view_account activity. Have each field become populated by calling the appropriate getter method.
   
   }
   
   public void updateAccount(String fname){
     //Need to make overloaded methods for this to update different aspects
     
    //This method might better serve as part of the update_account activity or whatever we call it. Each field can call a setter method.
   
   }
   
   public void deleteAccount() {
      userList.removeId(this.userId);
      numberOfUsers--;
      //Make a call to delete dog
      //Make a call to delete this account from votedOn for all dogs -- might not be necessary. When generating queues, it might just pass over the number... reassess later   
   }
   
//Getter methods for various fields
   public String getFirstName() {return this.firstName;}
   public String getLastName()  {return this.lastName;}   
   public String getUsername() {return this.username;}   
   public int getUserId() {return this.userId;}   
   public int getNumberOfUsers() {return numberOfUsers;}
   
//Setter methods for various fields
   public void setFirstName(String newFirstName) {this.firstName = newFirstName;}
   public void setLastName(String newLastName) {this.lastName = newLastName;}
   public void setUsername(String newUsername) {this.username = newUsername;}
   public void setPassword(String newPassword) {this.password = newPassword;}
   
//toString method - I'm just using it for some testing purposes.   
   @Override
   public String toString() {
   String output = "The user's name is " + this.getFirstName() + " " + this.getLastName() + " and has a username of " + this.getUsername() + ". This user's ID number is " + this.getUserId() + ". Currently, there are " + this.getNumberOfUsers() + " users in the system.";
   return output;
   }
   
}