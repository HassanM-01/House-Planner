package com.techelevator.dao;

import com.techelevator.model.HouseDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcHouseDetailsDao implements HouseDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcHouseDetailsDao (JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean createHouse(HouseDetails houseDetails) {
        String sql = "INSERT INTO house_details (house_name, foundation_size, region, user_id) " +
                        "VALUES (?,?,?,?)";

        return jdbcTemplate.update(sql,houseDetails.getHouseName(),houseDetails.getFoundationSize(),houseDetails.getRegion(), houseDetails.getUserId()) ==1;
    }

    @Override
    public HouseDetails getHouseDetails(Long houseId) {
        HouseDetails houseDetails = new HouseDetails();
        String sql = "SELECT * FROM house_details WHERE house_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, houseId);
        while(results.next()) {
            houseDetails = mapRowToHouseDetails(results);
        }
        return houseDetails;
    }

    @Override
    public List<HouseDetails> getAllHouses(Long userId) {
        List<HouseDetails> houses = new ArrayList<>();
        String sql = "SELECT * FROM house_details WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            HouseDetails houseDetails = mapRowToHouseDetails(results);
            houses.add(houseDetails);
        }
        return houses;
    }


    private HouseDetails mapRowToHouseDetails (SqlRowSet rs) {
        HouseDetails houseDetails = new HouseDetails();
        houseDetails.setFoundationSize(rs.getInt("foundation_size"));
        houseDetails.setHouseId(rs.getLong("house_id"));
        houseDetails.setHouseName(rs.getString("house_name"));
        houseDetails.setRegion(rs.getString("region"));
        houseDetails.setUserId(rs.getLong("user_id"));
        return houseDetails;
    }





}
