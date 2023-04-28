package mvcController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pending;
import mvcModel.MemberService;

/**
 * Servlet implementation class PendingController
 */
@WebServlet("/PendingController")
public class PendingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingController() {
        super();
        // TODO Auto-generated constructor stub
    }
    RequestDispatcher rd;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Pending> users = new ArrayList<Pending>();
		MemberService memberService = new MemberService();
		users = memberService.getAllPending();
		request.setAttribute("users", users);
		rd = request.getRequestDispatcher("Pending.jsp");
		rd.forward(request, response);
		
		
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		String action = request.getParameter("action");
		System.out.println("action is:");
		System.out.println(action);
		if ("delete".equals(action)) {
		    int id = Integer.parseInt(request.getParameter("id"));
		    service.deletePending(id);
		    response.sendRedirect("PendingController");
		    return;
		}
		System.out.println("you're in the PendingController doPost's");
		
		String[] selectedUserIds = request.getParameterValues("selectedUsers");
		String dept = request.getParameter("department");
		System.out.println(dept);
		

        if (selectedUserIds == null || selectedUserIds.length == 0) {
        	
            response.sendRedirect("promote-users.jsp?error=Please select at least one user to promote.");
        }
        else {
        	System.out.println("selectedUser sint null");
        	service.promoteUsers(selectedUserIds, dept);
        }
        

	}

}
