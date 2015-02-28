
package dao;

import java.sql.*;

public class DataBaseConnection {
	Connection conn;
	Statement st;
	ResultSet rs;
	public DataBaseConnection() 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/pharmacie";
			conn=DriverManager.getConnection(url,"root","");
			st = conn.createStatement();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}

	}
	public ResultSet getSelection(String sql)
	{
		try 
		{
			rs = st.executeQuery(sql);
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return rs;
	}
	public int getNumberOfRecords(String sql)
	{
		rs= getSelection(sql);
		int i = 0;
		try
		{
			while(rs.next())
			{
				i++;
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return i;

	}
	public void execute(String sql)
	{
		try
		{
			st.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
