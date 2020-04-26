package in.co.rays.service;

import java.util.Collection;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.DuplicateRecordException;

/**
 * Created By Zhu Lin on 3/13/2018.
 */
public interface UserService {
    UserDTO findOne(String email);

    Collection<UserDTO> findByRole(String role);

    UserDTO save(UserDTO user) throws DuplicateRecordException;

    void update(UserDTO user) throws DuplicateRecordException;
    
    
    
}
