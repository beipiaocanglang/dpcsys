package dpcsys.core.dpcnmm.dao.impl;

import dpcsys.api.dpcnmm.dao.DfieldCheckDao;
import dpcsys.api.dpcnmm.model.DfieldCheck;
import faner.dplatformSpringjdbc.core.frame.dao.impl.GenericNamedDaoImpl;

/**
*
* @author: lijianjun
* @version V2.0
*/
@SuppressWarnings("serial")
public class DfieldCheckDaoImpl extends GenericNamedDaoImpl<DfieldCheck, java.lang.Long> implements DfieldCheckDao{

	public DfieldCheckDaoImpl() {
		super(DfieldCheck.class);
	}

}

