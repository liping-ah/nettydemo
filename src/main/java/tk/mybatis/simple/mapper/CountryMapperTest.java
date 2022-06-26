package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

import java.util.List;

public class CountryMapperTest extends BaseMapperTest {
    private static SqlSessionFactory sqlSessionFactory;


    @Test
    public void  testSelectAll(){
        SqlSession sqlSession=getSqlSession();
        try {
            List<Country> countryList=sqlSession.selectList("selectAllcountry");
            printCountryList(countryList);
        } finally {
            sqlSession.close();
        }
    }
    private  void printCountryList(List<Country>countryList){
        for (Country country:countryList){
            System.out.print(country.getId()+"   ");
            System.out.print(country.getCountryName()+"    ");
            System.out.println(country.getCountryCode()+"   ");

        }
    }
}
