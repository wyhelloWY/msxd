package com.landian.mashangxiadan.mapper;

import com.landian.mashangxiadan.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/22 14:33
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> listAll();

    /**
     * 查询用户和密码
     * @param user_id
     * @return
     */
    String selectToPass(int user_id);
    /**
     * 查询单个用户 信息查询
     * @param user_id
     * @return
     */
    UserInfo selectById(int user_id);

    /**
     * 插入单个用户
     * @param userInfo
     * @return
     */
    int insertUser(UserInfo userInfo);

    /**
     * 插入多个用户
     * @param user_id
     * @param user_name
     * @param user_pass
     * @param user_sex
     * @param user_academy
     * @param user_devote
     * @param user_type
     * @return
     */
    int insertUserAll(int user_id,String user_name,String user_pass,int user_sex,String user_academy,int user_devote,int user_type);

    /**
     * 查询一个人所有贡献点
     * @param user_id
     * @return
     */
    int selectDevoteById(int user_id);

    /**
     * 删除单个用户
     * @param id
     */
    int deleteUser(int id);
    /**
     * 查询单个用户
     * @param id
     * @return
     */
    UserInfo selectUserById(int id);

    /**
     * 修改用户
     * @param userInfo
     * @return
     */
    int updateManageUser(UserInfo userInfo);
    /**
     * 修改某个用户权限
     * @param user_id
     * @param user_type
     * @return
     */
    int updateUserType(int user_id,int user_type);

    /**
     * 查询某个用户权限
     * @param user_id
     * @return
     */
    int selectUserType(int user_id);

    /**
     * 查询某个用户的姓名
     * @param user_id
     * @return
     */
    String selectUserName(int user_id);

    /**
     * 更改用户贡献点
     * @param user_id
     * @return
     */
    int updateUserDevote(int user_id);

    /**
     * 修改当前登入时间
     * @param user_login
     * @param user_id
     * @return
     */
    int updateUserLoginTime(Date user_login, int user_id);

    /**
     * 修改密码
     * @param user_pass
     * @param user_id
     * @return
     */
    int updateUserPass(String user_pass,int user_id);

    /**
     * 分页
     * @return
     */
    List<UserInfo> selectPage();

    /**
     * 查找
     * @param key
     * @return
     */
    List<UserInfo> listBykeyWord(String key);
}
