package com.emsjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	int eid;
	String name;
	int departmentId;
	String departmentName;
	int locationId;
	String locationName;
}
