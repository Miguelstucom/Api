package models;

import java.io.Serializable;


import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;


/**
 * The persistent class for the usertypeid database table.
 * 
 */
@Entity
@NamedQuery(name="Usertypeid.findAll", query="SELECT u FROM Usertypeid u")
public class Usertypeid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="usertypeid", cascade={CascadeType.ALL})
	private List<User> users;

	public Usertypeid() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUsertypeid(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUsertypeid(null);

		return user;
	}

}