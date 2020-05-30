package com.mirror.dao;

import com.github.pagehelper.PageInfo;
import com.mirror.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/5.
 * 操作日志Dao
 */
@Repository
public interface SysLogDao {
    //保存日志记录
    @Insert("insert into syslog(visitTime,username,userIp,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void saveSysLog(SysLog sysLog) throws Exception;

    //查询所有日志记录
    @Select("select * from sysLog")
    @Results({
          @Result(id = true,column = "userIp",property = "ip"),
          @Result(property = "visitTime",column = "visitTime"),
          @Result(property = "username",column = "username"),
          @Result(property = "ip",column = "userIp"),
          @Result(property = "url",column = "url"),
          @Result(property = "executionTime",column = "executionTime"),
          @Result(property = "method",column = "method"),
    })
    List<SysLog> findAll()throws Exception;
}
