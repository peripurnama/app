package id.co.iteacode.dto.user;

import java.util.HashSet;
import java.util.Set;

public class UserRequestEditDto {

	private Long id;
	private String username;
	private String email;
	private String password;
	private String confirmPassword;
	private Set<Long> roleIds = new HashSet<>();
	private long pegawaiId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public long getPegawaiId() {
		return pegawaiId;
	}

	public void setPegawaiId(long pegawaiId) {
		this.pegawaiId = pegawaiId;
	}

	@Override
	public String toString() {
		return "UserRequestEditDto [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", roleIds=" + roleIds + ", pegawaiId=" + pegawaiId + "]";
	}

}
