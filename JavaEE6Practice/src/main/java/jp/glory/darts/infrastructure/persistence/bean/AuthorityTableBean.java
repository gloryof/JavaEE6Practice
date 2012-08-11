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

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 4981897285375455760L;

	/** クエリ名の接頭語 */
	private static final String QUERY_NAME_HEADER = "Authority.";

	/** クエリ名：ユーザID検索 */
	public static final String QUERY_NAME_FIND_BY_USER_ID = AuthorityTableBean.QUERY_NAME_HEADER + "findByUserId";

	/** クエリ名：ユーザID AND 権限検索 */
	public static final String QUERY_NAME_FIND_BY_USER_AUTHORITY = AuthorityTableBean.QUERY_NAME_HEADER
			+ "findByUserAuthority";

	/** 連番 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_no")
	private int seqNo = 0;

	/** ユーザID */
	@Column(name = "user_id")
	private String userId = null;

	/** 権限タイプ */
	@Column(name = "authority_type")
	private int authorityType = 0;

	/** 有効状態 */
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
