package com.emsjwt.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class JSONResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String username;

	private List<String> roles;

	

	public JSONResponse(String accessToken, String username,List<String> roles) {
		this.token = accessToken;

		this.username = username;

		this.roles = roles;
	}
	public JSONResponse(String accessToken, String username,List<String> roles, String id) {
		this.token = accessToken;

		this.username = username;
		this.id =id;
		this.roles = roles;
	}
	
}
