package com.mirror.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mirror.dao.SysLogDao;
import com.mirror.domain.SysLog;
import com.mirror.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/5.
 * 日志业务处理层
 */
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 保存日志记录
     * @param sysLog
     * @throws Exception
     */
    @Override
    public void saveSysLog(SysLog sysLog)throws Exception{
        sysLogDao.saveSysLog(sysLog);
    }

    /**
     * 查询所有日志记录
     * @param page
     * @param pagesize
     * @return  分页后的日志记录
     * @throws Exception
     */
    @Override
    public List<SysLog> findAll(Integer page, Integer pagesize) throws Exception {
        PageHelper.startPage(page,pagesize);
        return sysLogDao.findAll();
    }
}
