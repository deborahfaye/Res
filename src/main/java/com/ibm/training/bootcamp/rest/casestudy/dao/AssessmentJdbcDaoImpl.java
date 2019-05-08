package com.ibm.training.bootcamp.rest.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.dao.SkillsJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.casestudy.domain.Assessment;


public class AssessmentJdbcDaoImpl implements AssessmentDao {

	private static AssessmentJdbcDaoImpl INSTANCE;

	private JDBCDataSource dataSource;

	static public AssessmentJdbcDaoImpl getInstance() {
		AssessmentJdbcDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			SkillsJdbcDaoImpl.getInstance();
			UserJdbcDaoImpl.getInstance();
			instance = new AssessmentJdbcDaoImpl();
			INSTANCE = instance;
		}
		return instance;
	}

	private AssessmentJdbcDaoImpl() {
		init();
	}

	public void init() {

		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:USER");
		dataSource.setUser("username");
		dataSource.setPassword("password");

		createAssessmentsTable();

		insertInitAssessment();

	}

	private void createAssessmentsTable() {
		
		
		try (Connection conn = dataSource.getConnection(); Statement stmt1 = conn.createStatement()) {
			
			String createSql = "CREATE TABLE ASSESSMENTS" + "(id INTEGER IDENTITY PRIMARY KEY," + "monthsExp Integer,"
					+ "skillLevel Integer," + "devId INTEGER FOREIGN  KEY REFERENCES USERS(devId)," +"skillId INTEGER FOREIGN  KEY REFERENCES SKILLS(id)," + "skill_name VARCHAR(255))";
			
			stmt1.executeUpdate(createSql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitAssessment() {
		                         
		add(new Assessment(6, 4, Long.valueOf(0),"Python",Long.valueOf(1)));
		add(new Assessment(12, 5, Long.valueOf(1), "Python",Long.valueOf(1)));
		add(new Assessment(6, 4, Long.valueOf(2), "Java",Long.valueOf(0)));
		add(new Assessment(2, 1, Long.valueOf(2), "Python",Long.valueOf(0)));
		add(new Assessment(6, 4, Long.valueOf(2), "Css",Long.valueOf(0)));

	}
	
	
	@Override
	public List<Assessment> findAll() {

		List<Assessment> assessments1 = new ArrayList<>();
		
		String sql1 = "SELECT * FROM ASSESSMENTS ";
		try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1); ) {

			ResultSet results1 = ps1.executeQuery();


			while (results1.next()) {
				Assessment assessment1 = new Assessment
						(Long.valueOf(results1.getInt("id")),
						results1.getInt("monthsExp"),
						results1.getInt("skillLevel"),
						Long.valueOf(results1.getInt("devId")), 
						results1.getString("skill_name"),
						Long.valueOf(results1.getInt("skillId")));

				assessments1.add(assessment1);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return assessments1;

	}

	
	
///----- for user story number 4------- 
	@Override
	public void add(Assessment assessment) {
		
		
		String insertSql1 = "INSERT INTO ASSESSMENTS (monthsExp, skillLevel, devId, skill_name) VALUES (?,?,?,?)";

		try (Connection conn1 = dataSource.getConnection();
				PreparedStatement ps1 = conn1.prepareStatement(insertSql1)) {
			
			
			
			
			ps1.setInt(1, assessment.getMonthsExp());
			ps1.setInt(2, assessment.getSkillLevel());
			ps1.setLong(3, assessment.getDevId());
			ps1.setString(4, assessment.getSkill_name());
			
			ps1.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
///----- 4-------
	
///----- for user story number 6------- 	
	@Override
	public void update(Assessment assessment) {
		String updateSql ="UPDATE ASSESSMENTS SET devId=?, monthsExp=?, skillLevel=?, skill_name=? WHERE id=?";
	
	try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

		ps.setLong(1, assessment.getDevId());
		ps.setInt(2, assessment.getMonthsExp());
		ps.setInt(3, assessment.getSkillLevel());
		ps.setString(4, assessment.getSkill_name());
		ps.setLong(5, assessment.getId());
		ps.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
	}
///----- 6------- 
	
	
///----- for user story number 7------- 
  @Override 
  public List<Skills> findBySkill(){
	  List<Skills> assessments1 = new ArrayList<>();
		
	  String sql1 = "SELECT (skill) FROM SKILLS";
	  try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {
		  ResultSet results1 = ps1.executeQuery();
		  
		  List<String> skillsList = new ArrayList<>();
			while (results1.next()) {
				Skills assessment1 = new Skills(
						results1.getString("skill"));
				
				assessments1.add(assessment1);
				skillsList.add(results1.getString("skill"));
			}		
			
			System.out.println(skillsList);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return assessments1;
		}
	  
  
	
	
	@Override
	public List<Assessment>  findByLevel() {
		List<Assessment> assessments1 = new ArrayList<>();
        
		
		String sql1 = "SELECT (skill_name), skillLevel,COUNT(skillLevel) FROM ASSESSMENTS GROUP BY skill_name, skillLevel ";
		
		try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {
			System.out.println("print");

			ResultSet results1 = ps1.executeQuery();
			while (results1.next()) {
				
				Assessment assessment1 = new Assessment(
							results1.getString("skill_name"),results1.getInt("skillLevel"));
					
					assessments1.add(assessment1);

				switch (results1.getInt(2)) {
			      case 0:
			    	 
			    	  System.out.println("0-Trained");
			    	  System.out.println(results1.getInt(3));
			        break;
			      case 1:

			    	  System.out.println("1-Novice");
			    	  System.out.println(results1.getInt(3));
			        break;
			      case 2:
			    	
			    	  System.out.println("2-Proficient");
			    	  System.out.println(results1.getInt(3));
			        break;
			      case 3:
			    	
			    	  System.out.println("3-Advanced");
			    	  System.out.println(results1.getInt(3));
			        break;
			      case 4:
			    	
			    	  System.out.println("4-Expert");
			    	  System.out.println(results1.getInt(3));
			        break;
			      case 5:

			    	  System.out.println("5-Leader");

			    	  System.out.println(results1.getInt(3));
			        break;
			    }

		}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return assessments1;
	}
///-----  7   ------- 

///----- for user story number 5------- 	
@Override 
public List<Assessment> findjoinedtable(String skill_name){
	  List<Assessment> assessments1 = new ArrayList<>();
		
	  String sql1 = "SELECT USERS.firstName, USERS.lastName, ASSESSMENTS.skill_name, ASSESSMENTS.skillLevel, ASSESSMENTS.monthsExp, ASSESSMENTS.devId FROM ASSESSMENTS INNER JOIN USERS ON ASSESSMENTS.devId=USERS.devId WHERE skill_name = ? ";
	  try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {
		  ps1.setString(1, skill_name);
		  
		  ResultSet results1 = ps1.executeQuery();

			while (results1.next()) {
				Assessment assessment1 = new Assessment(
						
						results1.getString("firstName"),
						results1.getString("lastName"),
						results1.getInt("monthsExp"),
						results1.getInt("skillLevel"),
						results1.getString("skill_name"),
						Long.valueOf(results1.getInt("devId")));
				
				assessments1.add(assessment1);
			}		
			
			System.out.println(assessments1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return assessments1;
		}


@Override
public List<Assessment> findAllJoined() {

	List<Assessment> assessments1 = new ArrayList<>();


	 String sql1 = "SELECT USERS.firstName, USERS.lastName, ASSESSMENTS.skill_name, ASSESSMENTS.skillLevel, ASSESSMENTS.monthsExp, ASSESSMENTS.devId FROM ASSESSMENTS INNER JOIN USERS ON ASSESSMENTS.devId=USERS.devId";
	try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1); ) {

		ResultSet results1 = ps1.executeQuery();


		while (results1.next()) {
			Assessment assessment1 = new Assessment
					(
					results1.getString("firstName"),
					results1.getString("lastName"),
					results1.getInt("monthsExp"),
					results1.getInt("skillLevel"),
					results1.getString("skill_name"),
					Long.valueOf(results1.getInt("devId"))
					);

			assessments1.add(assessment1);
			
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
	return assessments1;

}

@Override 
public List<Assessment> findjoinedtable2(String firstName, String lastName){
	  List<Assessment> assessments1 = new ArrayList<>();
		
	  String sql1 = "SELECT USERS.firstName, USERS.lastName, ASSESSMENTS.skill_name, ASSESSMENTS.skillLevel, ASSESSMENTS.monthsExp, ASSESSMENTS.devId FROM ASSESSMENTS INNER JOIN USERS ON ASSESSMENTS.devId=USERS.devId WHERE firstname = ? AND lastname =?";
	  try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {
		  ps1.setString(1, firstName);
		  ps1.setString(2, lastName);
		  
		  ResultSet results1 = ps1.executeQuery();
		  
			while (results1.next()) {
				Assessment assessment1 = new Assessment(
						
						results1.getString("firstName"),
						results1.getString("lastName"),
						results1.getInt("monthsExp"),
						results1.getInt("skillLevel"),
						results1.getString("skill_name"),
						Long.valueOf(results1.getInt("devId")));
				
				assessments1.add(assessment1);
			}		
			
			System.out.println(assessments1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return assessments1;
		}

@Override 
public List<Assessment> findjoinedtable3(String firstName, String lastName){
	  List<Assessment> assessments1 = new ArrayList<>();
		
	  String sql2 = "SELECT USERS.firstName, USERS.lastName, ASSESSMENTS.skill_name, ASSESSMENTS.skillLevel, ASSESSMENTS.monthsExp, ASSESSMENTS.devId FROM ASSESSMENTS INNER JOIN USERS ON ASSESSMENTS.devId=USERS.devId WHERE firstName = ? OR lastName =?";
	  try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql2)) {
		  ps1.setString(1, firstName);
		  ps1.setString(2, lastName);
		  
		  ResultSet results1 = ps1.executeQuery();
		  
			while (results1.next()) {
				Assessment assessment2 = new Assessment(
						results1.getString("firstName"),
						results1.getString("lastName"),
						results1.getInt("monthsExp"),
						results1.getInt("skillLevel"),
						results1.getString("skill_name"),
						Long.valueOf(results1.getInt("devId")));
				
				assessments1.add(assessment2);
			}		
			
			System.out.println(assessments1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return assessments1;
		}
@Override
public List<Assessment> findByLevelExp2(int monthsExp, int skillLevel) {
	List<Assessment> assessments1 = new ArrayList<>();

	 String sql1 = "SELECT USERS.firstName, USERS.lastName, ASSESSMENTS.skill_name, ASSESSMENTS.skillLevel, ASSESSMENTS.monthsExp, ASSESSMENTS.devId FROM ASSESSMENTS INNER JOIN USERS ON ASSESSMENTS.devId=USERS.devId WHERE monthsExp = ? AND skillLevel =?";

	
	try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {

		ps1.setInt(1, monthsExp);
		ps1.setInt(2, skillLevel);

		ResultSet results1 = ps1.executeQuery();

		while (results1.next()) {
			Assessment assessment1 = new Assessment(
					results1.getString("firstName"),
					results1.getString("lastName"),
					results1.getInt("monthsExp"),
					results1.getInt("skillLevel"),
					results1.getString("skill_name"),
					Long.valueOf(results1.getInt("devId")));

			assessments1.add(assessment1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
	return assessments1;
}
@Override
public List<Assessment> findByLevelExp(int monthsExp, int skillLevel) {
	List<Assessment> assessments1 = new ArrayList<>();

	 String sql1 = "SELECT USERS.firstName, USERS.lastName, ASSESSMENTS.skill_name, ASSESSMENTS.skillLevel, ASSESSMENTS.monthsExp, ASSESSMENTS.devId FROM ASSESSMENTS INNER JOIN USERS ON ASSESSMENTS.devId=USERS.devId WHERE monthsExp = ? OR skillLevel =?";

	
	try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {

		ps1.setInt(1, monthsExp);
		ps1.setInt(2, skillLevel);

		ResultSet results1 = ps1.executeQuery();

		while (results1.next()) {
			Assessment assessment1 = new Assessment(
					results1.getString("firstName"),
					results1.getString("lastName"),
					results1.getInt("monthsExp"),
					results1.getInt("skillLevel"),
					results1.getString("skill_name"),
					Long.valueOf(results1.getInt("devId")));

			assessments1.add(assessment1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
	return assessments1;
}

///----- 5  ------- 	
}


