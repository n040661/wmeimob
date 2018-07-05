package com.mm.basic.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.basic.po.Project;

/**
 * 
 * @author zJun
 * @date 2018年7月3日 下午4:00:28
 */
public interface IProjectDao extends JpaRepository<Project, BigInteger> {

}
