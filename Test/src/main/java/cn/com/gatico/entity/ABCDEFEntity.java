package cn.com.gatico.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "a_b_c_d_e_f")
public class ABCDEFEntity implements Serializable {

	private Long id;

	private Long boxId;

	private String srcCidr;

	private String dstCidr;

	private Long protocol;

	private String action;

	private Timestamp createTime;

	private Long priority;

	public ABCDEFEntity() {
	}

	public ABCDEFEntity(Long id) {
		this.id = id;
	}

	public ABCDEFEntity(Long id, String action) {
		this.id = id;
		this.action = action;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	@Column(name = "src_cidr")
	public String getSrcCidr() {
		return srcCidr;
	}

	public void setSrcCidr(String srcCidr) {
		this.srcCidr = srcCidr;
	}

	@Basic
	@Column(name = "dst_cidr")
	public String getDstCidr() {
		return dstCidr;
	}

	public void setDstCidr(String dstCidr) {
		this.dstCidr = dstCidr;
	}

	@Basic
	@Column(name = "protocol")
	public Long getProtocol() {
		return protocol;
	}

	public void setProtocol(Long protocol) {
		this.protocol = protocol;
	}

	@Basic
	@Column(name = "action")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Basic
	@Column(name = "create_time")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Basic
	@Column(name = "priority")
	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

}