package com.mirror.controller;

import com.github.pagehelper.PageInfo;
import com.mirror.domain.Product;
import com.mirror.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/2/29.
 * 产品表现层
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有产品信息（分页）
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pagesize", required = true, defaultValue = "5") Integer pagesize)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.findAll(page,pagesize);
        PageInfo pageInfo = new PageInfo(productList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    @PostMapping("/saveProduct.do")
    @RolesAllowed("ADMIN")
    public String saveProduct(Product product) throws Exception {
        productService.saveProduct(product);
        return "redirect:findAll.do";
    }
}
