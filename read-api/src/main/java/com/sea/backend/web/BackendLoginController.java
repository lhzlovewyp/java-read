package com.sea.backend.web;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sea.backend.model.AdminUser;
import com.sea.backend.service.AdminUserService;
import com.sea.common.cache.redis.RedisUtil;
import com.sea.common.constants.CommonConstant;
import com.sea.common.constants.ErrorCodeEnum;
import com.sea.common.utils.DesUtils;
import com.sea.model.dto.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * Created by lvhaizhen on 2018/7/24.
 */
@RestController
@RequestMapping("/backend")
public class BackendLoginController {

    private static Logger LOG = LoggerFactory.getLogger(BackendLoginController.class);

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private AdminUserService adminUserService;

    private  static int KAPCHA_CODE_MAX_CHECK= 5;

    private static final String KAPCHA_CODE_MAX_CHECK_KEY = "KAPCHA_CODE_MAX_CHECK";


    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String kaptchaCode,
                              HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        Integer value = redisUtil.getInteger(KAPCHA_CODE_MAX_CHECK_KEY);
        if(value >= KAPCHA_CODE_MAX_CHECK){
            return  JSONObject.toJSONString(new ResultDTO(ErrorCodeEnum.ERROR_KAPTCHACODE_CHECK_MAX.code,
                    ErrorCodeEnum.ERROR_KAPTCHACODE_CHECK_MAX.message));
        }
        String sessionKaptchaCode = (String) httpServletRequest.getSession().getAttribute(CommonConstant.VERIFY_CODE);
        //验证码校验.
        if(!kaptchaCode.toLowerCase().equals(sessionKaptchaCode)){
            redisUtil.set(KAPCHA_CODE_MAX_CHECK_KEY,value+1);
            return JSONObject.toJSONString(new ResultDTO(ErrorCodeEnum.ERROR_KAPTCHACODE.code,
                    ErrorCodeEnum.ERROR_KAPTCHACODE.message));
        }

        AdminUser adminUser = adminUserService.queryAdminUserByUsername(username);

        password = DesUtils.getInstance().encrypt(password);
        if(adminUser == null || !password.equals(adminUser.getPassword())){
            return JSONObject.toJSONString(new ResultDTO(ErrorCodeEnum.ERROR_USERNAME.code,
                    ErrorCodeEnum.ERROR_USERNAME.message));
        }

        //把用户信息写入到session中.
        httpServletRequest.getSession().setAttribute(CommonConstant.ADMIN_USER,adminUser);

        return JSONObject.toJSONString(new ResultDTO(ErrorCodeEnum.LOGIN_SUCCESS.code,
                ErrorCodeEnum.LOGIN_SUCCESS.message));
    }


    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{

        redisUtil.remove(KAPCHA_CODE_MAX_CHECK_KEY);

        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText().toLowerCase();
            httpServletRequest.getSession().setAttribute(CommonConstant.VERIFY_CODE, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
