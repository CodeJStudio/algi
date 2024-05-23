package com.codejstudio.algi.samples;

import java.util.Arrays;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.symbol.PutSessionAttributeSymbol;
import com.codejstudio.algi.action.symbolAction.CodeExecuteAction;
import com.codejstudio.algi.action.symbolAction.CombineAction;
import com.codejstudio.algi.action.symbolAction.CompareAction;
import com.codejstudio.algi.action.symbolAction.DynamicStatementSymbolAction;
import com.codejstudio.algi.action.symbolAction.InformationiverseAction;
import com.codejstudio.algi.action.symbolAction.LengthOfAction;
import com.codejstudio.algi.action.symbolAction.PutSessionAttributeAction;
import com.codejstudio.algi.action.symbolAction.SplitAction;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.OrConditionGroup;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: calculate: 10+1=?
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.algi.action.symbolAction.LengthOfAction
 * @see     com.codejstudio.algi.action.symbolAction.CompareAction
 * @see     com.codejstudio.algi.action.symbolAction.CodeExecuteAction
 * @see     com.codejstudio.algi.action.symbolAction.CombineAction
 * @see     com.codejstudio.algi.action.symbolAction.SplitAction
 * @see     com.codejstudio.algi.action.symbolAction.DynamicStatementSymbolAction
 * @see     com.codejstudio.algi.action.symbolAction.PutSessionAttributeAction
 * @since   algi_v1.0.0
 */
public class HelloHoliday5_1 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday5_1.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Concept c000 = new Concept("0");
		Concept c001 = new Concept("1");
		Concept c010 = new Concept("10");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c114 = new Concept("数字");
		Concept c115 = new Concept("算术表达式");
		Concept c116 = new Concept("计算");
		Concept c201 = new Concept("0+1");
		Concept c210 = new Concept("1+0");

		c000.addDefaultAttribute(c114);
		c001.addDefaultAttribute(c114);
		c010.addDefaultAttribute(c114);
		c201.addSubConceptWithPosition(c000, c101, c001);
		c201.addDefaultAttribute(c115);
		c210.addSubConceptWithPosition(c001, c101, c000);
		c210.addDefaultAttribute(c115);


		Definition df01 = new Definition("0+1=1");
		Definition df10 = new Definition("1+0=1");

		df01.addConceptWithPosition(c201, c102, c001);
		df10.addConceptWithPosition(c210, c102, c001);


		ConditionGroup cdg1 = new ConditionGroup(new AttributeCondition(c114));
		ConditionGroup cdg2 = new OrConditionGroup(new AttributeCondition(c114), new AttributeCondition(c115));


		Concept dc1 = new Concept("<$a>");//（属性：[备注：$a是数字]）
		Concept dc1_1 = new Concept("<$1>");//（属性：[备注：$1是数字，或算术表达式]）
		Concept dc2 = new Concept("<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）
		Concept dc3 = new Concept("计算<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）

		dc1.setNetDynamicConditions(cdg1);
		dc2.addSubConceptWithPosition(c101, dc1);
		dc2.setNetDynamicConditions(cdg1, cdg1);
		dc3.addSubConceptWithPosition(c116, dc1, dc2);
		dc3.setNetDynamicConditions(cdg1, cdg1);


		Definition ddf1 = new Definition("<$a>+<$b>=<$c>");//（属性：[备注：$a、$b、$c都是数字]）
		Definition ddf2 = new Definition("<$1>=<$2>");//（属性：[备注：$1、$2都是数字，或算术表达式]）

		ddf1.addConceptWithPosition(dc2, c102, dc1);
		ddf1.setNetDynamicConditions(cdg1, cdg1, cdg1);
		ddf2.addConceptWithPosition(dc1_1, c102);
		ddf2.setNetDynamicConditions(cdg2, cdg2);

		Definition ddf1_1 = (Definition) ddf1.substituteDynamicSymbolByParameterType(
				new DynamicParameterType[] {DynamicParameterType.INPUT, DynamicParameterType.INPUT, 
						DynamicParameterType.OUTPUT});//"<$a>+<$b>=<#c>"（属性：[备注：$a、$b、#c都是数字]）


//		EquivalenceRelation eqr11 = 
				new EquivalenceRelation(dc3, ddf1_1);



		// HelloHoliday5_1.java
		Action loa5_1_1 = new LengthOfAction("<lo(><$a><)>");
		Object[] loaOutputArray5_1_1 = loa5_1_1.execute(InputParameter.informationInstances("a", c010));//10
		System.out.println();
		System.out.println("LengthOfAction5_1_1.execute: " 
				+ ((loaOutputArray5_1_1 == null) ? null : Arrays.asList((Object[]) loaOutputArray5_1_1)));

		Action loa5_1_2 = new LengthOfAction("<lo(><$b><)>");
		Object[] loaOutputArray5_1_2 = loa5_1_2.execute(InputParameter.informationInstances("b", c001));//1
		System.out.println();
		System.out.println("LengthOfAction5_1_2.execute: " 
				+ ((loaOutputArray5_1_2 == null) ? null : Arrays.asList((Object[]) loaOutputArray5_1_2)));


		Action cpa5_1_1 = new CompareAction("<cp(><$loa>,<$lob><)>");
		Object[] cpaOutputArray5_1_1 = cpa5_1_1.execute(InputParameter.generateInstances("loa", loaOutputArray5_1_1[0], 
				"lob", loaOutputArray5_1_2[0]));
		System.out.println();
		System.out.println("CompareAction5_1_1.execute: " 
				+ ((cpaOutputArray5_1_1 == null) ? null : Arrays.asList((Object[]) cpaOutputArray5_1_1)));


		Action cxa5_1_1 = new CodeExecuteAction("<cx(><$loa>-<$lob><)>");
		Object[] cxaOutputArray5_1_1 = cxa5_1_1.execute(InputParameter.generateInstances("loa", loaOutputArray5_1_1[0], 
				"lob", loaOutputArray5_1_2[0]));
		System.out.println();
		System.out.println("CodeExecuteAction5_1_1.execute: " 
				+ ((cxaOutputArray5_1_1 == null) ? null : Arrays.asList((Object[]) cxaOutputArray5_1_1)));


		Action cba5_1_1 = new CombineAction("<cb(>0,<$b><)>");
		Object[] cbaOutputArray5_1_1 = cba5_1_1.execute(InputParameter.informationInstances("b", c001));//1
		System.out.println();
		System.out.println("CombineAction5_1_1.execute: " 
				+ ((cbaOutputArray5_1_1 == null) ? null : Arrays.asList((Object[]) cbaOutputArray5_1_1)));


		Action spa5_1_1 = new SplitAction("<sp(><$ad><)>");
		Object[] spaOutputArray5_1_1 = spa5_1_1.execute(InputParameter.informationInstances("ad", c010));//10
		System.out.println();
		System.out.println("SplitAction5_1_1.execute: " + ((spaOutputArray5_1_1 == null) ? null : ""));
		for (int i = 0; (spaOutputArray5_1_1 != null && i < spaOutputArray5_1_1.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((spaOutputArray5_1_1[i]) == null) ? null : Arrays.asList((Object[]) spaOutputArray5_1_1[i])));
		}

		Action spa5_1_2 = new SplitAction("<sp(><$bd><)>");
		Object[] spaOutputArray5_1_2 = spa5_1_2.execute(InputParameter.generateInstances("bd", cbaOutputArray5_1_1[0]));
		System.out.println();
		System.out.println("SplitAction5_1_2.execute: " + ((spaOutputArray5_1_2 == null) ? null : ""));
		for (int i = 0; (spaOutputArray5_1_2 != null && i < spaOutputArray5_1_2.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((spaOutputArray5_1_2[i]) == null) ? null : Arrays.asList((Object[]) spaOutputArray5_1_2[i])));
		}


		Action dssa5_1_1 = new DynamicStatementSymbolAction("<ds(><sdi(>" + ddf1_1.getId() + ",a0,b0,c0<)><)>");
		Object[] dssaOutputArray5_1_1 = dssa5_1_1.execute(InputParameter.generateInstances("a0", 
				((Object[]) spaOutputArray5_1_1[0])[0], "b0", ((Object[]) spaOutputArray5_1_2[0])[0]));
		System.out.println();
		System.out.println("DynamicStatementSymbolAction5_1_1.execute: " 
				+ ((dssaOutputArray5_1_1 == null) ? null : Arrays.asList((Object[]) dssaOutputArray5_1_1)));

		Action dssa5_1_2 = new DynamicStatementSymbolAction("<ds(><sdi(>" + ddf1_1.getId() + ",a1,b1,c1<)><)>");
		Object[] dssaOutputArray5_1_2 = dssa5_1_2.execute(InputParameter.generateInstances("a1", 
				((Object[]) spaOutputArray5_1_1[0])[1], "b1", ((Object[]) spaOutputArray5_1_2[0])[1]));
		System.out.println();
		System.out.println("DynamicStatementSymbolAction5_1_2.execute: " 
				+ ((dssaOutputArray5_1_2 == null) ? null : Arrays.asList((Object[]) dssaOutputArray5_1_2)));


		Action psa5_1_1 = new PutSessionAttributeAction("<ps(><#c0>,<$c0i>," 
				+ PutSessionAttributeSymbol.TYPE_INFO_STRING + "<)>");
		Object[] psaOutputArray5_1_1 = psa5_1_1.execute(InputParameter.generateInstances("c0i", dssaOutputArray5_1_1[0]));
		System.out.println();
		System.out.println("PutSessionAttributeAction5_1_1.execute: " 
				+ ((psaOutputArray5_1_1 == null) ? null : Arrays.asList((Object[]) psaOutputArray5_1_1)));

		Action psa5_1_2 = new PutSessionAttributeAction("<ps(><#c1>,<$c1i>," 
				+ PutSessionAttributeSymbol.TYPE_INFO_STRING + "<)>");
		psa5_1_2.setSession(psa5_1_1.getSession());
		Object[] psaOutputArray5_1_2 = psa5_1_2.execute(InputParameter.generateInstances("c1i", dssaOutputArray5_1_2[0]));
		System.out.println();
		System.out.println("PutSessionAttributeAction5_1_2.execute: " 
				+ ((psaOutputArray5_1_2 == null) ? null : Arrays.asList((Object[]) psaOutputArray5_1_2)));


		Action cba5_1_2 = new CombineAction("<cb(><$c0>,<$c1><)>");
		cba5_1_2.setSession(psa5_1_1.getSession());
		Object[] cbaOutputArray5_1_2 = cba5_1_2.execute();
		System.out.println();
		System.out.println("CombineAction5_1_2.execute: " 
				+ ((cbaOutputArray5_1_2 == null) ? null : Arrays.asList((Object[]) cbaOutputArray5_1_2)));


		Action iua5_1_1 = new InformationiverseAction("<iu(><cb(><$c0>,<$c1><)>,true<)>");
		iua5_1_1.setSession(psa5_1_1.getSession());
		Object[] iuaOutputArray5_1_1 = iua5_1_1.execute();
		System.out.println();
		System.out.println("InformationiverseAction5_1_1.execute: " 
				+ ((iuaOutputArray5_1_1 == null) ? null : Arrays.asList((Object[]) iuaOutputArray5_1_1)));



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
