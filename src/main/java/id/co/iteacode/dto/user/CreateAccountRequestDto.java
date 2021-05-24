package id.co.iteacode.dto.user;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import id.co.iteacode.dto.pegawai.PegawaiRequestAddDto;
import id.co.iteacode.dto.person.PersonRequestAddDto;

public class CreateAccountRequestDto {

	private @Valid UserRequestAddDto userRequestAddDto;
	
	private @Valid PersonRequestAddDto personRequestAddDto;
	
	private @Valid PegawaiRequestAddDto pegawaiRequestAddDto;
	
	@NotNull
	private Set<Long> roleIds;

	public UserRequestAddDto getUserRequestAddDto() {
		return userRequestAddDto;
	}

	public void setUserRequestAddDto(UserRequestAddDto userRequestAddDto) {
		this.userRequestAddDto = userRequestAddDto;
	}

	public PersonRequestAddDto getPersonRequestAddDto() {
		return personRequestAddDto;
	}

	public void setPersonRequestAddDto(PersonRequestAddDto personRequestAddDto) {
		this.personRequestAddDto = personRequestAddDto;
	}

	public PegawaiRequestAddDto getPegawaiRequestAddDto() {
		return pegawaiRequestAddDto;
	}

	public void setPegawaiRequestAddDto(PegawaiRequestAddDto pegawaiRequestAddDto) {
		this.pegawaiRequestAddDto = pegawaiRequestAddDto;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		return "CreateAccountRequestDto [userRequestAddDto=" + userRequestAddDto + ", personRequestAddDto="
				+ personRequestAddDto + ", pegawaiRequestAddDto=" + pegawaiRequestAddDto + ", roleIds=" + roleIds + "]";
	}
	
}
