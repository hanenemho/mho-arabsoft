package org.sid.catservice.controller;

import java.util.List;

import org.sid.catservice.entities.ParamPays;
import org.sid.catservice.service.ParamPaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ParamPay")
public class ParamPaysController {
	
	
	@Autowired
	private ParamPaysService paramPaysService;
	
	/* Get Param pays */
	@CrossOrigin
	@GetMapping(value = "/getParamPays")
	public List<ParamPays> getAllParamPays() {
		return  this.paramPaysService.getAllParamPays();
	}
	/* Add Param pays */
	@CrossOrigin
	@PostMapping(value = "/addParamPays")
	public ParamPays addParamPays(@RequestBody ParamPays paramPays) {
		return this.paramPaysService.addParamPays(paramPays);
	}
	
	/* Delete Param pays */
	@CrossOrigin
	@DeleteMapping("/deletePrmPays/{code}")
	public boolean deleteParam(@PathVariable("code") String code) {
	
		return this.paramPaysService.deleteParamPays(code);
	}
}
