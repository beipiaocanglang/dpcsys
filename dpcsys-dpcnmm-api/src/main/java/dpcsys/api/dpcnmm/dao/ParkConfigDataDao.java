package dpcsys.api.dpcnmm.dao;

import dpcsys.api.dpcnmm.model.ParkingConfig;

import java.util.List;
import java.util.Map;

public interface ParkConfigDataDao {
    //插入
    int insert(ParkingConfig parkingConfig);
    //动态插入
    int insertSelective(ParkingConfig parkingConfig);

    //删除
    int deleteByPrimaryKey(Long id);

    //根据主键动态修改
    int updateByPrimaryKeySelective(ParkingConfig parkingConfig);

    //查询单条
    ParkingConfig selectByPrimaryKey(Long id);
    //根据条件查询多条
    List<ParkingConfig> selectByParkConfigData(ParkingConfig parkingConfig);
    //根据门店id查询所有
    List<ParkingConfig> findList(String findList, Map<String, Object> paramsMap);
    //根据门店id查询搜有卡类别
    List<ParkingConfig> findCardType(String storeId);
    //添加卡前的查询，是否存在相同或包含当前添加的时间
    List<ParkingConfig> findActiveTimeIsExist(ParkingConfig parkingConfig);
    //驶入时调用查询剩余免费停车时间
    List<ParkingConfig> selectByRequire(ParkingConfig parkingConfig);
    List<ParkingConfig> selectByMultiKey(String[] ids);
}