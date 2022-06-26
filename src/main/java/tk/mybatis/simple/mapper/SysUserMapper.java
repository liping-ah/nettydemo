package tk.mybatis.simple.mapper;
import  tk.mybatis.simple.model.SysUser;

import java.util.List;

public interface SysUserMapper {
    SysUser selectById(Long id);
    List<SysUser> selectAll();
}
