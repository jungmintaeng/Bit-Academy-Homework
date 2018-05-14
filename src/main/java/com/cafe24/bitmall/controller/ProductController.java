package com.cafe24.bitmall.controller;

import com.cafe24.bitmall.service.CategoryService;
import com.cafe24.bitmall.service.OptionService;
import com.cafe24.bitmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OptionService optionService;

    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "c", required = false, defaultValue = "0L") Long cNo,
            Model model
    ){
        if(cNo == 0L){
            return "redirect:/";
        }
        model.addAttribute("category", categoryService.getByNo(cNo));
        model.addAttribute("productList", productService.getList(cNo));
        return "product/product";
    }

    @RequestMapping(value = "/detail/{no}")
    public String detail(
            @PathVariable("no") Long no,
            Model model
    ){
        model.addAttribute("optionList", optionService.optionDtoList(no));
        model.addAttribute("product", productService.getByNo(no));
        return "product/product_detail";
    }

    @RequestMapping(value = "/search")
    public String search(){
        return "product/product_search";
    }
}
