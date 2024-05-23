package com.codejstudio.algi.samples;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.ActionQueue;
import com.codejstudio.algi.action.ActionTemplate;
import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.IALGIRunnable;
import com.codejstudio.algi.common.web.SseEmitterWrapper;
import com.codejstudio.lim.common.collection.MultiValueMap;
import com.codejstudio.lim.common.collection.PropertiesWrapper;
import com.codejstudio.lim.common.util.IStringable;
import com.codejstudio.lim.common.util.PropertiesClass;
import com.codejstudio.lim.common.util.PropertiesClasses;
import com.codejstudio.lim.importer.ImportFile;
import com.codejstudio.lim.importer.ImportFile.Record;
import com.codejstudio.lim.importer.Importer;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.GenericElementGroup;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.InformationElementGroup;
import com.codejstudio.lim.pojo.InformationSection;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.InformationUnitGroup;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.OwnableInformationElementGroup;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.argument.ArgumentGroup;
import com.codejstudio.lim.pojo.argument.HypotheticalSyllogism;
import com.codejstudio.lim.pojo.argument.MixedHypotheticalSyllogism;
import com.codejstudio.lim.pojo.argument.PureHypotheticalSyllogism;
import com.codejstudio.lim.pojo.argument.Syllogism;
import com.codejstudio.lim.pojo.argument.Validity;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.attribute.AttributeGroup;
import com.codejstudio.lim.pojo.attribute.DefaultAttribute;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.comment.CommentGroup;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.concept.ConceptGroup;
import com.codejstudio.lim.pojo.condition.AndConditionGroup;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.DynamicCondition;
import com.codejstudio.lim.pojo.condition.FactorCondition;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.condition.NegativesCondition;
import com.codejstudio.lim.pojo.condition.OrConditionGroup;
import com.codejstudio.lim.pojo.condition.PremiseCondition;
import com.codejstudio.lim.pojo.condition.QuantifiersCondition;
import com.codejstudio.lim.pojo.condition.ScopeCondition;
import com.codejstudio.lim.pojo.doubt.Doubt;
import com.codejstudio.lim.pojo.doubt.Explanation;
import com.codejstudio.lim.pojo.doubt.ExplanationGroup;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.entity.EntityGroup;
import com.codejstudio.lim.pojo.i.IActionable;
import com.codejstudio.lim.pojo.i.IAttributable;
import com.codejstudio.lim.pojo.i.ICloneable;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IDynamicable;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.i.INonSurroundingSymbol;
import com.codejstudio.lim.pojo.i.IOwnable;
import com.codejstudio.lim.pojo.i.IRelationable;
import com.codejstudio.lim.pojo.i.ISession;
import com.codejstudio.lim.pojo.i.ISymbol;
import com.codejstudio.lim.pojo.i.ISymbolCombination;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.i.IValuable;
import com.codejstudio.lim.pojo.relation.AffiliationRelation;
import com.codejstudio.lim.pojo.relation.AnalogyRelation;
import com.codejstudio.lim.pojo.relation.AntonymyRelation;
import com.codejstudio.lim.pojo.relation.AttributeMappingRelation;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.CausalityRelation;
import com.codejstudio.lim.pojo.relation.ComparisonRelation;
import com.codejstudio.lim.pojo.relation.CustomizedRelation;
import com.codejstudio.lim.pojo.relation.DefiningRelation;
import com.codejstudio.lim.pojo.relation.DoubtNExplanationRelation;
import com.codejstudio.lim.pojo.relation.DummyRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.relation.GreaterThanRelation;
import com.codejstudio.lim.pojo.relation.LessThanRelation;
import com.codejstudio.lim.pojo.relation.MappingRelation;
import com.codejstudio.lim.pojo.relation.NearSynonymyRelation;
import com.codejstudio.lim.pojo.relation.PredicateMappingRelation;
import com.codejstudio.lim.pojo.relation.RelationGroup;
import com.codejstudio.lim.pojo.relation.SemanticRelation;
import com.codejstudio.lim.pojo.relation.SynonymyRelation;
import com.codejstudio.lim.pojo.role.BaseRole;
import com.codejstudio.lim.pojo.role.Observer;
import com.codejstudio.lim.pojo.role.ObserverGroup;
import com.codejstudio.lim.pojo.role.Proposer;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.JudgedStatementGroup;
import com.codejstudio.lim.pojo.statement.Narration;
import com.codejstudio.lim.pojo.statement.Proposition;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.statement.StatementGroup;
import com.codejstudio.lim.pojo.statement.Truth;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.DynamicInformation;
import com.codejstudio.lim.pojo.util.DynamicInformationGroup;
import com.codejstudio.lim.pojo.util.DynamicalizedStaticInformation;
import com.codejstudio.lim.pojo.util.HPPDynamicInformation;
import com.codejstudio.lim.pojo.util.InputParameter;
import com.codejstudio.lim.pojo.util.PartDynamicalizedStaticInformation;
import com.codejstudio.lim.pojo.util.Position;

/**
 * POJO naming samples
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @since   lim4j_v1.0.0
 */
public final class PojoElementNamingSample {
	Root root;

	IActionable iacb, acb;
	IAttributable iatb, atb;
	ICloneable iclb, clb;
	IConditionable icdb, cdb;
	IConvertible icvb, cvb;
	IDynamicable idnb, dnb;
	IDynamicable.DynamicParameterType dnpt;
	IGroupable<?> igb, gb;
//	IGroupable<E> eg;
	IIntegratable iitb, itb;
	INonSurroundingSymbol inssb, nssb, nss;
	IOwnable iob, ob;
	IRelationable irb, rb;
	ISession iss, ss;
	ISymbol isb, sb;
	ISymbolCombination isbc, sbc, sc;
	ISymbolElement isbe, sbe, se;
	IValuable ivlb, vlb;

	BaseElement be;
	GenericActionableElement gace, gae;
	GenericElement ge;
	GenericElementGroup geg;
	OwnableInformationElement oie;
	OwnableInformationElementGroup oieg;
	AbstractRelationableInformationElement arie;

	InformationElement ie;
	InformationElementGroup ieg;
	InformationSection is;
	InformationUnit iu;
	InformationUnitGroup iug;

	LogicValue lv;
	LogicValue.BooleanValueType bvt;
	Truth t;
	Truth.TruthType tt;
	Validity v;
	Validity.ValidityType vt;

	Concept c;
	ConceptGroup cg;

	Statement s;
	StatementGroup sg;
	JudgedStatement js;
	JudgedStatementGroup jsg;
	Definition df;
	Narration nr;
	Proposition pp;
	HypotheticalProposition hpp;

	Argument a;
	ArgumentGroup ag;
	Syllogism sy;
	HypotheticalSyllogism hsy;
	MixedHypotheticalSyllogism mhsy;
	PureHypotheticalSyllogism phsy;

	Attribute at;
	AttributeGroup atg;
	DefaultAttribute dat;

	Condition cd;
	ConditionGroup cdg;
	ConditionGroup.AndOrType aot;
	AndConditionGroup acdg;
	OrConditionGroup ocdg;
	AttributeCondition atcd;
	DynamicCondition dncd;
	FactorCondition fcd;
	HypotheticalCondition hcd;
	PremiseCondition pcd;
	ScopeCondition scd;
	NegativesCondition ncd;
	NegativesCondition.NegativesType nt;
	QuantifiersCondition qcd;
	QuantifiersCondition.QuantifiersType qt;

	Doubt d;
	Doubt.DoubtType dt;
	Explanation ex;
	ExplanationGroup exg;

	BaseRelation br;
	RelationGroup rg;
	MappingRelation mpr;
	AnalogyRelation anr;
	AttributeMappingRelation ampr;
	PredicateMappingRelation pmpr;
	AffiliationRelation afr;
	CausalityRelation csr;
	CustomizedRelation ctr;
	DefiningRelation dfr;
	SynonymyRelation snr;
	AntonymyRelation atr;
	NearSynonymyRelation nsnr;
	DoubtNExplanationRelation der;
	EquivalenceRelation eqr;
	ComparisonRelation cpr;
	GreaterThanRelation gtr;
	LessThanRelation ltr;
	SemanticRelation smr;
	DummyRelation dmr;

	Entity en;
	EntityGroup eng;

	Comment cm;
	CommentGroup cmg;

	BaseRole brl;
	Observer o;
	ObserverGroup og;
	Proposer p;


	Description descriptionObject, dsco;
	String description, dsc;
	DynamicalizedStaticInformation dsi;
	DynamicInformation di;
	DynamicInformationGroup dig;
	HPPDynamicInformation hdi;
	InputParameter ip;
	PartDynamicalizedStaticInformation pdsi;
	Position ps;
//	XmlAttributeEntry xate;
//	XmlElementEntry xee;


	Importer importer, ipt;
	ImportFile file, ipf;
	Record record, rc, r;


	MultiValueMap<?, ?> mvmap, mvm;
//	MultiValueMap<K, V> kvMVMap, kvmvm;
	PropertiesWrapper properties, pw;
	IStringable istb, stb;
	PropertiesClass pcl;
	PropertiesClasses pcls;



	Action ac;
	ActionFlow acfl, afl;
	ActionQueue acqu, aqu;
	ActionTemplate actp, atp;


	DispatchedObject dpobj;
	IALGIRunnable iar, ar;


	SseEmitterWrapper emitter, sew;

}
