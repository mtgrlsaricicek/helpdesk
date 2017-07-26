package io.mts.helpdesk.hibernate.entity.baseentity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */

@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1914842698571907341L;


    @Id
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 32)
    private String oid;

    @Version
    private long lastUpdated;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy'T'HH:mm")
    private Date createdDate;

    public BaseEntity(){

    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String toString(){
        return "BaseEntity{" +
                "oid:"+oid+",\n" +
                "createdDate:"+createdDate+"\n" +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) obj;

        if(getLastUpdated() != that.getLastUpdated()) return false;

        return getOid() != null ? getOid().equals(that.getOid()) : that.getOid()==null;
    }

    @Override
    public int hashCode() {
        int result = getOid() != null ? getOid().hashCode() : 0;
        result = 31 * result + (int) (getLastUpdated() ^ (getLastUpdated() >>> 32));
        return result;
    }
}
