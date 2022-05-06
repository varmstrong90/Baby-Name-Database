package com.victoriaarmstrong.babynames.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Name is required")
	@Size(min=3, max=35, message = "Name must be between 3 and 35 characters.")
	private String name;
	
	@NotEmpty(message="E-mail address is required")
	@Email(message="Please provide a valid E-mail address")
	private String email;
	
	@NotEmpty(message="Password is required")
	@Size(min=8, max=200, message="Password must be at least 8 characters long")
	private String password;
	
	//Transient Notation allows us to attribute the confirm password
	//to the user without adding anything into the data base. 
	@Transient
	@NotEmpty(message="Please confirm your password")
	@Size(min=8, max=200, message="Password must be at least 8 characters long")
	private String confirm;
	
	@Column(updatable=false)
	private Date createdAt;
	
	public Date updatedAt;
	
	@OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    private List<BabyName> addedNames;
	
	/////////////////////////////////////////many to many//////////////////////////////
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "likes", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "babyName_id"))
    private List<BabyName> likedNames;
        
    
	public User() {}
    
    
    public List<BabyName> getLikedNames() {
    	return likedNames;
    	}

    public void setLikedNames(List<BabyName> likedNames) {
    	this.likedNames = likedNames;
    	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////
    
    public List<BabyName> getAddedNames() {
		return addedNames;
	}

	public void setAddedNames(List<BabyName> addedNames) {
		this.addedNames = addedNames;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	@PreUpdate
    protected void onUpdate(){
    	this.updatedAt = new Date();
    }
    
    @PrePersist
    protected void onCreate(){
    	this.createdAt = new Date();
    }
	
	
}
