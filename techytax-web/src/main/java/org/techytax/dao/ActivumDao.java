/**
 * Copyright 2015 Hans Beemsterboer
 * <p/>
 * This file is part of the TechyTax program.
 * <p/>
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.dao;

import org.springframework.stereotype.Component;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Cost;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.util.DateHelper;

import java.util.List;

import static org.techytax.jpa.dao.QueryParameter.with;

@Component
public class ActivumDao extends GenericDao<Activum> {

    public List<Activum> getNewActiva(BalanceType balanceType) {
        return findByNamedQuery(Activum.NEW_ACTIVA, with("fiscalStartDate", DateHelper.getPeriodPreviousYear().getBeginDate()).and("fiscalEndDate", DateHelper.getPeriodPreviousYear().getEndDate()).and("balanceType", balanceType)
                .parameters());
    }

    public List<Activum> getAllActiva() {
        return findByNamedQuery(Activum.ALL_ACTIVA);
    }

    public Activum getActivumForCost(Cost cost) {
        return findEntityByNamedQuery(Activum.GET_ACTIVUM_FOR_COST, with("cost", cost).parameters());
    }

    public List<Activum> getActiveActiva() {
        return findByNamedQuery(Activum.ACTIVE_ACTIVA);
    }

    public List<Activum> getActiveActiva(BalanceType balanceType) {
        return findByNamedQuery(Activum.ACTIVE_ACTIVA_FOR_TYPE, with("startDate", DateHelper.getLastDayOfFiscalYear()).and("balanceType", balanceType).parameters());
    }
}
