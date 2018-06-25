package dpcsys.core.dpcInterface.dao.impl;

import dpcsys.api.dpcInterface.dao.DfieldCheckDao;
import dpcsys.api.dpcInterface.model.DfieldCheck;
import faner.dplatformSpringjdbc.core.frame.dao.impl.GenericNamedDaoImpl;

/**
* @Company:faner
* @author: lijianjun
* @version V2.0
*/
@SuppressWarnings("serial")
public class DfieldCheckDaoImpl extends GenericNamedDaoImpl<DfieldCheck, java.lang.Long> implements DfieldCheckDao{

	public DfieldCheckDaoImpl() {
		super(DfieldCheck.class);
	}

}

