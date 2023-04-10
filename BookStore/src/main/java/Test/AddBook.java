package Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addlink")
public class AddBook extends HttpServlet{
	Connection con;
	public void init()throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/1ejm9?user=root&password=sql@123");            

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String author=req.getParameter("author");
		String price=req.getParameter("price");
		String stock=req.getParameter("stock");
		
		double price1=Double.parseDouble(price);
		int stock1=Integer.parseInt(stock);
		
		//DECLARE THE RESOURCES
		PreparedStatement pstmt=null;
		
		//WRITE THE QUERY
		String query="insert into book_store(name,author,price,stock)values(?,?,?,?)";
		
		try {
			//CREATE PLATFORM
			pstmt=con.prepareStatement(query);
			//SET THE VALUES
			pstmt.setString(1, name);
			pstmt.setString(2, author);
			pstmt.setDouble(3, price1);
			pstmt.setInt(4, stock1);
			//EXECUTE THE QUERY
			int count=pstmt.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+"BOOK ADDED SUCCESSFULLY</h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
