package com.bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory= Persistence.createEntityManagerFactory("krishna");
        EntityManager entityManager=managerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery=criteriaBuilder.createQuery();
        Root<Teacher> from=criteriaQuery.from(Teacher.class);


        //Selecting all the records
        System.out.println("Select all records from Teacher");
        CriteriaQuery<Object> select=criteriaQuery.select(from);
        TypedQuery<Object> typedQuery=entityManager.createQuery(criteriaQuery);
        List<Object> list=typedQuery.getResultList();
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
           Teacher o=(Teacher) iterator.next();
           System.out.println(o);
        }


        //selecting specific columns in order
        criteriaQuery.multiselect(from.get("tname"),from.get("salary"));
        System.out.println("Descending order");
        CriteriaQuery<Object> desc= criteriaQuery.orderBy(criteriaBuilder.desc(from.get("salary")));
        TypedQuery<Object> typedQuery1=entityManager.createQuery(desc);
        List<Object> list1=typedQuery1.getResultList();

        Iterator iterator1=list1.iterator();

        while(iterator1.hasNext()){
            Object o[]=(Object[]) iterator1.next();
            System.out.println(o[0]+" "+o[1]);
        }
    }
}
