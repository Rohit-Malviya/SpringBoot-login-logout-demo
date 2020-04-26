package in.co.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAOInt{

	@PersistenceContext
	private EntityManager manager=null;
	@Override
	public long add(UserDTO dto) throws DataAccessException {
		Session session = manager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		return pk;
	}

	@Override
	public void update(UserDTO dto) throws DataAccessException {
		
		Session session = manager.unwrap(Session.class);
		session.merge(dto);
		
	}

	@Override
	public void delete(long id) throws DataAccessException {
		Session session = manager.unwrap(Session.class);
		session.delete(id);
	}

	@Override
	public UserDTO findByLogin(String login) throws DataAccessException {
		Session session = manager.unwrap(Session.class);
		Criteria c = session.createCriteria(UserDTO.class);
		c.add(Restrictions.like("loginId", login));
		UserDTO dto = null;
		if (!c.list().isEmpty() && c.list().size() > 0) {
			dto = (UserDTO) c.list().get(0);
		}
		return dto;
	
	}
	
	public List<UserDTO> findAllByRole(String role) throws DataAccessException {
		Session session = manager.unwrap(Session.class);
		Criteria c = session.createCriteria(UserDTO.class);
		c.add(Restrictions.like("roleName",role));
		List<UserDTO> list = null;
		if (!c.list().isEmpty() && c.list().size() > 0) {
			list =  c.list();
		}
		return list;
	
	}

	@Override
	public UserDTO findByPK(long pk) throws DataAccessException {
		Session session=manager.unwrap(Session.class);
		UserDTO dto=(UserDTO) session.get("id", pk);
		
		return dto;
	}

	@Override
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) throws DataAccessException {
		Session session = manager.unwrap(Session.class);

		System.out.println("DTO = = = = =" + (dto == null));
		Criteria criteria = session.createCriteria(UserDTO.class);
		if (dto != null) {
			if (dto.getId() != null && dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null && dto.getName().trim().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				criteria.add(Restrictions.like("email", dto.getEmail() + "%"));
			}
			if (dto.getGender() != null && dto.getGender().length() > 0) {
				System.out.println("city is"+dto.getGender());
				criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
			}
			if (dto.getPhone() != null && dto.getPhone().length() > 0) {
				criteria.add(Restrictions.like("phone", dto.getPhone() + "%"));
			}
			if (dto.getDob() != null && dto.getDob().getTime() > 0) {
				criteria.add(Restrictions.like("dob", dto.getDob() + "%"));
			}

		}
		if (pageSize > 0) {
			System.out.println("inside pagination");
			criteria.setFirstResult(((pageNo - 1) * pageSize));
			criteria.setMaxResults(pageSize);
		}
		List<UserDTO> list=null;
		
		try{
			list = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		list.forEach((dto1)->{
			System.out.println(dto.getName());		
		});
		System.out.println("return"+list.size());
		// log.debug("CollegeDAO search ended");
		return list;
		
	}

	@Override
	public List<UserDTO> search(UserDTO dto) throws DataAccessException {
		
		return search(dto, 0, 0);
	}

}
