package com.revature.rysk.entities.Game;

import java.util.ArrayList;
import java.util.List;

public class ContinentCountries {
    public static Continent.NAME getContinent(Country.NAME country) {
        switch (country) {
            case Alaska:
            case Alberta:
            case CentralAmerica:
            case EasternUnitedStates:
            case Greenland:
            case NorthwestTerritory:
            case Ontario:
            case Quebec:
            case WesternUnitedStates:
                return Continent.NAME.NorthAmerica;
            case Argentina:
            case Brazil:
            case Peru:
            case Venezuela:
                return Continent.NAME.SouthAmerica;
            case GreatBritain:
            case Iceland:
            case NorthernEurope:
            case Scandinavia:
            case SouthernEurope:
            case Ukraine:
            case WesternEurope:
                return Continent.NAME.Europe;
            case Congo:
            case EastAfrica:
            case Egypt:
            case Madagascar:
            case NorthAfrica:
            case SouthAfrica:
                return Continent.NAME.Africa;
            case Afghanistan:
            case China:
            case India:
            case Irkutsk:
            case Japan:
            case Kamchatka:
            case MiddleEast:
            case Mongolia:
            case Siam:
            case Siberia:
            case Ural:
            case Yakutsk:
                return Continent.NAME.Asia;
            case EasternAustralia:
            case WesternAustralia:
            case Indonesia:
            case NewGuinea:
                return Continent.NAME.Australia;
            default:
                return null;
        }
    }

    public static List<Country.NAME> getCountries(Continent.NAME name) {
        List<Country.NAME> countries = new ArrayList<>();
        switch (name) {
            case NorthAmerica:
                countries.add(Country.NAME.Alaska);
                countries.add(Country.NAME.Alberta);
                countries.add(Country.NAME.CentralAmerica);
                countries.add(Country.NAME.EasternUnitedStates);
                countries.add(Country.NAME.Greenland);
                countries.add(Country.NAME.NorthwestTerritory);
                countries.add(Country.NAME.Ontario);
                countries.add(Country.NAME.Quebec);
                countries.add(Country.NAME.WesternUnitedStates);
                break;
            case SouthAmerica:
                countries.add(Country.NAME.Argentina);
                countries.add(Country.NAME.Brazil);
                countries.add(Country.NAME.Peru);
                countries.add(Country.NAME.Venezuela);
                break;
            case Europe:
                countries.add(Country.NAME.GreatBritain);
                countries.add(Country.NAME.Iceland);
                countries.add(Country.NAME.NorthernEurope);
                countries.add(Country.NAME.Scandinavia);
                countries.add(Country.NAME.SouthernEurope);
                countries.add(Country.NAME.Ukraine);
                countries.add(Country.NAME.WesternEurope);
                break;
            case Africa:
                countries.add(Country.NAME.Congo);
                countries.add(Country.NAME.EastAfrica);
                countries.add(Country.NAME.Egypt);
                countries.add(Country.NAME.Madagascar);
                countries.add(Country.NAME.NorthAfrica);
                countries.add(Country.NAME.SouthAfrica);
                break;
            case Asia:
                countries.add(Country.NAME.Afghanistan);
                countries.add(Country.NAME.China);
                countries.add(Country.NAME.India);
                countries.add(Country.NAME.Irkutsk);
                countries.add(Country.NAME.Japan);
                countries.add(Country.NAME.Kamchatka);
                countries.add(Country.NAME.MiddleEast);
                countries.add(Country.NAME.Mongolia);
                countries.add(Country.NAME.Siam);
                countries.add(Country.NAME.Siberia);
                countries.add(Country.NAME.Ural);
                countries.add(Country.NAME.Yakutsk);
                break;
            case Australia:
                countries.add(Country.NAME.EasternAustralia);
                countries.add(Country.NAME.Indonesia);
                countries.add(Country.NAME.NewGuinea);
                countries.add(Country.NAME.WesternAustralia);
                break;
        }
        return countries;
    }
}
