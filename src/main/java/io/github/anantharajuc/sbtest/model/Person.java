package io.github.anantharajuc.sbtest.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.anantharajuc.sbtest.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity
{
	//Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min=3, max=15, message="Name must be between 3 and 15 characters.")
	@Column(name="name", nullable = false)
    private String name;
	
	@NotBlank
	@Column(name = "username", unique=true)
	@Size(max = 15)
	private String username;
	
	@Email
	@Size(max=255, message="Must be a valid email id")
	@Column(name="email_primary", unique=true, nullable = false)
	private String emailPrimary;
	
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Column(name="email_secondary", nullable = true)
	private String emailSecondary;
	
	@Column(name="phone", unique=true, nullable = false)
	private Long phone;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender", nullable = false)
	private GenderEnum gender;
	
	@Column(name="age", nullable = true)
	private int age;
	
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(max = 100)
	@Column(name = "password")
	private String password;
	
	@Past
	@JsonFormat(pattern="dd-MM-yyyy", timezone="Asia/Kolkata")
	@Column(name="dob", nullable = true)
	private LocalDate dob;
	
	@Column(name = "is_adult", nullable = false, length = 1)
	private Boolean isAdult;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;
}
