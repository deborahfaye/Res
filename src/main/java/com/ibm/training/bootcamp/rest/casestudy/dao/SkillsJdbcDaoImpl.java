package com.ibm.training.bootcamp.rest.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;

public class SkillsJdbcDaoImpl implements SkillsDao {

	private static SkillsJdbcDaoImpl INSTANCE;

	private JDBCDataSource dataSource;

	static public SkillsJdbcDaoImpl getInstance() {

		SkillsJdbcDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new SkillsJdbcDaoImpl();
			INSTANCE = instance;
		}
		return instance;
	}

	private SkillsJdbcDaoImpl() {
		init();
	}

	public void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:USER");
		dataSource.setUser("username");
		dataSource.setPassword("password");

		createSkillsTable();

		insertInitSkills();

	}

	private void createSkillsTable() {
		String createSql = "CREATE TABLE SKILLS" + "(id INTEGER IDENTITY PRIMARY KEY, " + "skill VARCHAR(255))";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(createSql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitSkills() {
		add(new Skills("Java" ));
		add(new Skills("Python" ));
	}

	
	@Override
	public List<Skills> findAll(){
		return findBySkill(null);
	}
	
	@Override
	public Skills find(Long id) {
		
		Skills skil =null;
		
		 if(id != null) {
			 String sql = "SELECT id, skill FROM SKILLS where skillId =?";
		 try(Connection conn =dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)){
			 
			 ps.setInt(1,id.intValue());
			 ResultSet results =ps.executeQuery();
			 
			 if(results.next()) {
				 skil = new Skills(Long.valueOf(results.getInt("id")),results.getString("skill") );
			 }
			 
		 }catch (SQLException e) {
			 e.printStackTrace();
			 throw new RuntimeException(e);
			 
		 }
		 
		 }
		 return skil;
	}
	
	@Override
    public List<Skills> findBySkill(String skill){
		List<Skills> skills= new ArrayList<>();
		
		String sql = "SELECT id, skill FROM SKILLS WHERE skill LIKE ?";
	
		try(Connection conn= dataSource.getConnection(); PreparedStatement ps =conn.prepareStatement(sql)){
			
			ps.setString(1, createSearchValue(skill));
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				Skills skil= new Skills(Long.valueOf(results.getInt("id")), results.getString("skill"));
			    
				skills.add(skil);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	       return skills;
	}
	
	private String createSearchValue(String string) {
		String value;
		
		if(StringUtils.isBlank(string)) {
		    value ="%";
		}else {
			value = string;
		}
		return value;
	}
	
	@Override
	public void add(Skills skil) {
		
		String insertSql ="INSERT INTO SKILLS (skill) VALUES (?) ";
	
	    try(Connection conn = dataSource.getConnection(); PreparedStatement ps= conn.prepareStatement(insertSql)){
	    	
	    	ps.setString(1, skil.getSkill());
//	    	ps.setLong(3, skil.getId());
	    	ps.executeUpdate();
	    	
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    	throw new RuntimeException(e);
	    }
	}
}
