/**
 * 
 */
package com.esb.cbp.dao;

/**
 * @author User
 * @date 2018年11月27日
 * @remark 
 */
public interface CustContactDao {

    /**
     * 根据name删除一个用户高
     * @param name
     */
    
    void deleteBycustContactId(Long custContactId);

    /**
     * 获取用户总量
     */
    Integer getAllCustContacts();

    /**
     * 删除所有用户
     */
    void deleteAllCustContacts();

    /**
         *  新增
     * @param custContactId
     * @param custId
     * @param efxContactPersonPhone
     * @param efxContactPersonName
     * @remark
     */
    void create(Long custId, String efxContactPersonPhone, String efxContactPersonName);
}
