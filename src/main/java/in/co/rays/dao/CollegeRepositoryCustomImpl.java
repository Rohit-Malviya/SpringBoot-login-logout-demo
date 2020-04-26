package in.co.rays.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.co.rays.dto.CollegeDTO;

//@Repository
public class CollegeRepositoryCustomImpl implements CollegeRepositoryCustom {

	@Override
	public List search(CollegeDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Autowired
	// private JdbcTemplate template;
	// private SessionFactory factory=null;

/*	private SessionFactory factory;

	@Autowired
	public void SomeService(EntityManagerFactory factory) {
		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		this.factory = factory.unwrap(SessionFactory.class);
	}

	@Override
	public List search(CollegeDTO dto, int pageNo, int pageSize) {
		System.out.println("In search");
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(CollegeDTO.class);
		if (dto != null) {
			if (dto.getId() != null && dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				criteria.add(Restrictions.like("city", dto.getCity() + "%"));
			}
			if (dto.getAddress() != null && dto.getAddress().length() > 0) {
				criteria.add(Restrictions.like("address", dto.getAddress() + "%"));
			}
			if(dto.getPhoneNo() != null && dto.getPhoneNo().length() > 0) {
				criteria.add(Restrictions.like("phoneNo", dto.getPhoneNo() + "%"));
			}
			if (dto.getState() != null && dto.getState().length() > 0) {
				criteria.add(Restrictions.like("state", dto.getState() + "%"));
			}
		}

		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		List<CollegeDTO> list = criteria.list();
		list.forEach((dtos)->{
			System.out.println(dtos.getName());
		});
		return list;
	}*/

}
