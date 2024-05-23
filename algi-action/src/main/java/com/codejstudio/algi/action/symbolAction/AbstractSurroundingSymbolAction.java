package com.codejstudio.algi.action.symbolAction;

import java.util.Arrays;
import java.util.List;

import com.codejstudio.algi.action.AbstractAction;
import com.codejstudio.algi.action.symbol.ISurroundingSymbol;
import com.codejstudio.algi.action.symbol.ISurroundingSymbolCombination;
import com.codejstudio.algi.action.symbolElement.AbstractSurroundingSymbolElement;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.ISession;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.DynamicInformation;
import com.codejstudio.lim.pojo.util.DynamicableUtil;
import com.codejstudio.lim.pojo.util.InputDynamicSymbol;
import com.codejstudio.lim.pojo.util.InputParameter;
import com.codejstudio.lim.pojo.util.Position;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractSurroundingSymbolAction extends AbstractAction implements SymbolAction {

	/* constants */

	private static final long serialVersionUID = -2246882353156409142L;


	/* variables */

	protected ISurroundingSymbol symbol;

	protected ISurroundingSymbolCombination symbolCombination;

	protected ISymbolElement symbolElement;

	protected String sourceDescription;

	protected Description sourceDescriptionObject;

	protected boolean symbolFlag;

	protected DynamicInformation dynamicInformation;


	/* initializers */

	protected void init() throws LIMException {
		this.symbolFlag = (this.symbol != null) 
				? this.symbol.verifySurroundingSymbol(this.sourceDescription) : this.symbolFlag;
		String str = this.symbolFlag ? this.sourceDescription : this.symbol.surround(this.sourceDescription);
		this.symbolElement = (this.symbolElement == null && this.symbol != null) 
				? this.symbol.generateSymbolElement(str, this) : this.symbolElement;
	}


	/* getters & setters */

	public ISurroundingSymbol getSymbol() {
		return symbol;
	}

	public ISurroundingSymbolCombination getSymbolCombination() {
		return symbolCombination;
	}

	@Override
	public void setSymbolCombination(ISurroundingSymbolCombination symbolCombination) {
		this.symbolCombination = symbolCombination;
	}

	public ISymbolElement getSymbolElement() {
		return symbolElement;
	}

	@Override
	public void setSymbolElement(ISymbolElement symbolElement) {
		this.symbolElement = symbolElement;
	}

	public String getSourceDescription() {
		return sourceDescription;
	}

	@Override
	public void setSourceDescription(String sourceDescription) throws LIMException {
		this.sourceDescription = sourceDescription;
		init();
	}

	public Description getSourceDescriptionObject() {
		return sourceDescriptionObject;
	}

	@Override
	public void setSourceDescriptionObject(Description sourceDescriptionObject) throws LIMException {
		this.sourceDescriptionObject = sourceDescriptionObject;
		if (sourceDescriptionObject != null) {
			setSourceDescription(sourceDescriptionObject.getEncodedText());
		}
	}


	public boolean isSymbolFlag() {
		return symbolFlag;
	}

	public DynamicInformation getDynamicInformation() {
		return dynamicInformation;
	}


	/* overridden methods */

	@Override
	public Object[] execute(final String sourceDescription, final ISession session, 
			final InputParameter... inputParameters) throws LIMException {
		setSourceDescription(sourceDescription);
		return execute(session, inputParameters);
	}


	@Override
	protected Object[] doExecute() throws LIMException {
		return !makeupContent() ? null : doExecuteWithContent();
	}

	protected abstract boolean makeupContent() throws LIMException;

	protected abstract Object[] doExecuteWithContent() throws LIMException;


	@Override
	protected void preExecute(final InputParameter... inputParameters) throws LIMException {
		String preExecuteSourceDescription = executeSubSymbolElements(inputParameters);
		if (preExecuteSourceDescription != null) {
			setSourceDescription(preExecuteSourceDescription);
		}

		if (dynamicInformationFlag()) {
			preExecuteSourceDescription = handleStringableDynamicInformation();
			if (preExecuteSourceDescription != null) {
				setSourceDescription(preExecuteSourceDescription);
			}
		}
	}

	protected boolean dynamicInformationFlag() {
		return true;
	}

	private String executeSubSymbolElements(final InputParameter... inputParameters) throws LIMException {
		if (this.symbol == null || !(this.symbolElement instanceof AbstractSurroundingSymbolElement)) {
			return null;
		}

		AbstractSurroundingSymbolElement asse = (AbstractSurroundingSymbolElement) this.symbolElement;
		List<ISymbolElement> ssel = asse.getSubSymbolElementList();
		List<Position> ssepl = asse.getSubSymbolElementPositionList();
		String[] sfa = asse.getSourceFragmentArray();
		if (CollectionUtil.checkNullOrEmpty(ssel) || CollectionUtil.checkNullOrEmpty(ssepl) 
				|| ssel.size() != ssepl.size() || CollectionUtil.checkNullOrEmpty(sfa)) {
			return null;
		}

		Object[][] arrayOfOutputArrays = new Object[ssel.size()][];
		for (int i = 0; i < ssel.size(); i++) {
			ISymbolElement se = ssel.get(i);
			if (se != null) {
				arrayOfOutputArrays[i] = se.executeAction(this.session, inputParameters);
			}
		}

		StringBuilder sb = new StringBuilder();
		int from = 0;
		for (int i = 0; i < arrayOfOutputArrays.length; i++) {
			Position ps = ssepl.get(i);
			int index = ps.getIndex(), length = ps.getLength();
			sb.append((index > from) ? String.join("", Arrays.copyOfRange(sfa, from, index)) : "");
			Object[] outputArray = arrayOfOutputArrays[i];
			for (int j = 0; (outputArray != null && j < outputArray.length); j++) {
				if (StringUtil.isStringable(outputArray[j])) {
					sb.append(outputArray[j]);
				} else {
					String inputId = InputDynamicSymbol.generateInputId(outputArray[j]);
					if (inputId != null) {
						this.session.putInputParameter(inputId, outputArray[j]);
						sb.append(DynamicableUtil.INPUT_DYNAMIC_SYMBOL.packageDynamicSymbol(inputId));
					}
				}
			}
			from = index + length;
		}
		if (from < sfa.length) {
			sb.append(String.join("", Arrays.copyOfRange(sfa, from, sfa.length)));
		}
		return this.symbol.surround(sb.toString());
	}

	private String handleStringableDynamicInformation() {
		this.dynamicInformation = DynamicInformation.newInstance(this.sourceDescription);
		return DynamicableUtil.substituteStaticContentForDynamic(this.dynamicInformation, this.session);
	}

}
