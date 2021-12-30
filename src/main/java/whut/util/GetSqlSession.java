package whut.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class GetSqlSession {
	public static SqlSessionFactory sqlSessionFactory = null;
	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			String resource = "mybatis-config.xml";
			try {
				InputStream input = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}

	public static SqlSession createSqlSession() {
//		SqlSessionFactory sqlSessionFactory = null;
//		InputStream input = null;
//		SqlSession session = null;
//
//		try {
//			String resource = "mybatis-config.xml";
//			input = Resources.getResourceAsStream(resource);
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
//			//sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
//			session = sqlSessionFactory.openSession();
//			return session;
//		}catch(IOException e) {
//			e.printStackTrace();
//			return null;
//		}
		sqlSessionFactory = getSqlSessionFactory();
		SqlSession session;
		session = sqlSessionFactory.openSession();
		return session;
	}
	public static void main(String[] args) {
		System.out.println(createSqlSession());
	}
	
}
