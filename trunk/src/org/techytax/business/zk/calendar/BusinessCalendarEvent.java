/**
 * Copyright 2014 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.business.zk.calendar;
 
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedStringType;
import org.techytax.business.jpa.entities.Project;
import org.techytax.domain.User;
import org.techytax.domain.UserEntity;
import org.techytax.domain.VatType;
 
@TypeDefs({
	@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
	@TypeDef(name = "encryptedBigDecimal", typeClass = EncryptedBigDecimalType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "bigDecimalEncryptor"),
			@Parameter(name = "decimalScale", value = "2") }) })
@Entity(name = "org.techytax.domain.BusinessCalendarEvent")
@Table(name = "calendar_event")
public class BusinessCalendarEvent extends BusinessCalendarEventParent {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "project_id")	
    private Project project;
    
    private boolean isBillable;
    
	@Type(type = "encryptedBigDecimal")
    private BigDecimal travelingByCarCostDeclaration;
    
	@Type(type = "encryptedBigDecimal")
    private BigDecimal otherCostDeclaration;
    
    private VatType vatTypeForOtherCostDeclaration = VatType.NONE;
    
    private float unitsOfWork;
    
    public void setBeginDate(Timestamp timestamp) {
    	super.setBeginDate(timestamp);
    }
    
    public void setEndDate(Timestamp timestamp) {
    	super.setEndDate(timestamp);
    }
    
    public Timestamp getBeginDate() {
    	if (super.getBeginDate() != null) {
    		return new Timestamp(super.getBeginDate().getTime());
    	} else {
    		return null;
    	}
    }
    
    public Timestamp getEndDate() {
    	if (super.getEndDate() != null) {
    		return new Timestamp(super.getEndDate().getTime());
    	} else {
    		return null;
    	}
    }
    
    public BusinessCalendarEvent(Date beginDate, Date endDate, String headerColor, String contentColor, String title) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
    }
    
    public BusinessCalendarEvent(Date beginDate, Date endDate, String headerColor, String contentColor, String title, Project project) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
        this.project = project;
    }    
 
    public BusinessCalendarEvent(Date beginDate, Date endDate, String headerColor, String contentColor, String content,
            String title) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setContent(content);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
    }
 
    public BusinessCalendarEvent(Date beginDate, Date endDate, String headerColor, String contentColor, String content,
            String title, boolean locked) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setContent(content);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setLocked(locked);
    }
     
    public BusinessCalendarEvent() {
        setHeaderColor("#FFFFFF");
        setContentColor("#000000");
    }

	public void roundValues() {
		if (travelingByCarCostDeclaration != null) {
			travelingByCarCostDeclaration = travelingByCarCostDeclaration.setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		if (otherCostDeclaration != null) {		
			otherCostDeclaration = otherCostDeclaration.setScale(2,BigDecimal.ROUND_HALF_UP);
		}		
	}
    
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project; 
	}

	public boolean isBillable() {
		return isBillable;
	}

	public void setBillable(boolean isBillable) {
		this.isBillable = isBillable;
	}

	public BigDecimal getTravelingByCarCostDeclaration() {
		return travelingByCarCostDeclaration;
	}

	public void setTravelingByCarCostDeclaration(BigDecimal travelingByCarCostDeclaration) {
		this.travelingByCarCostDeclaration = travelingByCarCostDeclaration;
	}

	public BigDecimal getOtherCostDeclaration() {
		return otherCostDeclaration;
	}

	public void setOtherCostDeclaration(BigDecimal otherCostDeclaration) {
		this.otherCostDeclaration = otherCostDeclaration;
	}

	public VatType getVatTypeForOtherCostDeclaration() {
		return vatTypeForOtherCostDeclaration;
	}

	public void setVatTypeForOtherCostDeclaration(VatType vatTypeForOtherCostDeclaration) {
		this.vatTypeForOtherCostDeclaration = vatTypeForOtherCostDeclaration;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = new UserEntity(user);
	}

	public float getUnitsOfWork() {
		return unitsOfWork;
	}

	public void setUnitsOfWork(float unitsOfWork) {
		this.unitsOfWork = unitsOfWork;
	}
	
}