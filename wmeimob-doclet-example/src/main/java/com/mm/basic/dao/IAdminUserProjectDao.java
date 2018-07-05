package com.mm.basic.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.basic.po.AdminUserProject;

/**
 * 
 * @author zJun
 * @date 2018年7月3日 下午3:59:37
 */
public interface IAdminUserProjectDao extends JpaRepository<AdminUserProject, BigInteger> {

}
