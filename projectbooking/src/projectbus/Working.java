package projectbus;
import java.util.Scanner;
import java.sql.*;

public class Working{
	
		public static void main(String[] args) throws Exception
		{
			System.out.println("    WELCOME!   ");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter 1 For Booking...");
			System.out.println("Enter 2 For Ticket Cancel...");
			int User=sc.nextInt();
			if(User==1)
			{

           Passenger p=new Passenger();
           if(p.j==0)
           {
		 try
		 { 
			 p.Buscheck();
		 }
		 catch(Exception sq)
		 {
			 System.out.println(sq);
		 }
		  p.Userbus=sc.nextInt();
		  Bus b=new Bus();
		 if(p.Seatinfo()<b.Buscapacity(p.Userbus))
		 {
			 if(b.Buscapacity(p.Userbus)-p.Seatinfo()>=p.Seats)
			 {
			System.out.println("<<<<TICKET BOOKED>>>");
			p.add_Passenger();
			System.out.println("Your BookingId is:"+p.BookingId);
			System.out.println("Tickets Booked!...Enjoy Your Journey");
			 }
			 else
			 {
				 System.out.println("SORRY...Insufficient number of tickets");
			 }
		}
		 else
		 {
			 System.out.println("SORRY...No Tickets Available For this Bus");
		 }
			}
         else
			{
			System.out.println("Try Again!...");
			}
					

}
			else if(User==2)
			{
				System.out.println("Enter Your BookingId:");
				int id=sc.nextInt();
				Bus.CancelTicket(id);
				{
				System.out.println("Your Ticket is Cancelled");
				}
			}


			
			System.out.println("Thank You!...");
					}


}
