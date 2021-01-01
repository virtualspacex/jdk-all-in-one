/*
 * @Author: keiki
 * @Date: 2017-04-11 13:44:18
 * @LastEditTime: 2020-12-30 17:46:43
 * @LastEditors: keiki
 * @Description: 
 */
/**
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.virtualspacex.sample.phonebilling;

import java.math.BigDecimal;
import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import com.virtualspacex.sample.phonebilling.items.PhoneBill;

/* Processor artifact for bills.
 * Compute amount and total for each bill
 */
@Dependent
@Named("BillProcessor")
public class BillProcessor implements ItemProcessor {
    
    @Inject
    JobContext jobCtx;

    @Override
    public Object processItem(Object billObject) throws Exception {

        String s_taxRate = jobCtx.getProperties().get("tax_rate").toString();
        double taxRate = Double.parseDouble(s_taxRate);
        PhoneBill bill = (PhoneBill) billObject;
        bill.calculate(new BigDecimal(taxRate));
        return bill;
    }
    
}
