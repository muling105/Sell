package com.ztes.sell.handler;


import com.ztes.sell.VO.ResultVO;
import com.ztes.sell.enums.ResultEnum;
import com.ztes.sell.exception.SellException;
import com.ztes.sell.exception.SellerAuthorizeException;
import com.ztes.sell.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class SellerExceptionHandler{

    //拦截登录异常，跳转到登录页面
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException(){
        return new ModelAndView("redirect:http://localhost:8080/sell/seller/user/login?openid=abc123");
    }

    //拦截自定义的SellException异常，返回前端错误数据
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.fail(e.getCode(), e.getMessage());
    }

    //拦截其他系统异常，返回前端未知错误数据
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handlerException(){
        return ResultVOUtil.fail(ResultEnum.UNKOWN_ERROR.getCode(), ResultEnum.UNKOWN_ERROR.getMsg());
    }

}
