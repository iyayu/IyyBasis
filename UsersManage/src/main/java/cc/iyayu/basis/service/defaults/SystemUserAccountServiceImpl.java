package cc.iyayu.basis.service.defaults;


import cc.iyayu.api.model.Menu;
import cc.iyayu.api.tools.Menus;
import cc.iyayu.api.tools.PasswordHash;
import cc.iyayu.basis.exception.ISystemUsersException;
import cc.iyayu.basis.mapper.SystemUserAccountMapper;
import cc.iyayu.basis.mapper.SystemUserMenuMapper;
import cc.iyayu.basis.mapper.SystemUserRoleMapper;
import cc.iyayu.basis.model.SystemUserAccountDO;
import cc.iyayu.basis.service.SystemUserAccountService;
import cc.iyayu.basis.vo.SystemUserAccountVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 这是 用户账号 操作服务接口默认实现
 */
public class SystemUserAccountServiceImpl implements SystemUserAccountService {

    /**
     * 添加账号动作
     */
    private static final String INSERT = "insert";
    private static final String UPDATE = "update";

    private static final String NEGATIVE_ONE = "-1";
    private static final int FIVE = 5;
    private static final int EIGHT = 8;
    private static final int EIGHTEEN = 18;
    private static final int EIGHTY = 80;


    /**
     * 初始化密码
     */
    private static final String INIT_PASSWORD = "iyayu";

    @Autowired(required = false)
    private SystemUserAccountMapper systemUserAccountMapper;

    @Autowired(required = false)
    private SystemUserMenuMapper systemUserMenuMapper;

    @Autowired(required = false)
    private SystemUserRoleMapper systemUserRoleMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 用户登录
     *
     * @param systemUserAccountCode 用户码
     * @param pwd         用户密码
     * @return 登录成功返回 UserAccountDO对象, 失败返回null或属性为null
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public SystemUserAccountDO login(String systemUserAccountCode, String pwd) throws ISystemUsersException {
        try {
            SystemUserAccountDO login = userValidation(systemUserAccountCode, pwd);
            login.setAvailable(null);
            login.setPassWord(null);
            login.setSalt(null);
            return login;
        } catch (Exception e) {
            throw new ISystemUsersException(e);
        }
    }

    /**
     * 获取用户菜单
     *
     * @param systemUserAccountId systemUserAccountId
     * @return 返回集合, 失败返回null或集合大小为零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public List<Menu> listMenuBySystemUserAccountId(String systemUserAccountId) throws ISystemUsersException {
        List<Menu> menus;
        try {
            List<Menu> menuByUserCode = systemUserMenuMapper.listMenuBySystemUserMenuId(
                    systemUserMenuMapper.listMenuIdBySystemUserAccountId(systemUserAccountId)
            );

            try {
                Menus menus1 = new Menus();
                menus = menus1.handleMenu(menuByUserCode);
                if (menus == null) {
                    throw new ISystemUsersException("菜单解析出错");
                }
            } catch (Exception e) {
                throw new ISystemUsersException("菜单解析出错");
            }
        } catch (Exception e) {
            throw new ISystemUsersException("没有找到该用户的菜单");
        }
        return menus;
    }

    /**
     * 通过账户ID获取权限
     *
     * @param systemUserAccountId 账户ID
     * @return 成功权限集合, 失败返回null或集合大小为零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public List<String> listPermissionBySystemUserAccountId(String systemUserAccountId) throws ISystemUsersException {
        try {
            List<String> listPermissionByUserCode = systemUserAccountMapper.listPermissionBySystemUserAccountId(systemUserAccountId);
            if (listPermissionByUserCode == null || listPermissionByUserCode.size() == 0) {
                throw new ISystemUsersException("找不到指定用户的权限");
            }

            return listPermissionByUserCode;
        } catch (Exception e) {
            throw new ISystemUsersException(e);
        }
    }

    /**
     * 获取用户账号列表
     *
     * @param systemUserAccountVO SystemUserAccountVO 对象
     * @return 成功返回集合, 失败返回null或集合大小为零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public List<SystemUserAccountVO> listSystemUserAccount(SystemUserAccountVO systemUserAccountVO) throws ISystemUsersException {
        try {
            Integer limit = systemUserAccountVO.getLimit();
            if (limit == null || limit <= 0) {
                systemUserAccountVO.setLimit(500);
            }

            Integer page = systemUserAccountVO.getPage();
            if (page == null || page <= 1) {
                systemUserAccountVO.setPage(0);
            } else {
                systemUserAccountVO.setPage(systemUserAccountVO.getLimit() * (systemUserAccountVO.getPage() - 1) + 1);
            }


            if (systemUserAccountVO.getRegTime() != null && !"".equals(systemUserAccountVO.getRegTime().trim())) {
                try {
                    simpleDateFormat.parse(systemUserAccountVO.getRegTime());
                } catch (Exception e) {
                    systemUserAccountVO.setRegTime(null);
                }
            } else {
                systemUserAccountVO.setRegTime(null);
            }

            if (systemUserAccountVO.getSystemUserAccountCode() == null) {
                systemUserAccountVO.setSystemUserAccountCode("");
            }

            if (systemUserAccountVO.getSystemUserAccountName() == null) {
                systemUserAccountVO.setSystemUserAccountName("");
            }

            if (systemUserAccountVO.getSystemUserRoleIdOrName() == null || NEGATIVE_ONE.equals(systemUserAccountVO.getSystemUserRoleIdOrName().trim())) {
                systemUserAccountVO.setSystemUserRoleIdOrName(null);
            }

            if ("".equals(systemUserAccountVO.getPhone())){
                systemUserAccountVO.setPhone(null);
            }

            return systemUserAccountMapper.listSystemUserAccount(systemUserAccountVO);
        } catch (Exception e) {
            throw new ISystemUsersException(e);
        }
    }

    /**
     * 返回查询数据量
     *
     * @param userAccountVO UserAccountVO对象
     * @return 返回 listUserAccount 方法查询的数据量
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer getCount(SystemUserAccountVO userAccountVO) throws ISystemUsersException {
        try {
            return systemUserAccountMapper.getCount(userAccountVO);
        } catch (Exception e) {
            throw new ISystemUsersException(e);
        }
    }

    /**
     * 添加系统用户账号
     *
     * @param systemUserAccountVO SystemUserAccountVO 对象
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer insertSystemUserAccount(SystemUserAccountVO systemUserAccountVO) throws ISystemUsersException {
        try {

            unifiedProcessing(systemUserAccountVO, INSERT);

            String[] passWords = PasswordHash.createHash("iyayu").split(":");
            systemUserAccountVO.setSalt(passWords[1]);
            systemUserAccountVO.setPassWord(passWords[2]);

            Integer userAccount = systemUserAccountMapper.insertSystemUserAccount(systemUserAccountVO);
            if (userAccount == null || userAccount == 0) {
                throw new ISystemUsersException("系统用户添加失败");
            }

            return userAccount;
        } catch (Exception e) {
            throw new ISystemUsersException(String.format("添加系统用户账号, 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 根据ID修改用户账号信息 (拥有权限的)
     *
     * @param userAccountVO UserAccountVO对象
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer updateUserAccountById(SystemUserAccountVO userAccountVO) throws ISystemUsersException {
        try {
            unifiedProcessing(userAccountVO, UPDATE);
            return systemUserAccountMapper.updateUserAccountById(userAccountVO);
        } catch (Exception e) {
            throw new ISystemUsersException(String.format("根据ID修改用户账号信息 (拥有权限的), 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 修改账号角色
     *
     * @param userAccountVO UserAccountVO对象
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer updateUserAccountRole(SystemUserAccountVO userAccountVO) throws ISystemUsersException {
        try {
            if (userAccountVO.getId() == null || "".equals(userAccountVO.getId().trim())) {
                throw new ISystemUsersException("ID都没有就请求了");
            }

            if (userAccountVO.getId().length() != 32) {
                throw new ISystemUsersException("ID长度不为32");
            }

            if (userAccountVO.getSystemUserRoleIdOrName() == null || "".equals(userAccountVO.getSystemUserRoleIdOrName())) {
                throw new ISystemUsersException("角色没有赋值");
            }
            Integer result = systemUserRoleMapper.getCountById(userAccountVO.getSystemUserRoleIdOrName());
            if (result == null || result <= 0) {
                throw new ISystemUsersException("没有找到角色");
            }

            return systemUserAccountMapper.updateUserAccountRole(userAccountVO);
        } catch (Exception e) {
            throw new ISystemUsersException(String.format("根据ID修改用户账号信息 (拥有权限的), 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 初始化密码为 iyayu
     *
     * @param userAccountId UserAccountVO对象
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer initPwd(String userAccountId) throws ISystemUsersException {
        try {

            if (userAccountId == null || "".equals(userAccountId.trim())) {
                throw new ISystemUsersException("ID都没有就请求了");
            }

            String[] passWords = PasswordHash.createHash(INIT_PASSWORD).split(":");
            SystemUserAccountVO userAccountVO = new SystemUserAccountVO();
            userAccountVO.setId(userAccountId);
            userAccountVO.setSalt(passWords[1]);
            userAccountVO.setPassWord(passWords[2]);

            return systemUserAccountMapper.updatePassWord(userAccountVO);
        } catch (Exception e) {
            throw new ISystemUsersException(String.format("初始化密码(iyayu), 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 禁用或启用
     *
     * @param systemUserAccountVO 账号id
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer disabledOrEnabled(SystemUserAccountVO systemUserAccountVO) throws ISystemUsersException {
        try {

            if (systemUserAccountVO.getId() == null || "".equals(systemUserAccountVO.getId().trim())) {
                throw new ISystemUsersException("ID都没有就请求了");
            }

            if (systemUserAccountVO.getAvailable() == null || "".equals(systemUserAccountVO.getAvailable().trim())) {
                throw new ISystemUsersException("没有设置Available属性");
            } else {
                Integer i = Integer.parseInt(systemUserAccountVO.getAvailable());
                if (i < 0 || i > 1) {
                    throw new ISystemUsersException("Available属性值超出范围");
                }
            }

            return systemUserAccountMapper.disabledOrEnabled(systemUserAccountVO);
        } catch (Exception e) {
            throw new ISystemUsersException(String.format("禁用或启用, 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 修改个人密码
     *
     * @param passWord    原密码
     * @param newPassWord 新密码
     * @param id          要修改的用户账号id
     * @return 成功返回1 失败返回 null 或 零
     * @throws ISystemUsersException 如果在执行过程中出现异常, 会抛出 ISystemUsersException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer updatePassWord(String passWord, String newPassWord, String id) throws ISystemUsersException {
        try {

            if (passWord == null || newPassWord == null || id == null) {
                throw new ISystemUsersException("所需要的条件一个都没有");
            }

            if (newPassWord.trim().length() < FIVE || newPassWord.trim().length() > EIGHT) {
                throw new ISystemUsersException("密码长度为5到8位");
            }

            SystemUserAccountDO systemUserAccountDO =
                    systemUserAccountMapper.getSystemUserAccountByIdOrAccountCode(id, null);
            if (systemUserAccountDO == null) {
                throw new ISystemUsersException("没有找到该用户");
            }

            userValidation(systemUserAccountDO.getSystemUserAccountCode(), passWord.trim());

            String[] passWords = PasswordHash.createHash(newPassWord.trim()).split(":");

            SystemUserAccountVO systemUserAccountVO = new SystemUserAccountVO();
            systemUserAccountVO.setId(id);
            systemUserAccountVO.setSalt(passWords[1]);
            systemUserAccountVO.setPassWord(passWords[2]);

            return systemUserAccountMapper.updatePassWord(systemUserAccountVO);
        } catch (Exception e) {
            throw new ISystemUsersException(String.format("修改个人密码异常. %s", e.getMessage()));
        }
    }

    /**
     * 用户验证
     *
     * @param pwd         密码
     * @param systemUserAccountCode 账号
     * @throws ISystemUsersException
     */
    private SystemUserAccountDO userValidation(String systemUserAccountCode, String pwd) throws ISystemUsersException {
        try {
            SystemUserAccountDO systemUserAccountDO =
                    systemUserAccountMapper.getSystemUserAccountByIdOrAccountCode(null, systemUserAccountCode);
            if (systemUserAccountDO == null) {
                throw new ISystemUsersException("没有找到该用户");
            }

            //找到用户后获取 盐计算密码
            byte[] saltHex = PasswordHash.fromHex(systemUserAccountDO.getSalt());
            String pwdHex = null;
            try {
                pwdHex = PasswordHash.toHex(PasswordHash.pbkdf2(pwd.toCharArray(), saltHex, PasswordHash.PBKDF2_ITERATIONS, PasswordHash.HASH_BYTE_SIZE));
            } catch (NoSuchAlgorithmException e) {
                throw new ISystemUsersException("没有找到这样的算法");
            } catch (InvalidKeySpecException e) {
                throw new ISystemUsersException("密钥规范异常");
            }

            SystemUserAccountDO login = systemUserAccountMapper.login(systemUserAccountCode, pwdHex);
            if (login == null) {
                throw new ISystemUsersException("用户名或密码错误!");
            }

            return login;
        } catch (Exception e) {
            throw new ISystemUsersException(e);
        }
    }

    /**
     * 统一数据验证处理 (添加 修改)
     *
     * @param userAccountVO 要验证的对象
     * @param action        当前动作是添加还是修改等
     * @throws ISystemUsersException
     */
    private void unifiedProcessing(SystemUserAccountVO userAccountVO, String action) throws ISystemUsersException {
        try {
            if (UPDATE.equals(action)) {
                if (userAccountVO.getId() == null || "".equals(userAccountVO.getId().trim())) {
                    throw new ISystemUsersException("ID都没有就请求了");
                }

                if (userAccountVO.getId().length() != 32) {
                    throw new ISystemUsersException("ID长度不为32");
                }
            }

            if (userAccountVO.getSystemUserAccountCode() == null || "".equals(userAccountVO.getSystemUserAccountCode().trim())) {
                throw new ISystemUsersException("用户代码不能为null");
            }

            if (userAccountVO.getSystemUserAccountName() == null || "".equals(userAccountVO.getSystemUserAccountName().trim())) {
                throw new ISystemUsersException("用户名称不能为null");
            }

            if (userAccountVO.getAge() == null || "".equals(userAccountVO.getAge().trim())) {
                throw new ISystemUsersException("年龄不能为null");
            } else {
                int age = Integer.parseInt(userAccountVO.getAge().trim());
                if (age > EIGHTY || age < EIGHTEEN) {
                    throw new ISystemUsersException("年龄不在18~80之间");
                }
            }

            if (userAccountVO.getPhone() != null) {
                if (userAccountVO.getPhone().length() != 11) {
                    throw new ISystemUsersException("手机号长度不为11");
                }
            } else {
                throw new ISystemUsersException("没有手机号");
            }

        } catch (Exception e) {
            throw new ISystemUsersException(e.getMessage());
        }
    }

}
