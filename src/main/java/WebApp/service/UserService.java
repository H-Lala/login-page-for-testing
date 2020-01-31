package WebApp.service;

import WebApp.dao.DaoUserSql;
import WebApp.entity.User;



public class UserService {
    private DaoUserSql daoUserSql;

    public UserService(DaoUserSql daoUserSql) {
        this.daoUserSql = daoUserSql;
    }

    public UserService() {this.daoUserSql = new DaoUserSql(); }

    public DaoUserSql getDaoUserSql() {
        return daoUserSql;
    }

   public int getLogin(User user) {
        return daoUserSql.getByLogin(user).getId();
    }
    public boolean getByLogin(User user) {
        return daoUserSql.getByLogin(user)!=null;

    }
    public boolean checkUsers(User check){
        User user = daoUserSql.getByLogin(check);
        return user!=null && user.getPassword().equals(check.getPassword());
    }
    public void save(User item) {
        daoUserSql.save(item);
    }

}
