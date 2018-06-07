package com.ztes.sell.utils;

import com.ztes.sell.VO.ResultVO;
import com.ztes.sell.enums.ResultVOEnum;

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultVOEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultVOEnum.SUCCESS.getMsg());
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO fail(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

}
