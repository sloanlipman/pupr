public class Queue {
   private Node first, last;
   private int size;
  
   //constructor method to create a Queue object with first, last, and size. 
   public Queue()
   {
      first = null;  // pointer for the front of the queue
      last = null;  // pointer for the end of the queue
      size = 0;     // queue size
   }
  
//Add to the queue
   public void enqueue(Object o) 
   {
      if (last == null) 
         first = last = new Node(o); //empty queue
      else 
      {
         last.next = new Node(o); //link new node as last node
         last = last.next; //make last pointer points to new last node
      }
      size++; //increase list size by one
   }

//Serve the Dog at the front of the Queue            
   public void dequeue(){
         Node temp = first; //temp points to the first node
         first = first.next; //make first point to the second node
         temp.next = null; //remove the link from the queue
         size--; //decrement size of the list
         if (first == null) last = null; //if there was only one node, list is now empty 
    }
   
   
   //Return the first element 
   public int front() {
      if (first != null)
         return this.first.o;
      else return -1; //if no elements in the queue
      }   

  
   //class to create nodes of the queue as objects
   private class Node
   {
      private Object o;  //o field
      private Node next; //link field
       
      public Node(Object item) //constructor method
      {
         o = item;
         next = null;
      }
   }
}
