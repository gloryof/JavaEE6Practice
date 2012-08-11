package jp.glory.darts.ui.page.util.impl;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import jp.glory.darts.common.DartsConst;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.ui.page.util.JsfUtil;

@Default
public class JsfUtilImpl implements JsfUtil, Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -8304829982623019659L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addErrorMessage(final String clientId, final ErrorInfo errorInfo) {

		final String message = errorInfo.getMessageValue();
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearSession() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLoginDateToSession() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		if (session != null) {

			session.setAttribute(DartsConst.SESSION_KEY_LOGIN_DATE, Calendar.getInstance().getTime());
		}
	}

}
