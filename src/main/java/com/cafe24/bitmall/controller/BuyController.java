package com.cafe24.bitmall.controller;

import com.cafe24.bitmall.dto.CartDto;
import com.cafe24.bitmall.dto.JSONResult;
import com.cafe24.bitmall.service.CartService;
import com.cafe24.bitmall.service.OrderService;
import com.cafe24.bitmall.vo.OrderVo;
import com.cafe24.bitmall.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/buy")
@Auth
public class BuyController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    /**
     * Member cart
     */

    @RequestMapping(value = "/cart")
    public String cart(
            @AuthUser UserVo userVo,
            Model model
    ) {
        model.addAttribute("cartList", cartService.getCartList(userVo.getNo()));
        return "buy/cart";
    }

    @RequestMapping(value="/cart/add", produces = "application/json")
    @ResponseBody
    public JSONResult addCart(
            @AuthUser UserVo userVo,
            @RequestParam(value = "p", required = true, defaultValue = "0L") Long productNo,
            @RequestParam(value = "o", required = false) List<Long> optionList,
            @RequestParam(value = "q", required = true, defaultValue = "0") Long quantity
    ){
        return JSONResult.success(cartService.addCart(userVo.getNo(), productNo, optionList, quantity));
    }

    @RequestMapping(value = "/cart/modify", produces = "application/json")
    @ResponseBody
    public JSONResult modifyCart(
            @RequestParam("c") Long no,
            @RequestParam("q") Long quantity
    ){
        return cartService.updateCart(no, quantity) ?
                JSONResult.success(no) : JSONResult.success(0);
    }

    @RequestMapping(value="/cart/delete", produces = "application/json")
    @ResponseBody
    public JSONResult deleteCart(
            @RequestParam("no") Long no
    ){
        return cartService.deleteCart(no) ?
                JSONResult.success(no) : JSONResult.success(0);
    }

    @RequestMapping(value="/cart/delete/all", produces = "application/json")
    @ResponseBody
    public JSONResult deleteAllCart(
            @AuthUser UserVo userVo
    ){
        return cartService.deleteAllCart(userVo.getNo()) ? JSONResult.success(1) : JSONResult.success(0);
    }

    /**
     * Member Order List
     */

    @RequestMapping(value = "/order/list")
    public String list() {
        return "buy/jumun";
    }

    @RequestMapping(value = "/order/detail")
    public String detail() {
        return "buy/jumun_info";
    }

    /**
     * Ordering
     */

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(
            @AuthUser UserVo userVo,
            @RequestParam(value = "p", required = false, defaultValue = "0L") Long productNo,
            @RequestParam(value = "o", required = false) List<Long> optionList,
            @RequestParam(value = "q", required = false, defaultValue = "0") Long quantity,
            Model model
    ) {
        if(productNo == 0L)
            model.addAttribute("orderProductList", cartService.getCartList(userVo.getNo()));
        else
            model.addAttribute("orderProductList", cartService.convertToDto(userVo.getNo(),productNo,optionList,quantity));
        return "buy/order";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(
            @ModelAttribute OrderVo vo,
            HttpServletRequest request
    ) {
        request.getSession().setAttribute("orderInfo", vo);
        return "buy/order_pay";
    }

    /**
     * Paying
     */

    @RequestMapping(value = "/order/pay", method = RequestMethod.POST)
    public String pay(
            @RequestParam(value = "bank_kind", required = false) String bankKind,
            @RequestParam(value = "card_kind", required = false) String cardKind,
            HttpServletRequest request
    ) {
        String paymentMethodName = bankKind == null ? cardKind : bankKind;
        HttpSession session = request.getSession();
        OrderVo orderVo = (OrderVo)session.getAttribute("orderInfo");
        List<CartDto> cartDtoList = (List<CartDto>)session.getAttribute("orderProductList");
        session.removeAttribute("orderInfo");
        session.removeAttribute("orderProductList");
        Calendar c = Calendar.getInstance();
        StringBuilder codeBuilder = new StringBuilder();
        codeBuilder.append(c.get(Calendar.YEAR));
        codeBuilder.append(c.get(Calendar.MONTH));
        codeBuilder.append(c.get(Calendar.DATE));
        codeBuilder.append(c.get(Calendar.HOUR_OF_DAY));
        codeBuilder.append(c.get(Calendar.MINUTE));
        codeBuilder.append(c.get(Calendar.SECOND));
        codeBuilder.append(c.get(Calendar.MILLISECOND));
        orderVo.setCode("MU24SIC" + codeBuilder.toString());
        orderVo.setState("주문완료");
        orderVo.setPaymentMethodName(paymentMethodName);
        orderService.addOrder(orderVo, cartDtoList);
        return "buy/order_ok";
    }
}
