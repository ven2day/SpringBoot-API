package com.spring.server;

public final class SpringBootApiConstants {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final class Tokens {
		public static final String SECRET_KEY = "secret-key";

		public static final String ACCESS_TOKEN_HEADER_NAME = "Access-Token";
		public static final String REFRESH_TOKEN_HEADER_NAME = "Refresh-Token";

		public static final Long ACCESS_TOKEN_EXPIRATION_TIME = 10 * 60 * 1000L; // 10분
		public static final Long REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L; // 24시간

		public static final String REFRESH_TOKEN_REDIS_PREFIX = "refresh-token-";
	}

	public static final class TableNames {
		public static final String TB_MEMBER = "tb_member";
		public static final String TB_ROLE = "tb_role";
		public static final String TB_GRANT_ROLE = "tb_grant_role";
		public static final String TB_POST = "tb_post";
	}

	public static final class ApiUrls {
		public static final String API_PREFIX = "/apis";
		public static final String MEMBER_API_PREFIX = API_PREFIX + "/members";
		public static final String ROLE_API_PREFIX = API_PREFIX + "/roles";
		public static final String POST_API_PREFIX = API_PREFIX + "/posts";
	}

	public static final class Authorizes {
		public static final String IS_ANONYMUS = "isAnonymous()";
		public static final String IS_AUTHENTICATED = "isAuthenticated()";
	}
}