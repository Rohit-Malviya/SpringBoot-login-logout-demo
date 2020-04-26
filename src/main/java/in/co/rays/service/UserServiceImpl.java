package in.co.rays.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.rays.dao.UserDAOInt;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.DuplicateRecordException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAOInt dao = null;

	@Override
	public UserDTO findOne(String email) {

		return dao.findByLogin(email);
	}

	@Override
	public Collection<UserDTO> findByRole(String role) {

		return dao.findAllByRole(role);
	}

	@Override
	public UserDTO save(UserDTO user) throws DuplicateRecordException {
		UserDTO dtoExist = dao.findByLogin(user.getLoginId());
		if (dtoExist!=null) {
			throw new DuplicateRecordException("User already exist");
		}
		long pk= dao.add(user);
		return dao.findByPK(pk);

	}

	@Override
	public void update(UserDTO user) throws DuplicateRecordException {
		UserDTO dtoExist=dao.findByLogin(user.getLoginId());
		if(dtoExist!=null && dtoExist.getId()!=user.getId()){
			throw new DuplicateRecordException("User already exist");
		}
		 dao.update(user);
	}

}
