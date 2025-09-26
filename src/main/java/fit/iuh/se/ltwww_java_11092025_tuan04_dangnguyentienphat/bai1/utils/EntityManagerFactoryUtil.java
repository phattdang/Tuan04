package fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private static final EntityManagerFactory entityManageFactory;

    static {
        try {
            entityManageFactory = Persistence.createEntityManagerFactory("user-management");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return entityManageFactory.createEntityManager();
    }

    public static void close() {
        if (entityManageFactory.isOpen()) {
            entityManageFactory.close();
        }
    }
}
