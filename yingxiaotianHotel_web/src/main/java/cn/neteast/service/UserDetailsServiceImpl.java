package cn.neteast.service;

import cn.neteast.yxtHotel.mapper.TbAdminMapper;
import cn.neteast.yxtHotel.pojo.TbAdmin;

import cn.neteast.yxtHotel.pojo.TbRoleType;
import cn.neteast.yxtHotel.service.AdminService;
import cn.neteast.yxtHotel.service.RoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证类
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private AdminService adminService;

    private RoleTypeService roleTypeService;

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void setRoleTypeService(RoleTypeService roleTypeService) {
        this.roleTypeService = roleTypeService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //得到管理员对象
        TbAdmin admin = adminService.findOneByUsername(username);
        TbRoleType role = roleTypeService.findOne(admin.getRoleTypeId());

        if (admin != null) {
            User user = null;
            try {
                user = new User(username, admin.getPassword(), true,
                        true, true, true, getAuthority(role));
                //System.out.println("user: "+user);
                return user;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
        //用户在输入密码root时就会通过(用户名随意)
        /*return new User(username, "123456", authorities);*/
    }
    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(TbRoleType role) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleType()));

        return authorities;
    }

}
