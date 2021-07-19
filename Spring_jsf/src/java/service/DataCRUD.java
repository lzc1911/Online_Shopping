/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Product;
import entity.UserAdm;
import entity.UserProduct;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 蓝爸爸
 */
public interface DataCRUD {
    public UserAdm login1(String id, String password);
    public List<Product> selectAll();
    public Product addProduct( String pName,String pIdent, Double pPrice, Integer pQuantity, String pTime);
    public Product deletProduct( String pIdent);
    public UserAdm selectId(String id);
    public UserAdm adduser(String id, String password, String address, String telephone, String name);
    public List<UserAdm> selectuser();
    public UserAdm deletUser(String id);
    public Product modifyPro(String PIdent, String PName, Double PPrice, Integer PQuantity, String PTime);
    public Product selectPro(String PIdent);
    public UserAdm selectAdm(String id);
    public void modifyUser(String sign, String id);
    public Product selectIdent(String i);
    public void modifyPro1(int Q1);
    public void modifyPro2(int Q1);
    public void modifyPro3(int Q1);
    public void modifyPro4(int Q1);
    public void modifyPro5(int Q1);
    public void modifyPro6(int Q1);
    public void modifyPro7(int Q1);
    public void modifyPro8(int Q1);
    public UserProduct adduserPro1(String id, Integer PQuantity, double P1);
    public UserProduct adduserPro2(String id, Integer PQuantity, double P1);
    public UserProduct adduserPro3(String id, Integer PQuantity, double P1);
    public UserProduct adduserPro4(String id, Integer PQuantity, double P1);
    public UserProduct adduserPro5(String id, Integer PQuantity, double P1);
    public UserProduct adduserPro6(String id, Integer PQuantity, double P1);
    public UserProduct adduserPro7(String id, Integer PQuantity, double P1) ;
    public UserProduct adduserPro8(String id, Integer PQuantity, double P1);
    public List<UserProduct> selectproduc(String id);
}
