<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Edit Members</title>
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
<form action="/JEEgotm/MemberControler" method="post">
					<div >						
						<h4 >Edit Member</h4>
					</div>
					<input type="hidden" id="action" for="action" name="action" value="editMember">
					<div class="modal-body" >
					<div class="form-group">
                            <label for="id">id</label>
                            <input type="text" name="id" for="id" id="id" class="form-control" value="${iduser}" required>
                        </div>					
						<div class="form-group">
                            <label for="username">username</label>
                            <input type="text" name="username" for="username" value="${username}" id="username" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="name">name</label>
                            <input type="text" name="name" for="name" id="name" value="${name}" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="discord">Discord Tag</label>
                            <input type="text" name="discord" for="discord" value="${DiscordTag}" id="discord" class="form-control" required>
                        </div>
						<div class="form-group">
							<label for="email">Email</label>
							<input type="email" name="email" for="email" value="${email}" id="email" class="form-control" required>
						</div>
						<div class="form-group">
							<label for="phone">Phone</label>
							<input type="text" name="phone" for="phone" id="phone" value="${phoneN}" class="form-control" required>
						</div>
						<div class="form-group">
							<label for="score">score</label>
							<input type="text" name="score" for="score" value="${score}" id="score" class="form-control" required>
						</div>
						<div class="form-group">
	                        <label>Departement</label>
	                        <select class="form-control" name="department" required>
								  <option value="admin" ${dept == "admin" ? "selected" : ""}>admin</option>
								  <option value="marketing" ${dept == "marketing" ? "selected" : ""}>marketing</option>
								  <option value="sponsoring" ${dept == "sponsoring" ? "selected" : ""}>sponsoring</option>
							</select>
                    	</div>
                    	
						<div class="form-group">
							<label for="password">password</label>
							<input type="text" value="${password}" name="password" for="password" id="password" class="form-control" required>
						</div>
						<div class="form-group">
							<label for="idprofile">id profile</label>
							<input type="text" value="${profile_idprofile}" name="idprofile" for="idprofile" id="idprofile" class="form-control" required>
						</div>
						<div class="form-check form-switch">
						    <input class="form-check-input" ${ is_admin == true ? "checked" : ""} type="checkbox" name="isAdmin" id="isAdmin">
						    <label class="form-check-label" for="isAdmin">is admin?</label>
						</div>

					</div>
					<div>
						<input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
						<input type="submit" class="btn btn-info" value="Save">
					</div>
				</form>
            
   
</body>
</html>