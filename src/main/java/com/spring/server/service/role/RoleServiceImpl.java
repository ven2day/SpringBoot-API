package com.spring.server.service.role;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.server.domain.DomainMapper;
import com.spring.server.domain.role.RoleEntity;
import com.spring.server.domain.role.RoleRequest.AddParam;
import com.spring.server.domain.role.RoleRequest.ModifyParam;
import com.spring.server.domain.role.RoleRequest.SearchParam;
import com.spring.server.domain.role.RoleResponse.Details;
import com.spring.server.domain.role.RoleResponse.Info;
import com.spring.server.exception.DuplicateResourceException.DuplicateRoleNameException;
import com.spring.server.exception.NotFoundResourceException.NotFoundRoleException;
import com.spring.server.repository.role.RoleRepository;
import com.spring.server.repository.role.RoleRepositorySupport;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	
	private final DomainMapper domainMapper;
	private final RoleRepository roleRepository;
	private final RoleRepositorySupport roleRepositorySupport;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Info addRole(AddParam param) throws Exception{
		try {
			return domainMapper.toInfo(roleRepository.save(domainMapper.toEntity(param)));
		} catch (DataIntegrityViolationException e) {
			SQLException sqlException = (SQLException) e.getRootCause();
			throw ("23505".equals(sqlException.getSQLState())) ? new DuplicateRoleNameException() : e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Info modifyRole(long roleNo, ModifyParam param) throws Exception {
		RoleEntity roleEntity = roleRepositorySupport.findByIdForUpdate(roleNo)
				.orElseThrow(NotFoundRoleException::new);

		roleEntity.changeAccessUrlPattern(param.getAccessUrlPattern());
		roleEntity.changeAccessMethod(param.getAccessMethod());

		return domainMapper.toInfo(roleRepository.save(roleEntity));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void removeRole(long roleNo) throws Exception {
		RoleEntity roleEntity = roleRepositorySupport.findByIdForUpdate(roleNo)
				.orElseThrow(NotFoundRoleException::new);

		roleRepository.delete(roleEntity);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Override
	public Details getRole(long roleNo) throws Exception {
		RoleEntity roleEntity = roleRepositorySupport.findByIdForUpdate(roleNo)
				.orElseThrow(NotFoundRoleException::new);

		return domainMapper.toDetails(roleEntity);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Override
	public List<Info> searchRole(SearchParam param) throws Exception {
		List<RoleEntity> list = roleRepositorySupport.findBySearchParam(param);
		return list.stream().map(entity -> domainMapper.toInfo(entity)).collect(Collectors.toList());
	}

}
