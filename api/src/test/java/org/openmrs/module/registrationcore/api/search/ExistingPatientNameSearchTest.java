/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.registrationcore.api.search;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.registrationcore.api.RegistrationCoreSensitiveTestBase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Tests ExistingPatientNameSearchTest
 */
public class ExistingPatientNameSearchTest extends RegistrationCoreSensitiveTestBase {

    @Autowired
    ExistingPatientNameSearch search;

    @Before
    public void setup() throws Exception {
        executeDataSet("patients_dataset.xml");
    }

    @Test
    public void findSimilarGivenNames_shouldFindAmongExistingGivenNames() {
        List<String> names = search.findSimilarGivenNames("Kip");

        Assert.assertEquals(Arrays.asList("Kiptogom"), names);
    }

    @Test
    public void findSimilarFamilyNames_shouldFindAmongExistingFamilyNames() {
        List<String> names = search.findSimilarFamilyNames("Nar");

        Assert.assertEquals(Arrays.asList("Nari"), names);
    }
}
