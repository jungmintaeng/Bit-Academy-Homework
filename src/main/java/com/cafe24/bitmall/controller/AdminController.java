package com.cafe24.bitmall.controller;

import com.cafe24.bitmall.dto.ImageDto;
import com.cafe24.bitmall.dto.JSONResult;
import com.cafe24.bitmall.dto.OptionDto;
import com.cafe24.bitmall.service.AdminService;
import com.cafe24.bitmall.vo.FaqVo;
import com.cafe24.bitmall.vo.OptionVo;
import com.cafe24.bitmall.vo.ProductVo;
import com.cafe24.bitmall.vo.UserVo;
import com.cafe24.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@Auth(role = Auth.Role.ADMIN)
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * Main Page
     */
    @RequestMapping(value = "/main")
    public String main(){
        return "admin/main";
    }

    /**
     * FAQ MANAGEMENT
     */

    @RequestMapping(value = "/faq/list", method = RequestMethod.GET)
    public String faqList(
            Model model
    ) {
        model.addAttribute("list", adminService.getFaqList());
        return "admin/faq";
    }

    @RequestMapping(value = "/faq/edit/{no}", method = RequestMethod.GET)
    public String faqEdit(
            @PathVariable("no") Long no,
            Model model
    ) {
        model.addAttribute("faq", adminService.getFaqByNo(no));
        return "admin/faq_edit";
    }

    @RequestMapping(value = "/faq/edit", method = RequestMethod.POST)
    public String faqEdit(
            @ModelAttribute FaqVo vo
    ) {
        if(adminService.updateFaq(vo)){
            return "redirect:/admin/faq/list";
        }
        return "admin/faq_edit";
    }

    @RequestMapping(value = "/faq/new", method = RequestMethod.GET)
    public String faqNew() {
        return "admin/faq_new";
    }

    @RequestMapping(value = "/faq/new", method = RequestMethod.POST)
    public String faqNew(
            @ModelAttribute FaqVo vo
    ) {
        if(adminService.addFaq(vo)){
            return "redirect:/admin/faq/list";
        }
        return "admin/faq_new";
    }

    @RequestMapping(value = "/faq/delete/{no}")
    public String faqDelete(
            @PathVariable("no") Long no
    ){
        adminService.deleteFaq(no);
        return "admin/faq";
    }

    /**
     * ORDER MANAGEMENT
     */

    @RequestMapping(value = "/order/list")
    public String orderList(
            Model model
    ) {
        model.addAttribute("list", adminService.getOrderList());
        return "admin/jumun";
    }

    @RequestMapping(value = "/order/edit/{no}", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult orderEdit(
            @PathVariable("no") Long no,
            @RequestParam("state") String state
    ) {
        return adminService.updateOrderState(no, state) ?
                JSONResult.success(no) : JSONResult.success(0);
    }

    @RequestMapping(value = "/order/delete/{no}")
    @ResponseBody
    public JSONResult orderDelete(
            @PathVariable("no") Long no
    ) {
        return adminService.deleteOrder(no) ?
                JSONResult.success(no) : JSONResult.success(0);
    }

    /**
     * Member Management
     */

    @RequestMapping(value = "/member/list")
    public String memberList(
            Model model
    ) {
        model.addAttribute("list", adminService.getUserList());
        return "admin/member";
    }

    @RequestMapping(value = "/member/edit/{no}", method = RequestMethod.GET)
    public String memberEdit(
            @PathVariable("no") Long no,
            Model model
    ) {
        model.addAttribute("who", "admin");
        model.addAttribute("user", adminService.getUserByNo(no));
        return "user/member_modify";
    }

    @RequestMapping(value = "/member/edit", method = RequestMethod.POST)
    public String memberEdit(
            @ModelAttribute UserVo vo,
            Model model
    ) {
        if(adminService.modifyUser(vo)){
            return "redirect:/admin/member/list";
        }

        model.addAttribute("user", vo);
        return "user/member_modify";
    }

    @RequestMapping(value = "/member/delete/{no}", produces = "application/json")
    @ResponseBody
    public JSONResult memberDelete(
            @PathVariable("no") Long no
    ) {
        return adminService.deleteUser(no) ?
                JSONResult.success(no) : JSONResult.success(0);
    }

    /**
     * Option Management
     */

    @RequestMapping(value = "/option/list")
    public String optionList(
            Model model
    ) {
        model.addAttribute("list", adminService.getOptionList());
        return "admin/opt";
    }

    @RequestMapping(value = "/option/edit/{no}", method = RequestMethod.GET)
    public String optionEdit(
            @PathVariable("no") Long no,
            Model model
    ) {
        model.addAttribute("option", adminService.getOptionByNo(no));
        return "admin/opt_edit";
    }

    @RequestMapping(value = "/option/edit", method = RequestMethod.POST)
    public String optionEdit(
            @ModelAttribute OptionVo vo,
            Model model
    ) {
        if(adminService.updateOption(vo))
            return "redirect:/admin/option/list";
        model.addAttribute("option", vo);
        return "admin/opt_edit";
    }

    @RequestMapping(value = "/option/new", method = RequestMethod.GET)
    public String optionNew() {
        return "admin/opt_new";
    }

    @RequestMapping(value = "/option/new", method = RequestMethod.POST)
    public String optionNew(
            @ModelAttribute OptionVo vo
    ) {
        vo.setParentNo(null);
        if(adminService.addOption(vo))
            return "redirect:/admin/option/list";
        return "admin/opt_new";
    }

    @RequestMapping(value = "/option/delete/{no}", produces = "application/json")
    @ResponseBody
    public JSONResult optionDelete(
            @PathVariable("no") Long no
    ) {
        return adminService.deleteOption(no) ?
                JSONResult.success(no) : JSONResult.success(0);
    }

    /**
     * Small Option Management
     */

    @RequestMapping(value = "/option/small/list/{parentNo}")
    public String smallOptionList(
            @PathVariable("parentNo") Long parentNo,
            Model model
    ) {
        model.addAttribute("parent", adminService.getOptionByNo(parentNo));
        model.addAttribute("list", adminService.getOptionList(parentNo));
        return "admin/opts";
    }

    @RequestMapping(value = "/option/small/new/{parentNo}/{name}", produces = "application/json")
    @ResponseBody
    public JSONResult smallOptionNew(
            @PathVariable("parentNo") Long parentNo,
            @PathVariable("name") String name
    ) {
        return JSONResult.success(adminService.addOption(parentNo, name));
    }

    @RequestMapping(value = "/option/small/edit/{parentNo}/{no}", method = RequestMethod.GET)
    public String smallOptionEdit(
            @PathVariable("parentNo") Long parentNo,
            @PathVariable("no") Long no,
            Model model
    ) {
        model.addAttribute("parentNo", parentNo);
        model.addAttribute("option", adminService.getOptionByNo(no));
        return "admin/opts_edit";
    }

    @RequestMapping(value = "/option/small/edit", method = RequestMethod.POST)
    public String smallOptionEdit(
            @ModelAttribute OptionVo vo,
            Model model
    ) {
        model.addAttribute("option", vo);
        return adminService.updateOption(vo) ?
                "redirect:/admin/option/small/list/" + vo.getParentNo()
                : "admin/opts_edit";
    }

    /**
     * Product Management
     */

    @RequestMapping(value = "/product/list")
    public String productList(
            Model model
    ) {
        model.addAttribute("list", adminService.getProductList());
        return "admin/product";
    }

    @RequestMapping(value = "/product/edit/{no}", method = RequestMethod.GET)
    public String productEdit(
            @PathVariable("no") Long no,
            Model model
    ) {
        model.addAttribute("product", adminService.getProductDtoByNo(no));
        return "admin/product_edit";
    }

    @RequestMapping(value = "/product/edit", method = RequestMethod.POST)
    public String productEdit(
            @ModelAttribute ProductVo productVo,
            @ModelAttribute ImageDto imageDto,
            @ModelAttribute OptionDto optionDto,
            Model model
    ) {
        model.addAttribute("product", adminService.getProductDtoByNo(productVo.getNo()));
        return adminService.updateProduct(productVo, imageDto, optionDto) ?
                "redirect:/admin/product/list" : "admin/product_edit";
    }

    @RequestMapping("/product/image/delete/{no}")
    @ResponseBody
    public JSONResult deleteImage(
            @PathVariable("no") Long no
    ){
        return adminService.deleteImage(no) ? JSONResult.success(no) : JSONResult.success(0);
    }

    @RequestMapping(value = "/product/new", method = RequestMethod.GET)
    public String productNew(
            Model model
    ) {
        model.addAttribute("product", adminService.getNewProductDto());
        return "admin/product_new";
    }

    @RequestMapping(value = "/product/new", method = RequestMethod.POST)
    public String productNew(
            @ModelAttribute ProductVo productVo,
            @ModelAttribute ImageDto imageDto,
            @ModelAttribute OptionDto optionDto
    ) {
        return adminService.addProduct(productVo, imageDto, optionDto) ?
                "redirect:/admin/product/list" : "admin/product_new";
    }

    @RequestMapping(value = "/product/delete/{no}", produces = "application/json")
    @ResponseBody
    public JSONResult productDelete(
            @PathVariable("no") Long no
    ) {
        return adminService.deleteProduct(no) ?
                JSONResult.success(no) : JSONResult.success(0);
    }
}
