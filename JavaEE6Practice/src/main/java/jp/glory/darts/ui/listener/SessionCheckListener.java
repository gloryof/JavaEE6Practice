package jp.glory.darts.ui.listener;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.glory.darts.common.DartsConst;
import jp.glory.darts.common.exception.DartsSystemException;
import jp.glory.darts.ui.page.common.ForwardKey;

/**
 * セッションチェックリスナー
 * 
 * @author Junki Yamada
 * 
 */
public class SessionCheckListener implements PhaseListener {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 5791126100542888026L;

	/**
	 * 何もしない
	 */
	@Override
	public void afterPhase(final PhaseEvent arg0) {

	}

	/**
	 * セッションのチェックを行う
	 */
	@Override
	public void beforePhase(final PhaseEvent event) {

		final FacesContext context = event.getFacesContext();

		if (!ForwardKey.INDEX_PAGE.getValue().equals(context.getViewRoot().getViewId())) {

			final ExternalContext extContext = context.getExternalContext();
			final HttpServletRequest request = (HttpServletRequest) extContext.getRequest();
			final HttpSession session = request.getSession(false);
			if ((session == null) || session.getAttribute(DartsConst.SESSION_KEY_LOGIN_DATE) == null) {

				try {

					extContext.dispatch(ForwardKey.INDEX_PAGE.getValue());
					context.responseComplete();
				} catch (IOException ioe) {

					throw new DartsSystemException("Session IO Error.", ioe);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhaseId getPhaseId() {

		return PhaseId.RENDER_RESPONSE;
	}

}
