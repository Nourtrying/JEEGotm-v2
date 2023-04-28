<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Manage Members</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,700;1,900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/Style.css">
<link rel="stylesheet" href="css/members.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/34c5593909.js" crossorigin="anonymous"></script>
</head>
<body style="background-color: #272727;">
    <nav class="navbar navbar-expand-lg navbar-dark ">
  <a class="navbar-brand" href="/src/main/webapp/WEB-INF/index.html">
    <img src="media\PNG\Asset 4@3x.png" height="40"
    loading="lazy"></a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/JEEgotm/MemberControler">Members <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="/JEEgotm/PendingController">Pending</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Events</a>
      </li>
    </ul>
    
    <div class="dropdown">
        <a
          class="dropdown-toggle d-flex align-items-center hidden-arrow"
          href="#"
          id="navbarDropdownMenuAvatar"
          role="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          <img
            src="https://mdbcdn.b-cdn.net/img/new/avatars/2.webp"
            class="rounded-circle"
            height="50"
            
            loading="lazy"
          />
        </a>
        <ul
          class="dropdown-menu dropdown-menu-end"
          aria-labelledby="navbarDropdownMenuAvatar"
          style="color:#272727"
        >
          <li>
            <a class="dropdown-item" href="#">My profile</a>
          </li>
          <li>
            <a class="dropdown-item" href="#">Settings</a>
          </li>
        </ul>
      </div>
        <div class="buttons" >
            
            <a href="/sign-in">
              <button class="signup"> Sign Out </button></a>
          </div>
    
  </div>
</nav>

<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-xs-5">
                        <h2><b>Member Management</b></h2>
                    </div>
                    <div class="col-xs-7">
                        <a href="add.jsp"><button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addMemberModal"><i class="material-icons">&#xE147;</i> <span>Add New Member</span></button></a>		
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>name</th>
                        <th>Discord Tag</th>						
                        <th>E-mail</th>
                        <th>Phone</th>
                        <th>Score</th>
                        <th>Departement</th>
                        <th>id profile</th>
                        <th>Username</th>
                        <th>password</th>
                        <th>Admin</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody >
					<c:forEach var="user" items="${requestScope.users}">
		                <tr>
		                	<td>${user.iduser}</td>
		                	<td>${user.name}</td>		
		                    <td>${user.discordTag}</td>
		                    <td>${user.email}</td>
		                    <td>${user.phoneN}</td>
		                    <td>${user.score}</td>
		                    <td>${user.dept.deptna}</td>
		                    <td>${user.profile.idprofile}</td>
		                    <td>${user.profile.username}</td>
		                    <td>${user.profile.password}</td>
		                    <td>${user.profile.isAdmin}</td>
		                    <td>
                            	<a href="/JEEgotm/MemberControler?id=${user.iduser}&action=edit"><button  class="btn btn-primary"   id="edit">edit</button></a>
								<a href="/JEEgotm/MemberControler?id=${user.iduser}&action=delete"><button class="btn btn-danger"  id="delete"><i class="material-icons">&#xE872;</i> <span>Delete</span></button></a>
                        	</td>
		                </tr>
            		</c:forEach>
             
                </tbody>
            </table>
            
        </div>
    </div>  
          
</div>    

	<!-- Delete Modal HTML -->
	<div id="deleteMemberModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<div class="modal-header">						
						<h4 class="modal-title">Delete Member</h4>
						<button type="button" class="btn btn-default" data-bs-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body" style="background: #6c6c6c;">					
						<p>Are you sure you want to delete these Records?</p>
						<p class="text-warning"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
						<input type="submit" class="btn btn-danger" name="action" value="delete">
					</div>
				</form>
			</div>
		</div>
	</div>
    


</body>
</html>