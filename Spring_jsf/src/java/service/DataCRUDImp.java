/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Product;
import entity.UserAdm;
import entity.UserProduct;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;


public class DataCRUDImp implements DataCRUD {

    @Override
    public UserAdm login1(String id, String password) {
        Session sessione = HibernateUtil.getSession();
        sessione.beginTransaction();
        Query query = sessione.createQuery("from entity.UserAdm where id= :adm_id");
        //select cname from entity.Car where carId =:car_id ---->JDBC( PreparedStatement)
        System.out.println("Qui lo prende: " + id);
        query.setParameter("adm_id", id);
        System.out.println(query);
        List<UserAdm> res = query.list();
        UserAdm adm = new UserAdm();
//        System.out.println(res.get(0).getCname());
        if (id.equals(res.get(0).getId())&&password.equals(res.get(0).getPassword())) {
            adm = res.get(0);
            return adm;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> selectAll() {
        Session session = HibernateUtil.getSession();
        ArrayList<Product> list = null;//
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from entity.Product";//
            Query query = session.createQuery(sql);//
            list = (ArrayList<Product>) query.list();//List
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Product addProduct( String pName,String pIdent, Double pPrice, Integer pQuantity, String pTime) {
        Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into PRODUCT  values('" + pName + "','" + pIdent + "'," + pPrice + "," + pQuantity + ",'"+pTime+"')";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    @Override
    public Product deletProduct( String pIdent){
         Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "delete from PRODUCT where P_IDENT = '"+pIdent+"'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

   
    @Override
    public UserAdm selectId(String id) {
        Session session = HibernateUtil.getSession();
        UserAdm  root= null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria c = session.createCriteria(UserAdm.class).add(Restrictions.eq("id", id));
            List ca = c.list();
            for (Iterator itr = ca.iterator(); itr.hasNext();) {
                root = (UserAdm) itr.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return root;
    }

    @Override
    public UserAdm adduser(String id, String password, String address, String telephone, String name) {
       Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_ADM  values('user','" + id + "','" + password + "','" + address + "','" + telephone + "','"+name+"')";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
    @Override
    public List<UserAdm> selectuser() {
    Session session = HibernateUtil.getSession();
        ArrayList<UserAdm> list = null;//
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from entity.UserAdm";//
            Query query = session.createQuery(sql);//
            list = (ArrayList<UserAdm>) query.list();//List
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public UserAdm deletUser(String id) {
    Session session = HibernateUtil.getSession();
        UserAdm user = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "delete from USER_ADM where ID = '"+id+"'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Product modifyPro(String PIdent, String PName, Double PPrice, Integer PQuantity, String PTime) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_NAME = '"+PName+"',P_PRICE = "+PPrice+",P_QUANTITY = "+PQuantity+", P_TIME = '"+PTime+"' where P_IDENT = '"+PIdent+"'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Product selectPro(String PIdent) {
        Session session = HibernateUtil.getSession();
        Product  pro= null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria c = session.createCriteria(Product.class).add(Restrictions.eq("PIdent", PIdent));
            List ca = c.list();
            for (Iterator itr = ca.iterator(); itr.hasNext();) {
                pro = (Product) itr.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pro;
    }
    
    public UserAdm selectAdm(String id) {
        Session session = HibernateUtil.getSession();
        UserAdm  user= null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria c = session.createCriteria(UserAdm.class).add(Restrictions.eq("id", id));
            List ca = c.list();
            for (Iterator itr = ca.iterator(); itr.hasNext();) {
                user = (UserAdm) itr.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void modifyUser(String sign, String id) {
        Session session = HibernateUtil.getSession();
        UserAdm user = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update USER_ADM set SIGN = '"+sign+"' where ID = '"+id+"'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Product selectIdent(String i) {
        Session session = HibernateUtil.getSession();
        Product  root= null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria c = session.createCriteria(Product.class).add(Restrictions.eq("PIdent", i));
            List ca = c.list();
            for (Iterator itr = ca.iterator(); itr.hasNext();) {
                root = (Product) itr.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return root;
    }

    public void modifyPro1(int Q1) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_QUANTITY = "+Q1+" where P_IDENT = '1'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyPro2(int Q1) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_QUANTITY = "+Q1+" where P_IDENT = '2'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyPro3(int Q1) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_QUANTITY = "+Q1+" where P_IDENT = '3'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyPro4(int Q1) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_QUANTITY = "+Q1+" where P_IDENT = '4'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyPro5(int Q1) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_QUANTITY = "+Q1+" where P_IDENT = '5'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyPro6(int Q1) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_QUANTITY = "+Q1+" where P_IDENT = '6'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyPro7(int Q1) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_QUANTITY = "+Q1+" where P_IDENT = '7'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyPro8(int Q1) {
       Session session = HibernateUtil.getSession();
        Product pro = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "update PRODUCT set P_QUANTITY = "+Q1+" where P_IDENT = '8'";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public UserProduct adduserPro1(String id, Integer PQuantity, double P1) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_PRODUCT  values('1','"+id+"','Apple iPhone12',"+P1+","+PQuantity+")";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
   public UserProduct adduserPro2(String id, Integer PQuantity, double P1) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_PRODUCT  values('2','"+id+"','Sony 5g Smartphone',"+P1+","+PQuantity+")";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
   public UserProduct adduserPro3(String id, Integer PQuantity, double P1) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_PRODUCT  values('3','"+id+"','Lenovo computer',"+P1+","+PQuantity+")";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
   public UserProduct adduserPro4(String id, Integer PQuantity, double P1) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_PRODUCT  values('4','"+id+"','Lenovo desktop',"+P1+","+PQuantity+")";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
   public UserProduct adduserPro5(String id, Integer PQuantity, double P1) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_PRODUCT  values('5','"+id+"','SKYWORTH TV',"+P1+","+PQuantity+")";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
   public UserProduct adduserPro6(String id, Integer PQuantity, double P1) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_PRODUCT  values('6','"+id+"','Sharp TV',"+P1+","+PQuantity+")";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
   public UserProduct adduserPro7(String id, Integer PQuantity, double P1) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_PRODUCT  values('7','"+id+"','Romantic jacket',"+P1+","+PQuantity+")";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    public UserProduct adduserPro8(String id, Integer PQuantity, double P1) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "insert into USER_PRODUCT  values('8','"+id+"','Playboy jacket',"+P1+","+PQuantity+")";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    public List<UserProduct> selectproduc(String id) {
      Session sessione = HibernateUtil.getSession();
      ArrayList<UserProduct> list = null;
      try {
        Criteria cri = sessione.createCriteria(UserProduct.class);
        
        cri.add(Restrictions.eq("id",id));
        list = (ArrayList<UserProduct>) cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessione.close();
        }
        return list;
    }
 }
    
