package in.co.rays.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import in.co.rays.dto.UserDTO;

/**
 * User DAO Interface provides abstract methods of CRUD operations.
 * Implementation will be done by JDBC, Hibrenate or JPA.
 * 
 * All methods propagate unchecked DataAccessException. It is a generic
 * exception handling provided by Spring-DAO.
 * 
 * If DataAccessException is propagated from a method then declarative
 * transaction is rolled back by Spring AOP.
 * 
 * @version 1.0
 * @since 1 Jan 2015
 * @author Proxy
 * @Copyright Rohit Malviya
 */

public interface UserDAOInt {

	 /**
     * Add a User
     * 
     * @param dto
     */
    public long add(UserDTO dto) throws DataAccessException;

    /**
     * Update a User
     * 
     * @param dto
     * @return
     */
    public void update(UserDTO dto) throws DataAccessException;

    /**
     * Delete a User
     * 
     * @param id
     * @return
     */
    public void delete(long id) throws DataAccessException;

    /**
     * Find User by Login
     * 
     * @param login
     *            : get parameter
     * @return dto
     */
    public UserDTO findByLogin(String login) throws DataAccessException;

    /**
     * Find User by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     */
    public UserDTO findByPK(long pk) throws DataAccessException;

    /**
     * Search Users with pagination
     * 
     * @return list : List of Users
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     */
    public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize)
            throws DataAccessException;

    /**
     * Search Users
     * 
     * @param dto
     *            : Search Parameters
     */
    public List<UserDTO> search(UserDTO dto) throws DataAccessException;
	
    public List<UserDTO> findAllByRole(String role) throws DataAccessException;
 
}
