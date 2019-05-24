package cn.com.gatico.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "performance_compute")
public class PerformanceComputeEntity {

    private long id;                                //主键ID

    private Long boxId;                             //小盒子ID

    private Integer cpuUsageGlobal;                 //CPU占用率（%）

    private Long memoryCapacity;                    //内存总量（Byte）

    private Long memoryUsed;                        //内存使用量（Byte）

    private Timestamp samplingTime;                 //采样时间

    private Timestamp systemTime;

    private Long workingTime;

    private String currentTemp;

    private Boolean ossSync;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "box_id")
    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    @Basic
    @Column(name = "cpu_usage_global")
    public Integer getCpuUsageGlobal() {
        return cpuUsageGlobal;
    }

    public void setCpuUsageGlobal(Integer cpuUsageGlobal) {
        this.cpuUsageGlobal = cpuUsageGlobal;
    }

    @Basic
    @Column(name = "memory_capacity")
    public Long getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(Long memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    @Basic
    @Column(name = "memory_used")
    public Long getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Long memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    @Basic
    @Column(name = "sampling_time")
    public Timestamp getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(Timestamp simplingTime) {
        this.samplingTime = simplingTime;
    }

    @Basic
    @Column(name = "system_time")
    public Timestamp getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Timestamp systemTime) {
        this.systemTime = systemTime;
    }

    @Basic
    @Column(name = "working_time")
    public Long getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Long workingTime) {
        this.workingTime = workingTime;
    }

    @Basic
    @Column(name = "current_temp")
    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    @Basic
    @Column(name = "oss_sync")
    public Boolean getOssSync() {
        return ossSync;
    }

    public void setOssSync(Boolean ossSync) {
        this.ossSync = ossSync;
    }
}
