package com.revature.rysk.entities.Game;

import java.util.ArrayList;
import java.util.List;

public class ContinentCountries {
    public static Continent.NAME getContinent(Country.NAME country) {
        switch (country) {
            case Alaska:
                return Continent.NAME.NorthAmerica;
            case Alberta:
                return Continent.NAME.NorthAmerica;
            case CentralAmerica:
                return Continent.NAME.NorthAmerica;
            case EasternUnitedStates:
                return Continent.NAME.NorthAmerica;
            case Greenland:
                return Continent.NAME.NorthAmerica;
            case NorthwestTerritory:
                return Continent.NAME.NorthAmerica;
            case Ontario:
                return Continent.NAME.NorthAmerica;
            case Quebec:
                return Continent.NAME.NorthAmerica;
            case WesternUnitedStates:
                return Continent.NAME.NorthAmerica;
            case Argentina:
                return Continent.NAME.SouthAmerica;
            case Brazil:
                return Continent.NAME.SouthAmerica;
            case Peru:
                return Continent.NAME.SouthAmerica;
            case Venezuela:
                return Continent.NAME.SouthAmerica;
            case GreatBritain:
                return Continent.NAME.Europe;
            case Iceland:
                return Continent.NAME.Europe;
            case NorthernEurope:
                return Continent.NAME.Europe;
            case Scandinavia:
                return Continent.NAME.Europe;
            case SouthernEurope:
                return Continent.NAME.Europe;
            case Ukraine:
                return Continent.NAME.Europe;
            case WesternEurope:
                return Continent.NAME.Europe;
            case Congo:
                return Continent.NAME.Africa;
            case EastAfrica:
                return Continent.NAME.Africa;
            case Egypt:
                return Continent.NAME.Africa;
            case Madagascar:
                return Continent.NAME.Africa;
            case NorthAfrica:
                return Continent.NAME.Africa;
            case SouthAfrica:
                return Continent.NAME.Africa;
            case Afghanistan:
                return Continent.NAME.Asia;
            case China:
                return Continent.NAME.Asia;
            case India:
                return Continent.NAME.Asia;
            case Irkutsk:
                return Continent.NAME.Asia;
            case Japan:
                return Continent.NAME.Asia;
            case Kamchatka:
                return Continent.NAME.Asia;
            case MiddleEast:
                return Continent.NAME.Asia;
            case Mongolia:
                return Continent.NAME.Asia;
            case Siam:
                return Continent.NAME.Asia;
            case Siberia:
                return Continent.NAME.Asia;
            case Ural:
                return Continent.NAME.Asia;
            case Yakutsk:
                return Continent.NAME.Asia;
            case EasternAustralia:
                return Continent.NAME.Australia;
            case Indonesia:
                return Continent.NAME.Australia;
            case NewGuinea:
                return Continent.NAME.Australia;
            case WesternAustralia:
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
