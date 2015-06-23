package com.ctrip.infosec.rule.convert.persist;

import com.ctrip.datasource.locator.DataSourceLocator;
import com.ctrip.infosec.configs.event.DataUnitMetadata;
import com.ctrip.infosec.configs.event.DatabaseType;
import com.ctrip.infosec.configs.event.DistributionChannel;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by jizhao on 2015/6/23.
 */
public class RdbmsInsert implements DbOperation {

    /**
     * 数据分发通道
     */
    private DistributionChannel channel;

    private String table;

    /**
     * key: 数据序列  value： 数据来源和 表达式
     */
    private Map<String, PersistColumnProperties> columnPropertiesMap;



    @Override
    public void execute(PersistContext ctx) throws DbExecuteException {
        DatabaseType databaseType = channel.getDatabaseType();
        if(databaseType.equals(DatabaseType.AllInOne_SqlServer)){
//            String sql = createSql(channel.get,columnPropertiesMap);

            DataSource dataSource;
            try {
                dataSource = DataSourceLocator.newInstance().getDataSource( channel.getDatabaseURL());
            } catch (Exception e) {
                e.printStackTrace();
                throw new DbExecuteException("获取dataSource异常",e);
            }

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

//            jdbcTemplate.update()

        }


    }

//    private String createSql(String table, Map<String, PersistColumnProperties> columnPropertiesMap) {
//        String sql= "insert "
//        for(Map.Entry<String,PersistColumnProperties> entry: columnPropertiesMap){
//
//        }
//    }


    @Override
    public Map<String, Object> getExposedValue() {
        return null;

    }


    public DistributionChannel getChannel() {
        return channel;
    }

    public void setChannel(DistributionChannel channel) {
        this.channel = channel;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Map<String, PersistColumnProperties> getColumnPropertiesMap() {
        return columnPropertiesMap;
    }

    public void setColumnPropertiesMap(Map<String, PersistColumnProperties> columnPropertiesMap) {
        this.columnPropertiesMap = columnPropertiesMap;
    }
}
