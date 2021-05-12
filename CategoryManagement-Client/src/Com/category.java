package Com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class category {
	
	private Connection connect() 
	{
		
		Connection con = null;
		
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/categorydb", "root", "dilshik980310*@1");
			
			System.out.println("Successfully Connected");
			
			
		}
		catch(Exception e) 
		{
			
			e.printStackTrace();
			
			
		}
		
		return con;
		
	}
	
	
	
	public String readCategories() {
		
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to the db for reading";
				
			}
			
			
				output = "<table border='1'><tr><th>Category ID</th><th>Category Name</th>" + 
				"<th>Description</th>" +
				 "<th>Tag Code</th>" +
				"<th>Tag Name</th>" +
				 "<th>Update</th><th>Remove</th></tr>";
				
				String query = "Select * from categorydb.category";
				
				PreparedStatement ps =  con.prepareStatement(query);
				
				ResultSet rs;
				
				rs = ps.executeQuery(query);
				
				// iterate through the rows in the resultset
				while (rs.next()) {
					
					String ID = Integer.toString(rs.getInt("ID"));
					String CategoryID = rs.getString("CategoryID");
					String CategoryName = rs.getString("CategoryName");
					String Description = rs.getString("Description");
					String TagCode = rs.getString("TagCode");
					String TagName = rs.getString("TagName");
							
					//add a row into the html table
					
					
					output += "<tr><td>" + CategoryID + "</td>";
					output += "<td>" + CategoryName + "</td>";
					output += "<td>" + Description + "</td>";
					output += "<td>" + TagCode + "</td>";
					output += "<td>" + TagName + "</td>";
					
					
					output += "<td><input name='btnUpdate' type='button' value='Update' "
					+ "class='btnUpdate btn btn-secondary' data-Id='" + ID + "'></td>"		
					+ "<td><input name='btnRemove' type='button' value='Remove' " 
							 + "class='btnRemove btn btn-danger' data-Id='" + ID + "'></td></tr>";  
							 
					}
					
					con.close();
				
					output += "</table>";
				
				
			
			
		}
			catch(Exception e) {
			
				output = "Error while reading the categories ";
				System.err.println(e.getMessage());
				
				
			}
			
			
			return output;
		
	}
	
	
	public String updateCategories(String id,String categoryid,String categoryname, String description,String tagcode, String tagname) {
		
		String output = "";
		
		try {
				Connection con = connect();
				
				if(con == null) {
					
					return "cannot update the data";
					
				}
				
					String query = "Update categorydb.category set `CategoryID` = ?, `CategoryName` = ?, `Description` = ?, `TagCode` = ?, `TagName` = ? where `ID` = ?";
					
					PreparedStatement ps = con.prepareStatement(query);
					
					ps.setString(1, categoryid);
					ps.setString(2, categoryname);
					ps.setString(3, description);
					ps.setString(4, tagcode);
					ps.setString(5, tagname);
					ps.setInt(6, Integer.parseInt(id));
					
					
					//execute statement
					ps.execute();
					con.close();
					
					String newCategories = readCategories();
					
					output = "{\"status\":\"success\", \"data\": \"" + newCategories + "\"}";
					
					
			  }catch(Exception e) {
				
				output = "{\"status\":\"success\", \"data\": \"Error while Updating the category\"}";
				
				System.err.println(e.getMessage());
			
			}	
				
				
			return output;	
		
		
		
	}
	
	public String deleteCategories(String id) {
		
		String output = "";
		
		try {
				Connection con = connect();
				
				if(con == null) {
					
					return "Error while connecting to the db for deleting";
				
				}
				else
				{
					
					String query = "delete from categorydb.category where ID = ?";
					
					//create preparedStatement
					PreparedStatement ps = con.prepareStatement(query);
					
					//binding values
					ps.setInt(1,Integer.parseInt(id));
					
					//execute the statement
					
					ps.execute();
					con.close();
					
					String newCategories = readCategories();
					
					output = "{\"status\":\"success\", \"data\": \"" + newCategories + "\"}";
					
				}
				
				
			
			}catch(Exception e) 
				{
					output = "{\"status\":\"success\", \"data\": \"Error while Delete category\"}";
					System.err.println(e.getMessage());
			
				}
		
				
				return output;
		
		
		}

	public String insertCategories(String categoryID, String categoryName, String description,
			String tagCode, String tagName) {
		
		
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null) {
				
				return "Error while connecting to the database";
				
			}
			
			String query = " insert into categorydb.category(`ID`,`CategoryID`,`CategoryName`,`Description`,`TagCode`,`TagName`)" + " values (?,?,?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, 0);
			ps.setString(2, categoryID);
			ps.setString(3, categoryName);
			ps.setString(4, description);
			ps.setString(5, tagCode);
			ps.setString(6, tagName);
			
			ps.execute();
			con.close();
			
			String newCategories = readCategories();
			output = "{\"status\":\"success\", \"data\": \"" + newCategories + "\"}";
			
		}
			
			catch(Exception e) {
				
				output = "{\"status\":\"success\", \"data\": \"Error while inserting the category\"}";
				
				System.err.println(e.getMessage());
				
			}
		
			return output;
			
	}



}
