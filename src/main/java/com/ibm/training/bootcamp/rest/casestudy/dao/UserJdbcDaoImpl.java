package com.ibm.training.bootcamp.rest.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.rest.casestudy.domain.User;

public class UserJdbcDaoImpl implements UserDao {

	private static UserJdbcDaoImpl INSTANCE;

	private JDBCDataSource dataSource;

	static public UserJdbcDaoImpl getInstance() {

		UserJdbcDaoImpl instance1;
		if (INSTANCE != null) {
			instance1 = INSTANCE;
		} else {
			instance1 = new UserJdbcDaoImpl();
			INSTANCE = instance1;
		}
		return instance1;
	}

	private UserJdbcDaoImpl() {
		init();
	}

	private void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:USER");
		dataSource.setUser("username");
		dataSource.setPassword("password");

		createUserTable();
		insertInitUsers();
	}

	private void createUserTable() {
		String createSql1 = " CREATE TABLE USERS" + "(id INTEGER IDENTITY PRIMARY KEY," + "firstName VARCHAR(255),"
				+ "middleName VARCHAR(255)," + " lastName VARCHAR(255)," + "birthdate DATE," + "position VARCHAR(255))";

		try (Connection conn1 = dataSource.getConnection(); Statement stmt1 = conn1.createStatement()) {
			stmt1.executeUpdate(createSql1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitUsers() {
		add(new User("Tony", " mName ", "Stark", Date.valueOf("1996-07-25"), "IronMan"));
		add(new User("Steve", " mName ", "Rogers", Date.valueOf("1996-07-25"), "Cap"));
		add(new User("Peter", " mName ", "Parker", Date.valueOf("1996-07-25"), "Spidy"));

	}

	@Override
	public List<User> findAll() {
		return findByName(null, null);
	}

	@Override
	public User find(Long id) {

		User user = null;

		if (id != null) {
			String sql1 = "SELECT id, firstName, middleName, lastName, birthDate, position FROM USERS where id =?";
			try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {

				ps1.setInt(1, id.intValue());
				ResultSet results1 = ps1.executeQuery();

				if (results1.next()) {
					user = new User(Long.valueOf(results1.getInt("id")), results1.getString("firstName"),
							results1.getString("middleName"), results1.getString("lastName"),
			   				results1.getDate("birthDate"), results1.getString("position"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return user;
	}

	@Override
	public List<User> findByName(String firstName, String lastName) {
		List<User> users = new ArrayList<>();

		String sql1 = "SELECT * FROM USERS WHERE firstName LIKE ? OR lastName LIKE ?";

		try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn1.prepareStatement(sql1)) {

			ps1.setString(1, createSearchValue(firstName));
			ps1.setString(2, createSearchValue(lastName));

			ResultSet results1 = ps1.executeQuery();

			while (results1.next()) {
				User user = new User(Long.valueOf(results1.getInt("id")), results1.getString("firstName"),
						results1.getString("middleName"), results1.getString("lastName"), results1.getDate("birthDate"),
						results1.getString("position"));

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return users;
	}

	private String createSearchValue(String string1) {
		String value1;

		if (StringUtils.isBlank(string1)) {
			value1 = "%";
		} else {
			value1 = string1;
		}
		return value1;
	}

	@Override
	public void add(User user) {
		String insertSql1 = "INSERT INTO USERS (firstName, middleName, lastName, birthDate, position) VALUES (?,?,?,?,?)";

		try (Connection conn1 = dataSource.getConnection();
				PreparedStatement ps1 = conn1.prepareStatement(insertSql1)) {

			ps1.setString(1, user.getFirstName());
			ps1.setString(2, user.getMiddleName());
			ps1.setString(3, user.getLastName());
			ps1.setDate(4, user.getBirthDate());
			ps1.setString(5, user.getPosition());

			ps1.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
