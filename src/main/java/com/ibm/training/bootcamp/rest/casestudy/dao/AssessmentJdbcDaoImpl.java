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
import org.apache.commons.lang3.math.NumberUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.rest.casestudy.domain.Skills;
import com.ibm.training.bootcamp.rest.casestudy.domain.User;
import com.ibm.training.bootcamp.rest.casestudy.service.UserService;
import com.ibm.training.bootcamp.rest.casestudy.service.UserServiceImpl;
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
		
		String createSql = "CREATE TABLE ASSESSMENTS" + "(id INTEGER IDENTITY PRIMARY KEY," + "monthsExp Integer,"
				+ "skillLevel Integer," + "devId INTEGER FOREIGN  KEY REFERENCES USERS(devId)," + "skillId INTEGER FOREIGN  KEY REFERENCES SKILLS(id))";
		

		
		try (Connection conn = dataSource.getConnection(); Statement stmt1 = conn.createStatement()) {
			
			stmt1.executeUpdate(createSql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitAssessment() {
		                         
		add(new Assessment(3, 4, Long.valueOf(0),Long.valueOf(1)));
		add(new Assessment(1, 5, Long.valueOf(1), Long.valueOf(1)));
		add(new Assessment(2, 6, Long.valueOf(2), Long.valueOf(0)));

	}

	@Override
	public List<Assessment> findAll() {

		List<Assessment> assessments1 = new ArrayList<>();

		String sql1 = "SELECT * FROM ASSESSMENTS ";

		try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {

			ResultSet results1 = ps1.executeQuery();

			while (results1.next()) {
				Assessment assessment1 = new Assessment(Long.valueOf(results1.getInt("id")),
						results1.getInt("monthsExp"), results1.getInt("skillLevel"),
						Long.valueOf(results1.getInt("devId")), Long.valueOf(results1.getInt("skillId")));

				assessments1.add(assessment1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return assessments1;

	}

	@Override
	public Assessment find(Long id) {
		Assessment assessment = null;

		if (id != null) {
			String sql = "SELECT id, monthsExp, skillLevel, devId, skillId  FROM ASSESSMENTS WHERE id=?";
			try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql)) {

				ps1.setInt(1, id.intValue());
				ResultSet results1 = ps1.executeQuery();

				if (results1.next()) {
					assessment = new Assessment(Long.valueOf(results1.getInt("id")), results1.getInt("monthsExp"),
							results1.getInt("skillLevel"), Long.valueOf(results1.getInt("devId")),
							Long.valueOf(results1.getInt("skillId")));

					System.out.println((Long.valueOf(results1.getInt("id")) + results1.getInt("monthsExp")
							+ results1.getInt("skillLevel") + Long.valueOf(results1.getInt("devId"))
							+ Long.valueOf(results1.getInt("skillId"))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return assessment;
	}

	@Override
	public List<Assessment> findByLevelExp(int monthsExp, int skillLevel) {
		List<Assessment> assessments1 = new ArrayList<>();

		String sql1 = "SELECT * FROM ASSESSMENTS WHERE monthsExp = ? OR skillLevel = ? ";

		try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {

			ps1.setInt(1, monthsExp);
			ps1.setInt(2, skillLevel);

			ResultSet results1 = ps1.executeQuery();

			while (results1.next()) {
				Assessment assessment1 = new Assessment(Long.valueOf(results1.getInt("id")),
						results1.getInt("monthsExp"), results1.getInt("skillLevel"),
						Long.valueOf(results1.getInt("devId")), Long.valueOf(results1.getInt("skillId")));

				assessments1.add(assessment1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return assessments1;
	}

	@Override
	public void add(Assessment assessment) {
		
		
		String insertSql1 = "INSERT INTO ASSESSMENTS (monthsExp, skillLevel, devId, skillId) VALUES (?,?,?,?)";

		try (Connection conn1 = dataSource.getConnection();
				PreparedStatement ps1 = conn1.prepareStatement(insertSql1)) {
			
			System.out.println(assessment.getDevId() + " " + assessment.getSkillId());
			
			
			ps1.setInt(1, assessment.getMonthsExp());
			ps1.setInt(2, assessment.getSkillLevel());
			ps1.setLong(3, assessment.getDevId());
			ps1.setLong(4, assessment.getSkillId());
			
			ps1.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
