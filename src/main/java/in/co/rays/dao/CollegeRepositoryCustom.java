package in.co.rays.dao;

import java.util.List;

import in.co.rays.dto.CollegeDTO;

public interface CollegeRepositoryCustom {

	public List search(CollegeDTO dto,int pageNo,int pageSize);

}
