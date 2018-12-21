/**
 * 
 */
package com.esb.cbp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

/**
 * @author User
 * @date 2018年11月27日
 * @remark 
 */
@Service
public class CustContactDaoImpl implements CustContactDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* (non-Javadoc)
     * @see com.esb.cbp.dao.CustContactDao#create(java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void create(Long custId, String efxContactPersonPhone, String efxContactPersonName) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("INSERT_TB_CUST_CONTACT");
        SqlParameterSource in = new MapSqlParameterSource().addValue("cust_id", custId).addValue("efx_contact_person_name", efxContactPersonPhone).addValue("efx_contact_person_phone", efxContactPersonName);
        simpleJdbcCall.execute(in);
//      jdbcTemplate.update("insert into TB_CUST_CONTACT(CUST_CONTACT_ID, CUST_ID, EFX_CONTACT_PERSON_NAME, EFX_CONTACT_PERSON_PHONE) values(TEST_ID_SEQ.nextval, ?, ?, ?)", custId, efxContactPersonPhone, efxContactPersonName);
    }

    /* (non-Javadoc)
     * @see com.esb.cbp.dao.CustContactDao#deleteBycustContactId(java.lang.Long)
     */
    @Override
    public void deleteBycustContactId(Long custContactId) {
        jdbcTemplate.update("delete from TB_CUST_CONTACT where CUST_CONTACT_ID = ?", custContactId);
    }

    /* (non-Javadoc)
     * @see com.esb.cbp.dao.CustContactDao#getAllCustContacts()
     */
    @Override
    public Integer getAllCustContacts() {
        return jdbcTemplate.queryForObject("select count(1) from TB_CUST_CONTACT", Integer.class);
    }

    /* (non-Javadoc)
     * @see com.esb.cbp.dao.CustContactDao#deleteAllCustContacts()
     */
    @Override
    public void deleteAllCustContacts() {
        jdbcTemplate.update("delete from TB_CUST_CONTACT");
    }
}
