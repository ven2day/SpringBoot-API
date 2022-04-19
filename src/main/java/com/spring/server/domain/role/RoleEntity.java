package com.spring.server.domain.role;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.spring.server.SpringBootApiConstants.TableNames;
import com.spring.server.domain.BaseTimeEntity;
import com.spring.server.domain.role.AccessMethod.AccessMethodAttributeConverter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = TableNames.TB_ROLE)
public class RoleEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@Column(nullable = false, length = 30, unique = true)
	private String name;

	@Column(nullable = false, length = 60)
	private String accessUrlPattern;

	@Column(nullable = false)
	@Convert(converter = AccessMethodAttributeConverter.class)
	private AccessMethod accessMethod;

	public void changeAccessUrlPattern(String accessUrlPattern) {
		this.accessUrlPattern = accessUrlPattern;
	}

	public void changeAccessMethod(AccessMethod accessMethod) {
		this.accessMethod = accessMethod;
	}

	@Builder
	public RoleEntity(String name, String accessUrlPattern, AccessMethod accessMethod) {
		super();
		this.name = name;
		this.accessMethod = accessMethod;
		this.accessUrlPattern = accessUrlPattern;
	}

}
