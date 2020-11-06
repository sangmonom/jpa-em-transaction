package com.mmit.test;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mmit.entity.Item;




class JPQL {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf=Persistence.createEntityManagerFactory("jpa-em-transaction");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em=emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

//	//@Test
//	void testForDynamicQuery() {
//		//dynamic query =create query...select * form i
////		Query query=em.createNamedQuery("SELECT i FROM Item i",Item.class);
//		TypedQuery<Item> query=em.createQuery("SELECT i FROM Item i",Item.class);
//		
//		//retrieve(get) data
////		Item item =query.getSingleResult();
////		System.out.println("size"+item.getName());
//		List<Item> itemList = query.getResultList();
//		System.out.println("size:"+itemList.size());
//	}
	//@Test
	void testForStaticQuery() {
		TypedQuery<Item> query =em.createNamedQuery("Item.getItems", Item.class);
		List<Item> itemList = query.getResultList();
		System.out.println("size:"+itemList.size());
	}
	//@Test
	void testForUpdate() {
		em.getTransaction().begin();
		Query query=em.createQuery("UPDATE Item i SET i.price=1200 WHERE i.id=1 AND i.category.id=1 ");
		query.executeUpdate();
		em.getTransaction().commit();
	}
	//@Test
	void testForDelete() {
		em.getTransaction().begin();
		Query query=em.createQuery("DELETE FROM  Item i WHERE i.category.id=2");
		query.executeUpdate();
		em.getTransaction().commit();
	}
	@Test
	void testFor() {
		String sql="SELECT i.name,i.max(price) FROM Item,Category i group by i.category.id";
		TypedQuery<Item>query=em.createQuery(sql,Item.class);
		
		List<Item> items=query.getResultList();
		items.forEach(i->{
			System.out.println(i.getName());
		}
		);
	}

}
