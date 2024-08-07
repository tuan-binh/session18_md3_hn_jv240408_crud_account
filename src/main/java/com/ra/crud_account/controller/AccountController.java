package com.ra.crud_account.controller;

import com.ra.crud_account.dto.request.AccountRequest;
import com.ra.crud_account.model.Account;
import com.ra.crud_account.service.IAccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AccountController
{
	private final IAccountService accountService;
	
	/**
	 * @param model Model
	 * @return accounts.jsp
	 * @apiNote handle return views and data list accounts
	 * @author bình
	 * @date 18/12/2022
	 */
	@GetMapping
	public String findAll(Model model)
	{
		model.addAttribute("accounts", accountService.findAll());
		return "accounts";
	}
	
	@GetMapping("/add")
	public String viewAdd(Model model)
	{
		model.addAttribute("account", new AccountRequest());
		return "add_account";
	}
	
	@PostMapping("/add")
	public String doAddAccount(@Valid @ModelAttribute AccountRequest account, BindingResult result, Model model)
	{
		if (result.hasErrors())
		{
			model.addAttribute("account", account);
			return "add_account";
		}
		
		accountService.save(Account.builder()
				  .id(account.getId())
				  .fullName(account.getFullName())
				  .email(account.getEmail())
				  .password(account.getPassword())
				  .gender(account.getGender())
				  .status(account.getStatus())
				  .build());
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String viewEdit(@PathVariable Long id, Model model)
	{
		model.addAttribute("accountEdit", accountService.findById(id));
		return "edit_account";
	}
	
	@PostMapping("/edit")
	public String doUpdate(@Valid @ModelAttribute("accountEdit") AccountRequest account, BindingResult result, Model model)
	{
		if (result.hasErrors())
		{
			model.addAttribute("accountEdit", account);
			return "edit_account";
		}
		accountService.save(Account.builder()
				  .id(account.getId())
				  .fullName(account.getFullName())
				  .email(account.getEmail())
				  .password(account.getPassword())
				  .gender(account.getGender())
				  .status(account.getStatus())
				  .build());
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAccount(@PathVariable Long id)
	{
		accountService.deleteById(id);
		return "redirect:/";
	}
	
}
