package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.domain.coupon.model.Coupon;

public interface CouponDao {
  public int insert(Coupon coupon);
  public Coupon findByCouponNumber(String couponNumber);
}
