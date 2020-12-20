package cn.com.virtualspacex.dao;

import org.hibernate.Session;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.model.SystemBatchSetparam;

import java.util.List;

import com.virtualspacex.component.hibernate.HibernateDao;

public class SystemBatchSetparamDao extends HibernateDao {

    /**
     * 获取配置信息
     * @param session
     * @return
     * @throws Exception
     */
    public List<SystemBatchSetparam> get(Session session) throws Exception {
    	
        String sql = "SELECT @ID \\:=@ID+ 1 AS ID,t.* FROM"
                        + " (SELECT param_id, param_value FROM system_batch_setparam WHERE id=" + Constant.TASK_ID
                        + " ) AS t ,"
                        + " (SELECT @ID \\:=0) ID ";
        List<SystemBatchSetparam> o = selectBySql(session, SystemBatchSetparam.class, sql);
        return o;
    }
}
