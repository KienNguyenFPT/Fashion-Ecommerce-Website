/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Discount;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raiku
 */
public class DiscountDAO extends MyConnection {

    public DiscountDAO() {
        getEntityManager();
    }

    public Discount checkCoupon(String cp) {
        try {
            TypedQuery query = entityManager.createNamedQuery("Discount.findByCode", Discount.class);
            query.setParameter("code", cp);
            List<Discount> rs = query.getResultList();
            Date today = new Date();
            for (Discount c : rs) {
                if (c.getCode().equals(cp) && today.after(c.getValidFrom()) && today.before(c.getValidTo())) {
                    return c;
                }
            }
        }finally{
            closeConnect();
        }
        return null;
    }
}
