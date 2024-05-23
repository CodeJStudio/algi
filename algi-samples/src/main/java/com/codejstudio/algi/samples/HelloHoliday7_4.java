package com.codejstudio.algi.samples;

import java.net.URL;
import java.util.Arrays;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.symbolAction.SimpleSentenceThinkAction;
import com.codejstudio.algi.action.symbolAction.SimpleThinkAction;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.importer.Importer;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.i.IActionable;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: calculate: XXXXX+XXXX=? (via ActionableElement / SimpleThinkAction / SimpleSentenceThinkAction with imported data)
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.importer.Importer
 * @see     com.codejstudio.algi.action.symbolAction.SimpleThinkAction
 * @see     com.codejstudio.algi.action.symbolAction.SimpleSentenceThinkAction
 * @since   algi_v1.0.0
 */
public class HelloHoliday7_4 {

	/* constants */

	private static final String INPUT_JSON_FILE_PATH = "samples/" + HelloHoliday7_4.class.getSimpleName() + ".json";


	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday7_4.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		// HelloHoliday7_4.java
		//Importer
		URL url = Thread.currentThread().getContextClassLoader().getResource(INPUT_JSON_FILE_PATH);
		Importer importer = new Importer(url);
		importer.doImport();
		ActionDefinition adf70 = (ActionDefinition) importer.getElement("adf70");
		Concept dc3 = (Concept) importer.getElement("dc3");

		//ActionableElement
		IActionable afl7_4_1 = new ActionFlow(adf70);
		InputParameter[] ipa7_4_1 = InputParameter.generateInstances("a", "1845659", "b", "3217932");
		Object[] aflOutputArray7_4_1 = afl7_4_1.execute(ipa7_4_1);
		System.out.println();
		System.out.println("ActionableElement7_4_1.execute: " 
				+ ((aflOutputArray7_4_1 == null) ? null : Arrays.asList((Object[]) aflOutputArray7_4_1)));

		//SimpleThinkAction
		Action sta7_4_1 = new SimpleThinkAction(dc3.getId());
		InputParameter[] ipa7_4_2 = InputParameter.generateInstances("a", "8348769", "b", "365876234");
		Object[] staOutputArray7_4_1 = sta7_4_1.execute(ipa7_4_2);
		System.out.println();
		System.out.println("SimpleThinkAction7_4_1.execute: " 
				+ ((staOutputArray7_4_1 == null) ? null : Arrays.asList((Object[]) staOutputArray7_4_1)));

		//SimpleSentenceThinkAction
		Action ssta7_4_1 = new SimpleSentenceThinkAction("3453445,+,8797345");
		Object[] sstaOutputArray7_4_1 = ssta7_4_1.execute();
		System.out.println();
		System.out.println("SimpleSentenceThinkAction7_4_1.execute: " 
				+ ((sstaOutputArray7_4_1 == null) ? null : Arrays.asList((Object[]) sstaOutputArray7_4_1)));

		Action ssta7_4_2 = new SimpleSentenceThinkAction("6537456,加,864333");
		Object[] sstaOutputArray7_4_2 = ssta7_4_2.execute();
		System.out.println();
		System.out.println("SimpleSentenceThinkAction7_4_2.execute: " 
				+ ((sstaOutputArray7_4_2 == null) ? null : Arrays.asList((Object[]) sstaOutputArray7_4_2)));

		Action ssta7_4_3 = new SimpleSentenceThinkAction("657693,plus,234567");
		Object[] sstaOutputArray7_4_3 = ssta7_4_3.execute();
		System.out.println();
		System.out.println("SimpleSentenceThinkAction7_4_3.execute: " 
				+ ((sstaOutputArray7_4_3 == null) ? null : Arrays.asList((Object[]) sstaOutputArray7_4_3)));



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
