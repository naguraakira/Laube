package site.laube.utility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.exception.LaubeException;

/**
 * LaubeUtility Test Case
 */
public class LaubeUtilityTest {

	/**
	 * ログオブジェクトを管理します。<br>
	 */
	private static Logger log = LoggerFactory.getLogger(LaubeUtilityTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws LaubeException {

		String after = LaubeUtility.doEncryption("ABC123");
		log.debug("[JUNIT]" + "暗号前文字列:" + "ABC123");
		log.debug("[JUNIT]" + "暗号後文字列:" + after);

		String dec = LaubeUtility.doDecryption(after);
		log.debug("[JUNIT]" + "複合前文字列:" + after);
		log.debug("[JUNIT]" + "複合後文字列:" + dec);

		 assertEquals("ABC123",dec);
}

}
