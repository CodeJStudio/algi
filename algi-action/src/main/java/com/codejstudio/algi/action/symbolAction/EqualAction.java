package com.codejstudio.algi.action.symbolAction;

import java.util.Collection;

import com.codejstudio.algi.action.symbol.EqualSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class EqualAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = -2731539164417353104L;


	/* constructors */

	public EqualAction() {
		super();
		this.symbol = EqualSymbol.getInstance();
	}

	public EqualAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public EqualAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		Object comparer = this.multiContentArray[0];
		Object comparee = this.multiContentArray[1];

		InformationElement cpr = (comparer instanceof String) 
				? Informationiverse.seekInformationByDescription((String) comparer) 
				: (!(comparer instanceof InformationElement) ? null : (InformationElement) comparer);
		InformationElement cpe = (comparee instanceof String) 
				? Informationiverse.seekInformationByDescription((String) comparee) 
				: (!(comparee instanceof InformationElement) ? null : (InformationElement) comparee);
		if (cpr == null || cpe == null) {
			return null;
		}

		Collection<BaseRelation> brc = CollectionUtil.generateNewCollection();
		Collection<BaseRelation> relationCollection = Informationiverse.seekRelations(cpr, cpe, true);
		if (!CollectionUtil.checkNullOrEmpty(relationCollection)) {
			for (BaseRelation br : relationCollection) {
				if (br instanceof EquivalenceRelation) {
					brc.add(br);
				}
			}
		}

		return CollectionUtil.checkNullOrEmpty(brc) ? null : brc.toArray();
	}

}
