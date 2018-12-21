/**
 * 
 */
package com.esb.cbp.entity;

/**
 * @author User
 * @date 2018年11月27日
 * @remark 
 */
import java.io.Serializable;

public class CustContact implements Serializable {

    private static final long serialVersionUID = 860527920076326985L;

    private Long custContactId = null;
    private Long custId = null;
    private String efxContactPersonPhone = null;// '外匯連絡人電話' ;
    private String efxContactPersonName = null;// '外匯聯絡人姓名' ;

    public CustContact() {
        super();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getEfxContactPersonPhone() {
        return efxContactPersonPhone;
    }

    public void setEfxContactPersonPhone(String efxContactPersonPhone) {
        this.efxContactPersonPhone = efxContactPersonPhone;
    }

    public String getEfxContactPersonName() {
        return efxContactPersonName;
    }

    public void setEfxContactPersonName(String efxContactPersonName) {
        this.efxContactPersonName = efxContactPersonName;
    }

    public Long getCustContactId() {
        return custContactId;
    }

    public void setCustContactId(Long custContactId) {
        this.custContactId = custContactId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((custId == null) ? 0 : custId.hashCode());
        result = prime * result + ((efxContactPersonName == null) ? 0 : efxContactPersonName.hashCode());
        result = prime * result + ((efxContactPersonPhone == null) ? 0 : efxContactPersonPhone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustContact other = (CustContact) obj;
        if (custId == null) {
            if (other.custId != null)
                return false;
        } else if (!custId.equals(other.custId))
            return false;
        if (efxContactPersonName == null) {
            if (other.efxContactPersonName != null)
                return false;
        } else if (!efxContactPersonName.equals(other.efxContactPersonName))
            return false;
        if (efxContactPersonPhone == null) {
            if (other.efxContactPersonPhone != null)
                return false;
        } else if (!efxContactPersonPhone.equals(other.efxContactPersonPhone))
            return false;
        return true;
    }
}