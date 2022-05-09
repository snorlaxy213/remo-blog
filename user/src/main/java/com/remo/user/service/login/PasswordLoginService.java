package com.remo.user.service.login;

import com.remo.user.common.constant.ErrorMessageConstant;
import com.remo.user.common.constant.RemoConstant;
import com.remo.user.common.enums.LoginType;
import com.remo.user.common.exception.exception.BusinessException;
import com.remo.user.manager.UserManager;
import com.remo.user.pojo.dto.UserDto;
import com.remo.user.pojo.dto.query.LoginQuery;
import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.utils.LoginHelper;
import com.remo.user.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("passwordLoginService")
public class PasswordLoginService implements LoginService {

    @Resource(name = "loginHelper")
    LoginHelper loginHelper;

    @Resource(name = "userManager")
    private UserManager userManager;

    @Autowired
    public PasswordLoginService() {
        LoginService.loginTypeMap.put(LoginType.PASSWORD_LOGIN.loginType, this);
    }

    @Override
    public ResponseVo login(LoginQuery query) throws Exception {
        String username = StringUtils.lowerCase(query.getUsername());
        String password = query.getPassword();
//        String password = MD5Util.encrypt(query.getPassword());

        UserDto userDto = this.userManager.getUser(username);

        //用户不存在
        if (userDto == null) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, ErrorMessageConstant.USER_NOT_EXIST);
        }
        //密码不正确
        if (!StringUtils.equals(userDto.getPassword(), password)) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, ErrorMessageConstant.PASSWORD_ERROR);
        }

        //登录成功生成返回信息
        Map<String, Object> userInfo = loginHelper.getUserInfo(userDto);
        return ResponseUtil.initSuccessResultVO(userInfo);
    }
}
