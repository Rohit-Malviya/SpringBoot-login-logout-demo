package in.co.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.rays.dao.CollegeDAOInt;
import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.DuplicateRecordException;

@Service
@Transactional
public class CollegeService {

	@Autowired
	private CollegeDAOInt dao = null;

	public CollegeDTO findById(Long id) {
		return dao.findByPK(id);
	}

	public CollegeDTO delete(long id) {
		CollegeDTO dto = dao.findByPK(id);
		System.out.println("DTO IN SERVICE = " + (dto == null));
		if (dto != null) {
			System.out.println("!= DTO INSIDE");
			dao.delete(dto);
		}
		System.out.println("DTO DELTED FROM SERVICE");
		return dto;
	}

	public void update(CollegeDTO dto) throws DuplicateRecordException {
		CollegeDTO dtoExist = findByNameAndCity(dto.getName(), dto.getCity());
		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException("College already exist");
		}

		dao.update(dto);

	}

	public Long add(CollegeDTO dto) throws DuplicateRecordException {
		CollegeDTO dtoExist = findByNameAndCity(dto.getName(), dto.getCity());
		if (dtoExist != null) {
			throw new DuplicateRecordException("College already exist");
		}

		return dao.add(dto);
	}

	public CollegeDTO findByNameAndCity(String name, String city) {

		return dao.findByNameAndCity(name, city);
	}

	public List search(CollegeDTO dto, int pageNo, int pageSize) {

		// System.out.println(" List size =
		// "+dao.search(dto,pageNo,pageSize).size());
		return dao.search(dto, pageNo, pageSize);
	}

}
