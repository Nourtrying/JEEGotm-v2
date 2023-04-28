package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pending database table.
 * 
 */
@Entity
@NamedQuery(name="Pending.findAll", query="SELECT p FROM Pending p")
public class Pending implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpending;

	private String confirmPassword;

	private String discordTag;

	private String email;

	private String password;

	private String username;
	
	private String phoneN;
	
	private String name;

	public String getPhoneN() {
		return phoneN;
	}

	public void setPhoneN(String phoneN) {
		this.phoneN = phoneN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pending() {
	}

	public int getIdpending() {
		return this.idpending;
	}

	public void setIdpending(int idpending) {
		this.idpending = idpending;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDiscordTag() {
		return this.discordTag;
	}

	public void setDiscordTag(String discordTag) {
		this.discordTag = discordTag;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}