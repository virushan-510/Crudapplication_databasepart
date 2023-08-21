package com.kasansoft.web.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory(){
        if (entityManagerFactory==null){
            entityManagerFactory = Persistence.createEntityManagerFactory("web-pu");
        }
        return entityManagerFactory;
    }
}
