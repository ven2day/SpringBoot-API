package com.spring.server.domain.role;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.server.SpringBootApiConstants;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface RoleResponse {

	@Getter
	@Builder
	@ToString
	@ApiModel(value = "RoleResponse.Info")
	public static class Info {
		
		private Long no;

		private String name;
		
		private String accessUrlPattern;

		private AccessMethod accessMethod;
	
	}

	@Getter
	@Builder
	@ToString
	@ApiModel(value = "RoleResponse.Details")
	public static class Details {
		
		private Long no;

		private String name;
		
		private String accessUrlPattern;

		private AccessMethod accessMethod;
		
		@JsonFormat(pattern = SpringBootApiConstants.DATE_TIME_FORMAT)
		private LocalDateTime createDateTime;
		
		@JsonFormat(pattern = SpringBootApiConstants.DATE_TIME_FORMAT)
		private LocalDateTime updateDateTime;		

	}

}
