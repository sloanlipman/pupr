package com.pupr;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class User{

//Attributes
    private int userId;
    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String password = "";
    private String dogName = "";
    private String bio = "";
    private double totalScore = 0;
    private int numberOfRatings = 0;
    private double averageRating = 0;
    private Drawable pic;


    Queue<User> votingQueue = new LinkedList<>(); //Individual voting queue for each user
    private static int nextUser = 0; //incremented each time a user is created to generate a unique userId
    static ArrayList<User> userList = new ArrayList<>(); //provide a list of users in an ArrayList structure for user authentication.
    ArrayList<User> votedOn = new ArrayList<>(); //An ArrayList that holds the id for which dogs a user has voted on
    static ArrayList<User> leaderboard = new ArrayList<>(); //ArrayList of Users to indicate their ranking in the leaderboard

//static Users to mark current user
    static User activeUser; // defines current user of the session
    static User currentDog; //defines the next Dog for a User to vote on

//update the current user of the session
    static void setActiveUser(User user) {
        activeUser = user;
    }


//Setter methods
    void setDogName(String newDogName) {
        this.dogName = newDogName;
    }
    void setBio(String newBio) {
        this.bio = newBio;
    }
    void setPic(Drawable newPic) {
        this.pic = newPic;
    }
    void incrementRatings() {
        this.numberOfRatings++;
    }
    void addScore(int vote) {


        this.totalScore = this.totalScore + vote;
        this.averageRating = this.totalScore / this.numberOfRatings;

    //Update leaderboard
        for (int i = 0; i < leaderboard.size(); i++) {
            User compare = leaderboard.get(i);
            if (this.averageRating > compare.averageRating ||  //if the dog that was just voted on has a higher average rating than the dog @ index i
                    (this.averageRating == compare.averageRating && this.totalScore > compare.totalScore)) { //OR the dogs have the same average, but the dog that was just voted on has a higher total
                leaderboard.add(i, this); //move the dog to i
                leaderboard.remove(leaderboard.lastIndexOf(this)); //remove the original entry of the dog
            } else if (this.averageRating == compare.averageRating && this.totalScore < compare.totalScore) {
                leaderboard.add(i + 1, this); //move dog to i+1
                leaderboard.remove(leaderboard.lastIndexOf(this));
            } else if (this.averageRating == compare.averageRating && this.totalScore == compare.totalScore) { //same average rating, same total score
                if (this.numberOfRatings >= compare.numberOfRatings) { //if dog has the same # of votes OR more votes (we have to make the cutoff somewhere)
                    leaderboard.add(i, this);
                    leaderboard.remove(leaderboard.lastIndexOf(this));
                } else {
                    leaderboard.add(i + 1, this);
                    leaderboard.remove(leaderboard.lastIndexOf(this));
                }

            }
        }
        //Print leaderboard to log
        for (int i = 0; i < leaderboard.size(); i++) {
            User curr = leaderboard.get(i);
            Log.d("Leaderboard pos #" + i, curr.dogName);
            Log.d("Average Rating", "" + curr.averageRating);
            Log.d("Total Score", "" + curr.totalScore);
            Log.d("# of votes", "" + curr.numberOfRatings);

        }

    }
//Setter methods used for loading app state from csv
    void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }
    void setRatings(int ratings) {
        this.numberOfRatings = ratings;
    }
    void setAverage(double average) {
        this.averageRating = average;
    }




//Constructors
    User() {}    //Empty constructor

//Constructor method with specified names and password -- useful for defaultpicture objects
    User(String fName, String lName, String uname, String pass) {
        this.firstName = fName;
        this.lastName = lName;
        this.username = uname;
        this.password = pass;
        this.userId = nextUser; //assign the next available userid to this user
        nextUser++; //increment the next available ID
        this.votedOn.add(this); //adds user's own id to the votedOn list so that a user cannot vote on his or her own dog
        leaderboard.add(this); //adds a new User to the end of the leaderboard ArrayList; since a new User has a score of 0, they should be at the end!
    }

//Generate a votingQueue for a User
    void makeQueue() {

        for (int i = 0; i < userList.size(); i++) { //trace the userList
            boolean hit = false; //hit is true if the user has voted on a particular user already

            for (int j = 0; j < activeUser.votedOn.size(); j++) { //trace the list of dogs the user has already voted on
                if (userList.get(i).equals(votedOn.get(j)))  //if the user at index i has been voted on already
                    hit = true;
            }

            if (!hit) {
                votingQueue.add(userList.get(i)); //add user @ i to queue if that user has not been voted on yet

                Log.d("New element in Queue", "" + userList.get(i).getDogName());
                Log.d("Queue size", "" + User.activeUser.votingQueue.size());
            }
        }
    }

//Getter methods for various fields
    String getFirstName() {return this.firstName;}
    String getLastName()  {return this.lastName;}
    String getUsername() {return this.username;}
    int getUserId() {return this.userId;}
    String getPassword() {return this.password;}
    public User getUser() {return this;} //returns the entire user
    String getDogName() {
        return this.dogName;
    }
    String getBio() {
        return this.bio;
    }
    Drawable getPicture() {
        return this.pic;
    }
    double getScore() {
        return this.totalScore;
    }
    int getRatings() {
        return this.numberOfRatings;
    }
    double getAverage() {
        return this.averageRating;
    }

//String method that takes an integer argument, used to print out leaderboard information
    String toLeaderboard(int i) {
        return ("Ranking # " + i + "\nName: " + this.getDogName() + "\nOwner: " + this.getFirstName() + " " + this.getLastName() + "\n" +
                "Average Score: " + this.getAverage() + "\n" +
                "Total Score: " + (int) this.getScore() + "\n" +
                "Total Votes: " + this.getRatings());
    }

//String method to return userList -- for debugging purposes
    static void printUserList() {
         for (int i = 0; i < userList.size(); i++) {
             Log.d("Index on userList:", "" + i);
             Log.d("Username:", "" + userList.get(i).getUsername());
             Log.d("userId", "" + userList.get(i).getUserId());
        }
    }

//Print which dogs the active user has voted on -- for debugging purposes
    static void printVotedOn() {
        for(int i = 0; i < activeUser.votedOn.size(); i++){
            Log.d("VotedOn", "" + activeUser.votedOn.get(i).getDogName());
        }
    }

}