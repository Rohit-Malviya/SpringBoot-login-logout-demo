package in.co.rays.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.co.rays.dto.CollegeDTO;

/*@Repository
public interface CollegeDAOInt extends JpaRepository<CollegeDTO, Long>, CollegeRepositoryCustom {

	public CollegeDTO findByNameAndCity(String name, String city);

}
*/

/**
 * College DAO interface.
 * 
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface CollegeDAOInt {
	/**
	 * Adds a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(CollegeDTO dto);

	/**
	 * Updates a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(CollegeDTO dto);

	/**
	 * Deletes a College
	 * 
	 * @param id
	 * @throws DatabaseException
	 */
	public void delete(CollegeDTO dto);

	/**
	 * Finds College by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public CollegeDTO findByName(String name);

	/**
	 * Finds College by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public CollegeDTO findByPK(long pk);

	/**
	 * Searches Colleges
	 * 
	 * @return list : List of Colleges
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List<CollegeDTO> search(CollegeDTO dto);

	/**
	 * Searches Colleges with pagination
	 * 
	 * @return list : List of Colleges
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize);

	public CollegeDTO findByNameAndCity(String name, String city);

	
	



}
