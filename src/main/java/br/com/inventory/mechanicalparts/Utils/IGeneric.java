//package br.com.inventory.mechanicalparts.Util;
//
//package com.smartbr.clinica.repository.dao;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.repository.NoRepositoryBean;
//
//@NoRepositoryBean
//public interface IGeneric<T, ID> {
//
//    JpaRepository<T, ID> getRepository();
//
//    JpaSpecificationExecutor<T> getSpecRepository();
//
//    <S extends JpaSpecificationExecutor> S getSpecRepository(Class<S> specClass);
//
//}