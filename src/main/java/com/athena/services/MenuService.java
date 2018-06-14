package com.athena.services;

import com.athena.dao.MenuDAO;
import com.athena.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService
{
    @Autowired
    MenuDAO menuDao;

    public MenuService() { }

    public List<Menu> getAllMenus()
    {
        return menuDao.getAllMenus();
    }

}
