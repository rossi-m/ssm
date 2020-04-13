package com.itssm.ssm.controller;

import com.itssm.ssm.domain.Role;
import com.itssm.ssm.domain.UserInfo;
import com.itssm.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    /**
     * 添加权限
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

    /**
     * 查询用户以及用户可以添加角色
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView  findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        ModelAndView mv=new ModelAndView();
         //根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据用户查询可以添加的角色
        List<Role> otherRoles=userService.findOtherRoles(userId);
        System.out.println(otherRoles);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 用户添加
     * @return
     */
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='ssm'")//ssm用户可以访问这个方法
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        //重定向到findAll()方法
        return "redirect:findAll.do";
    }
    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll.do")
    //只有有角色Role_ADMIN才可以访问方法
    @PreAuthorize("hasRole('ROLE_ADMIN')")//在方法调用之前，基于表达式的计算结果来限制对方法的访问
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<UserInfo> userList=userService.findAll();
        System.out.println(userList);
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 查询指定id的用户
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        System.out.println(userInfo);
        List<Role> roles = userInfo.getRoles();
        for (Role role:roles){
            System.out.println(role.getPermissions().toString());
        }

        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");
        return mv;
    }
}
