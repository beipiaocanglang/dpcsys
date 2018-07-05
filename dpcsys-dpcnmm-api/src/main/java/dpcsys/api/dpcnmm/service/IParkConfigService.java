package dpcsys.api.dpcnmm.service;

import dpcsys.api.dpcnmm.model.ParkingConfig;

import java.util.List;

/**
 * 停车配置表的 service 接口
 * author : sunpanhu
 * create time : 2018-5-25 下午2:11
 */
public interface IParkConfigService {
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
    List<ParkingConfig> findList(String storeId);
    List<ParkingConfig> findCardType(String storeId);
    //添加卡前的查询，是否存在相同或包含当前添加的时间
    List<ParkingConfig> findActiveTimeIsExist(ParkingConfig parkingConfig);
    List<ParkingConfig> selectByRequire(ParkingConfig parkingConfig);
    List<ParkingConfig> selectByMultiKey(String[] ids);
}
