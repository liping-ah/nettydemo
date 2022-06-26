package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.SysUser;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class SysUserMapperTest extends BaseMapperTest{
    private static SqlSessionFactory sqlSessionFactory;


    @Test
    public void  testSelectAll(){
        SqlSession sqlSession=getSqlSession();
        try {
            List<SysUser> countryList=sqlSession.selectList("selectAll");
            printCountryList(countryList);
        } finally {
            sqlSession.close();
        }
    }
    private  void printCountryList(List<SysUser>countryList){
        for (SysUser country:countryList){
            System.out.print(country.getId()+"   ");


        }
    }
}
