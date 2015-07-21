/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockpaperscissorslizardspock;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Architect
 */
public class ConnectDB {
private static final SessionFactory sessionFactory;
static {
Configuration config = new Configuration();
config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
config.setProperty("hibernate.connection.url", "jdbc:mysql://playerstats.c8erer4vktin.us-west-2.rds.amazonaws.com:3306/playerDB");
config.setProperty("hibernate.connection.username", "playerStatsApp");
config.setProperty("hibernate.connection.password", "i5H7a2901");
config.setProperty("hibernate.connection.pool_size", "1");
config.setProperty("hibernate.connection.autocommit", "true");
config.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
config.setProperty("hibernate.show_sql", "false");
config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
config.setProperty("hibernate.current_session_context_class", "thread");
config.addAnnotatedClass(Score.class);
ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
sessionFactory = config.buildSessionFactory(serviceRegistry);
}
public static SessionFactory getSessionFactory() {
return sessionFactory;
}
private ConnectDB() {
}
}