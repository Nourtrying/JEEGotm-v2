package mvcController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import mvcModel.MemberService;

/**
 * Servlet implementation class SignInController
 */
@WebServlet("/SignInController")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		rd = request.getRequestDispatcher("Sign-in.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Retrieve the username/email and password from the login form
		System.out.println("you're in the do post od sign in servlet!");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		MemberService memberService = new MemberService();
		// Query the user database to retrieve the user with the given username/email
		User user = memberService.getUserByUsername(username);
		System.out.println("user retrieved ? " + user.getDiscordTag());
		System.out.println("password " + password);
		System.out.println("user.getProfile().getPassword() " + user.getProfile().getPassword());
		System.out.println("user.getProfile().getPassword().equals(password) : " + user.getProfile().getPassword().equals(password));
		// Verify the password entered by the user matches the one stored in the database
		if (user != null && user.getProfile().getPassword().equals(password)) {
		    // Set the user type as a session attribute
			Boolean userType;
			if(user.getProfile().getIsAdmin()) {
				userType = true;
				
			}
			else {
				userType = false;
			}
		    request.getSession().setAttribute("userType", userType);
		    request.getSession().setAttribute("username", username);


		    // Redirect the user to the appropriate page based on their user type
		    if (userType) {
		    	response.sendRedirect("GreetingController?action=admin");
		        //response.sendRedirect("admin.jsp");
		    } else  {
		    	response.sendRedirect("GreetingController?action=member");
		        //response.sendRedirect("Member.jsp");
		    }
		} else {
		    // Handle incorrect login credentials
			System.out.println("Handle incorrect login credentials");
		}

	}

}
