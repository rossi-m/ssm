package com.itssm.ssm.controller;

import com.itssm.ssm.domain.Permission;
import com.itssm.ssm.domain.Role;
import com.itssm.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 给角色添加权限
     * @return
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    /**
     * 根据roleId查询role，并查询出可以添加的权限
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name ="id",required = true) String roleId) throws Exception {
        Role role=roleService.findById(roleId);
        List<Permission> otherPermission=roleService.findOtherPermissions(roleId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermission);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";

    }

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception{
        List<Role> roles = roleService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }
}
