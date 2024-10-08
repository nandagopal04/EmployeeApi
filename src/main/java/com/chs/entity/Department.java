package com.chs.entity;

import java.io.Serializable;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="department")
public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8565360193632071314L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="department_id", referencedColumnName = "id")
	List<Employee> employees;
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
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
	public Department(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(String name) {
		this.name = name;
	}
}
