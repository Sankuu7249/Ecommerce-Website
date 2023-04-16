import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
public class RegisterServlet extends HttpServlet
{
  Connection con;
  public void init()
  {
    try
    {
       Class.forName("oracle.jdbc.driver.OracleDriver");
       con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
  }
  public void service(HttpServletRequest hreq,HttpServletResponse hres)
  {
    try
    {
      Date currentDate = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String s1=hreq.getParameter("name");
      String s2=hreq.getParameter("no");
      String s3=hreq.getParameter("mail");
      String s4=hreq.getParameter("pwd");
      String s5= formatter.format(currentDate);
      PreparedStatement pstmt=con.prepareStatement("insert into amazon values(?,?,?,?,?)");
      pstmt.setString(1,s1);
      pstmt.setString(2,s2);
      pstmt.setString(3,s3);
      pstmt.setString(4,s4);
      pstmt.setString(5,s5);
      pstmt.executeUpdate();
      PrintWriter pw=hres.getWriter();
      pw.println("<html><body bgcolor=yellow text=red><center>");
      pw.println("<h1><B>Your Account has been Created Succesfully</B></h1>");
      pw.println("<a href=login.html>Sign In: "+s5+"</a>");
      pw.println("</center></body></html>");
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }
}