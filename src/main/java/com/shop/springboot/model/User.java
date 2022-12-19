package com.shop.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email") })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  @NotBlank(message = "username must not be blank")
  @Size(max = 20, min = 3, message = "username must be between {min} and {max} characters")
  private String username;
  @Column(unique = true)
  @NotBlank(message = "email must not be blank")
  @Size(max = 50, message = "email must be shorter than {max} characters")
  @Email
  private String email;
  @Column(name = "phoneNo")
  private String phoneNo;
  @JsonIgnore
  @NotBlank(message = "password must not be blank")
  @Size(max = 60, min = 6, message = "password must be between {min} and {max} characters")
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
  @Column(length = 2)
  @Nullable
  private int age;
  @Nullable
  private String name;
  @Nullable
  private String surname;

  @Column(name = "status")
  private String status = "activated";

  @Nullable
  private String address;
  @Nullable
  private String city;
  @Nullable
  private String country;
  @Nullable
  private String job;
  @Nullable
  @Size(max = 600)
  private String description;
  @Nullable
  @Column(nullable = true)
  private boolean image;


  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public User(String username, String email, String password, Set<Role> roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
