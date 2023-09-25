package dk.kea.jpamanytoone.jwtsecurity.service;


import dk.kea.jpamanytoone.jwtsecurity.model.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
