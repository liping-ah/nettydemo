package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class BaseMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void  init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        reader.close();
    }
   public  SqlSession getSqlSession(){
        return  sqlSessionFactory.openSession();
   }
}
