package cc.iyayu.basis.service;

import cc.iyayu.api.model.Menu;
import cc.iyayu.basis.exception.ISystemUsersException;
import cc.iyayu.basis.model.SystemUserAccountDO;
import cc.iyayu.basis.vo.SystemUserAccountVO;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 用户账号 操作服务接口
 */
public interface SystemUserAccountService {

    /**
     * 用户登录
     *
     * @param accountCode 用户码
     * @param pwd         用户密码
     * @return 登录成功返回 UserAccountDO对象, 失败返回null或属性为null
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    SystemUserAccountDO login(String accountCode, String pwd) throws ISystemUsersException;

    /**
     * 获取用户菜单
     *
     * @param userCode 用户 userCode
     * @return 返回菜单集合, 失败返回null或集合大小为零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    List<Menu> listMenuBySystemUserAccountId(String userCode) throws ISystemUsersException;

    /**
     * 通过账户ID获取权限
     *
     * @param systemUserAccountId 账户ID
     * @return 成功权限集合, 失败返回null或集合大小为零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    List<String> listPermissionBySystemUserAccountId(String systemUserAccountId) throws ISystemUsersException;

    /**
     * 获取用户账号列表
     *
     * @param systemUserAccountVO SystemUserAccountVO 对象
     * @return 成功返回集合, 失败返回null或集合大小为零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    List<SystemUserAccountVO> listSystemUserAccount(SystemUserAccountVO systemUserAccountVO) throws ISystemUsersException;

    /**
     * 返回查询数据量
     *
     * @param userAccountVO UserAccountVO对象
     * @return 返回 listUserAccount 方法查询的数据量
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    Integer getCount(SystemUserAccountVO userAccountVO) throws ISystemUsersException;

    /**
     * 添加用户账号
     *
     * @param userAccountVO UserAccountVO对象
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    Integer insertSystemUserAccount(SystemUserAccountVO userAccountVO) throws ISystemUsersException;

    /**
     * 禁用或启用
     *
     * @param systemUserAccountVO 账号id
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    Integer disabledOrEnabled(SystemUserAccountVO systemUserAccountVO) throws ISystemUsersException;

    /**
     * 根据ID修改用户账号信息 (拥有权限的)
     *
     * @param userAccountVO UserAccountVO对象
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    Integer updateUserAccountById(SystemUserAccountVO userAccountVO) throws ISystemUsersException;

    /**
     * 修改账号角色
     *
     * @param userAccountVO UserAccountVO对象
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    Integer updateUserAccountRole(SystemUserAccountVO userAccountVO) throws ISystemUsersException;

    /**
     * 初始化密码为 iyayu
     *
     * @param userAccountId UserAccountVO对象
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    Integer initPwd(String userAccountId) throws ISystemUsersException;

    /**
     * 修改个人密码
     *
     * @param passWord    原密码
     * @param newPassWord 新密码
     * @param id          要修改的用户账号id
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException
     */
    Integer updatePassWord(String passWord, String newPassWord, String id) throws ISystemUsersException;

}
