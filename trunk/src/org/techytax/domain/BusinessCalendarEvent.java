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
package org.techytax.domain;
 
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedStringType;
 
@TypeDefs({
	@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
	@TypeDef(name = "encryptedBigDecimal", typeClass = EncryptedBigDecimalType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "bigDecimalEncryptor"),
			@Parameter(name = "decimalScale", value = "2") }) })
@Entity
@Table(name = "calendar_event")
@Getter
@Setter
public class BusinessCalendarEvent extends BusinessCalendarEventParent {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", updatable=false)
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
    
	public void setUser(User user) {
		this.user = new UserEntity(user);
	}

}