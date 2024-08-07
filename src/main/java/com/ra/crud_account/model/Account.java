package com.ra.crud_account.model;

import com.ra.crud_account.validation.annotation.EmailExist;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	private String email;
	private String password;
	private Boolean gender = true;
	private Boolean status = true;
	
	public Account()
	{
	}
}
