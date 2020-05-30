package com.mirror.controller;

import com.github.pagehelper.PageInfo;
import com.mirror.domain.SysLog;
import com.mirror.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/6.
 * 日志控制层Controller
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有日志记录
     * @param page
     * @param pagesize
     * @return  ModelAndView ： 所有日志记录 And 视图
     */
    @GetMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "pagesize",required = true,defaultValue = "5") Integer pagesize) throws Exception {
        ModelAndView mv = new ModelAndView();

        List<SysLog> sysLogList = sysLogService.findAll(page, pagesize);
        for (SysLog s:sysLogList
             ) {
            System.out.println(s);
        }
        PageInfo pageInfo = new PageInfo(sysLogList);

        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
