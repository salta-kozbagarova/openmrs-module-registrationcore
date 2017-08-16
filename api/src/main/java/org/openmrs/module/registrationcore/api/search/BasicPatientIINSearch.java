package org.openmrs.module.registrationcore.api.search;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("registrationcore.BasicPatientIINSearch")
public class BasicPatientIINSearch implements PatientIINSearch {

	@Autowired
    @Qualifier("dbSessionFactory")
    private DbSessionFactory sessionFactory;
	
	@Override
	public Boolean isIINUnique(String iin) {
		PersonAttributeType personAttributeType = Context.getPersonService().getPersonAttributeTypeByName("IIN");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Patient.class);
		criteria.createAlias("attributes", "attributes");
		criteria.add(Restrictions.eq("attributes.attributeType", personAttributeType));
		@SuppressWarnings("unchecked")
		List<Patient> patients = criteria.list();
		if(patients != null && patients.size()>0){
			return false;
		}
		return true;
	}

}
