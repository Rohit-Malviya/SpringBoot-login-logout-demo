package in.co.rays.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.CollegeDTO;

public class CollegeForm extends BaseForm{

	
	/**
	 * Value of button clicked
	 */
	private String operation;
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		System.out.println("SET OP IN CLG = "+operation);
		this.operation = operation;
	}

	/**
	 * CollegeName
	 */
	@NotEmpty
	private String name;
	/**
	 * Address of the college
	 */
	@NotEmpty
	private String address;
	/**
	 * City of the college
	 */
	@NotEmpty
	private String city;
	/**
	 * PhoneNo of the college
	 */
	@NotEmpty
	private String phoneNo;
	/**
	 * State Of the college
	 */
	@NotEmpty
	private String state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public BaseDTO getDto(HttpSession session) {
		CollegeDTO dto = new CollegeDTO();
		dto.setId(id);
		dto.setAddress(address);
		dto.setCity(city);
		dto.setName(name);
		dto.setState(state);
		dto.setPhoneNo(phoneNo);
		populateGeneric(session);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
	/*	if (createdDatetime >0) {
			dto.setCreatedDatetime(new Timestamp(createdDatetime));
		} else {
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		}*/
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		return dto;
	}

		
	
	
}
