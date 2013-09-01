/**
 * Copyright 2013 Hans Beemsterboer
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

import org.techytax.domain.VatType;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
 
public class BusinessCalendarEvent extends SimpleCalendarEvent {
	
    private static final long serialVersionUID = 1L;
    
	private long id;
	
	private long userId;
    
    private long projectId;
    
    private boolean isBillable;
    
    private BigDecimal travelingByCarCostDeclaration;
    
    private BigDecimal otherCostDeclaration;
    
    private VatType vatTypeForOtherCostDeclaration;
    
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
    
    public BusinessCalendarEvent(Date beginDate, Date endDate, String headerColor, String contentColor, String title, long projectId) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
        this.projectId = projectId;
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
    
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId; 
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

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public float getUnitsOfWork() {
		return unitsOfWork;
	}

	public void setUnitsOfWork(float unitsOfWork) {
		this.unitsOfWork = unitsOfWork;
	}
	
}