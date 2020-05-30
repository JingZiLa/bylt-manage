package com.mirror.service;

import com.github.pagehelper.PageInfo;
import com.mirror.domain.SysLog;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/5.
 * 日志业务接口
 */
public interface SysLogService {

    public void saveSysLog(SysLog sysLog)throws Exception;

    List<SysLog> findAll(Integer page, Integer pagesize)throws Exception;
}
