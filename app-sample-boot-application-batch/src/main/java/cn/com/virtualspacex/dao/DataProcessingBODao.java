package cn.com.virtualspacex.dao;

import org.hibernate.Session;

import cn.com.virtualspacex.model.bo.SaleinfosFromLastTimeBO;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;

import java.util.List;

import com.virtualspacex.component.hibernate.HibernateDao;

public class DataProcessingBODao extends HibernateDao {

    /**
     * 获取缺号数据
     */
    public List<SaleinfosFromLastTimeBO> getNewSaleinfosFromLastTime(Session session) throws Exception {
    	
    	String onlineSaleInfosTableName = PropertiesManager.ONLINESALEINFOS_TABLENAME;
    	String saleSerialEndTableName = PropertiesManager.SALE_SERIAL_END_TABLENAME;
    	
    	executeBySQL(session, "SET SESSION group_concat_max_len=250000;");
    	
    	String sql = " SELECT g.vmCode, " + 
    			" o.location_code AS locationCode, " + 
    			" o.pos_code AS posCode," + 
    			" CAST(g.intMaxSaleSerial AS CHAR) AS maxSaleSerial, " + 
    			" g.saleSerialEndId, " + 
    			" g.lastSaleSerialEnd, " + 
    			" g.saleSerialList FROM " + 
    			onlineSaleInfosTableName + " AS o " + 
    			" INNER JOIN " + 
    			"	 (SELECT DISTINCT GROUP_CONCAT(sale_serial ORDER BY sale_serial) AS saleSerialList, " + 
    			"					MAX(sale_serial + 0) AS intMaxSaleSerial," + 
    			"					s.sale_serial_end AS lastSaleSerialEnd," + 
    			"					s.id AS saleSerialEndId," + 
    			"					o.op_vm_code AS vmCode" + 
    			"	 FROM " + onlineSaleInfosTableName + " AS o " + 
    			"	 INNER JOIN " + saleSerialEndTableName + " AS s " + 
    			"	 ON s.vm_code = o.op_vm_code  " + 
    			"	 AND o.system_time > s.checked_at " + 
    			"	 AND (o.sale_serial + 0) > (s.sale_serial_end + 0) " + 
    			"	 WHERE o.is_aggregated = '1' " + 
    			"	 AND o.op_vm_code IS NOT NULL " + 
    			"	 GROUP BY o.op_vm_code) AS g " + 
    			" ON  o.sale_serial = g.intMaxSaleSerial" + 
    			" AND o.op_vm_code = g.vmCode" + 
    			" INNER JOIN " + saleSerialEndTableName + " AS s " + 
    			" ON s.id = g.saleSerialEndId " + 
    			" AND o.system_time > s.checked_at;";

        List<SaleinfosFromLastTimeBO> o = selectBySqlToObject(session, SaleinfosFromLastTimeBO.class, sql);
        return o;
    }
}
