package cn.com.virtualspacex.dao;

import org.hibernate.Session;

import cn.com.virtualspacex.model.bo.VMFailureNumBO;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;

import java.util.List;

import com.virtualspacex.component.hibernate.HibernateDao;

public class HostFailureBODao extends HibernateDao {
	
    /**
     * マスタボックス故障判定
     * @param session
     * @return
     * @throws Exception
     */
    public List<VMFailureNumBO> getVMFailureNumBO(Session session) throws Exception {
    	
    	String onlineSaleInfosTableName = PropertiesManager.ONLINESALEINFOS_TABLENAME;
    	String saleSerialEndTableName = PropertiesManager.SALE_SERIAL_END_TABLENAME;
    	
    	String sql = "SELECT t.*, CAST(d.id AS CHAR) AS saleSerialEndId, CAST(d.created_at AS CHAR) AS createAt FROM " + 
    			"    	(SELECT (COUNT(*)-1) AS failureNum, " + 
    			"	    	o.location_code AS locationCode, " + 
    			"	    	o.op_vm_code AS vmCode, " + 
    			"	    	o.pos_code AS posCode, " + 
    			"	    	o.system_time AS checkAt FROM " + 
    			"    		(SELECT * FROM " + onlineSaleInfosTableName  + 
    			"    		WHERE sale_serial='1' AND is_aggregated='1' AND op_vm_code IS NOT NULL " + 
    			"    		GROUP BY  op_vm_code, system_time DESC) AS o " + 
    			"    	GROUP BY o.op_vm_code ) AS t " + 
    			"    	LEFT JOIN " + saleSerialEndTableName + " AS d " + 
    			"    	ON t.vmCode = d.vm_code " + 
    			"    	WHERE NOT EXISTS " + 
    			"    	(SELECT *, d.id AS saleSerialEnd FROM " + 
    			"    		(SELECT (COUNT(*)-1) AS failureNum, op_vm_code FROM " + onlineSaleInfosTableName  +  
    			"    		WHERE sale_serial='1' AND is_aggregated='1' AND op_vm_code IS NOT NULL " + 
    			"    		GROUP BY op_vm_code) f " + 
    			"    	INNER JOIN " + saleSerialEndTableName + " AS d " + 
    			"    	ON op_vm_code = d.vm_code" + 
    			"    	AND (f.failureNum + 0) <= (d.failure_num + 0) " +
    			"       WHERE t.vmCode = d.vm_code) ";
    	
        List<VMFailureNumBO> o = selectBySqlToObject(session, VMFailureNumBO.class, sql);
        return o;
    }
}
