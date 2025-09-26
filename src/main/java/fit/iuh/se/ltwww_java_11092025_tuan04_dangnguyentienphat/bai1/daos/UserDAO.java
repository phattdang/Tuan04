package fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.daos;


import fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.entities.User;

import java.util.List;

public interface UserDAO {
    public User save(User user);

    public User update(User user);

    public boolean delete(int id);

    public User findById(int id);

    public List<User> findAll();
}
