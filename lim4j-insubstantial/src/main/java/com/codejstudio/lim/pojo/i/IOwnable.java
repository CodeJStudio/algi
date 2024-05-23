package com.codejstudio.lim.pojo.i;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.role.Observer;
import com.codejstudio.lim.pojo.role.ObserverGroup;
import com.codejstudio.lim.pojo.role.Proposer;

/**
 * IOwnable.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public interface IOwnable {

	/* getters & setters */

	Proposer getProposer();

	boolean setProposer(Proposer proposer) throws LIMException;

	ObserverGroup getObserverGroup();


	/* CRUD for arrays, collections, maps, groups: observers */

	boolean containObserver(Observer observer);

	boolean containObserver(String id);

	Observer getObserver(String id);

	boolean addObserver(Observer... observers) throws LIMException;

	boolean addObserver(Collection<Observer> observerCollection) throws LIMException;

	boolean removeObserver(String id);

}
