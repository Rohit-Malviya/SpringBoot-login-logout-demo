package in.co.rays.form;

import javax.validation.constraints.NotBlank;

/**
 * Created By Zhu Lin on 1/1/2019.
 */

public class LoginForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
}
