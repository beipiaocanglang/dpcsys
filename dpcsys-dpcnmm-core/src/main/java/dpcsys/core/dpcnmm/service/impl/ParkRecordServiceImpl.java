package dpcsys.core.dpcnmm.service.impl;

import dpcsys.api.dpcnmm.dao.ParkRecordDao;
import dpcsys.api.dpcnmm.model.ParkingRecord;
import dpcsys.api.dpcnmm.service.IParkRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 停车记录表的 service 接口实现类
 * author : sunpanhu
 * create time : 2018-5-25 下午3:52
 */
@Service
public class ParkRecordServiceImpl implements IParkRecordService {
    @Resource
    private ParkRecordDao parkRecordDao;

    public int insertSelective(ParkingRecord parkingRecord) {
        int rows = parkRecordDao.insertSelective(parkingRecord);
        return rows;
    }

    public int updateByPrimaryKeySelective(ParkingRecord parkingRecord) {
        int rows = parkRecordDao.updateByPrimaryKeySelective(parkingRecord);
        return rows;
    }

    public ParkingRecord selectByPrimaryKey(Long id) {
        ParkingRecord parkingRecord = parkRecordDao.selectByPrimaryKey(id);
        return parkingRecord;
    }

    public List<ParkingRecord> selectByParkRecord(ParkingRecord parkingRecord) {
        List<ParkingRecord> parkingRecords = parkRecordDao.selectByParkRecord(parkingRecord);
        return parkingRecords;
    }

    //车辆驶入时查询当前车辆是否有未结算的停车记录
    public List<ParkingRecord> selectByRequire(ParkingRecord parkingRecord) {
        List<ParkingRecord> parkingRecords = parkRecordDao.selectByRequire(parkingRecord);
        return parkingRecords;
    }
}
