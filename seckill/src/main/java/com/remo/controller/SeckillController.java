package com.remo.controller;

import com.remo.service.SeckillService;
import com.remo.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@Controller
public class SeckillController {

    @Resource(name = "seckillServiceImpl")
    SeckillService seckillServiceImpl;

    /*@AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    public ResultGeekQ<String> getMiaoshaPath(HttpServletRequest request, MiaoshaUser user,
                                              @RequestParam("goodsId") long goodsId,
                                              @RequestParam(value = "verifyCode", defaultValue = "0") int verifyCode
    ) {
        ResultGeekQ<String> result = ResultGeekQ.build();
        if (user == null) {
            result.withError(SESSION_ERROR.getCode(), SESSION_ERROR.getMessage());
            return result;
        }
        boolean check = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
        if (!check) {
            result.withError(REQUEST_ILLEGAL.getCode(), REQUEST_ILLEGAL.getMessage());
            return result;
        }
        String path = miaoshaService.createMiaoshaPath(user, goodsId);
        result.setData(path);
        return result;
    }*/


    @GetMapping(value = "/verifyCode", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getSeckillVerifyCod(@RequestParam("userId") Long userId, @RequestParam("goodsId") Long goodsId) throws IOException {
        BufferedImage image = seckillServiceImpl.createVerifyCode(userId, goodsId);
        return ImageUtil.imageToBytes(image);
    }
}
