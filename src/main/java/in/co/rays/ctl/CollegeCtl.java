package in.co.rays.ctl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.rays.ORSResponse;
import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.form.CollegeForm;
import in.co.rays.service.CollegeService;

@RestController
@RequestMapping("/college")
@CrossOrigin("http://localhost:4200")
public class CollegeCtl extends BaseCtl {

	@Autowired
	private CollegeService service;

	@Value("${page.size}")
	private int pageSize = 0;

	/**
	 * Get entity by primary key ID
	 * 
	 * @param id
	 * @return
	 */
	// @CrossOrigin
	@GetMapping("/get/{id}")
	public CollegeDTO get(@PathVariable long id) {
		System.out.println("Inside get");
		ORSResponse res = new ORSResponse(true);
		CollegeDTO dto = service.findById(id);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return dto;
	}

	/**
	 * Delete entity by primary key ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		System.out.println("Inside delete" + id);
		ORSResponse res = new ORSResponse(true);
		try {
			CollegeDTO dto = service.delete(id);
			res.addData(dto);
			if (dto.getId() > 0) {
				System.out.println("Deleted success");
				res.addMessage("College deleted succesfully");
			}
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;
	}

	@PostMapping("/save")
	public ORSResponse save(@RequestBody @Valid CollegeForm form, BindingResult bindingResult, HttpSession session) {

		ORSResponse res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}
		// try {
		CollegeDTO dto = (CollegeDTO) form.getDto(session);
		System.out.println("---------------------------------------------->" + dto);
		if (dto.getId() != null && dto.getId() > 0) {
			try {
				service.update(dto);
				res.setSuccess(true);
				res.addMessage("College updated successfully");
				System.out.println("-------------------------- inside op update" + form.getOperation());
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.addMessage(e.getMessage());
				System.out.println("INSIDE PRINT STACK TRACE");
				System.out.println("IN update");
			}
		} else {
			System.out.println("In add");
			try {
				if (service.add(dto)>0) {
					res.addMessage("College saved successfully");
					res.setSuccess(true);
				}
			} catch (DuplicateRecordException e) {
				System.out.println("IN add exception");
				res.setSuccess(false);
				res.addMessage(e.getMessage());
				// e.printStackTrace();
			}

		}
		res.addData(dto.getId());
		/*
		 * } catch (DuplicateRecordException e) { res.setSuccess(false);
		 * res.addMessage(e.getMessage()); e.printStackTrace(); }
		 */
		return res;
	}

	/**
	 * Search entities by form attributes
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	// @GetMapping("/search")
	// @PostMapping("/search")
	public ORSResponse search(@RequestBody CollegeForm form, HttpSession session) {
		// CollegeForm form=new CollegeForm();
		System.out.println("Inside search op = " + form.getOperation());
		// Calculate next page number
		String operation = form.getOperation();
		int pageNo = form.getPageNo();
		// 0 is first page index
		pageNo = (pageNo == 0) ? 1 : pageNo;

		if (OP_NEXT.equals(operation)) {
			System.out.println("PAGE NO INSIDE = " + pageNo);
			pageNo++;
			System.out.println("PAGE NO INSIDE = " + pageNo);
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}

		System.out.println("Page No = " + pageNo + " Page size = " + pageSize);

		form.setPageNo(pageNo);

		CollegeDTO dto = (CollegeDTO) form.getDto(session);

		ORSResponse res = new ORSResponse(true);

		System.out.println("name=" + dto.getName() + " OP = " + operation);
		System.out.println("Page size is****************" + pageSize);
		// CollegeDTO dto =new CollegeDTO();
		// dto.setName("IIM");
		System.out.println("CTL DTO = == = = " + dto);
		List<CollegeDTO> list = service.search(dto, pageNo, pageNo);
		if(list.size()<=0){
			res.addMessage("No record found");
		}
		list.forEach((dx) -> {
			System.out.println("loop" + dx.getCity());
		});

		res.addData(service.search(dto, pageNo, pageSize));
		res.addResult("form", form);

		return res;
	}

	/*
	 * @RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public ORSResponse search(@RequestBody CollegeForm
	 * form, @PathVariable int pageNo) {
	 * 
	 * // 0 is first page index pageNo = (pageNo < 0) ? 0 : pageNo;
	 * 
	 * CollegeDTO dto = (CollegeDTO) form.getDto();
	 * 
	 * ORSResponse res = new ORSResponse(true);
	 * 
	 * System.out.println(pageNo + "Page size is****************" + pageSize);
	 * 
	 * res.addData(service.search(dto, pageNo, pageSize));
	 * 
	 * return res; }
	 */
}
