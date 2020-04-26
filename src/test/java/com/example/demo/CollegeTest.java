package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import in.co.rays.dao.CollegeDAOInt;
import in.co.rays.dto.CollegeDTO;
import in.co.rays.service.CollegeService;
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class CollegeTest {

	@Autowired
	private TestEntityManager manager;
	
	@Test
	public void testDelete() {
	/*	System.out.println("Service = "+service);
		CollegeDTO dto=manager.remove(entity);;
		System.out.println("DELETED DTO  = "+dto);*/
	}
	
	@Test
	public void testAdd(){
		
		CollegeDTO dto=new CollegeDTO();
		dto.setName("RLRLFkmzf");
		dto.setCity("Neemuch");
		dto.setAddress("aferr");
		System.out.println("SAVED =  ="+manager.persist(dto));
	}

}
