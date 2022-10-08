package cn.com.gatico.offline;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class Version1 {
    /*@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void checkBoxStatus(BoxVo boxVo) {
        DeviceBoxEntity boxEntity = deviceBoxDao.findOne(boxVo.getBoxId());
        String status = (String) boxStatusRabbitTemplate.convertSendAndReceive(boxEntity.getSerialNumber() + "_" + QueueEnum.BOX_STATUS.getQueueName(), boxVo.getSn());
        String boxStatus = StringUtil.isNull(status) ? BoxStatus.OFFLINE.getCode() : status;
        if (!boxEntity.getStatus().equals(boxStatus) && BoxStatus.OFFLINE.getCode().equals(boxStatus)) {
            boxEntity.setDisconnectedCount(boxEntity.getDisconnectedCount() + 1);
            if (boxEntity.getDisconnectedCount() == 3) {
                boxEntity.setStatus(StringUtil.isNull(status) ? BoxStatus.OFFLINE.getCode() : status);
                if ((boxEntity.getAlarmAuthority() & BoxAlarmAuthorityType.STATUS.getCode()) != 0 &&
                        boxEntity.getUserId() != null && securityUserDao.findOne(boxEntity.getUserId()) != null) {
                    createDeviceStatusAlarmRecord(boxEntity, status);
                }
                BoxWanEntity boxWanEntity = boxWanDao.findByBoxId(boxVo.getBoxId());
                if (boxWanEntity != null) {
                    boxWanEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    boxWanEntity.setWan1RunningStatus("None");
                    boxWanEntity.setWan1ProbeStatus("None");
                    boxWanEntity.setWan2RunningStatus("None");
                    boxWanEntity.setWan2ProbeStatus("None");
                    boxWanEntity.setMobileRunningStatus("None");
                    boxWanEntity.setMobileProbeStatus("None");
                    boxWanDao.saveOrUpdate(boxWanEntity);
                }
            }
        } else if (!boxEntity.getStatus().equals(boxStatus) && BoxStatus.ONLINE.getCode().equals(boxStatus)) {
            boxEntity.setDisconnectedCount(0);
            boxEntity.setStatus(StringUtil.isNull(status) ? BoxStatus.OFFLINE.getCode() : status);
            if ((boxEntity.getAlarmAuthority() & BoxAlarmAuthorityType.STATUS.getCode()) != 0 &&
                    boxEntity.getUserId() != null && securityUserDao.findOne(boxEntity.getUserId()) != null) {
                createDeviceStatusAlarmRecord(boxEntity, status);
            }
        }
        deviceBoxDao.saveOrUpdate(boxEntity);
    }*/
}
