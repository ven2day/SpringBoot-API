package com.spring.server.repository.role;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.server.domain.role.QRoleEntity;
import com.spring.server.domain.role.RoleEntity;
import com.spring.server.domain.role.RoleRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoleRepositorySupport {

	private final JPAQueryFactory jpaQueryFactory;

	public Optional<RoleEntity> findByIdForUpdate(long roleNo) {
		QRoleEntity roleEntity = QRoleEntity.roleEntity;
		return Optional.ofNullable(jpaQueryFactory
					.selectFrom(roleEntity)
					.where(roleEntity.no.eq(roleNo))
				.setLockMode(LockModeType.PESSIMISTIC_WRITE)
				.fetchOne());
	}

	public List<RoleEntity> findBySearchParam(RoleRequest.SearchParam param) {
		// TODO: 파라미터에 따른 동적 where 절 생성 필요 
		QRoleEntity roleEntity = QRoleEntity.roleEntity;
		return jpaQueryFactory.selectFrom(roleEntity).fetch();
	}

}
