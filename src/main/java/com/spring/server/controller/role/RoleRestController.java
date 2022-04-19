package com.spring.server.controller.role;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.server.SpringBootApiConstants.ApiUrls;
import com.spring.server.domain.role.RoleRequest;
import com.spring.server.domain.role.RoleResponse;
import com.spring.server.service.role.RoleService;
import com.spring.server.util.ValidationUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiUrls.ROLE_API_PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleRestController {

	private final RoleService roleService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleResponse.Info> roleAdd(
			@Valid @RequestBody RoleRequest.AddParam param,
			BindingResult bindingResult) throws Exception {

		ValidationUtil.checkFieldErrors(bindingResult);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(roleService.addRole(param));
	}

	@PutMapping(value = "/{roleNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleResponse.Info> roleModify(
			@PathVariable("roleNo") long roleNo,
			@Valid @RequestBody RoleRequest.ModifyParam param, 
			BindingResult bindingResult) throws Exception {

		ValidationUtil.checkFieldErrors(bindingResult);

		return ResponseEntity.status(HttpStatus.OK)
				.body(roleService.modifyRole(roleNo, param));
	}

	@DeleteMapping(value = "/{roleNo}")
	public ResponseEntity<Void> roleRemove(
			@PathVariable("roleNo") long roleNo) throws Exception {
		
		roleService.removeRole(roleNo);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping(value = "/{roleNo}")
	public ResponseEntity<RoleResponse.Details> roleGet(
			@PathVariable("roleNo") long roleNo) throws Exception {

		return ResponseEntity.status(HttpStatus.OK)
				.body(roleService.getRole(roleNo));
	}

	@GetMapping
	public ResponseEntity<List<RoleResponse.Info>> roleSearch(
			@Valid RoleRequest.SearchParam param,
			BindingResult bindingResult) throws Exception {

		ValidationUtil.checkFieldErrors(bindingResult);

		return ResponseEntity.status(HttpStatus.OK)
				.body(roleService.searchRole(param));
	}

}
