package org.go.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {

	@Autowired
	private DataSource ds;

	@Setter(onMethod_ = { @Autowired })
	private SqlSessionFactory sqlsessionFactory;


	@Test
	public void testMyBatis() {
		try (SqlSession session = sqlsessionFactory.openSession();
				Connection con = session.getConnection();
				){

			log.info(session);
			log.info(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testConnection() throws Exception {
		Connection con = ds.getConnection();

		log.info(con);

		con.close();
	}

}
