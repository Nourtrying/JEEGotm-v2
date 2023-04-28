package mvcController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pending;
import entities.User;
import mvcModel.MemberService;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	RequestDispatcher rd;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		rd = request.getRequestDispatcher("Sign-up.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String name = request.getParameter("name");
        String discord = request.getParameter("discord");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        
        MemberService serve = new MemberService();
        
        Pending p = new Pending();
        p.setPassword(password);
        p.setUsername(username);
        p.setConfirmPassword(phone);
        p.setName(name);
        p.setDiscordTag(discord);
        p.setEmail(email);
        p.setPhoneN(phone);
        
        System.out.println("hii dopost signup");
        System.out.println(name);
        
        String message = serve.addPending(p);
		// TODO Auto-generated method stub
        String url = request.getContextPath() + "/JEEgotm/SignupController?message=" + message;
        response.sendRedirect(url);
	}

}
