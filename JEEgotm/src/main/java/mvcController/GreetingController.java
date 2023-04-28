package mvcController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GreetingController
 */
@WebServlet("/GreetingController")
public class GreetingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GreetingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the current session
        HttpSession session = request.getSession();

        // Retrieve the user type from the session attribute
        Boolean userType = (Boolean) session.getAttribute("userType");
        
        String action = request.getParameter("action");
        
        String username = (String) session.getAttribute("username");
        
        if (action != null && action.equals("admin") && userType != null && userType) {
            // Admin user
        	String message = "Hello Admin " + username + "!";
        	request.setAttribute("message", message);

        	// Forward the request to the JSP
        	request.getRequestDispatcher("admin.jsp").forward(request, response);
            
            response.getWriter().append("Hello Admin ").append(username).append("!");
        } else if (action != null && action.equals("member") && userType != true)  {
        	// Set the greeting message as a request attribute
        	String message = "Hello Member " + username + "!";
        	request.setAttribute("message", message);

        	// Forward the request to the JSP
        	request.getRequestDispatcher("Member.jsp").forward(request, response);


        }
        // Check if the user is an admin or a member and display a greeting message
        
        else {
            response.getWriter().append("User is not logged in!");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
