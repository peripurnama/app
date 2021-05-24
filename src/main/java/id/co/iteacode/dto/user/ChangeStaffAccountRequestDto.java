package id.co.iteacode.dto.user;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import id.co.iteacode.dto.pegawai.PegawaiRequestEditDto;
import id.co.iteacode.dto.person.PersonRequestEditDto;

public class ChangeStaffAccountRequestDto {

	private @Valid UserRequestEditDto userRequestEditDto;

	private @Valid PegawaiRequestEditDto pegawaiRequestEditDto;

	@NotNull
	private Set<Long> roleIds;

	public UserRequestEditDto getUserRequestEditDto() {
		return userRequestEditDto;
	}

	public void setUserRequestEditDto(UserRequestEditDto userRequestEditDto) {
		this.userRequestEditDto = userRequestEditDto;
	}

	public PegawaiRequestEditDto getPegawaiRequestEditDto() {
		return pegawaiRequestEditDto;
	}

	public void setPegawaiRequestEditDto(PegawaiRequestEditDto pegawaiRequestEditDto) {
		this.pegawaiRequestEditDto = pegawaiRequestEditDto;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		return "CreateStaffAccountRequestDto [userRequestEditDto=" + userRequestEditDto + ", pegawaiRequestEditDto="
				+ pegawaiRequestEditDto + ", roleIds=" + roleIds + "]";
	}

}
