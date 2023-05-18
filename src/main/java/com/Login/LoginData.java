package com.Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


	@WebServlet("/register")
	public class LoginData extends HttpServlet {

	    //create the query 
		private static final String INSERT_QUERY ="INSERT INTO USER(PhoneNO,Emailid,Password) VALUES(?,?,?)";

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,
	    IOException {
	        //get PrintWriter
	        PrintWriter pw = res.getWriter();
	        //set Content type
	        res.setContentType("text/hmtl");
	        //read the form values
	        String PhoneNo = req.getParameter("PhoneNO");
	        String Emailid = req.getParameter("Email-id");
	        String Password = req.getParameter("Password");
	        

	      
	        //load the jdbc driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        //create the connection
	        try(Connection con = DriverManager.getConnection("jdbc:mysql:///WhatsApp","root","krushna");
	                PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
	            //set the values
	            ps.setString(1, PhoneNo);
	            ps.setString(2, Emailid);
	            ps.setString(3, Password);
	            

	            //execute the query
	            int count = ps.executeUpdate();

	            if(count==0) {
	                pw.println("Record not stored into database");
	            }else {
	                pw.println("Record Stored into Database");
	            }
	        }catch(SQLException se) {
	            pw.println(se.getMessage());
	            se.printStackTrace();
	        }catch(Exception e) {
	            pw.println(e.getMessage());
	            e.printStackTrace();
	        }

	      /*  
	        System.out.println("NAME"+ name);
	        System.out.println("city:"+city);
	        System.out.println("mobli"+ mobile);
	        System.out.println("dob:"+ dob);
	        //close the stream
	         * 
	         */
	        pw.close();
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, 
	    IOException {
	        // TODO Auto-generated method stub
	        doGet(req, resp);
	    }


}
