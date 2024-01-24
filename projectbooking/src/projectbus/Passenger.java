package projectbus;
import java.util.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Passenger {
	String Name;
	String userstarts;
	String userends;
	Date dateoftravel;
	int Userbus;
	int Seats;
	int BookingId;
	int j=0;
	
	public Passenger()
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Please Enter your Name:");
		Name=sc.next();
		System.out.println("Journey Starts:");  
		sc.nextLine();//Constructor
		userstarts=sc.next();
		sc.nextLine();
		System.out.println("Journey Ends:");
		userends=sc.next();
		System.out.println("No of Seats:");
		Seats=sc.nextInt();
		System.out.println("Date:");
		String dateinput=sc.next();
		SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");
		LocalDate localdate=LocalDate.now();
		Date dt2=convertToDate(localdate);
		try
		{ 
			Date dm=dateformat.parse(dateinput);
			int dc=dt2.compareTo(dm);
			if(dc<0)
			{
				dateoftravel=dm;
			}
			else
			{
				System.out.println("Invalid date");
				j++;
			}
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private Date convertToDate(LocalDate localdate) {
	        return java.util.Date.from(localdate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
	    }



	public void add_Passenger()throws Exception
	{
		Random r=new Random();
		BookingId=r.nextInt(1000);
		String url="jdbc:mysql://localhost:3306/buses";
		String userName="root";
		String password="rajasugumar";
		String query="insert into Passenger values(?,?,?,?,?)";
		Connection con=DriverManager.getConnection(url,userName,password);
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(dateoftravel.getTime());
		pst.setString(1,Name);
		pst.setInt(2,Userbus);
		pst.setDate(3,sqldate);
		pst.setInt(4, Seats);
		pst.setInt(5,BookingId);
		pst.executeUpdate();

	}
	public int Seatinfo()throws Exception
	{
		String url="jdbc:mysql://localhost:3306/buses";
		String userName="root";
		String password="rajasugumar";
		String query="select sum(Seats) from Passenger where Userbus=? and dateoftravel=?";
		Connection con=DriverManager.getConnection(url,userName,password);
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(dateoftravel.getTime());
		pst.setInt(1,this.Userbus);
		pst.setDate(2, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	public void Buscheck() throws Exception 
	   {
		String url="jdbc:mysql://localhost:3306/buses";
		String userName="root";
		String password="rajasugumar";
		String query="select * from bus where Starts=? and End=?";
		Connection con=DriverManager.getConnection(url,userName,password);   
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, this.userstarts);
		pst.setString(2, this.userends);
		ResultSet rs=pst.executeQuery();
		System.out.println("Buses for you");
		while(rs.next())
		{
		System.out.println("BusNo:"+rs.getString(2)+"\t"+"BusType:"+rs.getString(3)+"\t"+"Starts:"+rs.getString(4)+"\t"+"Ends: "+rs.getString(5)+"\t"+"Capacity "+rs.getInt(6));
		}
		con.close();
		}
	}
	

	
	

	




