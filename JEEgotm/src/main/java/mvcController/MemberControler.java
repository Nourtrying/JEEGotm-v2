package mvcController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Profile;
import entities.User;
import mvcModel.MemberService;

/**
 * Servlet implementation class MemberControler
 */
@WebServlet("/MemberControler")
public class MemberControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private MemberService memberService;
	
	RequestDispatcher rd;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("hello! the action is :");
        System.out.println(action);
        MemberService serve = new MemberService();
        if (action != null) {
            if (action.equals("acceuil")) {
            	List<User> users = new ArrayList<User>();
        		users = memberService.getAllUsers();
        		request.setAttribute("users", users);
        		rd = request.getRequestDispatcher("ManageMembers.jsp");
        		rd.forward(request, response);  	
            }
            else if(action.equals("edit")) {
            	String id = request.getParameter("id");
            	int idInt = Integer.parseInt(id);
            	User u = serve.getUserById(idInt);
            	String  m = u.getDiscordTag();
            	System.out.println(m);
            	
            	request.setAttribute("iduser", u.getIduser());
                request.setAttribute("name", u.getName());
                request.setAttribute("DiscordTag", u.getDiscordTag());
                request.setAttribute("email", u.getEmail());
                request.setAttribute("phoneN", u.getPhoneN());
                request.setAttribute("score", u.getScore());
                request.setAttribute("dept", u.getDept().getDeptna());
                request.setAttribute("profile_idprofile", u.getProfile().getIdprofile());
                request.setAttribute("username", u.getProfile().getUsername());
                request.setAttribute("password", u.getProfile().getPassword());
                request.setAttribute("is_admin", u.getProfile().getIsAdmin());
            	
            	rd = request.getRequestDispatcher("Edit.jsp");
        		rd.forward(request, response);
            }
            else if(action.equals("delete")) {
            	System.out.println("you're in the delete from doGet ?");
            	
            	
            	rd = request.getRequestDispatcher("delete.jsp");
        		rd.forward(request, response);
            	
            	 
            	
            	
            }
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("you are in the do post");
		System.out.println("and the action is");
		String action = request.getParameter("action");
        System.out.println(action);
        
        //getting all the attributes at once
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String discord = request.getParameter("discord");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        
        
        String department = request.getParameter("department");
        String password = request.getParameter("password");
        boolean isAdmin = request.getParameter("isAdmin") != null;
        
        
        MemberService serve = new MemberService();
        
        if (action != null) {
            if (action.equals("addMember")) {
            	System.out.println("you're in the add section");
            	String score = request.getParameter("score");
                int scoreInt = Integer.parseInt(score);
                
                System.out.println("department is");
                System.out.println(department);
                User u = new User();
                u.setName(name);
                u.setDiscordTag(discord);
                u.setEmail(email);
                u.setPhoneN(phone);
                u.setScore(scoreInt);
                
                System.out.println("user object is :");
                System.out.println(u.getDiscordTag());
                
                Profile p = new Profile();
                
                p.setUsername(username);
                p.setPassword(password);
                p.setIsAdmin(isAdmin);
                
                
                String m = serve.addUser(u, p, department);
                
                System.out.println(m);
                
                
            	
            }
            else if (action.equals("editMember")) {
            	System.out.println("you're in the add edit");
            	
            	String score = request.getParameter("score");
                int scoreInt = Integer.parseInt(score);
                
            	String id = request.getParameter("id");
            	int idInt = Integer.parseInt(id);
            	
            	System.out.println("id is in the edit " + id);
            	
            	String idprofile = request.getParameter("idprofile");
                int idprofileint = Integer.parseInt(idprofile);
            	 
            	Profile p = new Profile();
            	p.setIdprofile(idprofileint);
                p.setUsername(username);
                p.setPassword(password);
                p.setIsAdmin(isAdmin);
                
            	serve.updateUser(idInt, name, discord, email, phone, scoreInt, department, p);
            	
            	
            	
            }
            else if(action.equals("delete")) {
            	System.out.println("you're in the delete from doPost ?");
            	
            	String id = request.getParameter("id");
            	System.out.println("the id is " + id);
            	int idInt = Integer.parseInt(id);
            	
            	String m = serve.deleteUser(idInt);
            	
            	System.out.println(m);
            	
            	
            	 
            	
            	
            }
             
        }
	}

}
