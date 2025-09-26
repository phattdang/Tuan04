package fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat;

import fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;

/**
 * @author : user664dntp
 * @mailto : phatdang19052004@gmail.com
 * @created : 24/09/2025, Wednesday
 **/
public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
    }
}
