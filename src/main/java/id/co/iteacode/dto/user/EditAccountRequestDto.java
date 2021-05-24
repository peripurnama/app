package id.co.iteacode.dto.user;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import id.co.iteacode.dto.pegawai.PegawaiRequestEditDto;
import id.co.iteacode.dto.person.PersonRequestEditDto;

public class EditAccountRequestDto {

	private Long id;
	
	private @Valid UserRequestEditDto userRequestEditDto;
	
	private @Valid PersonRequestEditDto personRequestEditDto;
	
	private @Valid PegawaiRequestEditDto pegawaiRequestEditDto;
	
//	@NotNull
	private Set<Long> roleIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRequestEditDto getUserRequestEditDto() {
		return userRequestEditDto;
	}

	public void setUserRequestEditDto(UserRequestEditDto userRequestEditDto) {
		this.userRequestEditDto = userRequestEditDto;
	}

	public PersonRequestEditDto getPersonRequestEditDto() {
		return personRequestEditDto;
	}

	public void setPersonRequestEditDto(PersonRequestEditDto personRequestEditDto) {
		this.personRequestEditDto = personRequestEditDto;
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
		return "EditAccountRequestDto [id=" + id + ", userRequestEditDto=" + userRequestEditDto
				+ ", personRequestEditDto=" + personRequestEditDto + ", pegawaiRequestEditDto=" + pegawaiRequestEditDto
				+ ", roleIds=" + roleIds + "]";
	}

	
}
