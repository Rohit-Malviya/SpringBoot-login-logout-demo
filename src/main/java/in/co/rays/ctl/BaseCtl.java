package in.co.rays.ctl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.co.rays.ORSResponse;



/**
 * Base controller class contains get, search, save, delete REST APIs
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 * @param <F>
 * @param <T>
 * @param <S>
 */
public abstract class BaseCtl {

	/**
	 * Form operations
	 */
	protected static final String OP_SAVE = "Save";
	protected static final String OP_NEW = "New";
	protected static final String OP_DELETE = "Delete";
	protected static final String OP_CANCEL = "Cancel";
	protected static final String OP_ERROR = "Error";
	protected static final String OP_NEXT = "Next";
	protected static final String OP_PREVIOUS = "Previous";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String OP_GO = "Go";
	protected static final String OP_GET = "Get";


	

	/**
	 * Contains context of logged-in user
	 */
/*	protected UserContext userContext = null;*/

	/**
	 * Get user context from session
	 * 
	 * @param session
	 */
/*	@ModelAttribute
	public void setUserContext(HttpSession session) {
		userContext = (UserContext) session.getAttribute("userContext");
		if(userContext == null){
			UserDTO dto  = new UserDTO();
			dto.setLoginId("root@sunilos.com");
			dto.setFirstName("Dummy name");
			dto.setLastName("Dummy name");
			dto.setOrgId(0L);
			dto.setRoleId(1L);
			dto.setOrgName("root");
			userContext = new UserContext(dto);
		}
	
	}*/

	

	

	

	

	/**
	 * Gets input error messages and put into REST response
	 * 
	 * @param bindingResult
	 * @return
	 */
	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse(true);

		if (bindingResult.hasErrors()) {

			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();
			// Lambda expression Java 8 feature
			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputErrors(errors);
		}
		return res;

	}

}
