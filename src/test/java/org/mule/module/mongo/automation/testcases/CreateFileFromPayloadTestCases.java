/**
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.module.mongo.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.mongodb.gridfs.GridFSInputFile;

public class CreateFileFromPayloadTestCases extends MongoTestParent {

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		testObjects = (HashMap<String, Object>) context.getBean("createFileFromPayload");
	}
	
	@After
	public void tearDown() {
		deleteFilesCreatedByCreateFileFromPayload();
	}

	@Category({ SmokeTests.class, RegressionTests.class })
	@Test
	public void testCreateFileFromPayload() {
		try {
			assertEquals("There should be 0 files found before create-file-from-payload", 0, findFiles());
			
			GridFSInputFile res = createFileFromPayload(testObjects.get("filename1"));
			
			assertEquals("The created file should be named " + testObjects.get("filename1"), testObjects.get("filename1"), res.getFilename());
			assertEquals("There should be 1 files found after create-file-from-payload", 1, findFiles());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
}
