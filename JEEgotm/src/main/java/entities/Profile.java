package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idprofile;

	@Column(name="is_admin")
	private boolean isAdmin;

	private String password;

	private String username;

	//bi-directional one-to-one association to User
	@OneToOne(mappedBy="profile")
	private User user;

	public Profile() {
	}

	public int getIdprofile() {
		return this.idprofile;
	}

	public void setIdprofile(int idprofile) {
		this.idprofile = idprofile;
	}

	public boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}