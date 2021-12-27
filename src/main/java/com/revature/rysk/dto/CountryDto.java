package com.revature.rysk.dto;

import com.revature.rysk.entities.Game.Country;
import com.revature.rysk.entities.Game.CountryNeighbors;
import com.revature.rysk.entities.Player.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CountryDto {
    private long countryId;
    private PlayerForDisplayDto controlledBy;
    private Country.NAME name;
    private String printableName;
    private int armies;
    private List<Country.NAME> neighbors;

    public static CountryDto getDTO(Country country) {
        return CountryDto.builder()
                .countryId(country.getCountryId())
                .controlledBy(PlayerForDisplayDto.getDto(country.getControlledBy()))
                .name(country.getName())
                .printableName(country.getPrintableName())
                .armies(country.getArmies())
                .neighbors(CountryNeighbors.getNeighbors(country.getName()))
                .build();
    }
}
