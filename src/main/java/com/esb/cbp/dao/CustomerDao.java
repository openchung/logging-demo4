/**
 * 
 */
package com.esb.cbp.dao;

import java.util.List;

import com.esb.cbp.entity.Customer;

/**
 * @author User
 * @date 2018年11月29日
 * @remark 
 */
public interface CustomerDao {
        void insert(Customer cus);
        void inserBatch(List<Customer> customers);
        List<Customer> loadAllCustomer();
        Customer findCustomerById(long cust_id);
        String findNameById(long cust_id);
        int getTotalNumberCustomer();
}
