package in.co.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.DuplicateRecordException;

@Repository
public class CollegeDAOImpl implements CollegeDAOInt {

	@PersistenceContext
	private EntityManager manager = null;

	@Override
	public long add(CollegeDTO dto) {

		Session session = manager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		return pk;
	}

	@Override
	public void update(CollegeDTO dto) {
		Session session = manager.unwrap(Session.class);
		session.merge(dto);

	}

	@Override
	public void delete(CollegeDTO dto) {
		Session session = manager.unwrap(Session.class);
		session.delete(dto);

	}

	@Override
	public CollegeDTO findByName(String name) {
		Session session = manager.unwrap(Session.class);
		Criteria c = session.createCriteria(CollegeDTO.class);
		c.add(Restrictions.like("name", name));
		CollegeDTO dto = null;
		if (!c.list().isEmpty() && c.list().size() > 0) {
			dto = (CollegeDTO) c.list().get(0);
		}
		return dto;
	}

	@Override
	public CollegeDTO findByPK(long pk) {
		Session session = manager.unwrap(Session.class);
		CollegeDTO dto = session.get(CollegeDTO.class, pk);
		System.out.println("in dao findByPK="+(dto==null));
		return dto;
	}

	@Override
	public List<CollegeDTO> search(CollegeDTO dto) {

		return search(dto, 0, 0);
	}

	@Override
	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) {
		// log.debug("CollegeDAO search started");
		Session session = manager.unwrap(Session.class);

		System.out.println("DTO = = = = =" + (dto == null));
		Criteria criteria = session.createCriteria(CollegeDTO.class);
		if (dto != null) {
			if (dto.getId() != null && dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null && dto.getName().trim().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getAddress() != null && dto.getAddress().length() > 0) {
				criteria.add(Restrictions.like("address", dto.getAddress() + "%"));
			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				System.out.println("city is"+dto.getCity());
				criteria.add(Restrictions.like("city", dto.getCity() + "%"));
			}
			if (dto.getPhoneNo() != null && dto.getPhoneNo().length() > 0) {
				criteria.add(Restrictions.like("phoneNo", dto.getPhoneNo() + "%"));
			}
			if (dto.getState() != null && dto.getState().length() > 0) {
				criteria.add(Restrictions.like("state", dto.getState() + "%"));
			}

		}
		if (pageSize > 0) {
			System.out.println("inside pagination");
			criteria.setFirstResult(((pageNo - 1) * pageSize));
			criteria.setMaxResults(pageSize);
		}
		List<CollegeDTO> list=null;
		
		try{
			list = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		list.forEach((dto1)->{
			System.out.println(dto.getName()+dto.getCity());		
		});
		System.out.println("return"+list.size());
		// log.debug("CollegeDAO search ended");
		return list;
	}

	@Override
	public CollegeDTO findByNameAndCity(String name, String city) {
		Session session = manager.unwrap(Session.class);
		Criteria c = session.createCriteria(CollegeDTO.class);
		c.add(Restrictions.eq("name", name));
		c.add(Restrictions.eq("city", city));
		List l = c.list();

		CollegeDTO dto = null;
		if (!l.isEmpty() && l.size() > 0) {
			dto = (CollegeDTO) l.get(0);
		}
		return dto;
	}

}
