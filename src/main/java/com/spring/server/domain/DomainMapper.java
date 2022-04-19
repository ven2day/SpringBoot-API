package com.spring.server.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.spring.server.domain.role.RoleEntity;
import com.spring.server.domain.role.RoleRequest;
import com.spring.server.domain.role.RoleResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DomainMapper {

	@Mapping(target = "name", source = "name")
	@Mapping(target = "accessUrlPattern", source = "accessUrlPattern")
	@Mapping(target = "accessMethod", source = "accessMethod")
	RoleEntity toEntity(RoleRequest.AddParam param);
	
	@Mapping(target = "no", source = "no")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "accessUrlPattern", source = "accessUrlPattern")
	@Mapping(target = "accessMethod", source = "accessMethod")
	RoleResponse.Info toInfo(RoleEntity entity);

	@Mapping(target = "no", source = "no")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "accessUrlPattern", source = "accessUrlPattern")
	@Mapping(target = "accessMethod", source = "accessMethod")
	@Mapping(target = "createDateTime", source = "createDateTime")
	@Mapping(target = "updateDateTime", source = "updateDateTime")
	RoleResponse.Details toDetails(RoleEntity entity);

}
