package com.ra.crud_account.controller;

import com.ra.crud_account.model.Account;
import com.ra.crud_account.service.IAccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	public String viewAdd()
	{
		return "add_account";
	}
	
	@PostMapping("/add")
	public String doAddAccount(@ModelAttribute Account account)
	{
		accountService.save(account);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAccount(@PathVariable Long id)
	{
		accountService.deleteById(id);
		return "redirect:/";
	}
	
}
