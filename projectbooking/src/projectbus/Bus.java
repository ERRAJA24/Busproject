package projectbus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Bus {
	
	public static void CancelTicket(int id)throws Exception
	{
		String url="jdbc:mysql://localhost:3306/buses";
		String userName="root";
		String password="rajasugumar";
		String query="Delete from Passenger Where Bookingid=?";
		Connection con=DriverManager.getConnection(url,userName,password);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,id);
		pst.executeUpdate();
	}
	public int Buscapacity(int Userbus)throws Exception
	{
		String url="jdbc:mysql://localhost:3306/buses";
		String userName="root";
		String password="rajasugumar";
		String query="Select Capacity from bus where BusNo=?";                   
		Connection con=DriverManager.getConnection(url,userName,password);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,Userbus);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getInt(1);
	}


}
