package jp.glory.darts.infrastructure.persistence.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@NamedQueries({
		@NamedQuery(name = AuthorityTableBean.QUERY_NAME_FIND_BY_USER_ID, query = "SELECT a FROM AuthorityTableBean a WHERE a.userId = :userId"),
		@NamedQuery(name = AuthorityTableBean.QUERY_NAME_FIND_BY_USER_AUTHORITY, query = "SELECT a FROM AuthorityTableBean a WHERE a.userId = :userId AND a.authorityType = :authorityType")
})
@Cacheable(value = false)
public class AuthorityTableBean implements Serializable {

	/** �V���A���o�[�W����UID */
	private static final long serialVersionUID = 4981897285375455760L;

	/** �N�G�����̐ړ��� */
	private static final String QUERY_NAME_HEADER = "Authority.";

	/** �N�G�����F���[�UID���� */
	public static final String QUERY_NAME_FIND_BY_USER_ID = AuthorityTableBean.QUERY_NAME_HEADER + "findByUserId";

	/** �N�G�����F���[�UID AND �������� */
	public static final String QUERY_NAME_FIND_BY_USER_AUTHORITY = AuthorityTableBean.QUERY_NAME_HEADER
			+ "findByUserAuthority";

	/** �A�� */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_no")
	private int seqNo = 0;

	/** ���[�UID */
	@Column(name = "user_id")
	private String userId = null;

	/** �����^�C�v */
	@Column(name = "authority_type")
	private int authorityType = 0;

	/** �L����� */
	@Column(name = "activate")
	private boolean activate = false;

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(final int seqNo) {
		this.seqNo = seqNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public int getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(final int authorityType) {
		this.authorityType = authorityType;
	}

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(final boolean activate) {
		this.activate = activate;
	}
}
