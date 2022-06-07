package com.techelevator.dao;

import com.techelevator.model.Floor;
import com.techelevator.model.HouseDetails;

import java.util.List;

public interface HouseDetailsDao {

    Long createHouse(HouseDetails houseDetails);

    HouseDetails getHouseDetails (Long houseId);

    List <HouseDetails> getAllHousesByUserId (Long userId);

    boolean addFloorsWhenHouseisCreated(HouseDetails houseDetails, Long houseId);

    boolean removeFloorsFromHouseTable(Long houseId, int floorId);

    boolean removeFloorsFromFloorTable(int floorId);

    boolean deleteHouse(Long houseId);

    List<HouseDetails> getAllHousesForGuestUser();

    boolean addFloorToExistingFloors(Long houseId);

    boolean addFloorToFloorTable (Long houseId);

    List<Floor> getAllFloorsByHouseId(Long houseId);

}
