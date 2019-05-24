package cn.com.gatico.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "performance_link_quality")
public class PerformanceLinkQualityEntity {

    private long id;
    private Long linkId;
    private Integer latency;
    private Integer lossRate;
    private Timestamp samplingTime;
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
    @Column(name = "link_id")
    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    @Basic
    @Column(name = "latency")
    public Integer getLatency() {
        return latency;
    }

    public void setLatency(Integer latency) {
        this.latency = latency;
    }

    @Basic
    @Column(name = "loss_rate")
    public Integer getLossRate() {
        return lossRate;
    }

    public void setLossRate(Integer lossRate) {
        this.lossRate = lossRate;
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
    @Column(name = "oss_sync")
    public Boolean getOssSync() {
        return ossSync;
    }

    public void setOssSync(Boolean ossSync) {
        this.ossSync = ossSync;
    }
}
