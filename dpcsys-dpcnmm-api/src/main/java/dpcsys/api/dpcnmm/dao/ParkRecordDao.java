package dpcsys.api.dpcnmm.dao;

import dpcsys.api.dpcnmm.model.ParkingRecord;

import java.util.List;

public interface ParkRecordDao {
    //插入
    int insert(ParkingRecord parkingRecord);
    //动态插入
    int insertSelective(ParkingRecord parkingRecord);

    //根据主键删除
    int deleteByPrimaryKey(Long id);

    //根据主键动态更修改
    int updateByPrimaryKeySelective(ParkingRecord parkingRecord);

    //根据主键查询单条数据
    ParkingRecord selectByPrimaryKey(Long id);
    //根据条件查询多条数据
    List<ParkingRecord> selectByParkRecord(ParkingRecord parkingRecord);
    //车辆驶入时查询当前车辆是否有未结算的停车记录
    List<ParkingRecord> selectByRequire(ParkingRecord parkingRecord);
}