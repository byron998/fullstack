package com.ibm.fsd.my.stock.bkg.mapper;

import com.ibm.fsd.my.stock.bkg.domain.StockConsignbuyRecord;

public interface StockConsignbuyRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_consign_buy_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_consign_buy_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int insert(StockConsignbuyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_consign_buy_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int insertSelective(StockConsignbuyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_consign_buy_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    StockConsignbuyRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_consign_buy_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int updateByPrimaryKeySelective(StockConsignbuyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_consign_buy_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int updateByPrimaryKey(StockConsignbuyRecord record);
}