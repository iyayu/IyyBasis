package cc.iyayu.basis.mapper;

import cc.iyayu.basis.model.SystemUserAccountDO;
import cc.iyayu.basis.vo.SystemUserAccountVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 用户账号操作接口
 * 主要用于操作 'user_account'表 查看和添加对应的信息等操作
 */
public interface SystemUserAccountMapper {

    /**
     * 用户登录
     *
     * @param accountCode 用户码
     * @param pwd         用户密码
     * @return 登录成功返回 UserAccountDO对象, 失败返回 null
     * @throws Exception
     */
    SystemUserAccountDO login(@Param(value = "accountCode") String accountCode,
                              @Param(value = "pwd") String pwd) throws Exception;

    /**
     * 通过账户ID获取权限
     *
     * @param systemUserAccountId 账户ID
     * @return 成功权限集合, 失败返回null或者集合大小为零
     * @throws Exception
     */
    List<String> listPermissionBySystemUserAccountId(@Param(value = "systemUserAccountId") String systemUserAccountId) throws Exception;

    /**
     * 获取用户账号列表
     *
     * @param systemUserAccountVO 用户账号视图对象
     * @return 成功返回账号集合, 失败返回null或者集合大小为零
     * @throws Exception
     */
    List<SystemUserAccountVO> listSystemUserAccount(SystemUserAccountVO systemUserAccountVO) throws Exception;

    /**
     * 获取总数据量
     *
     * @param systemUserAccountVO 用户账号视图对象
     * @return 返回获取的总数据量
     * @throws Exception
     */
    Integer getCount(SystemUserAccountVO systemUserAccountVO) throws Exception;

    /**
     * 根据账号id 获取系统用户账号信息
     * @param systemUserAccountId 账户ID
     * @param systemUserAccountCode 账户Code
     * @return 成功对象, 失败返回null
     * @throws Exception
     */
    SystemUserAccountVO getSystemUserAccountByIdOrAccountCode(@Param(value = "systemUserAccountId") String systemUserAccountId,
                                                              @Param(value = "systemUserAccountCode") String systemUserAccountCode) throws Exception;
    /**
     * 添加系统用户账号
     *
     * @param systemUserAccountVO 系统用户账号视图对象
     * @return 成功返回1 失败返回零或null
     * @throws Exception
     */
    Integer insertSystemUserAccount(SystemUserAccountVO systemUserAccountVO) throws Exception;

    /**
     * 更新密码
     *
     * @param systemUserAccountVO 用户账号视图对象
     * @return 成功返回1 失败返回零或null
     * @throws Exception
     */
    Integer updatePassWord(SystemUserAccountVO systemUserAccountVO) throws Exception;

    /**
     * 禁用或启用
     *
     * @param systemUserAccountVO 用户账号视图对象
     * @return 成功返回1 失败返回零或null
     * @throws Exception
     */
    Integer disabledOrEnabled(SystemUserAccountVO systemUserAccountVO) throws Exception;

    /**
     * 根据ID修改用户账号信息 (拥有权限的)
     *
     * @param systemUserAccountVO 用户账号视图对象
     * @return 成功返回1 失败返回零或null
     * @throws Exception
     */
    Integer updateUserAccountById(SystemUserAccountVO systemUserAccountVO) throws Exception;

    /**
     * 修改用户账号角色
     *
     * @param systemUserAccountVO 用户账号视图对象
     * @return 成功返回1 失败返回零或null
     * @throws Exception
     */
    Integer updateUserAccountRole(SystemUserAccountVO systemUserAccountVO) throws Exception;

}
