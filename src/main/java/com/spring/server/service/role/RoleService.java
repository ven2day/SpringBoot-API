package com.spring.server.service.role;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.spring.server.domain.role.RoleRequest;
import com.spring.server.domain.role.RoleResponse;
import com.spring.server.exception.DuplicateResourceException.DuplicateRoleNameException;

public interface RoleService {

	RoleResponse.Info addRole(RoleRequest.AddParam param) throws Exception;

	RoleResponse.Info modifyRole(long roleNo, RoleRequest.ModifyParam param) throws Exception;

	void removeRole(long roleNo) throws Exception;

	RoleResponse.Details getRole(long roleNo) throws Exception;

	List<RoleResponse.Info> searchRole(RoleRequest.SearchParam param) throws Exception;

}
