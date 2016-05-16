package com.psenchanka.comant.config

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
open class HibernateConfiguration {

    @Autowired
    private lateinit var environment: Environment

    @Bean
    open fun sessionFactory(): LocalSessionFactoryBean {
        val sessionFactory = LocalSessionFactoryBean()
        sessionFactory.setDataSource(dataSource())
        sessionFactory.setPackagesToScan("com.psenchanka.comant")
        sessionFactory.hibernateProperties = hibernateProperties()
        return sessionFactory
    }

    @Bean
    open fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(environment.getProperty("comant.db.driverClassName"))
        dataSource.url = environment.getProperty("comant.db.url")
        dataSource.username = environment.getProperty("comant.db.username")
        dataSource.password = environment.getProperty("comant.db.password")
        return dataSource
    }

    private fun hibernateProperties(): Properties {
        val properties = Properties()
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"))
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"))
        properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"))
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"))
        return properties
    }

    @Bean
    @Autowired
    open fun transactionManager(s: SessionFactory): HibernateTransactionManager {
        val txManager = HibernateTransactionManager()
        txManager.sessionFactory = s
        return txManager
    }
}
