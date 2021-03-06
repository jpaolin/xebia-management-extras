/*
 * Copyright 2008-2010 Xebia and the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.xebia.springframework.jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:fr/xebia/springframework/jdbc/test-spring-context.xml")
public class ManagedBasicDataSourceTest {
    static {
        // this system property is used by the Spring xml config
        System.setProperty("tomcat.thread-pool.size", "20");
        System.setProperty("jdbc.min-idle", "1");

    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws Exception {
        ManagedBasicDataSource managedBasicDataSource = (ManagedBasicDataSource) dataSource;

        Assert.assertEquals("jdbc:h2:mem:dbcp-test", managedBasicDataSource.getUrl());
        Assert.assertEquals("org.h2.Driver", managedBasicDataSource.getDriverClassName());
        Assert.assertEquals("sa", managedBasicDataSource.getUsername());
        Assert.assertEquals(10, managedBasicDataSource.getMaxActive());
        Assert.assertEquals(1, managedBasicDataSource.getMinIdle());
        Assert.assertEquals(Connection.TRANSACTION_SERIALIZABLE, managedBasicDataSource.getDefaultTransactionIsolation());
    }
}
