package cn.neteast.service;

import cn.neteast.yxtHotel.pojo.TbAdmin;

import cn.neteast.yxtHotel.service.AdminService;
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
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //得到管理员对象
        TbAdmin admin = adminService.findOneByUsername(username);
        //System.out.println(seller);
        if (admin != null) {
            User user = null;
            try {
                //若seller不为空且seller的状态为1时, 才能成功创建user
                user = new User(username, admin.getPassword(), admin.getRoleTypeId().equals("0") ? true : false,
                        true, true, true, authorities);
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
}
