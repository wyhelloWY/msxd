package com.landian.mashangxiadan.service;

import com.landian.mashangxiadan.pojo.UserInfo;
import com.landian.mashangxiadan.utils.PageRequest;
import com.landian.mashangxiadan.utils.PageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/22 22:44
 */

public interface UserService {
    /**
     * 校验登录
     * @param user_id
     * @param user_pass
     * @return
     */
    boolean loginWx(int user_id,String user_pass);

    /**
     * 插入用户
     * @param userInfo
     * @return
     */
    boolean insertUser(UserInfo userInfo);

    /**
     * 修改并且返回当前贡献点
     * @param user_id
     * @param user_devote
     * @return
     */
    boolean updateDevote(int user_id,int user_devote);

    /**
     * 查询单个用户
     * @param user_id
     * @return
     */
    UserInfo selectUser(int user_id);
    /**
     * 修改用户
     * @param userInfo
     * @return
     */
    int updateManageUser(UserInfo userInfo);
    /**
     * 查询单个用户
     * @param id
     * @return
     */
    UserInfo selectUserById(int id);
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> selectAllUser();

    /**
     * 修改当前用户的权限状态
     * @param user_id
     * @param user_statue
     * @return
     */
    boolean updateUserStatue(int user_id,int user_statue);

    /**
     * 修改密码
     * @param user_pass
     * @param user_id
     * @return
     */
    boolean updateUserPass(String user_pass,int user_id);

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);

    List<UserInfo> getUserInfo(PageRequest pageRequest);

    /**
     * 查找
     * @param key
     * @return
     */
    List<UserInfo> listBykeyWord(String key);

    int deleteUser(int id);
}
