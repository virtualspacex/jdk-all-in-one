package cn.com.virtualspacex.dao;

import org.hibernate.Session;

import cn.com.virtualspacex.model.SaleSerialMissed;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;

import java.util.List;

import com.virtualspacex.component.hibernate.HibernateDao;

public class SaleSerialMissedDao extends HibernateDao {

    /**
     * 批量插入
     * @param session
     * @param saleSerialMisseds
     * @return
     * @throws Exception
     */
    public int insertByList( Session session,  List<SaleSerialMissed> saleSerialMisseds) throws Exception {
        return insertObjects(session,saleSerialMisseds).size();
    }

    /**
     *明细表中存在，更新缺号表状态，
     * @param session
     * @return 影响条数
     * @throws Exception
     */
    public int updateIfRecepted(Session session,SaleSerialMissed saleSerialMissed) throws Exception {
    	
    	String onlineSaleInfosTableName = PropertiesManager.ONLINESALEINFOS_TABLENAME;
    	String saleSerialEndTableName = PropertiesManager.SALE_SERIAL_END_TABLENAME;
    	String saleSerialMissedTableName = PropertiesManager.SALE_SERIAL_MISSED_TABLENAME;
    	
        String sql = "UPDATE " + saleSerialMissedTableName + " SET is_status=1, updated_at = now() " + 
        		" WHERE id = '" + saleSerialMissed.getId() + "' " +
        		" AND 0 < " + 
        		" (SELECT COUNT(o.id)" + 
        		" FROM " + onlineSaleInfosTableName + " AS o" + 
        		" INNER JOIN " + saleSerialEndTableName + " AS s " + 
        		" ON o.op_vm_code = s.vm_code " + 
        		" AND o.system_time > s.checked_at " + 
        		" WHERE o.op_vm_code = '" + saleSerialMissed.getVmCode() + "' " +
        		" AND o.sale_serial = '"+saleSerialMissed.getSaleSerial() + "' " +
        		" AND o.is_aggregated = '1' " + 
        		" AND o.op_vm_code IS NOT NULL);";
        int i = updateBySQL(session, sql);
        session.flush();
        return i;
    }

    /**
     * 当retry_time等于系统设定时，更新is_deleted=1
     * @param session
     * @return 影响条数
     * @throws Exception
     */
    public int deleteNumber( Session session, SaleSerialMissed saleSerialMissed) throws Exception {
    	
    	String saleSerialMissedTableName = PropertiesManager.SALE_SERIAL_MISSED_TABLENAME;
    	
        String sql="UPDATE " + saleSerialMissedTableName 
        		+ " SET is_deleted=1 , updated_at=now() "
        		+ "WHERE id = '" + saleSerialMissed.getId() + "'";
        int i = updateBySQL(session, sql);
        session.flush();
        return i;
    }

    /**
     * 查询缺号表中符合重发的数据
     * @param session
     * @return
     * @throws Exception
     */
    public List<SaleSerialMissed> querySaleSerialMissed( Session session) throws Exception {

    	String saleSerialMissedTableName = PropertiesManager.SALE_SERIAL_MISSED_TABLENAME;
    	
        String sql = "SELECT id,sale_serial,vm_code,location_code,pos_code,retry_times,is_status,is_deleted,created_at,updated_at "
                     +" FROM " + saleSerialMissedTableName
                     +" WHERE is_status != 1 AND is_deleted != 1 " ;
        List<SaleSerialMissed> o = selectBySql(session, SaleSerialMissed.class, sql);
        return o;
    }

    /**
     * retry_times加一
     * @param session
     * @param id 数据id
     * @return
     * @throws Exception
     */
    public int increaseRetryTimes( Session session, String id) throws Exception {
    	
    	String saleSerialMissedTableName = PropertiesManager.SALE_SERIAL_MISSED_TABLENAME;
    	
        String sql="UPDATE " + saleSerialMissedTableName
                +" SET retry_times = retry_times + 1, updated_at = now() WHERE "
                +" id = '" + id + "'";
        int i = updateBySQL(session, sql);
        session.flush();
        return i;
    }

    /**
     * retry_times减一
     * @param session
     * @param id 数据id
     * @return
     * @throws Exception
     */
    public int decreaseRetryTimes( Session session, String id) throws Exception {
    	
    	String saleSerialMissedTableName = PropertiesManager.SALE_SERIAL_MISSED_TABLENAME;
    	
        String sql="UPDATE " + saleSerialMissedTableName
                +" SET retry_times = retry_times - 1, updated_at = now() WHERE "
                +" id = '" + id + "'"
                +" AND retry_times > 0";
        int i = updateBySQL(session, sql);
        session.flush();
        return i;
    }
}
