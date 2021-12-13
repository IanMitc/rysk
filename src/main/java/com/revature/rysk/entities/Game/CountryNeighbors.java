package com.revature.rysk.entities.Game;

import java.util.ArrayList;
import java.util.List;

public class CountryNeighbors {
    public static List<Country.NAME> getNeighbors(Country.NAME name) {
        List<Country.NAME> neighbors = new ArrayList<>();

        switch (name) {
            case Alaska:
                neighbors.add(Country.NAME.Kamchatka);
                neighbors.add(Country.NAME.Alberta);
                neighbors.add(Country.NAME.NorthwestTerritory);
                break;
            case Alberta:
                neighbors.add(Country.NAME.Alaska);
                neighbors.add(Country.NAME.NorthwestTerritory);
                neighbors.add(Country.NAME.Ontario);
                neighbors.add(Country.NAME.WesternUnitedStates);
                break;
            case CentralAmerica:
                neighbors.add(Country.NAME.EasternUnitedStates);
                neighbors.add(Country.NAME.WesternUnitedStates);
                neighbors.add(Country.NAME.Venezuela);
                break;
            case EasternUnitedStates:
                neighbors.add(Country.NAME.CentralAmerica);
                neighbors.add(Country.NAME.Ontario);
                neighbors.add(Country.NAME.Quebec);
                neighbors.add(Country.NAME.WesternUnitedStates);
                break;
            case Greenland:
                neighbors.add(Country.NAME.NorthwestTerritory);
                neighbors.add(Country.NAME.Ontario);
                neighbors.add(Country.NAME.Quebec);
                neighbors.add(Country.NAME.Iceland);
                break;
            case NorthwestTerritory:
                neighbors.add(Country.NAME.Alaska);
                neighbors.add(Country.NAME.Alberta);
                neighbors.add(Country.NAME.Greenland);
                neighbors.add(Country.NAME.Ontario);
                break;
            case Ontario:
                neighbors.add(Country.NAME.Alberta);
                neighbors.add(Country.NAME.EasternUnitedStates);
                neighbors.add(Country.NAME.Greenland);
                neighbors.add(Country.NAME.NorthwestTerritory);
                neighbors.add(Country.NAME.Quebec);
                neighbors.add(Country.NAME.WesternUnitedStates);
                break;
            case Quebec:
                neighbors.add(Country.NAME.EasternUnitedStates);
                neighbors.add(Country.NAME.Greenland);
                neighbors.add(Country.NAME.Ontario);
                break;
            case WesternUnitedStates:
                neighbors.add(Country.NAME.Alberta);
                neighbors.add(Country.NAME.CentralAmerica);
                neighbors.add(Country.NAME.EasternUnitedStates);
                neighbors.add(Country.NAME.Ontario);
                break;
            case Argentina:
                neighbors.add(Country.NAME.Brazil);
                neighbors.add(Country.NAME.Peru);
                break;
            case Brazil:
                neighbors.add(Country.NAME.Argentina);
                neighbors.add(Country.NAME.Peru);
                neighbors.add(Country.NAME.Venezuela);
                neighbors.add(Country.NAME.NorthAfrica);
                break;
            case Peru:
                neighbors.add(Country.NAME.Argentina);
                neighbors.add(Country.NAME.Brazil);
                neighbors.add(Country.NAME.Venezuela);
                break;
            case Venezuela:
                neighbors.add(Country.NAME.CentralAmerica);
                neighbors.add(Country.NAME.Brazil);
                neighbors.add(Country.NAME.Peru);
                break;
            case GreatBritain:
                neighbors.add(Country.NAME.Iceland);
                neighbors.add(Country.NAME.NorthernEurope);
                neighbors.add(Country.NAME.Scandinavia);
                neighbors.add(Country.NAME.WesternEurope);
                break;
            case Iceland:
                neighbors.add(Country.NAME.Greenland);
                neighbors.add(Country.NAME.GreatBritain);
                neighbors.add(Country.NAME.Scandinavia);
                break;
            case NorthernEurope:
                neighbors.add(Country.NAME.GreatBritain);
                neighbors.add(Country.NAME.Scandinavia);
                neighbors.add(Country.NAME.SouthernEurope);
                neighbors.add(Country.NAME.Ukraine);
                neighbors.add(Country.NAME.WesternEurope);
                break;
            case Scandinavia:
                neighbors.add(Country.NAME.GreatBritain);
                neighbors.add(Country.NAME.Iceland);
                neighbors.add(Country.NAME.NorthernEurope);
                neighbors.add(Country.NAME.Ukraine);
                break;
            case SouthernEurope:
                neighbors.add(Country.NAME.NorthernEurope);
                neighbors.add(Country.NAME.Ukraine);
                neighbors.add(Country.NAME.WesternEurope);
                neighbors.add(Country.NAME.Egypt);
                neighbors.add(Country.NAME.NorthAfrica);
                neighbors.add(Country.NAME.MiddleEast);
                break;
            case Ukraine:
                neighbors.add(Country.NAME.NorthernEurope);
                neighbors.add(Country.NAME.Scandinavia);
                neighbors.add(Country.NAME.SouthernEurope);
                neighbors.add(Country.NAME.Afghanistan);
                neighbors.add(Country.NAME.MiddleEast);
                neighbors.add(Country.NAME.Ural);
                break;
            case WesternEurope:
                neighbors.add(Country.NAME.GreatBritain);
                neighbors.add(Country.NAME.NorthernEurope);
                neighbors.add(Country.NAME.SouthernEurope);
                neighbors.add(Country.NAME.NorthAfrica);
                break;
            case Congo:
                neighbors.add(Country.NAME.EastAfrica);
                neighbors.add(Country.NAME.NorthAfrica);
                neighbors.add(Country.NAME.SouthAfrica);
                break;
            case EastAfrica:
                neighbors.add(Country.NAME.Congo);
                neighbors.add(Country.NAME.Egypt);
                neighbors.add(Country.NAME.Madagascar);
                neighbors.add(Country.NAME.NorthAfrica);
                neighbors.add(Country.NAME.SouthAfrica);
                neighbors.add(Country.NAME.MiddleEast);
                break;
            case Egypt:
                neighbors.add(Country.NAME.SouthernEurope);
                neighbors.add(Country.NAME.EastAfrica);
                neighbors.add(Country.NAME.NorthAfrica);
                neighbors.add(Country.NAME.MiddleEast);
                break;
            case Madagascar:
                neighbors.add(Country.NAME.EastAfrica);
                neighbors.add(Country.NAME.SouthAfrica);
                break;
            case NorthAfrica:
                neighbors.add(Country.NAME.Brazil);
                neighbors.add(Country.NAME.SouthernEurope);
                neighbors.add(Country.NAME.WesternEurope);
                neighbors.add(Country.NAME.Congo);
                neighbors.add(Country.NAME.EastAfrica);
                neighbors.add(Country.NAME.Egypt);
                break;
            case SouthAfrica:
                neighbors.add(Country.NAME.Congo);
                neighbors.add(Country.NAME.EastAfrica);
                neighbors.add(Country.NAME.Madagascar);
                break;
            case Afghanistan:
                neighbors.add(Country.NAME.Ukraine);
                neighbors.add(Country.NAME.China);
                neighbors.add(Country.NAME.India);
                neighbors.add(Country.NAME.MiddleEast);
                neighbors.add(Country.NAME.Ural);
                break;
            case China:
                neighbors.add(Country.NAME.Afghanistan);
                neighbors.add(Country.NAME.India);
                neighbors.add(Country.NAME.Mongolia);
                neighbors.add(Country.NAME.Siam);
                neighbors.add(Country.NAME.Siberia);
                neighbors.add(Country.NAME.Ural);
                break;
            case India:
                neighbors.add(Country.NAME.Afghanistan);
                neighbors.add(Country.NAME.China);
                neighbors.add(Country.NAME.MiddleEast);
                neighbors.add(Country.NAME.Siam);
                break;
            case Irkutsk:
                neighbors.add(Country.NAME.Kamchatka);
                neighbors.add(Country.NAME.Mongolia);
                neighbors.add(Country.NAME.Siberia);
                neighbors.add(Country.NAME.Yakutsk);
                break;
            case Japan:
                neighbors.add(Country.NAME.Kamchatka);
                neighbors.add(Country.NAME.Mongolia);
                break;
            case Kamchatka:
                neighbors.add(Country.NAME.Alaska);
                neighbors.add(Country.NAME.Irkutsk);
                neighbors.add(Country.NAME.Japan);
                neighbors.add(Country.NAME.Mongolia);
                neighbors.add(Country.NAME.Yakutsk);
                break;
            case MiddleEast:
                neighbors.add(Country.NAME.SouthernEurope);
                neighbors.add(Country.NAME.Ukraine);
                neighbors.add(Country.NAME.EastAfrica);
                neighbors.add(Country.NAME.Egypt);
                neighbors.add(Country.NAME.Afghanistan);
                neighbors.add(Country.NAME.India);
                break;
            case Mongolia:
                neighbors.add(Country.NAME.China);
                neighbors.add(Country.NAME.Irkutsk);
                neighbors.add(Country.NAME.Japan);
                neighbors.add(Country.NAME.Kamchatka);
                neighbors.add(Country.NAME.Siberia);
                break;
            case Siam:
                neighbors.add(Country.NAME.China);
                neighbors.add(Country.NAME.India);
                neighbors.add(Country.NAME.Indonesia);
                break;
            case Siberia:
                neighbors.add(Country.NAME.China);
                neighbors.add(Country.NAME.Irkutsk);
                neighbors.add(Country.NAME.Mongolia);
                neighbors.add(Country.NAME.Ural);
                neighbors.add(Country.NAME.Yakutsk);
                break;
            case Ural:
                neighbors.add(Country.NAME.Ukraine);
                neighbors.add(Country.NAME.Afghanistan);
                neighbors.add(Country.NAME.China);
                neighbors.add(Country.NAME.Siberia);
                break;
            case Yakutsk:
                neighbors.add(Country.NAME.Irkutsk);
                neighbors.add(Country.NAME.Kamchatka);
                neighbors.add(Country.NAME.Siberia);
                break;
            case EasternAustralia:
                neighbors.add(Country.NAME.NewGuinea);
                neighbors.add(Country.NAME.WesternAustralia);
                break;
            case Indonesia:
                neighbors.add(Country.NAME.Siam);
                neighbors.add(Country.NAME.NewGuinea);
                neighbors.add(Country.NAME.WesternAustralia);
                break;
            case NewGuinea:
                neighbors.add(Country.NAME.EasternAustralia);
                neighbors.add(Country.NAME.Indonesia);
                neighbors.add(Country.NAME.WesternAustralia);
                break;
            case WesternAustralia:
                neighbors.add(Country.NAME.EasternAustralia);
                neighbors.add(Country.NAME.Indonesia);
                neighbors.add(Country.NAME.NewGuinea);
        }
        return neighbors;
    }
}
