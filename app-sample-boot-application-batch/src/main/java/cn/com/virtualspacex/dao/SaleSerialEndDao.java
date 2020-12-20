package cn.com.virtualspacex.dao;

import org.hibernate.Session;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.model.SaleSerialEnd;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;

import java.util.List;

import com.virtualspacex.component.hibernate.HibernateDao;

public class SaleSerialEndDao extends HibernateDao {

    /**
     * ��ʝ���
     *
     * @param session
     * @param saleSerialEnd
     * @return
     * @throws Exception
     */
    public int insertByList(Session session, List<SaleSerialEnd> saleSerialEnd) throws Exception {
        return insertObjects(session, saleSerialEnd).size();
    }

    /**
     * ��ʍX�VSaleSerialEnd
     *
     * @param session
     * @param updateSaleSerialEnds
     * @return
     * @throws Exception
     */
    public Integer updateSaleSerialEnd(Session session, List<SaleSerialEnd> updateSaleSerialEnds) throws Exception {
        
    	String saleSerialEndTableName = PropertiesManager.SALE_SERIAL_END_TABLENAME;
    	
    	Integer update = 0;
        for (SaleSerialEnd serialEnd : updateSaleSerialEnds) {
            String sql = "UPDATE " + saleSerialEndTableName
                    + " SET sale_serial_end='" + serialEnd.getSaleSerialEnd()
                    + "' ,location_code='" + serialEnd.getLocationCode()
                    + "' ,pos_code='" + serialEnd.getPosCode()
                    + "' ,updated_at = STR_TO_DATE('" + serialEnd.getUpdatedAt() + "'," + Constant.YMDHIS_DATE_FORMAT + ")"
                    + " WHERE id = '" + serialEnd.getId() + "'";
            int i = updateBySQL(session, sql);
            update = i + update;
        }
        session.flush();
        return update;
    }

    /**
     * �X�VSaleSerialEnd
     *
     * @param session
     * @param dataProcessingBO
     * @return
     * @throws Exception
     */
    public Integer updateSaleSerialEndByVmcode(Session session, String saleSerialEnd, String vmCode) throws Exception {
        
    	String saleSerialEndTableName = PropertiesManager.SALE_SERIAL_END_TABLENAME;
    	
    	String sql = "UPDATE " + saleSerialEndTableName
                + " SET sale_serial_end = '" + saleSerialEnd + "',"
                + " updated_at = now()"
                + " WHERE vm_code = '" + vmCode + "'";
        int i = updateBySQL(session, sql);
        session.flush();
        return i;
    }
}
