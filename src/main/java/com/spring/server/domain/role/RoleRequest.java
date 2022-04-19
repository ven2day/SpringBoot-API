package com.spring.server.domain.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public interface RoleRequest {

	@Getter
	@ToString
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@ApiModel(value = "RoleRequest.AddParam")
	public static class AddParam {
		
		@NotBlank
		@Size(min = 0, max = 30)
		private String name;
		
		@NotBlank
		@Size(min = 0, max = 60)
		@Pattern(regexp = "^/[a-zA-Z0-9./-_*]+")
		private String accessUrlPattern;

		@NotNull 
		private AccessMethod accessMethod;
		
	}

	@Getter
	@ToString
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@ApiModel(value = "RoleRequest.ModifyParam")
	public static class ModifyParam {

		@NotBlank
		@Size(min = 0, max = 60)
		@Pattern(regexp = "^/[a-zA-Z0-9./-_*]+")
		private String accessUrlPattern;

		@NotNull
		private AccessMethod accessMethod;
		
	}

	@Getter
	@ToString
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@ApiModel(value = "RoleRequest.SearchParam")
	public static class SearchParam {
		
		@Size(min = 0, max = 30)
		private String name;
		
		@Size(min = 0, max = 60)
		@Pattern(regexp = "^/[a-zA-Z0-9./-_*]+")
		private String accessUrlPattern;

		private AccessMethod accessMethod;
		
	}
	
}
