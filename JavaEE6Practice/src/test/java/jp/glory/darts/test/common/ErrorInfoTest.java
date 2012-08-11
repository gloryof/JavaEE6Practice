package jp.glory.darts.test.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.MessageFormat;

import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;

import org.junit.Test;

public class ErrorInfoTest {

	@Test
	public void testInitialize1() {

		final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
		final ErrorInfo testObj = new ErrorInfo(messageInfo);

		assertThat(testObj.getMessageInfo(), is(messageInfo));
		assertThat(testObj.getMessageParam().length, is(0));
	}

	@Test
	public void testInitialize2() {

		final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
		final String[] messageParam = new String[] {
			"テスト"
		};
		final ErrorInfo testObj = new ErrorInfo(messageInfo, messageParam);

		assertThat(testObj.getMessageInfo(), is(messageInfo));
		assertThat(testObj.getMessageParam().length, is(messageParam.length));
		for (int i = 0; i < messageParam.length; i++) {

			assertThat(testObj.getMessageParam()[i], is(messageParam[i]));
		}
	}

	@Test
	public void testGetMessageValue() {

		final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
		final String[] messageParam = new String[] {
			"テスト"
		};
		final ErrorInfo testObj = new ErrorInfo(messageInfo, messageParam);

		final MessageFormat format = new MessageFormat(messageInfo.getMessage());
		final String value = format.format(messageParam);

		assertThat(testObj.getMessageValue(), is(value));
	}

	@Test
	public void testGetMessageValueNullMessageInfo() {

		final String[] messageParam = new String[] {
			"テスト"
		};
		final ErrorInfo testObj = new ErrorInfo(null, messageParam);

		assertThat(testObj.getMessageValue(), is(""));
	}

	@Test
	public void testGetMessageValueNullMessageParam() {

		final MessageInfo messageInfo = MessageInfo.ERROR_LOGIN;
		final ErrorInfo testObj = new ErrorInfo(messageInfo, null);

		assertThat(testObj.getMessageValue(), is(messageInfo.getMessage()));
	}

	@Test
	public void testGetMessageValueEmptyMessageParam() {

		final MessageInfo messageInfo = MessageInfo.ERROR_LOGIN;
		final ErrorInfo testObj = new ErrorInfo(messageInfo, new String[] {});

		assertThat(testObj.getMessageValue(), is(messageInfo.getMessage()));
	}
}
