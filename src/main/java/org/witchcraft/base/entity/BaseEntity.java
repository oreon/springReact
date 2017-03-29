package org.witchcraft.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.td.bbwp.users.AppUser;





@MappedSuperclass
@EntityListeners({EntityListener.class})
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = -2225862673125944610L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
	//@JsonIgnore
    //private boolean inactive = false;
    
    @Version
    //@org.springframework.data.annotation.Version
    @Column(name="version")
    private Integer version;
    
    @ManyToOne(optional=true, fetch=FetchType.LAZY)
    @JoinColumn(name="created_by_user_id", nullable=true)
    private AppUser createdByUser;
    
    @LastModifiedBy
    @ManyToOne(optional=true, fetch=FetchType.LAZY)
    @JoinColumn(name="updated_by_user_id", nullable=true)
    private AppUser updatedByUser;
    
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created")
    private Date  dateCreated;
    
   // @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_updated")
    @LastModifiedDate
    private Date  dateUpdated;
    
    @Column(name = "IS_ACTIVE", /*columnDefinition = "bit default 1",*/ nullable = false)
    private Boolean active = true; 
    
   
    //@JsonIgnore
   // private Long tenant;
    
    
    public AppUser getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(AppUser createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date  getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date  dateCreated) {
		this.dateCreated = dateCreated;
	}

    
    
    public Date  getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date  dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    
    @Transient
    public String getDisplayName(){
    	return toString();
    }
    
    
    protected <T extends BaseEntity> String listAsString(List<T> listItems) {
		StringBuilder result = new StringBuilder();
		int count = 0;
		
		for (T t: listItems) {
			++count;
			result.append(t.getDisplayName() );
			if(count < listItems.size()){
				result.append(", ");
			}
			if(count > 10 ){
				result.append("...."); 
				break; 
			}
		}
		
		return result.toString();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() 
	{ 
	    return ToStringBuilder.reflectionToString(this); 
	}
    
    /*
    
    public boolean getInactive(){
    	return isInactive();
    }
	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	*/
    
	
}
