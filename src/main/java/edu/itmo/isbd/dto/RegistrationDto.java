package edu.itmo.isbd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistrationDto {
	private String login;
	private String phone;
	private String mail;
	private String password;
}
