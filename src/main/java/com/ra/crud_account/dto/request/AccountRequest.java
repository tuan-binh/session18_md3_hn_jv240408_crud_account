package com.ra.crud_account.dto.request;

import com.ra.crud_account.validation.annotation.EmailExist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountRequest
{
	private Long id;
	@NotBlank(message = "tên không được để trống")
	private String fullName;
	@EmailExist(message = "email đã bị trùng")
	@NotBlank(message = "email không được để trống")
	@Pattern(regexp = "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", message = "email không đúng định dạng")
	private String email;
	@NotBlank(message = "password không được để trống")
	private String password;
	private Boolean gender = true;
	private Boolean status = true;
}
