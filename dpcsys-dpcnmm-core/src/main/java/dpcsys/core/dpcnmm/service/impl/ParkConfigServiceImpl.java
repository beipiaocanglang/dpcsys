package dpcsys.core.dpcnmm.service.impl;
import dpcsys.api.dpcnmm.dao.ParkConfigDataDao;
import dpcsys.api.dpcnmm.model.ParkingConfig;
import dpcsys.api.dpcnmm.service.IParkConfigService;
import faner.dplatformSpringjdbc.api.frame.command.db.springJDBC.xml.OverallSituationStatic;
import faner.dplatformSpringjdbc.api.frame.util.tools.velocity.VelocityCoreUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 停车配置表的 service 接口实现类
 * author : sunpanhu
 * create time : 2018-5-25 下午2:12
 */
@Service
public class ParkConfigServiceImpl implements IParkConfigService {
    @Resource
    private ParkConfigDataDao parkConfigDataMapper;

    public int insertSelective(ParkingConfig parkingConfig) {
        int insert = parkConfigDataMapper.insertSelective(parkingConfig);
        return insert;
    }

    public int deleteByPrimaryKey(Long id) {
        int row = parkConfigDataMapper.deleteByPrimaryKey(id);
        return row;
    }

    public int updateByPrimaryKeySelective(ParkingConfig parkingConfig) {
        int rows = parkConfigDataMapper.updateByPrimaryKeySelective(parkingConfig);
        return rows;
    }

    public ParkingConfig selectByPrimaryKey(Long id) {
        ParkingConfig parkingConfig = parkConfigDataMapper.selectByPrimaryKey(id);
        return parkingConfig;
    }

    @Override
    public List<ParkingConfig> selectByParkConfigData(ParkingConfig parkingConfig) {
        List<ParkingConfig> parkingConfigList = parkConfigDataMapper.selectByParkConfigData(parkingConfig);
        return parkingConfigList;
    }

    public List<ParkingConfig> findList(Map<String, Object> paramsMap) {
        /*List<ParkingConfig> parkingConfigList = parkConfigDataMapper.findList(storeId);
        return parkingConfigList;*/
        Map<String, String> sqlMap = OverallSituationStatic.getTableAllSql(ParkingConfig.class.getSimpleName());
        return parkConfigDataMapper.findList(VelocityCoreUtils.render(sqlMap.get("findList"), paramsMap), paramsMap);
    }

    public List<ParkingConfig> findCardType(String storeId) {
        List<ParkingConfig> cardTypeMap = parkConfigDataMapper.findCardType(storeId);
        return cardTypeMap;
    }

    public List<ParkingConfig> findActiveTimeIsExist(ParkingConfig parkingConfig) {
        List<ParkingConfig> activeTimeIsExist = parkConfigDataMapper.findActiveTimeIsExist(parkingConfig);
        return activeTimeIsExist;
    }

    public List<ParkingConfig> selectByRequire(ParkingConfig parkingConfig) {
        List<ParkingConfig> parkingConfigList = parkConfigDataMapper.selectByRequire(parkingConfig);
        return parkingConfigList;
    }

    public List<ParkingConfig> selectByMultiKey(String[] ids) {
        List<ParkingConfig> parkingConfig = parkConfigDataMapper.selectByMultiKey(ids);
        return parkingConfig;
    }
}
