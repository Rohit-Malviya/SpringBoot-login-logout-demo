package in.co.rays.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.rays.dto.BaseDTO;


public interface BaseDAOInt extends JpaRepository<BaseDTO,Long>{

}
