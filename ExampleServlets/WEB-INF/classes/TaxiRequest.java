import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaxiRequest extends HttpServlet {
	
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // Returns the content type used for the MIME body sent in this response. The content type proper must have 
        // been specified using setContentType(String) before the response is committed. If no content type
        // has been specified, this method returns null. If no character encoding has been specified, 
        // the charset parameter is omitted.
        
        // In short, it basically tells the client (the Web Browser) what content type it is so that it knows what to do with it
        response.setContentType("text/html");

        // The PrintWriter class prints formatted representations of objects to a text-output stream. Also, getWriter() returns
        // a PrintWriter object
        PrintWriter out = response.getWriter();
        
        // MIME BODY(Multi-Purpose Internet Mail Extensions) 
        // is an extension of the original Internet e-mail protocol that lets people use the protocol to exchange different 
        // kinds of data files on the Internet: audio, video, images, application programs, and other kinds, as well as the 
        // ASCII text handled in the original protocol, the Simple Mail Transfer Protocol (SMTP)
        
        // MIME BODY
        out.println("<html>");
        out.println("<head>");
        out.println("<title>WCC Express Taxi Service</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<h3>WCC Taxi Service</h3>");
        
        // With the HttpServletRequest object your able to call a inhireted function from the Interface ServletRequest
        // called getParameter(), which returns the value of a request parameter(input, select, etc) as a String, or null  
        // if the parameter does not exist. Request parameters are extra information sent with the request.
        String firstName = request.getParameter("Firstname");
        String lastName = request.getParameter("Lastname");
        String Destination = request.getParameter("destination");
        String pickup_location = request.getParameter("Location");
		  String pickup_time = request.getParameter("pickup_Time");
        String card = request.getParameter("Card");
        
        // Verifies all fields are entered and if not then display a error message. Also, the file is not written unless
        // all fields are entered with a text
        if ((firstName != null&&firstName != "") && (lastName != null&&lastName != "") && (Destination != null&&Destination != "") && (pickup_time != null&&pickup_time != "") && (pickup_location != null&&pickup_location != "")) 
		  {	
                  // Allow you to write to a file in the same manor as System.out.print() vs BufferedWriter's member 
                  // functions like write() and newLine()
                  
                  PrintWriter fileWriter = new PrintWriter(new FileOutputStream("TaxiRequestFile.txt"));  

                  //Write request info to a text file named TaxiRequestFile
                  fileWriter.print("First Name:");
                  fileWriter.println(" = " + firstName);
                  fileWriter.print("Last Name:");
                  fileWriter.println(" = " + lastName);
                  fileWriter.print("Destination:");
                  fileWriter.println(" = " + Destination);
                  fileWriter.print("Pickup Location:");
                  fileWriter.println(" = " + pickup_location);
                  fileWriter.print("Pickup Time:");
                  fileWriter.println(" = " + pickup_time);
                  fileWriter.print("Card:");
                  fileWriter.println(" = " + card);
                  fileWriter.flush();
                  fileWriter.close();   
                  
                  out.println("Thank you, " + firstName+" "+lastName + " for submitting your request. A car will be picking you up soon.....");     
   
        } 
		
		    else 
          {
          
            // Error message 
            out.println("All fields are required to submit request. Please make sure all fields are completed.....");
            
          }
          
        // MIME BODY 
        out.println("<P>");
        out.print("<form action=\"");
        out.print("TaxiRequest\" ");
        out.println("method=POST>");
        out.println("First Name:");
        out.println("<input type=text size=20 name=Firstname>");
        out.println("<br>");
        out.println("Last Name: ");
        out.println("<input type=text size=20 name=Lastname>");
        out.println("<br>");
        out.println("Destination: ");
        out.println("<input type=text size=20 name=destination>");
        out.println("<br>");
        out.println("Pickup Location: ");
        out.println("<input type=text size=20 name=Location>");
        out.println("<br>");
        out.println("Pickup Time: ");
        out.println("<input type=text size=20 name=pickup_Time>");
        out.println("<br>");
        out.println("<br>");
        out.println("<select name=Card>");
        out.println("<option>AMEX</option>");
        out.println("<option>VISA</option>");
        out.println("<option>MASTERCARD</option>");
        out.println("</select>");
        out.println("<br>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

}
