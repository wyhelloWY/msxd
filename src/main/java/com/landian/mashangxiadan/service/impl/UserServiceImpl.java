package com.landian.mashangxiadan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landian.mashangxiadan.mapper.UserMapper;
import com.landian.mashangxiadan.pojo.UserInfo;
import com.landian.mashangxiadan.service.UserService;
import com.landian.mashangxiadan.utils.GetDateUtil;
import com.landian.mashangxiadan.utils.PageRequest;
import com.landian.mashangxiadan.utils.PageResult;
import com.landian.mashangxiadan.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/22 22:44
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 日志记录
     */
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserMapper userMapper;

    /**
     * 登录实现
     * @param user_id
     * @param user_pass
     * @return
     */
    @Override
    public boolean loginWx(int user_id, String user_pass) {
        
        String pass = userMapper.selectToPass(user_id);

        if(pass.equals(user_pass)){
            try{
                userMapper.updateUserLoginTime(GetDateUtil.getDateTime(),user_id);
            }catch (Exception e){
                e.printStackTrace();
                logger.info("登录校验出现异常");
            }
            return true;
        }else{
            return false;
        }
    }


    /**
     * 插入用户
     * @param userInfo
     * @return
     */
    @Override
    public boolean insertUser(UserInfo userInfo) {
        try{
            int flag = userMapper.insertUser(userInfo);
            if(flag>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("插入用户出现异常");
        }
        return false;
    }

    /**
     * 修改贡献点
     * @param user_id
     * @param user_devote
     * @return
     */
    @Override
    public boolean updateDevote(int user_id, int user_devote) {
        int flag = 0;
        try{
            flag = userMapper.updateUserType(user_id,user_devote);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改贡献点出现异常");
        }
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UserInfo selectUser(int user_id) {
        return userMapper.selectById(user_id);
    }

    @Override
    public int updateManageUser(UserInfo userInfo) {
        return userMapper.updateManageUser(userInfo);
    }

    @Override
    public UserInfo selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    /**
     * 查找所有用户
     * @return
     */
    @Override
    public List<UserInfo> selectAllUser() {
        List<UserInfo> userInfos = new ArrayList<>();
        try{
            userInfos = userMapper.listAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有用户出现异常");
        }
        return userInfos;
    }

    /**
     * 修改权限状态
     * @param user_id
     * @param user_statue
     * @return
     */
    @Override
    public boolean updateUserStatue(int user_id, int user_statue) {
        int flag = 0;
        try{
            flag = userMapper.updateUserType(user_id,user_statue);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改权限状态出现异常");
        }
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateUserPass(String user_pass, int user_id) {
        int flag = 0;
        boolean result = false;
        flag = userMapper.updateUserPass(user_pass,user_id);
        if(flag>0){
            result = true;
        }
        return result;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }
    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<?> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> sysMenus = userMapper.selectPage();
        return new PageInfo<UserInfo>(sysMenus);
    }

    @Override
    public List<UserInfo> getUserInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> sysMenus = userMapper.selectPage();
        return sysMenus;
    }

    @Override
    public List<UserInfo> listBykeyWord(String key) {
        return userMapper.listBykeyWord(key);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

}
