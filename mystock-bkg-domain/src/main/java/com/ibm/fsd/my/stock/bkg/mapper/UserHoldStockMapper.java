package com.ibm.fsd.my.stock.bkg.mapper;

import com.ibm.fsd.my.stock.bkg.domain.UserHoldStock;

public interface UserHoldStockMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_hold_stock
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_hold_stock
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    int insert(UserHoldStock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_hold_stock
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    int insertSelective(UserHoldStock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_hold_stock
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    UserHoldStock selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_hold_stock
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    int updateByPrimaryKeySelective(UserHoldStock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_hold_stock
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    int updateByPrimaryKey(UserHoldStock record);
}